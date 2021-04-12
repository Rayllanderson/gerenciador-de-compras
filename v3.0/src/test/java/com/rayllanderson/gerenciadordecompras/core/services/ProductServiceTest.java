package com.rayllanderson.gerenciadordecompras.core.services;


import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import com.rayllanderson.gerenciadordecompras.core.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.core.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.core.requests.SelectItemsRequestBody;
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

        //findAll PAGE
        BDDMockito.when(productRepositoryMock.findAllByCategoryId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        //findAll LIST
        BDDMockito.when(productRepositoryMock.findAllByCategoryId(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(ProductCreator.createProductWithId()));

        //findById
        BDDMockito.when(productRepositoryMock.findByIdAndCategoryId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreator.createProductWithId()));

        //FindByName
        BDDMockito.when(productRepositoryMock.findByNameIgnoreCaseContainingAndCategoryId(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class))).thenReturn(productPage);

        //save
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreator.createProductWithId());

        //delete
        BDDMockito.doNothing().when(productRepositoryMock).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findALL_ReturnsListOfCategoriesInsidePage_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Product expectedProduct = ProductCreator.createProductWithId();
        String expectedName = expectedProduct.getName();

        Page<Product> productPage = productService.findAll(expectedCategory, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(productPage.toList()).contains(expectedProduct);
        Assertions.assertThat(productPage.toList().get(0).getCategory()).isEqualTo(expectedCategory);
    }

    @Test
    void findALL_ReturnsEmptyPageOfProducts_WhenProductHasNoCategory() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        BDDMockito.when(productRepositoryMock.findAllByCategoryId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(Page.empty());

        Page<Product> productPage = productService.findAll(currentCategory, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void findById_ReturnsProduct_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Long expectedId = ProductCreator.createProductWithId().getId();
        BigDecimal spentPrice = ProductCreator.createProductWithId().getSpentPrice();
        Product product = productService.findById(1L, expectedCategory.getId());

        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isNotNull().isEqualTo(expectedId);
        Assertions.assertThat(product.getCategory()).isNotNull().isEqualTo(expectedCategory);
        Assertions.assertThat(product.getSpentPrice()).isEqualTo(spentPrice);
    }

    @Test
    void findById_ThrowNotFoundException_WhenProductIsNotFound() {
        BDDMockito.when(productRepositoryMock.findByIdAndCategoryId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> productService.findById(5541L, 541L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void findByName_ReturnsPageOfProducts_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        String expectedName = ProductCreator.createProductWithId().getName();
        Page<Product> productPage = productService
                .findByName("any search", currentCategory, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsEmptyPageOfCategories_WhenProductIsNotFound() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        //sobrescrevendo o mockito aqui
        BDDMockito.when(productRepositoryMock.findByNameIgnoreCaseContainingAndCategoryId(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class))).thenReturn(Page.empty());

        Page<Product> productPage = productService
                .findByName("any name that does not exist", currentCategory, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isEmpty();
    }

    @Test
    void save_ReturnsProduct_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        ProductPostResponseBody product = productService.save(ProductPostRequestBodyCreator.createProductPostRequestBody(), currentCategory);
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isNotNull();

    }


    @Test
    void save_ThrowException_WhenNameIsNotPresent() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenThrow(new ConstraintViolationException(null, null));
        Assertions.assertThatThrownBy(() ->
                productService.save(new ProductPostRequestBody(), currentCategory))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void update_UpdatesProduct_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        Assertions.assertThatCode(() -> productService.update(ProductPutRequestBodyCreator.createProductPutRequestBody(), currentCategory))
                .doesNotThrowAnyException();
    }

    @Test
    void deleteById_RemovesProduct_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        Assertions.assertThatCode(() -> productService.deleteById(1L, currentCategory)).doesNotThrowAnyException();
    }

    @Test
    void deleteVariousById_RemovesSeveralCategories_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        List<SelectItemsRequestBody> ids = List.of(new SelectItemsRequestBody(1L), new SelectItemsRequestBody(2L));
        Assertions.assertThatCode(() -> productService.deleteVariousById(ids, currentCategory)).doesNotThrowAnyException();
    }

    @Test
    void copyProductsToAnotherCategory_WhenSuccessful(){
        Product productToBeCopied = ProductCreator.createProductWithId();
        Long productId = productToBeCopied.getId();
        Long currentCategoryId = CategoryCreator.createCategoryWithId().getId();
        Long newCategoryId = CategoryCreator.createAnotherCategoryWithId().getId();
        TransferProductRequestBody data = TransferProductRequestBody
                .builder()
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
                .newCategoryId(newCategoryId)
                .selectItems(List.of(new SelectItemsRequestBody(productId)))
                .build();
        Assertions.assertThatCode(() -> productService.moveProductsToAnotherCategory(data)).doesNotThrowAnyException();
    }

    @Test
    void findPurchased_ReturnsProductPurchased_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Product expectedProduct = ProductCreator.createProductWithId();

        Page<Product> productPage = productService.findPurchased(currentCategory, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(true);
        Assertions.assertThat(productPage.toList()).contains(expectedProduct);
        Assertions.assertThat(productPage.toList().get(0).getCategory()).isEqualTo(expectedCategory);
    }

    @Test
    void findNotPurchased_ReturnsProductPurchased_WhenSuccessful() {
        Category currentCategory = CategoryCreator.createCategoryWithId();
        Category expectedCategory = CategoryCreator.createCategoryWithId();
        Product expectedProduct = ProductCreator.createANonPurchasedProductWithId();

        BDDMockito.when(productRepositoryMock.findAllByCategoryId(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(expectedProduct));

        Page<Product> productPage = productService.findNotPurchased(currentCategory, PageRequest.of(1, 2));

        Assertions.assertThat(productPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getPurchased()).isEqualTo(false);
        Assertions.assertThat(productPage.toList()).contains(expectedProduct);
        Assertions.assertThat(productPage.toList().get(0).getCategory()).isEqualTo(expectedCategory);
    }
}