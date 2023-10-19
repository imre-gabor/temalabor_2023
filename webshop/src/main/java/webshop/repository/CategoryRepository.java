package webshop.repository;

import java.util.List;

import webshop.model.Category;

public interface CategoryRepository {

	List<Category> findByName(String name);
}
