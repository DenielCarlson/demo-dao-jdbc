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
		
		/*departmentDao.insert(department);
			
		System.out.println("inserted! department id = " + department.getId());*/
		
		//------------------------------------------------------------------------------------------------------
		
		System.out.println("============TEST 2 : update============");
		
		department.setId(10);
		department.setName("Desenvolvimento");
		
		departmentDao.update(department);
			
		System.out.println("updated!");
		
		//------------------------------------------------------------------------------------------------------
		
		System.out.println("============TEST 3 : deleteById============");
				
		/*departmentDao.deleteById(16);
					
		System.out.println("deleted!");*/
		
	//------------------------------------------------------------------------------------------------------
		
		System.out.println("============TEST 4 : findById============");
				
		Department departmentReturned = departmentDao.findById(15);
					
		System.out.println(departmentReturned);
				
				
		
		
		
		
			
		DataBase.closeConnection();
	}

}
