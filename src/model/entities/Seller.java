package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String email;
	private Date birthDate;
	private float baseSalary;
	
	private Department department;
	
	public Seller() {
		
	}
	
	public Seller(int id, String name, String email, Date birthDate, float baseSalary, Department department) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public float getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public Department getDepartment() {
		return this.department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("=========================================\n");
		sb.append("Id = " + this.id + "\n");
		sb.append("Name = " + this.name + "\n");
		sb.append("Email = " + this.email + "\n");
		sb.append("BirthDate = " + this.birthDate + "\n");
		sb.append("BaseSalary = " + String.format("$%.2f \n", this.baseSalary));
		sb.append("Department Id = " + this.department.getId() + "\n");
		sb.append("Department Name = " + this.department.getName() + "\n");
		sb.append("==========================================\n");
		return sb.toString();
	}
	
}
