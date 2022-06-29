package com.example.domain.repository;

import com.example.domain.model.Category;
import com.example.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void saveCategory(){
        Category category = new Category("Livros");
        repository.save(category);
        System.out.println(repository.findAll());
    }

}