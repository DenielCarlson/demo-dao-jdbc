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
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(7);
		
		System.out.println(seller);
		
		DataBase.closeConnection();

	}

}
