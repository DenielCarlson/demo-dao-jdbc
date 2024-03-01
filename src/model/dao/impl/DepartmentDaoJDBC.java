package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import db.DataBase;
import db.DataBaseException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
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
			}else {
				throw new DataBaseException("Unexpected error! Department has no inserted!");
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

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
