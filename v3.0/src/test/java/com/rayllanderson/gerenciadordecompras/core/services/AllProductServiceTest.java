package com.rayllanderson.gerenciadordecompras.core.services;


import com.rayllanderson.gerenciadordecompras.core.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.ProductCreator;
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

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for AllProductServiceTest")
class AllProductServiceTest {

    @InjectMocks
    private AllProductService allProductService;

    @Mock
    private CategoryService categoryServiceMock;

    @Mock
    private ProductService productServiceMock;

    @BeforeEach
    void setUp() {

        PageImpl<Product> productPage = new PageImpl<>(List.of(ProductCreator.createProductWithId()));
        PageImpl<Product> productNotPurchasedPage = new PageImpl<>(List.of(ProductCreator.createANonPurchasedProductWithId()));

        //CATEGORY - findAllNonPageable
        BDDMockito.when(categoryServiceMock.findAllNonPageable(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(CategoryCreator.createCategoryWithId()));

        //PRODUCT - findAllNonPageable
        BDDMockito.when(productServiceMock.findAllNonPageable(ArgumentMatchers.any(Category.class)))
                .thenReturn(List.of(ProductCreator.createProductWithId()));

        //PRODUCT - findAllPageable
        BDDMockito.when(productServiceMock.findAll(ArgumentMatchers.any(Category.class), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(productPage);

        //PRODUCT - findPurchased
        BDDMockito.when(productServiceMock.findPurchased(ArgumentMatchers.any(Category.class), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(productPage);

        //PRODUCT - findNotPurchased
        BDDMockito.when(productServiceMock.findNotPurchased(ArgumentMatchers.any(Category.class), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(productNotPurchasedPage);
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

        //CATEGORY - findAllNonPageable
        BDDMockito.when(categoryServiceMock.findAllNonPageable(ArgumentMatchers.anyLong()))
                .thenReturn(Collections.emptyList());

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
        BDDMockito.when(productServiceMock.findAllNonPageable(ArgumentMatchers.any(Category.class)))
                .thenThrow(new NotFoundException());
        Assertions.assertThatThrownBy(() -> allProductService.findById(1L, 1L)).isInstanceOf(NotFoundException.class);
    }

}