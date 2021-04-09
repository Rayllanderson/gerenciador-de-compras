package com.rayllanderson.gerenciadordecompras.core.mapper;

import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import org.modelmapper.ModelMapper;

public class CategoryMapper {

    public static Category toCategory(CategoryPostRequestBody categoryPostRequestBody){
        return new ModelMapper().map(categoryPostRequestBody, Category.class);
    }

    public static Category toCategory(CategoryPutRequestBody categoryPutRequestBody){
        return new ModelMapper().map(categoryPutRequestBody, Category.class);
    }

    public static CategoryPostRequestBody toCategoryPostRequestBody(Category category){
        return new ModelMapper().map(category, CategoryPostRequestBody.class);
    }

    public static CategoryPutRequestBody toCategoryPutRequestBody(Category category){
        return new ModelMapper().map(category, CategoryPutRequestBody.class);
    }

    public static CategoryPostResponseBody toCategoryPostResponseBody(Category category){
        return new ModelMapper().map(category, CategoryPostResponseBody.class);
    }
}
