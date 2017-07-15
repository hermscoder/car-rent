/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent.DAO;

/**
 *
 * @author MarcoTulio
 */
public class Reserva {
    private int codR; // codigo da reserva
    private int datPrevRetDia,datPrevRetMes,datPreRetvAno; // data prevista da reserva. Dia, mes e ano
    private int datPrevDevDia,datPrevDevMes,datPreDevAno; // data prevista de devoluca.
    private String local; // Local reservado
    private int clienteCod; // chave estrangeira para cliente
    private int tipoVeicCod; // chave estrangeira para Tipo Veiculo.

    public Reserva(int codR, int datPrevRetDia, int datPrevRetMes, int datPreRetvAno, int datPrevDevDia, int datPrevDevMes, int datPreDevAno, String local, int clienteCod, int tipoVeicCod) {
        this.codR = codR;
        this.datPrevRetDia = datPrevRetDia;
        this.datPrevRetMes = datPrevRetMes;
        this.datPreRetvAno = datPreRetvAno;
        this.datPrevDevDia = datPrevDevDia;
        this.datPrevDevMes = datPrevDevMes;
        this.datPreDevAno = datPreDevAno;
        this.local = local;
        this.clienteCod = clienteCod;
        this.tipoVeicCod = tipoVeicCod;
    }

    public int getCodR() {
        return codR;
    }

    public void setCodR(int codR) {
        this.codR = codR;
    }

    public int getDatPrevRetDia() {
        return datPrevRetDia;
    }

    public void setDatPrevRetDia(int datPrevRetDia) {
        this.datPrevRetDia = datPrevRetDia;
    }

    public int getDatPrevRetMes() {
        return datPrevRetMes;
    }

    public void setDatPrevRetMes(int datPrevRetMes) {
        this.datPrevRetMes = datPrevRetMes;
    }

    public int getDatPreRetvAno() {
        return datPreRetvAno;
    }

    public void setDatPreRetvAno(int datPreRetvAno) {
        this.datPreRetvAno = datPreRetvAno;
    }

    public int getDatPrevDevDia() {
        return datPrevDevDia;
    }

    public void setDatPrevDevDia(int datPrevDevDia) {
        this.datPrevDevDia = datPrevDevDia;
    }

    public int getDatPrevDevMes() {
        return datPrevDevMes;
    }

    public void setDatPrevDevMes(int datPrevDevMes) {
        this.datPrevDevMes = datPrevDevMes;
    }

    public int getDatPreDevAno() {
        return datPreDevAno;
    }

    public void setDatPreDevAno(int datPreDevAno) {
        this.datPreDevAno = datPreDevAno;
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
    
    
}
