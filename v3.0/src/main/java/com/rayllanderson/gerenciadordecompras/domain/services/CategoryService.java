package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.mapper.CategoryMapper;
import com.rayllanderson.gerenciadordecompras.domain.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.categories.TransferCategoryRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.utils.UpdateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public Page<Category> findAll(Long userId, Pageable pageable) {
        return categoryRepository.findAllByUserId(userId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllNonPageable(Long userId) {
        return categoryRepository.findAllByUserId(userId);
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
        UpdateUtil.updateCategoryData(categoryPutRequestBody, categoryToBeUpdated);
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

    @Transactional
    public void duplicateCategory(TransferCategoryRequestBody transferCategoryRequestBody, Long userId){
        Long originalCategoryId = transferCategoryRequestBody.getId();
        Category originalCategory = findById(originalCategoryId, userId);
        Category duplicatedCategory = CategoryMapper.createCategoryToBeDuplicated(originalCategory);
        duplicatedCategory.setName(transferCategoryRequestBody.getNewName());
        duplicatedCategory = categoryRepository.save(duplicatedCategory);

        TransferProductRequestBody transferProductRequest = TransferProductRequestBody.builder()
                .currentCategoryId(originalCategoryId)
                .selectItems(getSelectItemsFromCategory(originalCategory, userId))
                .newCategoryId(duplicatedCategory.getId())
                .build();
        productService.copyProductsToAnotherCategory(transferProductRequest);
    }

    private List<SelectItemsRequestBody> getSelectItemsFromCategory(Category category, Long userId){
        List<SelectItemsRequestBody> selectItems = new ArrayList<>();
        productService.findAllNonPageable(category.getId(), userId)
                .forEach(product -> selectItems.add(new SelectItemsRequestBody(product.getId())));
        return selectItems;
    }

}
