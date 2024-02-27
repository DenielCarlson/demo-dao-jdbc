package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DataBase;
import db.DataBaseException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
				
		try {
					
			st = conn.prepareStatement("SELECT seller.*, department.name AS DepName FROM seller "
					+ "JOIN department ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
		
				Department department = new Department();
				department.setId(rs.getInt("DepartmentId"));
				department.setName(rs.getString("DepName"));
				
				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getFloat("BaseSalary"));
				seller.setDepartment(department);
				
				return seller;
			}
			
			return null;
					
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(st);
			DataBase.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}