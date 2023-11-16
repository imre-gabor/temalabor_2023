package webshop.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import webshop.dto.CategoryDto;
import webshop.mapper.CategoryMapper;
import webshop.repository.CategoryRepository;
import webshop.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;

	@GetMapping
	public List<CategoryDto> findAll() {
		return categoryMapper.categoriesToDtos(categoryRepository.findAllWithProducts());
	}

	@GetMapping("/{id}")
	public CategoryDto findById(@PathVariable int id) {
		return categoryMapper.categoryToDto(
				categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<CategoryDto> findById2(@PathVariable int id){
//		Optional<Category> optionalCat = categoryRepository.findById(id);
//		if(optionalCat.isPresent()) {
//			return ResponseEntity.ok(categoryMapper.categoryToDto(optionalCat.get()));
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//		
//	}

	@PostMapping
	public CategoryDto create(@RequestBody CategoryDto category) {
		return categoryMapper.categoryToDto(categoryRepository.save(categoryMapper.dtoToCategory(category)));
	}

	@PutMapping("/{id}")
	public CategoryDto update(@PathVariable int id, @RequestBody CategoryDto category) {
		category.setId(id);
		return categoryMapper.categoryToDto(categoryService.update(categoryMapper.dtoToCategory(category)));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		categoryRepository.deleteById(id);
	}

}
