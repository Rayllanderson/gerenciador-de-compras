package model.dao;

import java.sql.Connection;

import model.dao.impl.ProductJDBC;

public class DaoFactory {
    
    public static ProductDao createProductDao(Connection conn) {
	return new ProductJDBC(conn);
    }

}
