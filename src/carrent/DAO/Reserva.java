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
public class Reserva {
    
    private Connection connection;
    
    private int codR; // codigo da reserva
    private String datPrevRet; // data prevista da reserva. Dia, mes e ano


    private String datPrevDev; // data prevista de devoluca.
    private String local; // Local reservado
    private int clienteCod; // chave estrangeira para cliente
    private int tipoVeicCod; // chave estrangeira para Tipo Veiculo.
    private String NomeCliente;


    
    public Reserva(){}

    public Reserva(int codR, int datPrevRetDia, int datPrevRetMes, int datPreRetvAno, int datPrevDevDia, int datPrevDevMes, int datPreDevAno, String local, int clienteCod, int tipoVeicCod) {
        this.codR = codR;
        this.datPrevRet = datPrevRet;
        this.datPrevDev = datPrevDev;
        this.local = local;
        this.clienteCod = clienteCod;
        this.tipoVeicCod = tipoVeicCod;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    } 
    
    public boolean insert(Reserva reserva){
        String sql = "INSERT INTO RESERVA(DATAPREVISTARETIRADA,DATAPREVISTADEVOLUCAO,LOCALRETIRADA,CODC,CODTV) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,datPrevRet );
            stmt.setString(2,datPrevDev );
            stmt.setString(3, local);
            stmt.setInt(4, clienteCod);
            stmt.setInt(5, tipoVeicCod);
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Reserva.insert()");
            return false;
        }
        
    }

    public boolean update(Reserva reserva){
        String sql = "UPDATE RESERVA SET DATAPREVISTARETIRADA=?,DATAPREVISTADEVOLUCAO=?,LOCALRETIRADA=?,CODC=?,CODTV=? WHERE CODR = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,datPrevRet );
            stmt.setString(2,datPrevDev );
            stmt.setString(3, local);
            stmt.setInt(4, clienteCod);
            stmt.setInt(5, tipoVeicCod);
            stmt.setInt(6, codR);
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Reserva.update()");
            return false;
        }
        
    }

    public boolean delete(Reserva reserva){
        String sql = "DELETE FROM RESERVA WHERE CODR = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1,codR);
            stmt.execute();
            
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.Reserva.delete()");
            return false;
        }
        
    }
 
    public List<Reserva> listar(){
        String sql = "SELECT * FROM RESERVA";
        List<Reserva> listaDeReserva = new ArrayList<>();
        Cliente c = new Cliente();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Reserva reserva = new Reserva();
                reserva.setCodR(resultado.getInt("codr"));
                reserva.setDatPrevRet(resultado.getString("dataprevistaretirada"));
                reserva.setDatPrevDev(resultado.getString("dataprevistadevolucao"));
                reserva.setLocal(resultado.getString("localretirada"));
                reserva.setClienteCod(resultado.getInt("codc"));
                reserva.setTipoVeicCod(resultado.getInt("codtv"));
                c.setConnection(connection);
                c.setCodCliente(resultado.getInt("codc"));
                c = c.select(c);
                reserva.setNomeCliente(c.getNome());
                listaDeReserva.add(reserva);
            }         
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return listaDeReserva;
    }
    
    public int getCodR() {
        return codR;
    }

    public void setCodR(int codR) {
        this.codR = codR;
    }

    public String getDatPrevRet() {
        return datPrevRet;
    }
    
    public void setDatPrevRet(String datPrevRet) {
        this.datPrevRet = datPrevRet;
    }
    
    public String getDatPrevDev() {
        return datPrevDev;
    }
    
    public void setDatPrevDev(String datPrevDev) {
        this.datPrevDev = datPrevDev;
    }
    
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getClienteCod() {
        return clienteCod;
    }

    public void setClienteCod(int clienteCod) {
        this.clienteCod = clienteCod;
    }

    public int getTipoVeicCod() {
        return tipoVeicCod;
    }

    public void setTipoVeicCod(int tipoVeicCod) {
        this.tipoVeicCod = tipoVeicCod;
    }
    
    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }    
    
}
