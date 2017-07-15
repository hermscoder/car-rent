/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent;

/**
 *
 * @author MarcoTulio
 */
public class Aluguel {
    private String placaVeiculo; // Chave estrangeira de Veiculo
    private int codCliente; // Chave estrangeira de Cliente
    private int datRetDia,datRetMes,datRetAno; // Data de retirada do veiculo. Dia ,mes e ano
    private int datDevDia,datDevMes,datDevAno; // Data de devolucao do veiculo. Dia,mes e ano.
    private String TipoFranquia; // 
    private String nroCNH; // 
    private int VencDiaCNH,VencMesCNH,VencAnoCNH; //Data de vencimento CNH do comprador. Dia, mes e ano 

    public Aluguel(String placaVeiculo, int codCliente, int datRetDia, int datRetMes, int datRetAno, int datDevDia, int datDevMes, int datDevAno, String TipoFranquia, String nroCNH, int VencDiaCNH, int VencMesCNH, int VencAnoCNH) {
        this.placaVeiculo = placaVeiculo;
        this.codCliente = codCliente;
        this.datRetDia = datRetDia;
        this.datRetMes = datRetMes;
        this.datRetAno = datRetAno;
        this.datDevDia = datDevDia;
        this.datDevMes = datDevMes;
        this.datDevAno = datDevAno;
        this.TipoFranquia = TipoFranquia;
        this.nroCNH = nroCNH;
        this.VencDiaCNH = VencDiaCNH;
        this.VencMesCNH = VencMesCNH;
        this.VencAnoCNH = VencAnoCNH;
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

    public int getDatRetDia() {
        return datRetDia;
    }

    public void setDatRetDia(int datRetDia) {
        this.datRetDia = datRetDia;
    }

    public int getDatRetMes() {
        return datRetMes;
    }

    public void setDatRetMes(int datRetMes) {
        this.datRetMes = datRetMes;
    }

    public int getDatRetAno() {
        return datRetAno;
    }

    public void setDatRetAno(int datRetAno) {
        this.datRetAno = datRetAno;
    }

    public int getDatDevDia() {
        return datDevDia;
    }

    public void setDatDevDia(int datDevDia) {
        this.datDevDia = datDevDia;
    }

    public int getDatDevMes() {
        return datDevMes;
    }

    public void setDatDevMes(int datDevMes) {
        this.datDevMes = datDevMes;
    }

    public int getDatDevAno() {
        return datDevAno;
    }

    public void setDatDevAno(int datDevAno) {
        this.datDevAno = datDevAno;
    }

    public String getTipoFranquia() {
        return TipoFranquia;
    }

    public void setTipoFranquia(String TipoFranquia) {
        this.TipoFranquia = TipoFranquia;
    }

    public String getNroCNH() {
        return nroCNH;
    }

    public void setNroCNH(String nroCNH) {
        this.nroCNH = nroCNH;
    }

    public int getVencDiaCNH() {
        return VencDiaCNH;
    }

    public void setVencDiaCNH(int VencDiaCNH) {
        this.VencDiaCNH = VencDiaCNH;
    }

    public int getVencMesCNH() {
        return VencMesCNH;
    }

    public void setVencMesCNH(int VencMesCNH) {
        this.VencMesCNH = VencMesCNH;
    }

    public int getVencAnoCNH() {
        return VencAnoCNH;
    }

    public void setVencAnoCNH(int VencAnoCNH) {
        this.VencAnoCNH = VencAnoCNH;
    }
    
    
}
