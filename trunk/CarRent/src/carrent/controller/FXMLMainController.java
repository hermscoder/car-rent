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
    private MenuItem menuitemServicosReserva;
    @FXML
    private MenuItem handlemenuitemServicosAluguel;
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
    @FXML 
    public void handlemenuitemCadastrosTipoVeiculos() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneCadastrosTipoVeiculos.fxml"));
       anchorPane.getChildren().setAll(b);
    }
    @FXML 
    public void handlemenuitemCadastrosVeiculos() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneCadastrosVeiculos.fxml"));
       anchorPane.getChildren().setAll(b);
    }
        @FXML 
    public void handlemenuitemServicosReservas() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneServicosReservas.fxml"));
       anchorPane.getChildren().setAll(b);
    }
    public void handlemenuitemServicosAluguel() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneServicosAluguel.fxml"));
       anchorPane.getChildren().setAll(b);
    }
}
