
package carrent.controller;

import carrent.DAO.Aluguel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import carrent.database.ConnectionFactory;
import java.sql.Connection;

import javafx.scene.control.DatePicker;

public class FXMLAnchorPaneRelatoriosLocacoesVeiculoController implements Initializable {


    @FXML
    private DatePicker dtPickertDtInicial;
    @FXML
    private DatePicker dtPickerDtFinal;    
  
    
    private final Connection connection = new ConnectionFactory().getConnection();
    private final Aluguel Aluguel = new Aluguel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Aluguel.setConnection(connection);

    }   

   
}
