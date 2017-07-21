package carrent.database; 


import carrent.DAO.Cliente;
import java.sql.Connection; 
import java.sql.SQLException; 
import java.sql.PreparedStatement;
import java.util.List;

public class TestaConexao {     
    public static void main(String[] args) throws SQLException {
        
        Connection connection = new ConnectionFactory().getConnection();
       
         System.out.println("Conexão aberta!");
         Cliente c = new Cliente();
         c.setConnection(connection);
         c.setCodCliente(6);
//         c.setCodCliente(5);
//         c.setCpfCliente("1232131");
//         c.setNome("Ruan Barros");
//         c.setEndRua("Aruã");
//         c.setEndBairro("Jardim das Palmeiras");
//         c.setEndNumero(189);
//         c.setdataNasc("05/01/1996");
//         c.setSexo("M");
//         c.setTelFixo("");
//         c.setTelCelular("");
//         
//         c.insert(c);
//         c = c.select(c);
//           List<Cliente> l = c.listar();
//           System.out.println(c.getCodCliente() + " " + c.getendereco() + " " + c.getCpfCliente() + " " + c.getNome());
         connection.close();
     
    }
}