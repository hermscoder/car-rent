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
public class Veiculo {
    private String placa; // placa do veiculo
    private String cor;//Cor do veiculo
    private String nroChassi; // Numero do chassi
    private String nroMotor; // Numero do motor
    private int km_atual; // km rodada pelo carro atualmente
    private int TipoVCod; // Chave estrangeira de Veiculo

    public Veiculo(String placa, String cor, String nroChassi, String nroMotor, int km_atual, int TipoVCod) {
        this.placa = placa;
        this.cor = cor;
        this.nroChassi = nroChassi;
        this.nroMotor = nroMotor;
        this.km_atual = km_atual;
        this.TipoVCod = TipoVCod;
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

    public int getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(int km_atual) {
        this.km_atual = km_atual;
    }

    public int getTipoVCod() {
        return TipoVCod;
    }

    public void setTipoVCod(int TipoVCod) {
        this.TipoVCod = TipoVCod;
    }
    
    
}
