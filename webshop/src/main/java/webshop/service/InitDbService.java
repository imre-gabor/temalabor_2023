package webshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class InitDbService {

	private final ProductRepository productRepository;
	
	private final CategoryRepository categoryRepository;
	
	private final CategoryService categoryService;
	
	@Transactional
	public void initDb() {
//		Product prod1 = createProduct("prod1", 100.0);
//		Product prod2 = createProduct("prod2", 200.0);
//		Product prod3 = createProduct("prod3", 100.0);
//		
//		Category cat1 = categoryRepository.save(Category.builder().name("cat1").build());
//		Category cat2 = categoryRepository.save(Category.builder().name("cat2").build());
//		
//		cat1.addProduct(prod1);
//		cat1.addProduct(prod2);
//		cat2.addProduct(prod3);
//		categoryService.discountProductInCategory("cat1", 10);
	}

	private Product createProduct(String name, double price) {
		return productRepository.save(Product.builder().name(name).price(price).build());
	}
}
