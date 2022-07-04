package com.example.domain.repository;

import com.example.domain.model.Category;
import com.example.domain.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    private CategoryRepository underTest;

    @Autowired
    private CategoryRepositoryTest(CategoryRepository underTest){
        this.underTest = underTest;
    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    public void saveCategory(){
        Category category = new Category("Livros");
        Category categorySaved = underTest.save(category);
        assertThat(categorySaved).isNotNull();
    }

}