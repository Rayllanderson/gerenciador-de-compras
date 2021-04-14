package com.rayllanderson.gerenciadordecompras.integration;

import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.CategoryMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.categories.TransferCategoryRequestBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.CategoryPostRequestBodyCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductCreator;
import com.rayllanderson.gerenciadordecompras.wrapper.PageableResponse;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Log4j2
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerIT extends BaseApiTest{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private final String BASE_API_URL = "/api/v1/categories";

    @Test
    void findAll_FindAllCategoriesPageable_WhenSuccessful() {

        Category category = CategoryCreator.createCategoryToBeSaved();
        category = categoryRepository.save(category);

        String expectedName = category.getName();

        HttpHeaders headers = getHeaders();
        PageableResponse<Category> categoryPage = rest.exchange(BASE_API_URL, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Category>>() {
                }).getBody();
        Assertions.assertThat(categoryPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(categoryPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void save_SaveCategory_WhenSuccessful() {
        CategoryPostRequestBody categoryToBeSaved = CategoryPostRequestBodyCreator.createCategoryPostRequestBody();
        String expectedName = categoryToBeSaved.getName();
        ResponseEntity<CategoryPostResponseBody> responseEntity = post(BASE_API_URL, categoryToBeSaved, CategoryPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getName()).isEqualTo(expectedName);
    }

    @Test
    void save_NonSaveCategory_WhenNameIsEmpty() {
        CategoryPostRequestBody categoryToBeSaved = CategoryPostRequestBodyCreator.createCategoryPostRequestBody();
        categoryToBeSaved.setName("");
        ResponseEntity<CategoryPostResponseBody> responseEntity = post(BASE_API_URL, categoryToBeSaved, CategoryPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void update_UpdatesCategory_WhenSuccessful() {
        Category savedCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        savedCategory.setName("new name");
        savedCategory.setBudget(BigDecimal.ZERO);

        String url = BASE_API_URL + "/" + savedCategory.getId();
        ResponseEntity<Void> responseEntity = put(url, CategoryMapper.toCategoryPutRequestBody(savedCategory), Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void update_NonUpdatesCategory_WhenNameIsInvalid() {
        Category savedCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        savedCategory.setName("");
        savedCategory.setBudget(BigDecimal.ZERO);

        String url = BASE_API_URL + "/" + savedCategory.getId();
        ResponseEntity<Void> responseEntity = put(url, CategoryMapper.toCategoryPutRequestBody(savedCategory), Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void findById_ReturnsCategory_WhenSuccessful() {
        Category savedCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long expectedId = savedCategory.getId();

        String url = BASE_API_URL + "/" + savedCategory.getId();
        ResponseEntity<Category> responseEntity = get(url, Category.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(expectedId);
    }

    @Test
    void findById_NonFindCategory_WhenCategoryIsNotFound() {
        String url = BASE_API_URL + "/32974372";
        ResponseEntity<Category> responseEntity = get(url, Category.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete_DeletesCategory_WhenSuccessful() {
        Category savedCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Product productInsideCategory = ProductCreator.createProductToBeSaved();
        productInsideCategory.setCategory(savedCategory);
        productInsideCategory = productRepository.save(productInsideCategory);

        Long categoryId = savedCategory.getId();
        Long productInsideCategoryId = productInsideCategory.getId();

        // confirmando que estão persistidos
        Assertions.assertThat(categoryRepository.findById(categoryId)).isNotEmpty();
        Assertions.assertThat(productRepository.findById(productInsideCategoryId)).isNotEmpty();

        String url = BASE_API_URL + "/" + savedCategory.getId();
        ResponseEntity<Void> responseEntity = delete(url, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // confirmando que foram deletados
        Assertions.assertThat(categoryRepository.findById(categoryId)).isEmpty();
        Assertions.assertThat(productRepository.findById(productInsideCategoryId)).isEmpty();
    }

    @Test
    void delete_NonDeletesCategory_WhenCategoryIsNotFound() {
        String url = BASE_API_URL + "/65656565";
        ResponseEntity<Void> responseEntity = delete(url, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteVarious_DeleteVariousCategories_WhenSuccessful() {
        Category firstSavedCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Category secondSavedCategory = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        Product productInsideFirstCategory = ProductCreator.createProductToBeSaved();
        Product productInsideSecondCategory = ProductCreator.createAnotherProductToBeSaved();
        productInsideFirstCategory.setCategory(firstSavedCategory);
        productInsideSecondCategory.setCategory(secondSavedCategory);
        productInsideFirstCategory = productRepository.save(productInsideFirstCategory);
        productInsideSecondCategory = productRepository.save(productInsideSecondCategory);

        Long firstCategoryId = firstSavedCategory.getId();
        Long secondCategoryId = secondSavedCategory.getId();
        Long firstProductInsideCategoryId = productInsideFirstCategory.getId();
        Long secondProductInsideCategoryId = productInsideSecondCategory.getId();

        Assertions.assertThat(categoryRepository.findById(firstCategoryId)).isNotEmpty();
        Assertions.assertThat(categoryRepository.findById(secondCategoryId)).isNotEmpty();
        Assertions.assertThat(productRepository.findById(firstProductInsideCategoryId)).isNotEmpty();
        Assertions.assertThat(productRepository.findById(secondProductInsideCategoryId)).isNotEmpty();

        SelectItemsRequestBody firstSelectedCategory = SelectItemsRequestBody.builder().id(firstSavedCategory.getId()).build();
        SelectItemsRequestBody secondSelectedCategory = SelectItemsRequestBody.builder().id(secondSavedCategory.getId()).build();

        List<SelectItemsRequestBody> selectedCategories = Arrays.asList(firstSelectedCategory, secondSelectedCategory);

        HttpHeaders headers = getHeaders();
        rest.delete(BASE_API_URL, selectedCategories);
        ResponseEntity<Void> responseEntity = rest.exchange(BASE_API_URL, HttpMethod.DELETE, new HttpEntity<>(selectedCategories, headers),
                Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(categoryRepository.findById(firstCategoryId)).isEmpty();
        Assertions.assertThat(categoryRepository.findById(secondCategoryId)).isEmpty();
        Assertions.assertThat(productRepository.findById(firstProductInsideCategoryId)).isEmpty();
        Assertions.assertThat(productRepository.findById(secondProductInsideCategoryId)).isEmpty();
    }

    @Test
    void deleteVarious_NonDeleteVariousCategories_WhenCategoriesIsNotFound() {
        SelectItemsRequestBody firstSelectedCategory = SelectItemsRequestBody.builder().id(1321331313131L).build();
        SelectItemsRequestBody secondSelectedCategory = SelectItemsRequestBody.builder().id(39839232L).build();

        List<SelectItemsRequestBody> selectedCategories = Arrays.asList(firstSelectedCategory, secondSelectedCategory);

        HttpHeaders headers = getHeaders();
        rest.delete(BASE_API_URL, selectedCategories);
        ResponseEntity<Void> responseEntity = rest.exchange(BASE_API_URL, HttpMethod.DELETE, new HttpEntity<>(selectedCategories, headers),
                Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void findByName_ReturnsCategoriesInsidePage_WhenSuccessful() {
        Category category = CategoryCreator.createCategoryToBeSaved();
        category = categoryRepository.save(category);

        String expectedName = category.getName();

        String url = BASE_API_URL + "/search?name=" + expectedName;
        HttpHeaders headers = getHeaders();
        PageableResponse<Category> categoryPage = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Category>>() {
                }).getBody();
        Assertions.assertThat(categoryPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(categoryPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsNoneCategories_WhenCategoryIsNotFound() {
        String url = BASE_API_URL + "/search?name=any name";
        HttpHeaders headers = getHeaders();
        PageableResponse<Category> categoryPage = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Category>>() {
                }).getBody();
        Assertions.assertThat(categoryPage).isNotNull().isEmpty();
    }

    @Test
    void duplicateCategory_DuplicateCategory_WhenSuccessful() {
        Category categoryToBeDuplicated = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());

        String categoryName = categoryToBeDuplicated.getName();
        Long categoryToBeDuplicatedId = categoryToBeDuplicated.getId();

        //confirmando antes de duplicar que existe apenas uma
        Assertions.assertThat(categoryRepository.findAll()).isNotNull().isNotEmpty().hasSize(1);

        TransferCategoryRequestBody data = TransferCategoryRequestBody
                .builder()
                .id(categoryToBeDuplicatedId)
                .newName(categoryName + " copy")
                .build();

        log.info(data.getId());
        ResponseEntity<Void> responseEntity = post(BASE_API_URL + "/copy", data, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        //confirmando que agora existem 2 categorias
        Assertions.assertThat(categoryRepository.findAll()).isNotNull().isNotEmpty().hasSize(2);
    }
}