package application;

import model.dao.DaoFactory;
import model.dao.TelaPrincipalDao;

public class Test {
    
    
    public static void main(String[] args) {
	TelaPrincipalDao telaPrincipal = DaoFactory.createTelaPrincipalDao();
	
	telaPrincipal.logar();
	System.out.println("XAHAHA");
    }
    
}
