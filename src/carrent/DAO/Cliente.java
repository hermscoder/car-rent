package carrent.DAO;

import carrent.controller.FXMLMainController;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import carrent.database.ConnectionFactory;
import com.sun.deploy.util.SessionState;
import java.util.List;
import java.sql.Connection; 
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.Arrays;



public class Cliente {
    
    

    
    
    private int codCliente; //codigo do cliente
    private String cpfCliente; //cpf do cliente
    private String nome; // nome do cliente
    private String endereco; // Nome da rua e bairro e numero da casa
    private Date dataNasc; // Data de nascimento do mesmo. Dia, mes e ano
    private String sexo; 
    private String telCelular;
    private String telFixo;
    
    private Connection connection;
    
    public Cliente(){
    }    
    public Cliente(int codCliente, String cpfCliente, String nome, String endRua, String endBairro, int endNumero, int nascDia, int nascMes, int nascAno, String sexo, String telCelular, String telFixo) {
        this.codCliente = codCliente;
        this.cpfCliente = cpfCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telCelular = telCelular;
        this.telFixo = telFixo;
    }
    
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    
    public boolean insert(Cliente cliente){
        String sql = "INSERT INTO CLIENTE(CPF,ENDERECO,DATANASC,SEXO,TELEFONEFIXO,TELEFONECELULAR,NOME) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,cpfCliente );
            stmt.setString(2,endereco );
            stmt.setDate(3, carrent.Main.FormatDate(dataNasc));
            stmt.setString(4, sexo);
            stmt.setString(5, telFixo);
            stmt.setString(6, telCelular);
            stmt.setString(7, nome);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Cliente.insert()");
            return false;
        }
        
    }
    public boolean update(Cliente cliente){
        String sql = "UPDATE CLIENTE SET CPF=?,ENDERECO=?,DATANASC=?,SEXO=?,TELEFONEFIXO=?,TELEFONECELULAR=?,NOME=? WHERE CODC= "+Integer.toString(codCliente);
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,cpfCliente );
            stmt.setString(2,endereco );
//            stmt.setDate(3, carrent.Main.FormatDate(dataNasc));
            stmt.setDate(3, dataNasc);
            stmt.setString(4, sexo);
            stmt.setString(5, telFixo);
            stmt.setString(6, telCelular);
            stmt.setString(7, nome);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Cliente.update()");
            return false;
        }
        
    }

    public boolean delete(Cliente cliente){
        String sql = "DELETE FROM CLIENTE WHERE CODC = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1,codCliente);
            stmt.execute();
            
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Cliente.delete()");
            return false;
        }
        
    }
    
    public List<Cliente> listar(){
        String sql = "SELECT * FROM CLIENTE";
        List<Cliente> listaDeClientes = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Cliente cliente = new Cliente();
                cliente.setCodCliente(resultado.getInt("CODC"));
                cliente.setCpfCliente(resultado.getString("CPF"));
                cliente.setNome(resultado.getString("NOME"));
                cliente.setendereco(resultado.getString("ENDERECO"));
                cliente.setdataNasc(resultado.getDate("DATANASC"));
                cliente.setSexo(resultado.getString("SEXO"));
                cliente.setTelFixo(resultado.getString("TELEFONEFIXO"));
                cliente.setTelCelular(resultado.getString("TELEFONECELULAR"));
                listaDeClientes.add(cliente);
            }         
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return listaDeClientes;
    }

    public Cliente select(Cliente clienteParametro){
        String sql = "SELECT * FROM CLIENTE WHERE CODC = ?";
        Cliente client = new Cliente();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, clienteParametro.getCodCliente());
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            
            client.setCodCliente(resultado.getInt("CODC"));
            client.setCpfCliente(resultado.getString("CPF"));
            client.setNome(resultado.getString("NOME"));
            client.setendereco(resultado.getString("ENDERECO"));
            client.setdataNasc(resultado.getDate("DATANASC"));
            client.setSexo(resultado.getString("SEXO"));
            client.setTelFixo(resultado.getString("TELEFONEFIXO"));
            client.setTelCelular(resultado.getString("TELEFONECELULAR"));  
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return client;
    }   
    
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getendereco() {
        return endereco;
    }

    public void setendereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getdataNasc() {
        return dataNasc;
    }

    public void setdataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }
    
    
}
