/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent.DAO;

import carrent.Main;
import static carrent.Main.StringToDate;
import static carrent.Main.FormatDate;
import java.sql.Connection;
import java.sql.Date;
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
    private Date datRet; // Data de retirada do veiculo. Dia ,mes e ano
    private Date datDev; // Data de devolucao do veiculo. Dia,mes e ano.
    private String TipoFranquia; // 
    private long nroCNH; // 
    private Date DatVencCNH; //Data de vencimento CNH do comprador. Dia, mes e ano 
    private String NomeCliente;


    
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
            stmt.setDate(3, datRet);
            stmt.setDate(4, datDev);
            stmt.setString(5, TipoFranquia);
            stmt.setLong(6, nroCNH);
            stmt.setDate(7, DatVencCNH);
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Aluguel.insert()");
            return false;
        }
        
    }
    
    public boolean update(Aluguel aluguel){
        String sql = "UPDATE ALUGUEL SET PLACA=?,CODC=?,DATARETIRADA=?,DATADEVOLUCAO=?,TIPOFRANQUIA=?,NROCNH=?, DATAVENCIMENTOCNH=? WHERE  PLACA LIKE ? AND CODC = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,placaVeiculo );
            stmt.setInt(2,codCliente );
            stmt.setDate(3, datRet);
            stmt.setDate(4, datDev);
            stmt.setString(5, TipoFranquia);
            stmt.setLong(6, nroCNH);
            stmt.setDate(7, DatVencCNH);
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
        Cliente c = new Cliente();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Aluguel aluguel = new Aluguel();
                aluguel.setPlacaVeiculo(resultado.getString("PLACA"));
                aluguel.setCodCliente(resultado.getInt("CODC"));
                aluguel.setDatRet(resultado.getDate("DATARETIRADA"));
                aluguel.setDatDev(resultado.getDate("DATADEVOLUCAO"));
                aluguel.setTipoFranquia(resultado.getString("TIPOFRANQUIA"));
                aluguel.setNroCNH(resultado.getLong("NROCNH"));
                aluguel.setDatVencCNH(resultado.getDate("DATAVENCIMENTOCNH"));
                c.setConnection(connection);
                c.setCodCliente(resultado.getInt("codc"));
                c = c.select(c);
                aluguel.setNomeCliente(c.getNome());                
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

    public Date getDatRet() {
        return datRet;
    }

    public void setDatRet(Date datRet) {
        this.datRet = datRet;
    }

    public Date getDatDev() {
        return datDev;
    }

    public void setDatDev(Date datDev) {
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

    public Date getDatVencCNH() {
        return DatVencCNH;
    }

    public void setDatVencCNH(Date VencDiaCNH) {
        this.DatVencCNH = VencDiaCNH;
    }  
    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }    
}
