package com.ray.model.dao;

import com.ray.db.DB;
import com.ray.model.dao.impl.AllProductJDBC;
import com.ray.model.dao.impl.CategoriaJDBC;
import com.ray.model.dao.impl.ProductJDBC;
import com.ray.model.dao.impl.UserDaoJDBC;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;

public class DaoFactory {

    public static ProductDao createProductDao(Categoria categoria) {
        return new ProductJDBC(DB.getConnection(), categoria);
    }

    public static AllProductJDBC createAllProductDao(User user) {
        return new AllProductJDBC(DB.getConnection(), user);
    }

    public static UserDao createUserDao() {
        return new UserDaoJDBC(DB.getConnection());
    }

    public static CategoriaDao createCategoriaDao(User user) {
        return new CategoriaJDBC(DB.getConnection(), user);
    }

}
