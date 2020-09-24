package model.dao;

import db.DB;
import model.dao.impl.CategoriaJDBC;
import model.dao.impl.ProductJDBC;
import model.dao.impl.UserDaoJDBC;
import model.entities.Categoria;
import model.entities.User;

public class DaoFactory {
    
    public static ProductDao createProductDao(Categoria categoria) {
	return new ProductJDBC(DB.getConnection(), categoria);
    }

    public static UserDao createTelaLoginDao() {
	return new UserDaoJDBC(DB.getConnection());
    }
    
    public static CategoriaDao createCategoriaDao(User user) {
	return new CategoriaJDBC(DB.getConnection(), user);
    }
    
}
