package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println(" ====Test1==== \n insert new department");
		Department dep = new Department(null,"food");
		departmentDao.insert(dep);
		System.out.println("inserted new id ="+dep.getId());
		
		System.out.println(" ====Test2==== \n find a department by Id");
		Department dep2 =departmentDao.findById(5);
		System.out.println(dep2);
		
		System.out.println(" ====Test3==== \n update a department");
		Department dep3 = departmentDao.findById(3);
		dep3.setName("moda");
		departmentDao.update(dep3);
		System.out.println(dep3);
		
		System.out.println(" ====Test4==== \n delete a department");
		departmentDao.deleteById(6);
		
		
		System.out.println(" ====Test5==== \n find all departments");
		 List<Department> deps =departmentDao.findAll();
		 for(Department departs: deps) {
			 System.out.println(departs);
		 }
		
		
		sc.close();

	}

}
