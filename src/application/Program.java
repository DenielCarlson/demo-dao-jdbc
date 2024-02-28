package application;

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
		
		System.out.println(seller);
		
		List<Seller> sellers = sellerDao.findByDepartment(new Department(3, null));
		
		for(Seller s : sellers) {
			System.out.println(s);
		}
		
		DataBase.closeConnection();

	}

}
