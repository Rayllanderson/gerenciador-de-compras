package com.rayllanderson.gerenciadordecompras.integration;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferAllProductRequestBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.AllProductPostRequestBodyCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductCreator;
import com.rayllanderson.gerenciadordecompras.utils.user.UserCreator;
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
class AllProductControllerIT extends BaseApiTest{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private final String BASE_API_URL= "/api/v1/all-products";

    @Test
    void findAll_ReturnsProductPage_WhenSuccessful() {
        getProductWithCategorySaved();
        getProductNonPurchasedWithDifferentCategory();

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(BASE_API_URL, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(2);
    }

    @Test
    void findAll_ReturnsEmptyPage_WhenProductsIsNotFound() {
        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(BASE_API_URL, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();
        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void findPurchased_ReturnsPurchasedProducts_WhenSuccessful() {
        //cadastrando
        getProductWithCategorySaved();
        getProductPurchasedWithDifferentCategory();

        String apiUrl = BASE_API_URL + "/purchased";

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(2);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(true);
        Assertions.assertThat(productPage.toList().get(1).getPurchased()).isEqualTo(true);
    }

    @Test
    void findNotPurchased_ReturnsNonPurchasedProducts_WhenSuccessful() {
        getProductNonPurchasedWithDifferentCategory(); //produto nao comprado
        getProductWithCategorySaved(); //produto comprado

        String apiUrl = BASE_API_URL + "/non-purchased";

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1); // precisar ser 1
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(false);
    }

    @Test
    void save_SavesProduct_WhenSuccessful() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long categoryId = currentCategory.getId();

        AllProductPostRequestBody productToBeSaved = AllProductPostRequestBodyCreator.createAllProductPostRequestBody();
        productToBeSaved.setCategoryId(categoryId);
        String expectedName = productToBeSaved.getName();

        ResponseEntity<ProductPostResponseBody> responseEntity = post(BASE_API_URL, productToBeSaved, ProductPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getName()).isEqualTo(expectedName);

    }

    @Test
    void save_NonSavesProduct_WhenNameCategoryNotExists() {

        AllProductPostRequestBody productToBeSaved = AllProductPostRequestBodyCreator.createAllProductPostRequestBody();
        productToBeSaved.setCategoryId(11315435145L);

        ResponseEntity<ProductPostResponseBody> responseEntity = post(BASE_API_URL, productToBeSaved, ProductPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    void delete_DeleteProduct_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();
        Long savedProductId = productSaved.getId();

        ResponseEntity<Void> responseEntity = delete(BASE_API_URL + "/" + savedProductId, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(productRepository.findById(savedProductId)).isEmpty();
    }

    @Test
    void delete_NonDeleteProduct_WhenProductIsNotFound() {
        String apiUrl = BASE_API_URL + "/545784";

        ResponseEntity<Void> responseEntity = delete(apiUrl, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void deleteVarious_DeleteVariousProducts_WhenSuccessful() {
        Product firstProduct = getProductPurchasedWithDifferentCategory();
        Product secondProduct = getProductWithCategorySaved();

        Long fistId = firstProduct.getId();
        Long secondId = secondProduct.getId();

        Assertions.assertThat(productRepository.findById(fistId)).isNotEmpty();
        Assertions.assertThat(productRepository.findById(secondId)).isNotEmpty();

        SelectItemsRequestBody firstSelectedProduct = SelectItemsRequestBody.builder().id(fistId).build();
        SelectItemsRequestBody secondSelectedProduct = SelectItemsRequestBody.builder().id(secondId).build();

        List<SelectItemsRequestBody> selectedProducts = Arrays.asList(firstSelectedProduct, secondSelectedProduct);

        HttpHeaders headers = getHeaders();
        rest.delete(BASE_API_URL, selectedProducts);
        ResponseEntity<Void> responseEntity = rest.exchange(BASE_API_URL, HttpMethod.DELETE, new HttpEntity<>(selectedProducts, headers),
                Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(productRepository.findById(fistId)).isEmpty();
        Assertions.assertThat(productRepository.findById(secondId)).isEmpty();

    }

    @Test
    void findByName_ReturnsProductsPage_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();

        String expectedName = productSaved.getName();

        String apiUrl = BASE_API_URL + "/search?name=" + expectedName;

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsEmptyProductsPage_WhenProductsNotFound() {
        String apiUrl = BASE_API_URL + "/search?name=whatever";

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void copyProductsToAnotherCategory_WhenSuccessful() {
        Category categoryToReceiveProducts = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        Product firstProduct = getProductPurchasedWithDifferentCategory();
        Product secondProduct = getProductWithCategorySaved();

        Long newCategoryId = categoryToReceiveProducts.getId();

        Long userId = UserCreator.createUserResponseBody().getId();
        Assertions.assertThat(productRepository.findAllByCategoryIdAndCategoryUserId(newCategoryId, userId))
                .isNotNull().isEmpty(); //confirmando que a categoria que vai receber os produtos está vazia.

        Long fistId = firstProduct.getId();
        Long secondId = secondProduct.getId();

        SelectItemsRequestBody firstSelectedProduct = SelectItemsRequestBody.builder().id(fistId).build();
        SelectItemsRequestBody secondSelectedProduct = SelectItemsRequestBody.builder().id(secondId).build();

        TransferAllProductRequestBody data = TransferAllProductRequestBody
                .builder()
                .selectItems(Arrays.asList(firstSelectedProduct, secondSelectedProduct))
                .newCategoryId(newCategoryId)
                .build();

        String apiUrl = BASE_API_URL + "/copy";

        ResponseEntity<Void> responseEntity = post(apiUrl, data, Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(productRepository.findAllByCategoryIdAndCategoryUserId(newCategoryId, userId))
                .isNotNull().isNotEmpty().hasSize(2); //Confirmando que 2 produtos foram copiados com sucesso!

        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(4); //Confirmando que foram COPIADOS e
        // não movidos, ou seja, 4 produtos no total.
    }

    @Test
    void moveProductsToAnotherCategory_WhenSuccessful() {
        Category categoryToReceiveProducts = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        Product firstProduct = getProductNonPurchasedWithDifferentCategory();
        Product secondProduct = getProductWithCategorySaved();
        productRepository.saveAll(Arrays.asList(firstProduct, secondProduct));

        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(2); // 1ª busca - 2 produtos salvos

        Long fistId = firstProduct.getId();
        Long secondId = secondProduct.getId();

        SelectItemsRequestBody firstSelectedProduct = SelectItemsRequestBody.builder().id(fistId).build();
        SelectItemsRequestBody secondSelectedProduct = SelectItemsRequestBody.builder().id(secondId).build();
        Long newCategoryId = categoryToReceiveProducts.getId();
        TransferAllProductRequestBody data = TransferAllProductRequestBody
                .builder()
                .selectItems(Arrays.asList(firstSelectedProduct, secondSelectedProduct))
                .newCategoryId(newCategoryId)
                .build();

        String apiUrl = BASE_API_URL + "/move";

        ResponseEntity<Void> responseEntity = post(apiUrl, data, Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // 2ª busca - confirmando que foram movidos e não copiados
        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(2);

        Assertions.assertThat(productRepository.findAll().get(0).getCategory()).isEqualTo(categoryToReceiveProducts);
    }

    @Test
    void getStatistics_ReturnsStatisticsFromCategory_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();
        BigDecimal expectedStipulatedPrice = productSaved.getStipulatedPrice();
        BigDecimal expectedSpentPrice = productSaved.getSpentPrice();
        BigDecimal expectedCategoryBudget = productSaved.getCategory().getBudget();
        BigDecimal expectedAvailableToSpend = expectedCategoryBudget.subtract(expectedSpentPrice);
        BigDecimal expectedAmountSaved = expectedStipulatedPrice.subtract(expectedSpentPrice);
        int expectedNumberOfProducts = 1;
        int expectedNumberOfProductsPurchased = 1;
        int expectedNumberOfProductsNonPurchased = 0;

        String apiUrl = BASE_API_URL + "/statistics";

        ResponseEntity<StatisticResponseBody> responseEntity = get(apiUrl, StatisticResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getAmountSaved()).isEqualTo(expectedAmountSaved);
        Assertions.assertThat(responseEntity.getBody().getAvailableToSpend()).isEqualTo(expectedAvailableToSpend);
        Assertions.assertThat(responseEntity.getBody().getTotalStipulated()).isEqualTo(expectedStipulatedPrice);
        Assertions.assertThat(responseEntity.getBody().getCategoryBudget()).isEqualTo("0"); //não tem categoria, then, return 0
        Assertions.assertThat(responseEntity.getBody().getNumberOfProducts()).isEqualTo(expectedNumberOfProducts);
        Assertions.assertThat(responseEntity.getBody().getNumberOfProductsPurchased()).isEqualTo(expectedNumberOfProductsPurchased);
        Assertions.assertThat(responseEntity.getBody().getNumberOfProductsNotPurchased()).isEqualTo(expectedNumberOfProductsNonPurchased);
        Assertions.assertThat(responseEntity.getBody().isCompleted()).isEqualTo(false); //não tem categoria, portanto, false
        Assertions.assertThat(responseEntity.getBody().getStipulatedValueFromBoughtProducts()).isEqualTo(expectedStipulatedPrice);
    }

    /**
     * Salva uma categoria, seta no produto, salva o produto e retorna
     */
    Product getProductWithCategorySaved(){
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Product productToBeSaved = ProductCreator.createProductToBeSaved();
        productToBeSaved.setCategory(currentCategory);
        return productRepository.save(productToBeSaved);
    }

    Product getProductPurchasedWithDifferentCategory(){
        Category currentCategory = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        Product productToBeSaved = ProductCreator.createAnotherProductToBeSaved();
        productToBeSaved.setCategory(currentCategory);
        return productRepository.save(productToBeSaved);
    }

    Product getProductNonPurchasedWithDifferentCategory(){
        Category currentCategory = categoryRepository.save(CategoryCreator.createAnotherOneCategoryToBeSaved());
        Product productToBeSaved = ProductCreator.createANonPurchasedProductToBeSaved();
        productToBeSaved.setCategory(currentCategory);
        return productRepository.save(productToBeSaved);
    }
}