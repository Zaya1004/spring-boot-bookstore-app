package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookstore.entity.Category;
import com.bookstore.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	// public List<Category> butsaadag method:
	public List<Category> findAllCategories(){
		return categoryRepository.findAll();
	}
	
	// public List<Category> avdag method:
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow();
	}
	
	// createCategory gedeg Category Entity pharametr avdag Category turul butsaadag method:
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	// updateCategory: Loong id, Category newCategory avdag method, Category buutsaana.
	// Ehleed Category bga esehiig shalgana, bhgui bol orElseThrow hiine.
	public Category updateCategory(Long id, Category newCategory) {
		Category foundCategory = categoryRepository.findById(id).orElseThrow();
		
		foundCategory.setName(newCategory.getName());
		return categoryRepository.save(foundCategory);
	}
	
	// deleteCategory: Long id avdg utga butsaahgui function, herev id olgohui bol orElseThrow
	public void deleteCategory(Long id) {
		Category foundCategory = categoryRepository.findById(id).orElseThrow();
		categoryRepository.delete(foundCategory);
	}
	
}
