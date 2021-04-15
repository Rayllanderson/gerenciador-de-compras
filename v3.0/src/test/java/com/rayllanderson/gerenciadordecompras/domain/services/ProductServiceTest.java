package com.rayllanderson.gerenciadordecompras.domain.services;


import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductPostRequestBodyCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductPutRequestBodyCreator;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for ProductService")
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepositoryMock;

    @BeforeEach
    void setUp() {

        PageImpl<Product> productPage = new PageImpl<>(List.of(ProductCreator.createProductWithId()));
        PageImpl<Product> productNotPurchasedPage = new PageImpl<>(List.of(ProductCreator.createANonPurchasedProductWithId()));

        //findAll PAGE
        BDDMockito.when(productRepositoryMock.findAllByCategoryIdAndCategoryUserId(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        //findAll LIST
        BDDMockito.when(productRepositoryMock.findAllByCategoryIdAndCategoryUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(List.of(ProductCreator.createProductWithId()));

        //findPurchased
        BDDMockito.when(productRepositoryMock
                .findPurchasedFromCategory(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        //findPurchased
        BDDMockito.when(productRepositoryMock
                .findPurchasedFromCategory(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        //findNonPurchased
        BDDMockito.when(productRepositoryMock
                .findNonPurchasedFromCategory(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(),
                        ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productNotPurchasedPage);

        //findById
        BDDMockito.when(productRepositoryMock.findByIdAndCategoryIdAndCategoryUserId(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreator.createProductWithId()));

        //FindByName
        BDDMockito.when(productRepositoryMock.findByNameIgnoreCaseContainingAndCategoryIdAndCategoryUserId(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class))).thenReturn(productPage);

        //save
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreator.createProductWithId());

        //delete
        BDDMockito.doNothing().when(productRepositoryMock).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findALL_ReturnsListOfCategoriesInsidePage_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Product expectedProduct = ProductCreator.createProductWithId();
        String expectedName = expectedProduct.getName();

        Page<Product> productPage = productService.findAll(currentCategoryId, 1L, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(productPage.toList()).contains(expectedProduct);
        Assertions.assertThat(productPage.toList().get(0).getCategory().getId()).isEqualTo(currentCategoryId);
    }

    @Test
    void findALL_ReturnsEmptyPageOfProducts_WhenProductHasNoCategory() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        BDDMockito.when(productRepositoryMock.findAllByCategoryIdAndCategoryUserId(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(Page.empty());

        Page<Product> productPage = productService.findAll(currentCategoryId, 1L, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void findById_ReturnsProduct_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Long expectedId = ProductCreator.createProductWithId().getId();
        BigDecimal spentPrice = ProductCreator.createProductWithId().getSpentPrice();
        Product product = productService.findById(1L, expectedCategory.getId(), 1L);

        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isNotNull().isEqualTo(expectedId);
        Assertions.assertThat(product.getCategory()).isNotNull().isEqualTo(expectedCategory);
        Assertions.assertThat(product.getSpentPrice()).isEqualTo(spentPrice);
    }

    @Test
    void findById_ThrowNotFoundException_WhenProductIsNotFound() {
        BDDMockito.when(productRepositoryMock.findByIdAndCategoryIdAndCategoryUserId(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> productService.findById(5541L, 541L, 1L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void findByName_ReturnsPageOfProducts_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        String expectedName = ProductCreator.createProductWithId().getName();
        Page<Product> productPage = productService
                .findByName("any search", currentCategoryId, 1L, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsEmptyPageOfCategories_WhenProductIsNotFound() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        //sobrescrevendo o mockito aqui
        BDDMockito.when(productRepositoryMock.findByNameIgnoreCaseContainingAndCategoryIdAndCategoryUserId(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class))).thenReturn(Page.empty());

        Page<Product> productPage = productService
                .findByName("any name that does not exist", currentCategoryId, 1L, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void save_ReturnsProduct_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        ProductPostResponseBody product = productService.save(ProductPostRequestBodyCreator.createProductPostRequestBody(),
                currentCategoryId);
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isNotNull();

    }


    @Test
    void save_ThrowException_WhenNameIsNotPresent() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenThrow(new ConstraintViolationException(null, null));
        Assertions.assertThatThrownBy(() ->
                productService.save(new ProductPostRequestBody(), currentCategoryId))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void update_UpdatesProduct_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Assertions.assertThatCode(() -> productService.update(ProductPutRequestBodyCreator.createProductPutRequestBody(),
                currentCategoryId, 1L))
                .doesNotThrowAnyException();
    }

    @Test
    void deleteById_RemovesProduct_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Assertions.assertThatCode(() -> productService.deleteById(1L, currentCategoryId, 1L)).doesNotThrowAnyException();
    }

    @Test
    void deleteVariousById_RemovesSeveralCategories_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        List<SelectItemsRequestBody> ids = List.of(new SelectItemsRequestBody(1L), new SelectItemsRequestBody(2L));
        Assertions.assertThatCode(() -> productService.deleteVariousById(ids, currentCategoryId, 1L)).doesNotThrowAnyException();
    }

    @Test
    void copyProductsToAnotherCategory_WhenSuccessful(){
        Product productToBeCopied = ProductCreator.createProductWithId();
        Long productId = productToBeCopied.getId();
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Long newCategoryId = CategoryCreator.createAnotherCategoryWithId().getId();
        TransferProductRequestBody data = TransferProductRequestBody
                .builder()
                .userId(1L)
                .currentCategoryId(currentCategoryId)
                .newCategoryId(newCategoryId)
                .selectItems(List.of(new SelectItemsRequestBody(productId)))
                .build();
        Assertions.assertThatCode(() -> productService.copyProductsToAnotherCategory(data)).doesNotThrowAnyException();
    }

    @Test
    void moveProductsToAnotherCategory_WhenSuccessful(){
        Product productToBeMoved = ProductCreator.createProductWithId();
        Long productId = productToBeMoved.getId();
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Long newCategoryId = CategoryCreator.createAnotherCategoryWithId().getId();
        TransferProductRequestBody data = TransferProductRequestBody
                .builder()
                .currentCategoryId(currentCategoryId)
                .userId(1L)
                .newCategoryId(newCategoryId)
                .selectItems(List.of(new SelectItemsRequestBody(productId)))
                .build();
        Assertions.assertThatCode(() -> productService.moveProductsToAnotherCategory(data)).doesNotThrowAnyException();
    }

    @Test
    void findPurchased_ReturnsProductPurchased_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Product expectedProduct = ProductCreator.createProductWithId();

        Page<Product> productPage = productService.findPurchased(currentCategoryId, 1L, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(true);
        Assertions.assertThat(productPage.toList()).contains(expectedProduct);
        Assertions.assertThat(productPage.toList().get(0).getCategory()).isEqualTo(expectedCategory);
    }

    @Test
    void findNotPurchased_ReturnsProductPurchased_WhenSuccessful() {
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Product expectedProduct = ProductCreator.createANonPurchasedProductWithId();

        BDDMockito.when(productRepositoryMock.findAllByCategoryIdAndCategoryUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(List.of(expectedProduct));

        Page<Product> productPage = productService.findNotPurchased(currentCategoryId, 1L, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(false);
        Assertions.assertThat(productPage.toList()).contains(expectedProduct);
        Assertions.assertThat(productPage.toList().get(0).getCategory()).isEqualTo(expectedCategory);
    }
}