package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;



public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
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
		

		System.out.println("\n=== TEST 3 findAll ===");
		
		 sellers= sellerDao.findAll();
		
		for(Seller obj: sellers) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 4 insert seller ===");
		Seller newSeller = new Seller(null, "greg","greg@gmail.com",new Date(),4000,department);
		sellerDao.insert(newSeller);
		System.out.println("inserted new id="+ newSeller.getId());
		
		System.out.println("\n=== TEST 5 update seller ===");
		seller = sellerDao.findById(1);
		seller.setName("bruce banner");
		sellerDao.update(seller);
		System.out.println("update completed");
		
		System.out.println("\n=== TEST 6 delete seller ===");
		System.out.println("enter an id to delete seller");
		int deletId =sc.nextInt();
		sellerDao.deleteById(deletId);
		System.out.println("dlete complete");
		
		sc.close();
	}

}
