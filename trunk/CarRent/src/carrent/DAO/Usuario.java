package carrent.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import carrent.database.ConnectionFactory;
import com.sun.deploy.util.SessionState;
import java.util.List;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.Arrays;



public class Usuario {
    
    

    
    

    private String usuario; //cpf do cliente
    private String senha; // nome do cliente
    private boolean ehgerente; // Nome da rua e bairro e numero da casa

    
    private Connection connection;
    
    public Usuario(){
    }    
   
    public Usuario VerificaUsuario(Usuario user){
        String sql = "SELECT * FROM USUARIO WHERE USUARIO = ? AND SENHA = ?";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,user.getusuario());
            stmt.setString(2,user.getsenha());
            
            ResultSet resultado = stmt.executeQuery();
            
            while(resultado.next()){
                user.setehgerente(resultado.getBoolean("EHGERENTE"));
                return user;
                
            }        
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
        return null;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
            
    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getsenha() {
        return senha;
    }

    public void setsenha(String senha) {
        this.senha = senha;
    }

    public boolean getehgerente() {
        return ehgerente;
    }

    public void setehgerente(boolean ehgerente) {
        this.ehgerente = ehgerente;
    }
    
    
}
