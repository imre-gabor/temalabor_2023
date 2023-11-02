package webshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase
//@Transactional --> minden test metódus végén rollback
//előny: a teszt során létrejött DB írások nem zavarnak meg más teszteket
//hátrány: elfedi az olyan hibákat, ahol a service metódusról lemaradta a Transactional
public class CategoryServiceIT {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@BeforeEach
	public void clearDB() {
		productRepository.deleteAllInBatch();
		categoryRepository.deleteAllInBatch();
	}
	
	@Test
	void testDiscountProductInCategory() throws Exception {
		//ARRANGE
		Product prod1 = createProduct("prod1", 100.0);
		Product prod2 = createProduct("prod2", 200.0);
		Product prod3 = createProduct("prod3", 100.0);
		
		Category cat1 = categoryRepository.save(Category.builder().name("cat1").build());
		Category cat2 = categoryRepository.save(Category.builder().name("cat2").build());
		
		cat1.addProduct(prod1);
		cat1.addProduct(prod2);
		cat2.addProduct(prod3);
		
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		//ACT
		categoryService.discountProductInCategory("cat1", 10);
		
		//ASSERT
		List<Product> allProductsFromDb = productRepository.findAll();
		allProductsFromDb.sort(Comparator.comparing(Product::getId));
		
		assertThat(allProductsFromDb.get(0).getPrice()).isCloseTo(90.0, offset(0.00001));
		assertThat(allProductsFromDb.get(1).getPrice()).isCloseTo(180.0, offset(0.00001));
		assertThat(allProductsFromDb.get(2).getPrice()).isCloseTo(100.0, offset(0.00001));
	}
	
	
	@Test
	void testDiscountProductInCategory2() throws Exception {
		//ARRANGE
		Product prod1 = createProduct("prod1", 100.0);
		Product prod2 = createProduct("prod2", 200.0);
		Product prod3 = createProduct("prod3", 100.0);
		
		Category cat1 = categoryRepository.save(Category.builder().name("cat1").build());
		Category cat2 = categoryRepository.save(Category.builder().name("cat2").build());
		
		cat1.addProduct(prod1);
		cat1.addProduct(prod2);
		cat2.addProduct(prod3);
		
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		//ACT
		categoryService.discountProductInCategory("cat1", 10);
		
		//ASSERT
		List<Product> allProductsFromDb = productRepository.findAll();
		allProductsFromDb.sort(Comparator.comparing(Product::getId));
		
		assertThat(allProductsFromDb.get(0).getPrice()).isCloseTo(90.0, offset(0.00001));
		assertThat(allProductsFromDb.get(1).getPrice()).isCloseTo(180.0, offset(0.00001));
		assertThat(allProductsFromDb.get(2).getPrice()).isCloseTo(100.0, offset(0.00001));
	}
	
	private Product createProduct(String name, double price) {
		return productRepository.save(Product.builder().name(name).price(price).build());
	}
}
