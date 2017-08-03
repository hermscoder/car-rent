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

/**
 *
 * @author MarcoTulio
 */
public class Aluguel {
    
    private Connection connection;
    
    private String placaVeiculo; // Chave estrangeira de Veiculo
    private int codCliente; // Chave estrangeira de Cliente
    private String datRet; // Data de retirada do veiculo. Dia ,mes e ano
    private String datDev; // Data de devolucao do veiculo. Dia,mes e ano.
    private String TipoFranquia; // 
    private long nroCNH; // 
    private String DatVencCNH; //Data de vencimento CNH do comprador. Dia, mes e ano 

    public Aluguel(){}
    public Aluguel(String placaVeiculo, int codCliente, int datRetDia, int datRetMes, int datRetAno, int datDevDia, int datDevMes, int datDevAno, String TipoFranquia, long nroCNH, int VencDiaCNH, int VencMesCNH, int VencAnoCNH) {
        this.placaVeiculo = placaVeiculo;
        this.codCliente = codCliente;
        this.datRet = datRet;
        this.datDev = datDev;
        this.TipoFranquia = TipoFranquia;
        this.nroCNH = nroCNH;
        this.DatVencCNH = DatVencCNH;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }   
    
    public boolean insert(Aluguel aluguel){
        String sql = "INSERT INTO ALUGUEL(PLACA,CODC,DATARETIRADA,DATADEVOLUCAO,TIPOFRANQUIA,NROCNH,DATAVENCIMENTOCNH) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placaVeiculo );
            stmt.setInt(2,codCliente );
            stmt.setString(3, datRet);
            stmt.setString(4, datDev);
            stmt.setString(5, TipoFranquia);
            stmt.setLong(6, nroCNH);
            stmt.setString(7, DatVencCNH);
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Aluguel.insert()");
            return false;
        }
        
    }
    
    public boolean update(Aluguel aluguel){
        String sql = "UPDATE ALUGUEL SET PLACA=?,CODC=?,DATARETIRADA=?,DATADEVOLUCAO=?,TIPOFRANQUIA=?,NROCNH=?, DATAVENCIMENTOCNH=? WHERE  PLACA LIKE '?' AND CODC = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placaVeiculo );
            stmt.setInt(2,codCliente );
            stmt.setString(3, datRet);
            stmt.setString(4, datDev);
            stmt.setString(5, TipoFranquia);
            stmt.setLong(6, nroCNH);
            stmt.setString(7, DatVencCNH);
            //PARAMETROS
            stmt.setString(8,placaVeiculo);
            stmt.setInt(9,codCliente);
                    
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Aluguel.update()");
            return false;
        }
        
    }
    
    public boolean delete(Aluguel aluguel){
        String sql = "DELETE FROM ALUGUEL WHERE PLACA LIKE '?' AND CODC = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placaVeiculo);
            stmt.setInt(2,codCliente);
            stmt.execute();
            
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Aluguel.delete()");
            return false;
        }
        
    }
    
    public List<Aluguel> listar(){
        String sql = "SELECT * FROM ALUGUEL";
        List<Aluguel> listaDeAlugueis = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Aluguel aluguel = new Aluguel();
                aluguel.setPlacaVeiculo(resultado.getString("PLACA"));
                aluguel.setCodCliente(resultado.getInt("CODC"));
                aluguel.setDatRet(resultado.getString("DATARETIRADA"));
                aluguel.setDatDev(resultado.getString("DATADEVOLUCAO"));
                aluguel.setTipoFranquia(resultado.getString("TIPOFRANQUIA"));
                aluguel.setNroCNH(resultado.getLong("NROCNH"));
                aluguel.setDatVencCNH(resultado.getString("DATAVENCIMENTOCNH"));
                
                listaDeAlugueis.add(aluguel);
            }         
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return listaDeAlugueis;
    }
    
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getDatRet() {
        return datRet;
    }

    public void setDatRet(String datRet) {
        this.datRet = datRet;
    }

    public String getDatDev() {
        return datDev;
    }

    public void setDatDev(String datDev) {
        this.datDev = datDev;
    }

    public String getTipoFranquia() {
        return TipoFranquia;
    }

    public void setTipoFranquia(String TipoFranquia) {
        this.TipoFranquia = TipoFranquia;
    }

    public long getNroCNH() {
        return nroCNH;
    }

    public void setNroCNH(long nroCNH) {
        this.nroCNH = nroCNH;
    }

    public String getDatVencCNH() {
        return DatVencCNH;
    }

    public void setDatVencCNH(String VencDiaCNH) {
        this.DatVencCNH = VencDiaCNH;
    }  
    
}
