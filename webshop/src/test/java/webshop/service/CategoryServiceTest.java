package webshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@InjectMocks
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	ProductRepository productRepository;
	
	
	@Test
	void testDiscountProductInCategory() throws Exception {
		//ARRANGE
		Category cat = Category.builder()
			.name("testcat")
			.products(Arrays.asList(
				Product.builder().price(100.0).build(),
				Product.builder().price(200.0).build()				
			)).build();
		
		when(categoryRepository.findByName("testcat")).thenReturn(
			Arrays.asList(cat)
		);
		
		when(productRepository.save(any())).thenAnswer(inv -> inv.getArguments()[0]);
		
		//ACT
		categoryService.discountProductInCategory("testcat", 10);
		
		//ASSERT
		assertThat(cat.getProducts().get(0).getPrice()).isCloseTo(90.0, offset(0.00001));
		assertThat(cat.getProducts().get(1).getPrice()).isCloseTo(180.0, offset(0.00001));		
		verify(productRepository, times(2)).save(any());
	}
	
	
}
