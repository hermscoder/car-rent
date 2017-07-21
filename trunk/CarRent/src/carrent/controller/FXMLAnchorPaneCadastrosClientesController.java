
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

public class FXMLAnchorPaneCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tablecolumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tablecolumnClienteCPF;
    @FXML
    private Label labelClienteCodigo;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteCPF;
    @FXML
    private Label labelClienteTelefone;
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
        
    }   
    public void carregarTableViewClientes(){
        tablecolumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listClientes = Cliente.listar();
        
        observableListClientes = FXCollections.observableList(listClientes);
        tableViewClientes.setItems(observableListClientes);
    }
    
}
