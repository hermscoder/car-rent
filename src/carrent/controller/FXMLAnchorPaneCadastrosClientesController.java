
package carrent.controller;

import java.awt.MenuItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


public class FXMLAnchorPaneCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Clientes> tableViewClientes;
    @FXML
    private TableColumn<Clientes, String> tablecolumnClienteNome;
    @FXML
    private TableColumn<Clientes, String> tablecolumnClienteCPF;
    @FXML
    private Label labelClienteCodigo;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteCPF;
    @FXML
    private Label labelClienteTelefone;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
