package model.dao;

import db.DB;
import model.dao.impl.CategoriaJDBC;
import model.dao.impl.ProductJDBC;
import model.dao.impl.TelaLoginJDBC;
import model.dao.impl.TelaPrincipalJDBC;

public class DaoFactory {
    
    public static ProductDao createProductDao() {
	return new ProductJDBC(DB.getConnection());
    }

    public static TelaLoginDao createTelaLoginDao() {
	return new TelaLoginJDBC(DB.getConnection());
    }
    
    public static TelaPrincipalDao createTelaPrincipalDao() {
	return new TelaPrincipalJDBC(DB.getConnection());
    }
    
    public static CategoriaDao createCategoriaDao() {
	return new CategoriaJDBC(DB.getConnection());
    }
}
