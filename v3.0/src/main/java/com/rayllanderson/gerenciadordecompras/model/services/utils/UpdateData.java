package com.rayllanderson.gerenciadordecompras.model.services.utils;

import com.rayllanderson.gerenciadordecompras.model.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import org.springframework.beans.BeanUtils;

public class UpdateData {

    /**
     * copia as propriedades de um categoryPutRequestBody para category ignorando o ID
     * @param categoryPutRequestBody contendo os novos dados
     * @param category receberá os novos dados
     */
    public static void updateCategoryData(CategoryPutRequestBody categoryPutRequestBody, Category category){
        BeanUtils.copyProperties(categoryPutRequestBody, category, "id");
    }
}
