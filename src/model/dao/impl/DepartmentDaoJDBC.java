package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DataBase;
import db.DataBaseException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	/*
	 * 
	 * 
	 * */
	
	@Override
	public void insert(Department department) {
		PreparedStatement ps = null;
		int rowsAffected;
		
		try {
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement("INSERT INTO department(Name)"
					+ "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, department.getName());
			
			rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					department.setId(rs.getInt(1));
				}
				
				DataBase.closeResultSet(rs);
			}else {
				throw new SQLException("Unexpected error! Department has no inserted!");
			}
			
			conn.commit();
			
		}
		catch(SQLException e) {
			
			try {
				conn.rollback();
			}
			catch(SQLException i) {
				
				throw new DataBaseException("Unecpected error! Insert rolled back!");
			}
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(ps);
		}
		
	}
	
	/*
	 * 
	 * 
	 * */

	@Override
	public void update(Department department) {
		PreparedStatement ps = null;
		
		try {
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement("UPDATE department SET Name = ? "
					+ "WHERE id = ?");
			
			ps.setString(1, department.getName());
			ps.setInt(2, department.getId());
			
			ps.executeUpdate();
			
			conn.commit();
			
		}
		catch(SQLException e) {
			
			try {
				conn.rollback();
			}
			catch(SQLException i) {
				
				throw new DataBaseException("Unecpected error! update rolled back!");
			}
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(ps);
		}
	}
	
	/*
	 * 
	 * 
	 * */

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		int rowsAffected;
		
		try {
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement("DELETE FROM department "
					+ "WHERE id = ?");
			
			ps.setInt(1, id);
			
			rowsAffected = ps.executeUpdate();
			
			if(rowsAffected == 0) {
				
				throw new SQLException("Unexpected error! Department has no deleted!");
			}
			
			conn.commit();
			
		}
		catch(SQLException e) {
			
			try {
				conn.rollback();
			}
			catch(SQLException i) {
				
				throw new DataBaseException("Unecpected error! update rolled back!");
			}
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(ps);
		}
		
	}
	
	/*
	 * 
	 * 
	 * */

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Department department =  null;
		
		try {
			
			ps = conn.prepareStatement("SELECT * FROM department "
					+ "WHERE id = ?");
			
		    ps.setInt(1, id);
		    
		    rs = ps.executeQuery();
			
			if(rs.next()) department = this.instatiateDepartment(rs);
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(ps);
			DataBase.closeResultSet(rs);
		}
		
		return department;
	}
	
	/*
	 * 
	 * 
	 * */

	@Override
	public List<Department> findAll() {
		
		Statement st = null;
		ResultSet rs = null;
		
		List<Department> departments = new ArrayList<Department>();
		
		try {
			
			st = conn.createStatement();
			
		
		    
		    rs = st.executeQuery("SELECT * FROM department");
			
		    while(rs.next()) {
		    	
		    	departments.add(this.instatiateDepartment(rs));
		    }
			
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(st);
			DataBase.closeResultSet(rs);
		}
		
		return departments;
	}
	
	/*
	 * 
	 * 
	 * */
	
	private Department instatiateDepartment(ResultSet rs) {
		Department department = null;
		
		try {
				
				department = new Department(rs.getInt(1), rs.getString(2));
			
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		
		return department;
	}

}
