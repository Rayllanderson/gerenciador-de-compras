package model.dao;

import db.DB;
import model.dao.impl.CategoriaJDBC;
import model.dao.impl.ProductJDBC;
import model.dao.impl.TelaLoginJDBC;
import model.entities.User;

public class DaoFactory {
    
    public static ProductDao createProductDao() {
	return new ProductJDBC(DB.getConnection());
    }

    public static TelaLoginDao createTelaLoginDao() {
	return new TelaLoginJDBC(DB.getConnection());
    }
    
    public static CategoriaDao createCategoriaDao(User user) {
	return new CategoriaJDBC(DB.getConnection(), user);
    }
}
