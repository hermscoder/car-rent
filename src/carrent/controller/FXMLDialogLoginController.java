
package carrent.controller;

import java.awt.MenuItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import carrent.controller.FXMLMainController;
import carrent.DAO.Usuario;
import carrent.database.ConnectionFactory;
import java.sql.Connection;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLDialogLoginController implements Initializable {
  
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldSenha;
    
    @FXML
    private Button btnLogin;
    
    
   
    private final Connection connection = new ConnectionFactory().getConnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLMainController.UsuarioAtual = null;
      
        //FXMLMainController.ehGerente = true;
        
    }   

    public void btnLoginClicked() throws Throwable{
        Usuario user = new Usuario();
        FXMLMainController.UsuarioAtual = null;
        user.setConnection(connection);
        user.setusuario(textFieldUsuario.getText());
        user.setsenha(textFieldSenha.getText()); 
        user = user.VerificaUsuario(user);
        
        if(user != null){
            FXMLMainController.UsuarioAtual = new Usuario();
            FXMLMainController.UsuarioAtual.setusuario(user.getusuario());
            FXMLMainController.UsuarioAtual.setsenha(user.getsenha());
            FXMLMainController.UsuarioAtual.setehgerente(user.getehgerente());
            Stage stage = (Stage) textFieldUsuario.getScene().getWindow();
            stage.close();
            user = null;
        }else{
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção!");
            alert.setContentText("Usuário ou senha incorretos. Tente Novamente.");
            alert.showAndWait();
            user = null;
            FXMLMainController.UsuarioAtual = null;
        }

       
    }    
    
    
}
