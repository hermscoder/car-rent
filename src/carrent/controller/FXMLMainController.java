package carrent.controller;


import carrent.DAO.Usuario;
import carrent.DAO.UsuarioAtual;
import carrent.database.ConnectionFactory;
import javafx.scene.control.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.control.Label;

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
    private MenuItem menuitemRelatoriosAlugueisPeriodo;
    @FXML
    private MenuItem menuitemRelatoriosLocacoesVeiculo;    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lbUsuario;
    public static Usuario UsuarioAtual = new Usuario();
    private Connection con;
    public static boolean ehGerente;
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //UsuarioAtual.setConnection(con);
            if(!chamaLogin()){
                
                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML 
    public boolean chamaLogin() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLDialogLogin.fxml"));
       Stage dialog = new Stage();
       dialog.setTitle("Login");
       Scene scene = new Scene(b);
       dialog.setScene(scene);
       dialog.showAndWait();
       if(UsuarioAtual != null){
            return true;
       }else{
           return false;
       }
      
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
    @FXML 
    public void handlemenuitemServicosAluguel() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneServicosAluguel.fxml"));
       anchorPane.getChildren().setAll(b);
    }    
    @FXML 
    public void handlemenuitemRelatoriosAlugueisPeriodo() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneRelatoriosAlugueisPeriodo.fxml"));
       Stage dialog = new Stage();
       dialog.setTitle("Relatório");
       Scene scene = new Scene(b);
       dialog.setScene(scene);
       dialog.showAndWait();
       
    }
    @FXML 
    public void handlemenuitemRelatoriosLocacoesVeiculo() throws IOException{
       AnchorPane b;
       b = (AnchorPane) FXMLLoader.load(getClass().getResource("/carrent/view/FXMLAnchorPaneRelatoriosLocacoesVeiculo.fxml"));
       Stage dialog = new Stage();
       dialog.setTitle("Relatório");
       Scene scene = new Scene(b);
       dialog.setScene(scene);
       dialog.showAndWait();

    }

}
