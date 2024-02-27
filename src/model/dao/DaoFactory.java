package model.dao;

import java.sql.Connection;

import db.DataBase;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DataBase.getConnection());
	}
	
}
