package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
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
		PreparedStatement ps = null;
		java.util.Date date = seller.getBirthDate();
		int id = 0;
		int rowsAffected;
		
		try {
			ps = conn.prepareStatement("INSERT INTO seller(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new java.sql.Date(date.getTime()));;
			ps.setFloat(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());
			
			rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					id = rs.getInt(1);
					
					seller.setId(id);
				}
				
				DataBase.closeResultSet(rs);
			}else {
				throw new DataBaseException("Unexpected error! Has no rows affected!");
			}
			
			
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		finally {
			DataBase.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Busca um Seller no banco de dados pelo parametro do tipo Integer e retorna o Seller que possui esse Id
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
		
				Department department = this.instatiateDepartment(rs);
				
				Seller seller = this.instantiateSeller(rs, department);
				
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
	
	
	//Busca um Seller no Banco de dados pelo parametro de um objeto do tipo Department e retorna uma lista do Sellers encontrados
	@Override
	public List<Seller> findByDepartment(Department department){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Seller> sellerList = new ArrayList<Seller>();
		Map<Integer, Department> map = new HashMap<Integer, Department>();
		
		try {
			
			ps = conn.prepareStatement("SELECT seller.*, department.Name AS DepName FROM seller "
					+ "JOIN department ON seller.DepartmentId = department.Id "
					+ "Where department.Id = ? "
					+ "ORDER BY seller.Name ASC");
			
			ps.setInt(1, department.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(map.get(rs.getObject("DepartmentId")) == null) {
					
					dep = this.instatiateDepartment(rs);
					
					map.put(dep.getId(), dep);
				}
				
				Seller seller = this.instantiateSeller(rs, dep);
				
				sellerList.add(seller);
			}
			
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage()); 
		}
		finally {
			DataBase.closeStatement(ps);
			DataBase.closeResultSet(rs);
			
		}
		
		return sellerList;
	}
	
	//Busca todos os Sellers no banco de dados e retorna uma Lista destes
	@Override
	public List<Seller> findAll() {
		Statement ps = null;
		ResultSet rs = null;
		
		List<Seller> sellerList = new ArrayList<Seller>();
		Map<Integer, Department> map = new HashMap<Integer, Department>();
		
		try {
			
			ps = conn.createStatement();
			
			rs = ps.executeQuery("SELECT seller.*, department.Name AS DepName FROM seller "
					+ "JOIN department ON seller.DepartmentId = department.Id "
					+ "ORDER BY seller.Name ASC");
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(map.get(rs.getObject("DepartmentId")) == null) {
					
					dep = this.instatiateDepartment(rs);
					
					map.put(dep.getId(), dep);
				}
				
				Seller seller = this.instantiateSeller(rs, dep);
				
				sellerList.add(seller);
			}
			
		}
		catch(SQLException e) {
			throw new DataBaseException(e.getMessage()); 
		}
		finally {
			DataBase.closeStatement(ps);
			DataBase.closeResultSet(rs);
			
		}
		
		return sellerList;
	}
	
	//Responsável por intanciar um objeto do tipo Department recebendo um ResultSet de parametro 
	private Department instatiateDepartment(ResultSet rs) throws SQLException{
		
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		
		return department;
	}
	
	//Responsável por intanciar um objeto do tipo Seller recebendo um ResultSet de paramentro
	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
	
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getFloat("BaseSalary"));
		seller.setDepartment(department);
		
		return seller;
	}

}
