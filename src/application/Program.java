package application;

import java.sql.Connection;
import java.util.Date;

import db.DataBase;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = DataBase.getConnection();
			
		Department department01 = new Department(1, "Eletronics");
		
		Seller seller = new Seller(31, "bob", "bob@gmail.com", new Date(), 3000.00f, department01);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller1 = sellerDao.findById(4);
		
		System.out.println(seller1);
		
		DataBase.closeConnection();

	}

}
