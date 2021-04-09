package com.rayllanderson.gerenciadordecompras.model.services;

import com.rayllanderson.gerenciadordecompras.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.model.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import com.rayllanderson.gerenciadordecompras.model.entities.User;
import com.rayllanderson.gerenciadordecompras.model.mapper.CategoryMapper;
import com.rayllanderson.gerenciadordecompras.model.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.model.services.utils.UpdateData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<Category> findAll(Long userId, Pageable pageable){
        return categoryRepository.findAllByUserId(userId, pageable);
    }

    @Transactional
    public Category save(CategoryPostRequestBody categoryPostRequestBody, Long userId){
        Category categoryToBeSaved = CategoryMapper.toCategory(categoryPostRequestBody);
        categoryToBeSaved.setUser(new User(userId));
        return categoryRepository.save(categoryToBeSaved);
    }

    @Transactional(readOnly = true)
    public Category findById(Long id, Long userId) throws NotFoundException{
        return categoryRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Categoria não encontrada."));
    }

    @Transactional
    public Category update(CategoryPutRequestBody categoryPutRequestBody, Long userId){
        Category categoryToBeUpdated = findById(categoryPutRequestBody.getId(), userId);
        UpdateData.updateCategoryData(categoryPutRequestBody, categoryToBeUpdated);
        categoryToBeUpdated.setId(userId);
        return categoryRepository.save(categoryToBeUpdated);
    }

    @Transactional
    public void deleteById(Long id, Long userId){
        findById(id, userId);
        categoryRepository.deleteById(id);
    }

    public void deleteSeveralById(List<Long>categoriesIds, Long userId){
        categoriesIds.forEach(id -> deleteById(id, userId));
    }

    @Transactional(readOnly = true)
    public Page<Category> findByName(String search, Long userId, Pageable pageable){
        return categoryRepository.findByNameIgnoreCaseContainingAndUserId(search, userId, pageable);
    }

}
