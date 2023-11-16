package webshop.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	private final ProductRepository productRepository;
	
	@Transactional
	public void discountProductInCategory(String catName, int percent) {
		List<Category> categories = categoryRepository.findByName(catName);
		categories.forEach(c -> {
			c.getProducts().forEach(p -> {
				discountProduct(percent, p);
				productRepository.save(p); //nem kell a @Transactional miatt
			});
		});
	}

	private void discountProduct(int percent, Product p) {
		p.setPrice(p.getPrice() * (100.0-percent) /100.0);
	}
	
	@Transactional
	public Category update(Category category) {
		if(!categoryRepository.existsById(category.getId()))
			throw new EntityNotFoundException();
		return categoryRepository.save(category);
	}
}
