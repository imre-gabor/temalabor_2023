package webshop.repository;

import webshop.model.Product;

public interface ProductRepository {

	Product save(Product p);
	
	Product findById(int id);	
	
}
