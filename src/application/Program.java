package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1 findSellerById ===");
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2 findSellerByDepartment ===");
		Department department = new Department(1,null);
		List<Seller> sellers= sellerDao.findByDepartment(department);
		
		for(Seller obj: sellers) {
			System.out.println(obj);
		}
	}

}
