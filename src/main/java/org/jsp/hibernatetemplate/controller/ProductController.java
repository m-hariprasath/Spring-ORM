package org.jsp.hibernatetemplate.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibernatetemplate.dao.ProductDao;
import org.jsp.hibernatetemplate.dto.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductController {

	static ProductDao dao;
	static Scanner in = new Scanner(System.in);
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("product.cfg.xml");
		dao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
//		System.out.println(dao);

		boolean flag = true;

		while (flag) {
			System.out.println("Enter the option \n1.Add product" + "\n2.Update product" + "\n3.Find product by id"
					+ "\n4.Delete Product" + "\n5.Find All Products");
			switch (in.nextInt()) {
			case 1: {
				System.out.println("Enter the name,brand,category,description,cost");
				Product p = new Product();
				p.setName(in.next());
				p.setBrand(in.next());
				p.setCategory(in.next());
				p.setDescription(in.next());	
				p.setCost(in.nextDouble());
				p = dao.saveProduct(p);
				System.out.println("Product has been saved with id: " + p.getId());
				break;
			}
			case 2: {
				System.out.println("Enter the id,name,brand,category,description,cost");
				Product p = new Product();
				p.setId(in.nextInt());
				p.setName(in.next());
				p.setBrand(in.next());
				p.setCategory(in.next());
				p.setDescription(in.next());
				p.setCost(in.nextDouble());
				p = dao.updateProduct(p);
				if (p != null) {
					System.out.println("Product has been updated");
				} else {
					System.err.println("Invalid id to update the product");
				}
				break;
			}
			case 3: {
				System.out.println("Enter the id to find prduct");
				int id = in.nextInt();
				Product p = dao.findById(id);
				if (p != null) {
					System.out.println("Product id: " + p.getId());
					System.out.println("Product name: " + p.getName());
					System.out.println("Product brand: " + p.getBrand());
					System.out.println("Product Description: " + p.getDescription());
					System.out.println("Product Category: " + p.getCategory());
					System.out.println("Product Cost: " + p.getCost());
				} else {
					System.err.println("Unable to find product as id is invalid");
				}
				break;
			}
			case 4: {
				System.out.println("Enter the product id to delete");
				int id = in.nextInt();
				boolean deleted = dao.deleteProduct(id);
				if (deleted) {
					System.out.println("Product has been deleted");
				} else {
					System.err.println("Invalid id to delete the product");
				}
				break;
			}
			case 5: {
				List<Product> products = dao.findAll();
				for (Product p : products) {
					System.out.println("Product id: " + p.getId());
					System.out.println("Product name: " + p.getName());
					System.out.println("Product brand: " + p.getBrand());
					System.out.println("Product Description: " + p.getDescription());
					System.out.println("Product Category: " + p.getCategory());
					System.out.println("Product Cost: " + p.getCost());
					System.out.println("============><===============");
				}
				break;
			}
			default:
				System.out.println("Invalid option entered");
				break;
			}
		}

	}
}
