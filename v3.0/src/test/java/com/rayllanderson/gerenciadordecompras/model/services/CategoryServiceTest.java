package com.rayllanderson.gerenciadordecompras.model.services;

import com.rayllanderson.gerenciadordecompras.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.model.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import com.rayllanderson.gerenciadordecompras.model.entities.User;
import com.rayllanderson.gerenciadordecompras.model.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.CategoryPostRequestBodyCreator;
import com.rayllanderson.gerenciadordecompras.utils.CategoryPutRequestBodyCreator;
import com.rayllanderson.gerenciadordecompras.utils.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DisplayName("Tests for CategoryService")
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @BeforeEach
    void setUp() {

        PageImpl<Category> categoryPage = new PageImpl<>(List.of(CategoryCreator.createCategoryWithId()));

        //findAll
        BDDMockito.when(categoryRepositoryMock.findAllByUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(categoryPage);

        //findById
        BDDMockito.when(categoryRepositoryMock.findByIdAndUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CategoryCreator.createCategoryWithId()));

        //FindByName
        BDDMockito.when(categoryRepositoryMock.findByNameIgnoreCaseContainingAndUserId(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class))).thenReturn(categoryPage);

        //save
        BDDMockito.when(categoryRepositoryMock.save(ArgumentMatchers.any(Category.class)))
                .thenReturn(CategoryCreator.createCategoryWithId());

        //delete
        BDDMockito.doNothing().when(categoryRepositoryMock).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findALL_ReturnsListOfCategoriesInsidePage_WhenSuccessful() {
        User expectedUser = UserCreator.createAValidUser();
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        String expectedName = expectedCategory.getName();

        Page<Category> categoryPage = categoryService.findAllByUserId(1L, PageRequest.of(1, 2));

        Assertions.assertThat(categoryPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(categoryPage.toList().get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(categoryPage.toList()).contains(expectedCategory);
        Assertions.assertThat(categoryPage.toList().get(0).getUser()).isEqualTo(expectedUser);
    }

    @Test
    void findALL_ReturnsEmptyPageOfCategories_WhenCategoriesUserHasNoCategories() {
        BDDMockito.when(categoryRepositoryMock.findAllByUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(Page.empty());

        Page<Category> categoryPage = categoryService.findAllByUserId(1L, PageRequest.of(1, 2));

        Assertions.assertThat(categoryPage).isNotNull().isEmpty();
    }

    @Test
    void findById_ReturnsCategory_WhenSuccessful() {
        User expectedUser = UserCreator.createAValidUser();
        Long expectedId = CategoryCreator.createCategoryWithId().getId();
        Category category = categoryService.findById(1L, 1L);

        Assertions.assertThat(category).isNotNull();
        Assertions.assertThat(category.getId()).isNotNull().isEqualTo(expectedId);
        Assertions.assertThat(category.getUser()).isNotNull().isEqualTo(expectedUser);
    }

    @Test
    void findById_ThrowNotFoundException_WhenCategoryIsNotFound() {
        BDDMockito.when(categoryRepositoryMock.findByIdAndUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> categoryService.findById(5541L, 541L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void findByName_ReturnsPageOfCategories_WhenSuccessful() {
        String expectedName = CategoryCreator.createCategoryWithId().getName();
        Page<Category> categoryPage = categoryService
                .findByName("any search", 1L, PageRequest.of(1, 2));

        Assertions.assertThat(categoryPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(categoryPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsEmptyPageOfCategories_WhenCategoryIsNotFound() {
        //sobrescrevendo o mockito aqui
        BDDMockito.when(categoryRepositoryMock.findByNameIgnoreCaseContainingAndUserId(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class))).thenReturn(Page.empty());

        Page<Category> categoryPage = categoryService
                .findByName("any name that does not exist", 2L, PageRequest.of(1, 2));

        Assertions.assertThat(categoryPage).isNotNull().isEmpty();
    }

    @Test
    void save_ReturnsCategory_WhenSuccessful() {
        Category category = categoryService.save(CategoryPostRequestBodyCreator.createCategoryPostRequestBody(), 1L);
        Assertions.assertThat(category).isNotNull().isEqualTo(CategoryCreator.createCategoryWithId());
    }


    @Test
    void save_ThrowException_WhenNameIsNotPresent() {
        BDDMockito.when(categoryRepositoryMock.save(ArgumentMatchers.any(Category.class)))
                .thenThrow(new ConstraintViolationException(null, null));
        Assertions.assertThatThrownBy(() ->
                categoryService.save(new CategoryPostRequestBody(), 1L))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void update_UpdatesCategory_WhenSuccessful() {
        Assertions.assertThatCode(() -> categoryService.update(CategoryPutRequestBodyCreator.createCategoryPutRequestBody(), 1L))
                .doesNotThrowAnyException();
    }

    @Test
    void deleteById_RemovesCategory_WhenSuccessful() {
        Assertions.assertThatCode(() -> categoryService.deleteById(1L, 2L)).doesNotThrowAnyException();
    }

    @Test
    void deleteSeveralById_RemovesSeveralCategories_WhenSuccessful() {
        List<Long> ids = List.of(1L, 2L, 3L);
        Assertions.assertThatCode(() -> categoryService.deleteSeveralById(ids, 1L)).doesNotThrowAnyException();
    }
}