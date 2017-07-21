package carrent.controller;


import javafx.scene.control.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
//carrent.controller.FXMLMainController
public class FXMLMainController implements Initializable {
    @FXML
    private MenuItem menuitemCadastrosClientes;
    @FXML
    private MenuItem menuitemCadastrosTipoVeiculos;
    @FXML
    private MenuItem menuitemCadastrosVeiculos;
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML 
    public void handlemenuitemCadastrosClientes() throws IOException{
       AnchorPane a;
       a = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneCadastrosClientes.fxml"));
       anchorPane.getChildren().setAll(a);
    }
}
