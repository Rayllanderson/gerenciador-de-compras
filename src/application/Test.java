package application;


import java.sql.Connection;

import db.DB;

public class Test {

    public static void main(String[] args) {
	Connection conn = DB.getConnection();

	System.out.println("Conexão funcionou");
    }

}
