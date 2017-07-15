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
public class TipoVeiculo {
    private int codTV; //Codigo do tipo de veiculo, mantem relacao com a classe Veiculo
    private double tamanho; // tamanho do carro
    private int numPassageiros; // numero passageiros
    private int numPortas; //numero de portas
    private double valorDiarioLocacao; // valor locacao diaria
    private double valorKMROdado; // valor por km rodado
    private double valorFranqNormal; // valor franquia normal
    private double valorFranqReduzida; // valor franquia reudizida
    private int ArCondicionado; // 1- Sim / 0- Não 

    public TipoVeiculo(int codTV, double tamanho, int numPassageiros, int numPortas, double valorDiarioLocacao, double valorKMROdado, double valorFranqNormal, double valorFranqReduzida, int ArCondicionado) {
        this.codTV = codTV;
        this.tamanho = tamanho;
        this.numPassageiros = numPassageiros;
        this.numPortas = numPortas;
        this.valorDiarioLocacao = valorDiarioLocacao;
        this.valorKMROdado = valorKMROdado;
        this.valorFranqNormal = valorFranqNormal;
        this.valorFranqReduzida = valorFranqReduzida;
        this.ArCondicionado = ArCondicionado;
    }

    public int getCodTV() {
        return codTV;
    }

    public void setCodTV(int codTV) {
        this.codTV = codTV;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        this.numPassageiros = numPassageiros;
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    public double getValorDiarioLocacao() {
        return valorDiarioLocacao;
    }

    public void setValorDiarioLocacao(double valorDiarioLocacao) {
        this.valorDiarioLocacao = valorDiarioLocacao;
    }

    public double getValorKMROdado() {
        return valorKMROdado;
    }

    public void setValorKMROdado(double valorKMROdado) {
        this.valorKMROdado = valorKMROdado;
    }

    public double getValorFranqNormal() {
        return valorFranqNormal;
    }

    public void setValorFranqNormal(double valorFranqNormal) {
        this.valorFranqNormal = valorFranqNormal;
    }

    public double getValorFranqReduzida() {
        return valorFranqReduzida;
    }

    public void setValorFranqReduzida(double valorFranqReduzida) {
        this.valorFranqReduzida = valorFranqReduzida;
    }

    public int getArCondicionado() {
        return ArCondicionado;
    }

    public void setArCondicionado(int ArCondicionado) {
        this.ArCondicionado = ArCondicionado;
    }
    
    
}
