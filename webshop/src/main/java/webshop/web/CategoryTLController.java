package webshop.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import webshop.model.Category;
import webshop.repository.CategoryRepository;

@Controller
@RequiredArgsConstructor
public class CategoryTLController {

	private final CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public String index(Map<String, Object> model) {
		model.put("categories", categoryRepository.findAll());
		model.put("newCat", new Category());
		return "index";
	}
	
	@PostMapping("/newCategory")
	public String create(Category cat) {
		categoryRepository.save(cat);
		return "redirect:/";
	}
}
