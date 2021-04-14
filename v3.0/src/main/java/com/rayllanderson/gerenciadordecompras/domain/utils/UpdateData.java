package com.rayllanderson.gerenciadordecompras.domain.utils;

import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
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

    public static void updateProductData(ProductPutRequestBody productPutRequestBody, Product product){
        BeanUtils.copyProperties(productPutRequestBody, product, "id");
    }

    public static void updatePasswordFromUser(UserPutRequestBody source, User target) {
        BeanUtils.copyProperties(source, target, "id", "name", "email", "username");
    }

    public static void updateNameUsernameOrEmailFromUser(UserPutRequestBody source, User target) {
        BeanUtils.copyProperties(source, target, "id", "password", "username");
    }
}
