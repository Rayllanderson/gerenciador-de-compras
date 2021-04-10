package com.rayllanderson.gerenciadordecompras.core.services;

import com.rayllanderson.gerenciadordecompras.core.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.core.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.core.requests.StatisticsResponseBody;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for StatisticService")
class StatisticServiceTest {

    @InjectMocks
    private StatisticService statisticService;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @BeforeEach
    void setUp() {

        //CATEGORY - findById
        BDDMockito.when(categoryRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CategoryCreator.createCategoryWithId()));

        //PRODUCT - findAll
        BDDMockito.when(productRepositoryMock.findAllByCategoryId(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(ProductCreator.createProductWithId()));
    }

    @Test
    void getStatistic_ReturnsStatisticsDataFromCategory_WhenSuccessful(){
        StatisticsResponseBody statistics = statisticService.getStatistics(2L);
        Assertions.assertThat(statistics).isNotNull();
        Assertions.assertThat(statistics.isCompleted()).isTrue();
    }

    @Test
    void getStatistic_ReturnsNull_WhenCategoryIsNotFound(){
        BDDMockito.when(categoryRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        StatisticsResponseBody statistics = statisticService.getStatistics(2L);
        Assertions.assertThat(statistics).isNull();
    }

}