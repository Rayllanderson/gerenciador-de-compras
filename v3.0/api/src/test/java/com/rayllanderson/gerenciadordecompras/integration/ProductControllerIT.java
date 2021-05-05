package com.rayllanderson.gerenciadordecompras.integration;

import com.google.gson.Gson;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductPostRequestBodyCreator;
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
class ProductControllerIT extends BaseApiTest{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private final String BASE_API_URL= "/api/v1/categories";

    @Test
    void findAll_ReturnsProductPage_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();

        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products";

        String expectedName = productSaved.getName();

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findAll_ReturnsEmptyPage_WhenProductsIsNotFound() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long currentCategoryId = currentCategory.getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products";
        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void findById_ReturnsProduct_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();

        Long currentCategoryId = productSaved.getCategory().getId();
        Long expectedId = productSaved.getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/" + expectedId;

        ResponseEntity<ProductResponseBody> responseEntity = get(apiUrl, ProductResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(expectedId);
    }

    @Test
    void findById_ReturnsNonProduct_WhenProductIsNotFound() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long currentCategoryId = currentCategory.getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/1212121";
        ResponseEntity<ProductResponseBody> responseEntity = get(apiUrl, ProductResponseBody.class);
        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void findPurchased_ReturnsPurchasedProducts_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();

        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/purchased";

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(true);
    }

    @Test
    void findNotPurchased_ReturnsNonPurchasedProducts_WhenSuccessful() {
        Product productSaved = getProductNonPurchasedWithCategorySaved();

        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/non-purchased";

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(false);
    }

    @Test
    void save_SavesProduct_WhenSuccessful() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long currentCategoryId = currentCategory.getId();

        ProductPostRequestBody productToBeSaved = ProductPostRequestBodyCreator.createProductPostRequestBody();
        String expectedName = productToBeSaved.getName();

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products";

        ResponseEntity<ProductPostResponseBody> responseEntity = post(apiUrl, productToBeSaved, ProductPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getName()).isEqualTo(expectedName);
    }

    @Test
    void save_SavesProductAndAssertPricesIsNotNull_WhenSuccessful() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long currentCategoryId = currentCategory.getId();

        ProductPostRequestBody productToBeSaved = ProductPostRequestBodyCreator.createProductPostRequestBody();

        productToBeSaved.setStipulatedPrice(null);
        productToBeSaved.setSpentPrice(null);

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products";

        ResponseEntity<ProductPostResponseBody> responseEntity = post(apiUrl, productToBeSaved, ProductPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getSpentPrice()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getStipulatedPrice()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getSpentPrice()).isEqualTo(BigDecimal.ZERO);
        Assertions.assertThat(responseEntity.getBody().getStipulatedPrice()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void save_NonSavesProduct_WhenNameIsInvalid() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Long currentCategoryId = currentCategory.getId();

        ProductPostRequestBody productToBeSaved = ProductPostRequestBodyCreator.createProductPostRequestBody();
        productToBeSaved.setName("");

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products";

        ResponseEntity<ProductPostResponseBody> responseEntity = post(apiUrl, productToBeSaved, ProductPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void update_UpdatesProduct_WhenSuccessful() {
        Product productSaved = getProductNonPurchasedWithCategorySaved();
        Long currentCategoryId = productSaved.getCategory().getId();

        String expectedName = "Placa mãe";
        productSaved.setName(expectedName);

        ProductPutRequestBody productToBeUpdated = ProductMapper.toProductPutRequestBody(productSaved);

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/" + productSaved.getId();

        ResponseEntity<Void> responseEntity = put(apiUrl, productToBeUpdated, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void update_UpdatesProductCategory_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();

        Category oldCategory = productSaved.getCategory();
        Long currentCategoryId = productSaved.getCategory().getId();
        Long productId = productSaved.getId();

        Assertions.assertThat(productRepository.findById(productId)).isPresent();
        Assertions.assertThat(productRepository.findById(productId).get().getCategory()).isEqualTo(oldCategory);

        Category newCategory = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());

        ProductPutRequestBody productToBeUpdated = ProductMapper.toProductPutRequestBody(productSaved);

        productToBeUpdated.setCategoryId(newCategory.getId());

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/" + productId;

        ResponseEntity<Void> responseEntity = put(apiUrl, productToBeUpdated, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        if (productRepository.findById(productId).isPresent()){ // só pra sumir o warning.. grr
            Assertions.assertThat(productRepository.findById(productId).get().getCategory()).isEqualTo(newCategory);
            Assertions.assertThat(productRepository.findById(productId).get().getCategory()).isNotEqualTo(oldCategory);
        }
    }

    @Test
    void update_NonUpdatesProduct_WhenStipulatedPriceIsInvalid() {
        Product productSaved = getProductNonPurchasedWithCategorySaved();
        Long currentCategoryId = productSaved.getCategory().getId();

        productSaved.setStipulatedPrice(new BigDecimal("99999999999999999999999999999"));

        ProductPutRequestBody productToBeUpdated = ProductMapper.toProductPutRequestBody(productSaved);

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/" + productSaved.getId();

        ResponseEntity<ProductPostResponseBody> responseEntity = put(apiUrl, productToBeUpdated, ProductPostResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void delete_DeleteProduct_WhenSuccessful() {
        Product productSaved = getProductNonPurchasedWithCategorySaved();
        Long currentCategoryId = productSaved.getCategory().getId();
        Long savedProductId = productSaved.getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/" + savedProductId;

        ResponseEntity<Void> responseEntity = delete(apiUrl, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(productRepository.findById(savedProductId)).isEmpty();
    }

    @Test
    void delete_NonDeleteProduct_WhenProductIsNotFound() {
        Product productSaved = getProductNonPurchasedWithCategorySaved();
        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/545784";

        ResponseEntity<Void> responseEntity = delete(apiUrl, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void deleteVarious_DeleteVariousProducts_WhenSuccessful() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Product firstProduct = getProductNonPurchasedWithCategorySaved();
        Product secondProduct = getProductWithCategorySaved();
        firstProduct.setCategory(currentCategory);
        secondProduct.setCategory(currentCategory);
        productRepository.saveAll(Arrays.asList(firstProduct, secondProduct));

        Long fistId = firstProduct.getId();
        Long secondId = secondProduct.getId();

        Assertions.assertThat(productRepository.findById(fistId)).isNotEmpty();
        Assertions.assertThat(productRepository.findById(secondId)).isNotEmpty();

        SelectItemsRequestBody firstSelectedProduct = SelectItemsRequestBody.builder().id(fistId).build();
        SelectItemsRequestBody secondSelectedProduct = SelectItemsRequestBody.builder().id(secondId).build();

        List<SelectItemsRequestBody> selectedProducts = Arrays.asList(firstSelectedProduct, secondSelectedProduct);

        String apiUrl = BASE_API_URL + "/" + currentCategory.getId() + "/products";
        HttpHeaders headers = getHeaders();
        rest.delete(apiUrl, selectedProducts);
        ResponseEntity<Void> responseEntity = rest.exchange(apiUrl, HttpMethod.DELETE, new HttpEntity<>(selectedProducts, headers),
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

        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/search?name=" + expectedName;

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsEmptyProductsPage_WhenProductsNotFound() {
        Product productSaved = getProductWithCategorySaved();

        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/search?name=whatever";

        HttpHeaders headers = getHeaders();
        PageableResponse<Product> productPage = rest.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void copyProductsToAnotherCategory_WhenSuccessful() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Category categoryToReceiveProducts = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        Product firstProduct = getProductNonPurchasedWithCategorySaved();
        Product secondProduct = getProductWithCategorySaved();
        firstProduct.setCategory(currentCategory);
        secondProduct.setCategory(currentCategory);
        productRepository.saveAll(Arrays.asList(firstProduct, secondProduct));

        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(2); // 1ª busca - 2 produtos salvos

        Long fistId = firstProduct.getId();
        Long secondId = secondProduct.getId();
        Long currentCategoryId = currentCategory.getId();

        SelectItemsRequestBody firstSelectedProduct = SelectItemsRequestBody.builder().id(fistId).build();
        SelectItemsRequestBody secondSelectedProduct = SelectItemsRequestBody.builder().id(secondId).build();
        Long newCategoryId = categoryToReceiveProducts.getId();
        TransferProductRequestBody data = TransferProductRequestBody
                .builder()
                .selectItems(Arrays.asList(firstSelectedProduct, secondSelectedProduct))
                .currentCategoryId(currentCategoryId)
                .newCategoryId(newCategoryId)
                .build();

        String json = new Gson().toJson(data);
        log.info(json);

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/copy";

        ResponseEntity<Void> responseEntity = post(apiUrl, data, Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(4); // 2ª busca -  + 2 produtos copiados
    }

    @Test
    void moveProductsToAnotherCategory_WhenSuccessful() {
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Category categoryToReceiveProducts = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        Product firstProduct = getProductNonPurchasedWithCategorySaved();
        Product secondProduct = getProductWithCategorySaved();
        firstProduct.setCategory(currentCategory);
        secondProduct.setCategory(currentCategory);
        productRepository.saveAll(Arrays.asList(firstProduct, secondProduct));

        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(2); // 1ª busca - 2 produtos salvos

        Long fistId = firstProduct.getId();
        Long secondId = secondProduct.getId();
        Long currentCategoryId = currentCategory.getId();

        SelectItemsRequestBody firstSelectedProduct = SelectItemsRequestBody.builder().id(fistId).build();
        SelectItemsRequestBody secondSelectedProduct = SelectItemsRequestBody.builder().id(secondId).build();
        Long newCategoryId = categoryToReceiveProducts.getId();
        TransferProductRequestBody data = TransferProductRequestBody
                .builder()
                .selectItems(Arrays.asList(firstSelectedProduct, secondSelectedProduct))
                .currentCategoryId(currentCategoryId)
                .newCategoryId(newCategoryId)
                .build();

        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/move";

        ResponseEntity<Void> responseEntity = post(apiUrl, data, Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // 2ª busca - confirmando que foram movidos e não copiados
        Assertions.assertThat(productRepository.findAll()).isNotNull().isNotEmpty().hasSize(2);

        Assertions.assertThat(productRepository.findAll().get(0).getCategory()).isEqualTo(categoryToReceiveProducts);
        Assertions.assertThat(productRepository.findAll().get(0).getCategory()).isNotEqualTo(currentCategory);

    }

    @Test
    void getStatistics_ReturnsStatisticsFromCategory_WhenSuccessful() {
        Product productSaved = getProductWithCategorySaved();
        BigDecimal expectedStipulatedPrice = productSaved.getStipulatedPrice();
        BigDecimal expectedSpentPrice = productSaved.getSpentPrice();
        BigDecimal expectedCategoryBudget = productSaved.getCategory().getBudget();
        BigDecimal expectedAvailableToSpend = expectedCategoryBudget.subtract(expectedSpentPrice);
        BigDecimal expectedAmountSaved = expectedStipulatedPrice.subtract(expectedSpentPrice);
        BigDecimal expectedAmountToSpend = BigDecimal.ZERO;
        int expectedNumberOfProducts = 1;
        int expectedNumberOfProductsPurchased = 1;
        int expectedNumberOfProductsNonPurchased = 0;


        Long currentCategoryId = productSaved.getCategory().getId();
        String apiUrl = BASE_API_URL + "/" + currentCategoryId + "/products/statistics";

        ResponseEntity<StatisticResponseBody> responseEntity = get(apiUrl, StatisticResponseBody.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getAmountSaved()).isEqualTo(expectedAmountSaved);
        Assertions.assertThat(responseEntity.getBody().getAvailableToSpend()).isEqualTo(expectedAvailableToSpend);
        Assertions.assertThat(responseEntity.getBody().getTotalStipulated()).isEqualTo(expectedStipulatedPrice);
        Assertions.assertThat(responseEntity.getBody().getCategoryBudget().doubleValue()).isEqualTo(expectedCategoryBudget.doubleValue());
        Assertions.assertThat(responseEntity.getBody().getNumberOfProducts()).isEqualTo(expectedNumberOfProducts);
        Assertions.assertThat(responseEntity.getBody().getNumberOfProductsPurchased()).isEqualTo(expectedNumberOfProductsPurchased);
        Assertions.assertThat(responseEntity.getBody().getNumberOfProductsNotPurchased()).isEqualTo(expectedNumberOfProductsNonPurchased);
        Assertions.assertThat(responseEntity.getBody().getAmountToSpend()).isEqualTo(expectedAmountToSpend);
        Assertions.assertThat(responseEntity.getBody().isCompleted()).isEqualTo(true);
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

    Product getProductNonPurchasedWithCategorySaved(){
        Category currentCategory = categoryRepository.save(CategoryCreator.createCategoryToBeSaved());
        Product productToBeSaved = ProductCreator.createANonPurchasedProductToBeSaved();
        productToBeSaved.setCategory(currentCategory);
        return productRepository.save(productToBeSaved);
    }
}