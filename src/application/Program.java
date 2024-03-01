package application;

import java.util.Date;
import java.util.List;

import db.DataBase;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(7);
		
		System.out.println("============TEST 1 : findById============");
		System.out.println(seller);
		System.out.println();
		
		//------------------------------------------------------------------------------------------------------
		
		List<Seller> sellers = sellerDao.findByDepartment(new Department(1, null));
		
		System.out.println("============TEST 2 : findByDepartment============");
		
		for(Seller s : sellers) {
			System.out.println(s);
		}
		
		System.out.println();
		
		//------------------------------------------------------------------------------------------------------
		
		sellers = sellerDao.findAll();
		
		System.out.println("============TEST 3 : findAll============");
		
		for(Seller s : sellers) {
			
			System.out.println(s);
		}
		
		
		//------------------------------------------------------------------------------------------------------
		
		
		
		System.out.println("============TEST 4 : insert============");
		
		Seller newSeller = new Seller(0, "Oliv", "oliver@gmail.com", new Date(2003 - 1900, 3, 19), 2000.0f, new Department(1, null));
		
		sellerDao.insert(newSeller);
		
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		DataBase.closeConnection();
		

	}

}
