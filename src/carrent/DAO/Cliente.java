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
public class Cliente {
    private int codCliente; //codigo do cliente
    private String cpfCliente; //cpf do cliente
    private String nome; // nome do cliente
    private String endRua,endBairro; // Nome da rua e bairro
    private int endNumero; // Numero da casa
    private int nascDia,nascMes,nascAno; // Data de nascimento do mesmo. Dia, mes e ano
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
        this.nascDia = nascDia;
        this.nascMes = nascMes;
        this.nascAno = nascAno;
        this.sexo = sexo;
        this.telCelular = telCelular;
        this.telFixo = telFixo;
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

    public int getNascDia() {
        return nascDia;
    }

    public void setNascDia(int nascDia) {
        this.nascDia = nascDia;
    }

    public int getNascMes() {
        return nascMes;
    }

    public void setNascMes(int nascMes) {
        this.nascMes = nascMes;
    }

    public int getNascAno() {
        return nascAno;
    }

    public void setNascAno(int nascAno) {
        this.nascAno = nascAno;
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
