package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
			
		Department department01 = new Department(1, "Eletronics");
		
		Seller seller = new Seller(31, "bob", "bob@gmail.com", new Date(), 3000.00f, department01);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(seller);

	}

}
