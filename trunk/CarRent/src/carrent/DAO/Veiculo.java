/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Veiculo {
    
    private Connection connection;
    
    private String placa; // placa do veiculo
    private String cor;//Cor do veiculo
    private String nroChassi; // Numero do chassi
    private String nroMotor; // Numero do motor
    private double km_atual; // km rodada pelo carro atualmente
    private int TipoVCod; // Chave estrangeira de Veiculo

    public Veiculo(){}
    public Veiculo(String placa, String cor, String nroChassi, String nroMotor, int km_atual, int TipoVCod) {
        this.placa = placa;
        this.cor = cor;
        this.nroChassi = nroChassi;
        this.nroMotor = nroMotor;
        this.km_atual = km_atual;
        this.TipoVCod = TipoVCod;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }    
    
    
    public boolean insert(Veiculo veiculo){
        String sql = "INSERT INTO VEICULO(PLACA,COR,NRO_CHASSI,NRO_MOTOR,KM_ATUAL,TIPO) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placa );
            stmt.setString(2,cor );
            stmt.setString(3, nroChassi);
            stmt.setString(4, nroMotor);
            stmt.setDouble(5, km_atual);
            stmt.setInt(6, TipoVCod);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Veiculo.insert()");
            return false;
        }
        
    }
    public boolean update(Veiculo veiculo){
        String sql = "UPDATE VEICULO SET PLACA=?,COR=?,NRO_CHASSI=?,NRO_MOTOR=?,KM_ATUAL=?,TIPO=? WHERE PLACA = '" + placa.toString() + "'";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placa );
            stmt.setString(2,cor );
            stmt.setString(3, nroChassi);
            stmt.setString(4, nroMotor);
            stmt.setDouble(5, km_atual);
            stmt.setInt(6, TipoVCod);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Veiculo.update()");
            return false;
        }
        
    }

    public boolean delete(Veiculo veiculo){
        String sql = "DELETE FROM VEICULO WHERE PLACA = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placa);
            stmt.execute();
            
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Veiculo.delete()");
            return false;
        }
        
    }
    
    public List<Veiculo> listar(){
        String sql = "SELECT * FROM VEICULO";
        List<Veiculo> listaDeVeiculos = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(resultado.getString("PLACA"));
                veiculo.setCor(resultado.getString("COR"));
                veiculo.setNroChassi(resultado.getString("NRO_CHASSI"));
                veiculo.setNroMotor(resultado.getString("NRO_MOTOR"));
                veiculo.setKm_atual(resultado.getDouble("KM_ATUAL"));
                veiculo.setTipoVCod(resultado.getInt("TIPO"));

                listaDeVeiculos.add(veiculo);
            }         
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return listaDeVeiculos;
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
            client.setdataNasc(resultado.getString("DATANASC"));
            client.setSexo(resultado.getString("SEXO"));
            client.setTelFixo(resultado.getString("TELEFONEFIXO"));
            client.setTelCelular(resultado.getString("TELEFONECELULAR"));  
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return client;
    }    
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNroChassi() {
        return nroChassi;
    }

    public void setNroChassi(String nroChassi) {
        this.nroChassi = nroChassi;
    }

    public String getNroMotor() {
        return nroMotor;
    }

    public void setNroMotor(String nroMotor) {
        this.nroMotor = nroMotor;
    }

    public double getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(double km_atual) {
        this.km_atual = km_atual;
    }

    public int getTipoVCod() {
        return TipoVCod;
    }

    public void setTipoVCod(int TipoVCod) {
        this.TipoVCod = TipoVCod;
    }
    
    
}
