
package carrent.database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabasePostgreSQL {
    private Connection connection;
    @Override
    public Connection conectar(){
        try{
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/carrent","postgres","postgres");
        }
    }
}
