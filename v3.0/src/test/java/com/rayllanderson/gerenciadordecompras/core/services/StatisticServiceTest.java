package com.rayllanderson.gerenciadordecompras.core.services;

import com.rayllanderson.gerenciadordecompras.core.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.product.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for StatisticService")
class StatisticServiceTest {

    @InjectMocks
    private StatisticService statisticService;

    @Mock
    private ProductService productServiceMock;

    @Mock
    private CategoryService categoryServiceMock;

    @BeforeEach
    void setUp() {

        //CATEGORY - findById
        BDDMockito.when(categoryServiceMock.findById(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(CategoryCreator.createCategoryWithId());

        //PRODUCT - findAll
        BDDMockito.when(productServiceMock.findAllNonPageable(ArgumentMatchers.any(Category.class)))
                .thenReturn(List.of(ProductCreator.createProductWithId()));
    }

    @Test
    void getStatistic_ReturnsStatisticsDataFromCategory_WhenSuccessful(){
        StatisticResponseBody statistics = statisticService.getStatistics(1L, 2L);
        Assertions.assertThat(statistics).isNotNull();
        Assertions.assertThat(statistics.isCompleted()).isTrue();
    }

    @Test
    void getStatistic_ThrowNotFoundException_WhenCategoryIsNotFound(){
        BDDMockito.when(categoryServiceMock.findById(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException());

        Assertions.assertThatThrownBy(() -> statisticService.getStatistics(2L, 3L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void getStatistic_ReturnsStatisticsDataFromAllCategories_WhenSuccessful(){
        StatisticResponseBody statistics = statisticService.getStatistics(1L, 2L);
        Assertions.assertThat(statistics).isNotNull();
        Assertions.assertThat(statistics.getNumberOfProducts()).isEqualTo(1);
    }

    @Test
    void getStatistic_ReturnsNull_WhenUserDoesNotHasACategory(){
        BDDMockito.when(categoryServiceMock.findAllNonPageable(ArgumentMatchers.anyLong()))
                .thenReturn(Collections.emptyList());
        StatisticResponseBody statistics = statisticService.getStatistics(2L);
        Assertions.assertThat(statistics).isNull();
    }

}