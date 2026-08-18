package com.bookstore.controller;

import com.bookstore.entity.Category;
import com.bookstore.service.CategoryService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;

	CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// get controller uusgeed buh findAllCategories geh API uusgeh, List <category> butsaadag
	@GetMapping
	public List<Category> findAllCategories(){
		return categoryService.findAllCategories();
	}
	
	// category butsaadag create nertei Post APi endpoint uusgeh 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category create(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	// pathvariable-aar id avaad, olood categoryg butsaana. findById geh GET API endpoint bna.
	@GetMapping("/{id}")
	public Category findById(@PathVariable Long id) {
		return categoryService.findCategoryById(id);
	}
	
	// pathvariable-aar id avaad, requestbody deern category turul avdag PUT API endpoint uusgeh, category butsaadag
	@PutMapping("/{id}")
	public Category update(@PathVariable Long id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}
	
	// delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
	
}
