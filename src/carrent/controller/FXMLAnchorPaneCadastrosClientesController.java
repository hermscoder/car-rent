
package carrent.controller;

import java.awt.MenuItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import carrent.DAO.*;
import carrent.database.ConnectionFactory;
import java.sql.Connection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Dialog;

public class FXMLAnchorPaneCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tablecolumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tablecolumnClienteCPF;
      
    @FXML
    private TextField textFieldClienteCodigo;
    @FXML
    private TextField textFieldClienteNome;
    @FXML
    private TextField textFieldClienteCPF;
    @FXML
    private TextField textFieldClienteDtNascimento;
    @FXML
    private TextField textFieldClienteEndereco;
    @FXML
    private TextField textFieldClienteCelular;
    @FXML
    private TextField textFieldClienteTelefoneFixo;
    
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    
    @FXML
    private Button btnPost;
    @FXML
    private Button btnCancel;
    
    @FXML
    private List<Cliente> listClientes;
    @FXML
    private ObservableList<Cliente> observableListClientes;
    
    private final Connection connection = new ConnectionFactory().getConnection();
    private final Cliente Cliente = new Cliente();
    private String state;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cliente.setConnection(connection);
        carregarTableViewClientes();
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                    (observable,oldValue,newValue) -> selecionarItemTableViewClientes(newValue));
    }   
    public void carregarTableViewClientes(){
        //O parametro do PropertyValueFactory é o nome da coluna da tabela
        tablecolumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
        
        listClientes = Cliente.listar();
        
        observableListClientes = FXCollections.observableList(listClientes);
        tableViewClientes.setItems(observableListClientes);
    }
    
    
    public void selecionarItemTableViewClientes(Cliente cliente){
        if (cliente != null){
            textFieldClienteCodigo.setText(Integer.toString(cliente.getCodCliente()));
            textFieldClienteNome.setText(cliente.getNome());
            textFieldClienteCPF.setText(cliente.getCpfCliente());
            textFieldClienteDtNascimento.setText(cliente.getdataNasc());
            textFieldClienteEndereco.setText(cliente.getendereco());
            textFieldClienteCelular.setText(cliente.getTelCelular());
            textFieldClienteTelefoneFixo.setText(cliente.getTelFixo());
            
            preencheCliente();
//            Cliente.setCodCliente(Integer.parseInt(textFieldClienteCodigo.getText()));
//            Cliente.setNome(textFieldClienteNome.getText());
//            Cliente.setCpfCliente(textFieldClienteCPF.getText());
//            Cliente.setdataNasc(textFieldClienteDtNascimento.getText());
//            Cliente.setendereco(textFieldClienteEndereco.getText());
//            Cliente.setTelCelular(textFieldClienteCelular.getText());
//            Cliente.setTelFixo(textFieldClienteTelefoneFixo.getText());            
        
        }else{
            preencheTextField(false);
//            textFieldClienteCodigo.setText("");
//            textFieldClienteNome.setText("");
//            textFieldClienteCPF.setText("");
//            textFieldClienteDtNascimento.setText("");
//            textFieldClienteEndereco.setText("");
//            textFieldClienteCelular.setText("");
//            textFieldClienteTelefoneFixo.setText("");
  
        }

    }
    public void btnAlterarClicked(){
            state = "update";
            btnInserir.setDisable(true);
            btnRemover.setDisable(true);
            btnAlterar.setDisable(false); 
            
            btnPost.setVisible(true);
            btnCancel.setVisible(true);
            
            textFieldsEditable(true);     
    }
    public void btnInserirClicked(){
            state = "insert";
            btnInserir.setDisable(false);
            btnRemover.setDisable(true);
            btnAlterar.setDisable(true); 
            
            btnPost.setVisible(true);
            btnCancel.setVisible(true);
            
            textFieldsEditable(true);     
            preencheTextField(false);
//            textFieldClienteCodigo.setText("");
//            textFieldClienteNome.setText("");
//            textFieldClienteCPF.setText("");
//            textFieldClienteDtNascimento.setText("");
//            textFieldClienteEndereco.setText("");
//            textFieldClienteCelular.setText("");
//            textFieldClienteTelefoneFixo.setText("");
            
    }
    public void btnRemoverClicked(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Atenção!");
        alert.setContentText("Deseja deletar o registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Cliente.delete(Cliente);
            preencheTextField(false);
//            textFieldClienteCodigo.setText("");;
//            textFieldClienteNome.setText("");
//            textFieldClienteCPF.setText("");
//            textFieldClienteDtNascimento.setText("");
//            textFieldClienteEndereco.setText("");
//            textFieldClienteCelular.setText("");
//            textFieldClienteTelefoneFixo.setText("");
            carregarTableViewClientes();
        }    
    }
    public void btnPostClicked(){
        
        btnInserir.setDisable(false);
        btnRemover.setDisable(false);
        btnAlterar.setDisable(false);
        
        btnPost.setVisible(false);
        btnCancel.setVisible(false); 
        
        textFieldsEditable(false);   

        preencheCliente();
//        if(!textFieldClienteCodigo.getText().trim().equals("")){
//            Cliente.setCodCliente(Integer.parseInt(textFieldClienteCodigo.getText()));
//        }        
//        Cliente.setNome(textFieldClienteNome.getText());
//        Cliente.setCpfCliente(textFieldClienteCPF.getText());
//        Cliente.setdataNasc(textFieldClienteDtNascimento.getText());
//        Cliente.setendereco(textFieldClienteEndereco.getText());
//        Cliente.setTelCelular(textFieldClienteCelular.getText());
//        Cliente.setTelFixo(textFieldClienteTelefoneFixo.getText()); 
        
        if(state == "update"){
           Cliente.update(Cliente); 
        }
        if(state == "insert"){
           Cliente.insert(Cliente); 
        }
        
        carregarTableViewClientes();
        
        preencheTextField(true);
//        textFieldClienteCodigo.setText(Integer.toString(Cliente.getCodCliente()));
//        textFieldClienteNome.setText(Cliente.getNome());
//        textFieldClienteCPF.setText(Cliente.getCpfCliente());
//        textFieldClienteDtNascimento.setText(Cliente.getdataNasc());
//        textFieldClienteEndereco.setText(Cliente.getendereco());
//        textFieldClienteCelular.setText(Cliente.getTelCelular());
//        textFieldClienteTelefoneFixo.setText(Cliente.getTelFixo()); 
       
    }
    public void btnCancelClicked(){
        
        btnInserir.setDisable(false);
        btnRemover.setDisable(false);
        btnAlterar.setDisable(false);
        
        btnPost.setVisible(false);
        btnCancel.setVisible(false); 
        
        textFieldsEditable(false);   
         
        preencheTextField(true);
//        textFieldClienteCodigo.setText(Integer.toString(Cliente.getCodCliente()));
//        textFieldClienteNome.setText(Cliente.getNome());
//        textFieldClienteCPF.setText(Cliente.getCpfCliente());
//        textFieldClienteDtNascimento.setText(Cliente.getdataNasc());
//        textFieldClienteEndereco.setText(Cliente.getendereco());
//        textFieldClienteCelular.setText(Cliente.getTelCelular());
//        textFieldClienteTelefoneFixo.setText(Cliente.getTelFixo());         
        //carregarTableViewClientes();
       
    }    
    
    public void preencheTextField(boolean preencherFields){
        if(preencherFields){
            textFieldClienteCodigo.setText(Integer.toString(Cliente.getCodCliente()));
            textFieldClienteNome.setText(Cliente.getNome());
            textFieldClienteCPF.setText(Cliente.getCpfCliente());
            textFieldClienteDtNascimento.setText(Cliente.getdataNasc());
            textFieldClienteEndereco.setText(Cliente.getendereco());
            textFieldClienteCelular.setText(Cliente.getTelCelular());
            textFieldClienteTelefoneFixo.setText(Cliente.getTelFixo()); 
        }else{
            textFieldClienteCodigo.setText("");
            textFieldClienteNome.setText("");
            textFieldClienteCPF.setText("");
            textFieldClienteDtNascimento.setText("");
            textFieldClienteEndereco.setText("");
            textFieldClienteCelular.setText("");
            textFieldClienteTelefoneFixo.setText("");
        }
    }
    
    public void preencheCliente(){
        if(!textFieldClienteCodigo.getText().trim().equals("")){
            Cliente.setCodCliente(Integer.parseInt(textFieldClienteCodigo.getText()));
        }        
        Cliente.setNome(textFieldClienteNome.getText());
        Cliente.setCpfCliente(textFieldClienteCPF.getText());
        Cliente.setdataNasc(textFieldClienteDtNascimento.getText());
        Cliente.setendereco(textFieldClienteEndereco.getText());
        Cliente.setTelCelular(textFieldClienteCelular.getText());
        Cliente.setTelFixo(textFieldClienteTelefoneFixo.getText());         
    }
    public void textFieldsEditable(boolean editable){
        if (editable){
            //textFieldClienteCodigo.setEditable(true);
            textFieldClienteNome.setEditable(true);
            textFieldClienteCPF.setEditable(true);
            textFieldClienteDtNascimento.setEditable(true);
            textFieldClienteEndereco.setEditable(true);
            textFieldClienteCelular.setEditable(true);
            textFieldClienteTelefoneFixo.setEditable(true);
        }
        else{
            //textFieldClienteCodigo.setEditable(false);
            textFieldClienteNome.setEditable(false);
            textFieldClienteCPF.setEditable(false);
            textFieldClienteDtNascimento.setEditable(false);
            textFieldClienteEndereco.setEditable(false);
            textFieldClienteCelular.setEditable(false);
            textFieldClienteTelefoneFixo.setEditable(false);            
        }
    }
}
