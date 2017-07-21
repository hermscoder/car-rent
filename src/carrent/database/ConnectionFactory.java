// situa em qual package ou “pacote” está a classe 
package carrent.database; 
// faz as importações de classes necessárias para o funcionamento do programa 
import java.sql.Connection; 
// conexão SQL para Java 
import java.sql.DriverManager; 
// driver de conexão SQL para Java 
import java.sql.SQLException; 
// classe para tratamento de exceções 
public class ConnectionFactory {
     public Connection getConnection() {
		 try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/carrent","postgres","postgres");
		 }         
		 catch(SQLException excecao) {
			throw new RuntimeException(excecao);
		 
                 }
                 
                 
                         //Cria uma variável de conexão



     }
}
