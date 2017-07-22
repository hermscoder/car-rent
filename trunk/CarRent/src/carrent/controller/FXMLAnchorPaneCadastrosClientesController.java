
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLAnchorPaneCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tablecolumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tablecolumnClienteCPF;
    
    
    @FXML
    private ImageView imgPost;
    @FXML
    private ImageView imgCancel;
    
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
    private List<Cliente> listClientes;
    @FXML
    private ObservableList<Cliente> observableListClientes;
    
    private final Connection connection = new ConnectionFactory().getConnection();
    private final Cliente Cliente = new Cliente();
    
    
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
            textFieldClienteTelefoneFixo.setText(cliente.getTelCelular());    
          
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
    public void btnAlterarClicked(){
        
            btnInserir.setDisable(true);
            btnRemover.setDisable(true);
            imgPost.setVisible(true);
            imgCancel.setVisible(true);
            
            textFieldClienteCodigo.setEditable(true);
            textFieldClienteNome.setEditable(true);
            textFieldClienteCPF.setEditable(true);
            textFieldClienteDtNascimento.setEditable(true);
            textFieldClienteEndereco.setEditable(true);
            textFieldClienteCelular.setEditable(true);
            textFieldClienteTelefoneFixo.setEditable(true);      
    }
}
