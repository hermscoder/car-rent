/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import carrent.database.ConnectionFactory;
import java.sql.Connection; 
import java.sql.SQLException; 
/**
 *
 * @author MarcoTulio
 */
public class Cliente {
    
    private Connection connection;

    
    
    private int codCliente; //codigo do cliente
    private String cpfCliente; //cpf do cliente
    private String nome; // nome do cliente
    private String endRua,endBairro; // Nome da rua e bairro
    private int endNumero; // Numero da casa
    private String dataNasc; // Data de nascimento do mesmo. Dia, mes e ano
    private String sexo; 
    private String telCelular;
    private String telFixo;

    public Cliente(int codCliente, String cpfCliente, String nome, String endRua, String endBairro, int endNumero, int nascDia, int nascMes, int nascAno, String sexo, String telCelular, String telFixo) {
        this.codCliente = codCliente;
        this.cpfCliente = cpfCliente;
        this.nome = nome;
        this.endRua = endRua;
        this.endBairro = endBairro;
        this.endNumero = endNumero;
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
    public Cliente(){
    }
    
    public boolean insert(Cliente cliente){
        String sql = "INSERT INTO CLIENTE(CPF,ENDERECO,DATANASC,SEXO,TELEFONEFIXO,TELEFONECELULAR,NOME) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,cpfCliente );
            stmt.setString(2,endRua + ", " + Integer.toString(endNumero) + " - " + endBairro );
            stmt.setString(3, dataNasc);
            stmt.setString(4, sexo);
            stmt.setString(5, telFixo);
            stmt.setString(6, telCelular);
            stmt.setString(7, nome);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        
    }
    public boolean update(Cliente cliente){
        String sql = "UPDATE CLIENTE SET CPF=?,ENDERECO=?,DATANASC=?,SEXO=?,TELEFONEFIXO=?,TELEFONECELULAR=?,NOME=? WHERE CODC="+Integer.toString(codCliente);
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,cpfCliente );
            stmt.setString(2,endRua + ", " + Integer.toString(endNumero) + " - " + endBairro );
            stmt.setString(3, dataNasc);
            stmt.setString(4, sexo);
            stmt.setString(5, telFixo);
            stmt.setString(6, telCelular);
            stmt.setString(7, nome);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
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
            return false;
        }
        
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

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public int getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(int endNumero) {
        this.endNumero = endNumero;
    }

    public String getdataNasc() {
        return dataNasc;
    }

    public void setdataNasc(String dataNasc) {
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
