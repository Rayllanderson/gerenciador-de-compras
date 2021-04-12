package com.rayllanderson.gerenciadordecompras.domain.services;


import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferAllProductRequestBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.AllProductPostRequestBodyCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductCreator;
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
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for AllProductServiceTest")
class AllProductServiceTest {

    @InjectMocks
    private AllProductService allProductService;

    @Mock
    private CategoryService categoryServiceMock;

    @Mock
    private ProductService productServiceMock;

    @Mock
    private com.rayllanderson.gerenciadordecompras.domain.validations.Assertions assertions;
    
    @Mock
    private ProductRepository productRepositoryMock;

    @BeforeEach
    void setUp() {

        PageImpl<Product> productPage = new PageImpl<>(List.of(ProductCreator.createProductWithId()));
        PageImpl<Product> productNotPurchasedPage = new PageImpl<>(List.of(ProductCreator.createANonPurchasedProductWithId()));

        BDDMockito.when(assertions.assertThatCategoryIsValid(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(CategoryCreator.createCategoryWithId());

        //CATEGORY - findAllNonPageable
        BDDMockito.when(categoryServiceMock.findAllNonPageable(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(CategoryCreator.createCategoryWithId()));


        //PRODUCT - save
        BDDMockito.when(productServiceMock.save(ArgumentMatchers.any(ProductPostRequestBody.class), ArgumentMatchers.anyLong()))
                .thenReturn(ProductMapper.toProductPostResponseBody(ProductCreator.createProductWithId()));

        //PRODUCT REPO - save
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreator.createProductWithId());

        //PRODUCT REPO - find by name
        BDDMockito.when(productRepositoryMock
                .findByNameIgnoreCaseContainingAndCategoryIdAndCategoryUserId(ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(List.of(ProductCreator.createProductWithId()));

        //PRODUCT REPO - find all page
        BDDMockito.when(productRepositoryMock
                .findAllByCategoryUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        //PRODUCT REPO - find all non page
        BDDMockito.when(productRepositoryMock
                .findAllByCategoryUserId(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(ProductCreator.createProductWithId()));

        //PRODUCT REPO - findPurchasedFromUser
        BDDMockito.when(productRepositoryMock
                .findPurchasedFromUser(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        //PRODUCT REPO - findNonPurchasedFromUser
        BDDMockito.when(productRepositoryMock
                .findNonPurchasedFromUser(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productNotPurchasedPage);

        BDDMockito.doNothing().when(productServiceMock).deleteById(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());

        //findById
        BDDMockito.when(productRepositoryMock.findByIdAndCategoryUserId(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreator.createProductWithId()));
    }

    @Test
    void findAll_ReturnsAllProductsPageFromUser_WhenSuccessful(){
        Page<Product> allProducts = allProductService.findAll(1L, PageRequest.of(1, 2));
        Product expectedProduct = ProductCreator.createProductWithId();

        Assertions.assertThat(allProducts).isNotNull().hasSize(1);
        Assertions.assertThat(allProducts.toList().get(0)).isEqualTo(expectedProduct);
    }

    @Test
    void findAll_ReturnsAEmptyPage_WhenUserDoesNotHasAnyCategory(){

        //PRODUCT REPO - find all
        BDDMockito.when(productRepositoryMock
                .findAllByCategoryUserId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(Page.empty());

        Page<Product> allProducts = allProductService.findAll(1L, PageRequest.of(1, 2));

        Assertions.assertThat(allProducts).isNotNull().hasSize(0);
        Assertions.assertThat(allProducts.toList()).isEmpty();
    }

    @Test
    void findAllNonPageable_ReturnsAllProductsListFromUser_WhenSuccessful(){
        List<Product> allProducts = allProductService.findAllNonPageable(1L);
        Product expectedProduct = ProductCreator.createProductWithId();

        Assertions.assertThat(allProducts).isNotNull().hasSize(1);
        Assertions.assertThat(allProducts.get(0)).isEqualTo(expectedProduct);
    }

    @Test
    void findPurchased_ReturnsAllProductsPurchasedFromUser_WhenSuccessful(){
        Page<Product> productsPurchased = allProductService.findPurchased(1L, PageRequest.of(2, 3));
        Product expectedProduct = ProductCreator.createProductWithId();

        Assertions.assertThat(productsPurchased).isNotNull().hasSize(1);
        Assertions.assertThat(productsPurchased.toList().get(0)).isEqualTo(expectedProduct);
    }

    @Test
    void findNotPurchased_ReturnsAllProductsNotPurchasedFromUser_WhenSuccessful(){
        Page<Product> productsNotPurchased = allProductService.findNotPurchased(1L, PageRequest.of(2, 3));
        Product expectedProduct = ProductCreator.createANonPurchasedProductWithId();

        Assertions.assertThat(productsNotPurchased).isNotNull().hasSize(1);
        Assertions.assertThat(productsNotPurchased.toList().get(0)).isEqualTo(expectedProduct);
    }

    @Test
    void findById_ReturnsProductFromUser_WhenSuccessful(){
        Product foundProduct = allProductService.findById(1L, 1L);
        Long expectedId = foundProduct.getId();

        Assertions.assertThat(foundProduct).isNotNull();
        Assertions.assertThat(foundProduct.getId()).isEqualTo(expectedId);
    }

    @Test
    void findById_ThrowNotFoundException_WhenProductIsNotFound(){
        BDDMockito.when(productRepositoryMock.findByIdAndCategoryUserId(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> allProductService.findById(1L, 1L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void save_SaveProduct_WhenSuccessful(){
        ProductPostResponseBody productToBeSaved = allProductService
                .save(AllProductPostRequestBodyCreator.createAllProductPostRequestBody(), 1L);
        ProductPostResponseBody expectedProduct = ProductMapper.toProductPostResponseBody(ProductCreator.createProductWithId());

        Assertions.assertThat(productToBeSaved).isNotNull();
        Assertions.assertThat(productToBeSaved).isEqualTo(expectedProduct);
    }

    @Test
    void update_UpdatesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> allProductService.update(ProductPutRequestBodyCreator.createProductPutRequestBody(), 1L))
                .doesNotThrowAnyException();
    }

    @Test
    void deleteById_RemovesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> allProductService.deleteById(1L, 1L))
                .doesNotThrowAnyException();
    }

    @Test
    void deleteVariousById_RemovesSeveralCategories_WhenSuccessful() {
        BDDMockito.when(productServiceMock.findAllNonPageable(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(List.of(ProductCreator.createProductWithId(), ProductCreator.createAnotherProductWithId()));

        List<SelectItemsRequestBody> ids = List.of(new SelectItemsRequestBody(1L), new SelectItemsRequestBody(2L));
        Assertions.assertThatCode(() -> allProductService.deleteVariousById(ids, 1L)).doesNotThrowAnyException();
    }

    @Test
    void copyProductsToAnotherCategory_WhenSuccessful(){
        Product productToBeCopied = ProductCreator.createProductWithId();
        Long productId = productToBeCopied.getId();
        Long newCategoryId = CategoryCreator.createAnotherCategoryWithId().getId();
        TransferAllProductRequestBody data = TransferAllProductRequestBody
                .builder()
                .newCategoryId(newCategoryId)
                .selectItems(List.of(new SelectItemsRequestBody(productId)))
                .build();
        Assertions.assertThatCode(() -> allProductService.copyProductsToAnotherCategory(data, 1L)).doesNotThrowAnyException();
    }

    @Test
    void moveProductsToAnotherCategory_WhenSuccessful(){
        Product productToBeMoved = ProductCreator.createProductWithId();
        Long productId = productToBeMoved.getId();
        Long newCategoryId = CategoryCreator.createAnotherCategoryWithId().getId();
        TransferAllProductRequestBody data = TransferAllProductRequestBody
                .builder()
                .newCategoryId(newCategoryId)
                .selectItems(List.of(new SelectItemsRequestBody(productId)))
                .build();
        Assertions.assertThatCode(() -> allProductService.moveProductsToAnotherCategory(data, 1L)).doesNotThrowAnyException();
    }

}