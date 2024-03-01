package application;

import db.DataBase;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		System.out.println("DEPARTMENT");
		
		System.out.println("============TEST 1 : insert============");
		
		Department department = new Department(0, "Musica");
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		departmentDao.insert(department);
			
		System.out.println("inserted! department id = " + department.getId());
		
			
		DataBase.closeConnection();
	}

}
