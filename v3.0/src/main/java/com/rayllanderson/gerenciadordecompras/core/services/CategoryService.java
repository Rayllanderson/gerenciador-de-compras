package com.rayllanderson.gerenciadordecompras.core.services;

import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.User;
import com.rayllanderson.gerenciadordecompras.core.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.core.mapper.CategoryMapper;
import com.rayllanderson.gerenciadordecompras.core.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.core.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.core.services.utils.UpdateData;
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
    public Page<Category> findAll(Long userId, Pageable pageable) {
        return categoryRepository.findAllByUserId(userId, pageable);
    }

    @Transactional
    public CategoryPostResponseBody save(CategoryPostRequestBody categoryPostRequestBody, Long userId) {
        Category categoryToBeSaved = CategoryMapper.toCategory(categoryPostRequestBody);
        categoryToBeSaved.setUser(new User(userId));
        return CategoryMapper.toCategoryPostResponseBody(categoryRepository.save(categoryToBeSaved));
    }

    @Transactional(readOnly = true)
    public Category findById(Long id, Long userId) throws NotFoundException {
        return categoryRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Categoria não encontrada."));
    }

    @Transactional
    public void update(CategoryPutRequestBody categoryPutRequestBody, Long userId) {
        Category categoryToBeUpdated = findById(categoryPutRequestBody.getId(), userId);
        UpdateData.updateCategoryData(categoryPutRequestBody, categoryToBeUpdated);
        categoryRepository.save(categoryToBeUpdated);
    }

    @Transactional
    public void deleteById(Long id, Long userId) {
        findById(id, userId);
        categoryRepository.deleteById(id);
    }

    public void deleteVariousById(List<SelectItemsRequestBody> categoriesIds, Long userId) {
        if (categoriesIds.isEmpty()) {
            return;
        }
        categoriesIds.forEach(request -> deleteById(request.getId(), userId));
    }

    @Transactional(readOnly = true)
    public Page<Category> findByName(String search, Long userId, Pageable pageable) {
        boolean searchIsEmpty = search != null && (search.isEmpty() || search.trim().isEmpty());
        if (searchIsEmpty) {
            return Page.empty();
        }
        return categoryRepository.findByNameIgnoreCaseContainingAndUserId(search, userId, pageable);
    }

}
