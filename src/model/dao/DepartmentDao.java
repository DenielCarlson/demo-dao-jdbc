package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void insert(Department department);
	void update(Department department);
	void deleteById(Integer id);
	void deleteByName(String departmentName);
	Department findById(Integer id);
	Department findbyName(String departmentName);
	List<Department> findAll();
}
