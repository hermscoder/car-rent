
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
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

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
    private List<Cliente> listClientes;
    @FXML
    private ObservableList<Cliente> observableListClientes;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
