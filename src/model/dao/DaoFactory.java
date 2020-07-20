package model.dao;

import java.sql.Connection;

import model.dao.impl.ProductJDBC;
import model.dao.impl.TelaLoginJDBC;

public class DaoFactory {
    
    public static ProductDao createProductDao(Connection conn) {
	return new ProductJDBC(conn);
    }

    public static TelaLoginDao createTelaLogin(Connection conn) {
	return new TelaLoginJDBC(conn);
    }
}
