package webshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	@Transactional
	public void discountProductInCategory(String catName, int percent) {
		List<Category> categories = categoryRepository.findByName(catName);
		categories.forEach(c -> {
			c.getProducts().forEach(p -> {
				discountProduct(percent, p);
			});
		});
	}

	private void discountProduct(int percent, Product p) {
		p.setPrice(p.getPrice() * (100.0-percent) /100.0);
	}
}
