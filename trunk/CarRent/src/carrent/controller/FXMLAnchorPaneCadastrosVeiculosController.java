
package carrent.controller;

import carrent.DAO.TipoVeiculo;
import java.awt.MenuItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import carrent.DAO.Veiculo;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Dialog;

public class FXMLAnchorPaneCadastrosVeiculosController implements Initializable {

    @FXML
    private TableView<Veiculo> tableViewVeiculos;
    @FXML
    private TableColumn<Veiculo, String> tablecolumnVeiculoPlaca;
    @FXML
    private TableColumn<Veiculo, String> tablecolumnVeiculoCor;
    @FXML
    private TableColumn<Veiculo, String> tablecolumnVeiculoTipo;
    
    @FXML
    private TextField textFieldVeiculoPlaca;
    @FXML
    private TextField textFieldVeiculoCor;
    @FXML
    private TextField textFieldVeiculoNumChassi;
    @FXML
    private TextField textFieldVeiculoNumMotor;
    @FXML
    private TextField textFieldVeiculoKmAtual;
    
    @FXML
    private Label lbDetalhesTipoVeiculo;
    //@FXML
    //private TextField textFieldVeiculoTipo;
    
    @FXML
    private ComboBox comboBoxVeiculoTipo;
    
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
    private List<Veiculo> listClientes;
    @FXML
    private ObservableList<Veiculo> observableListClientes;
   
    
    private final Connection connection = new ConnectionFactory().getConnection();
    private final Veiculo Veiculo = new Veiculo();
    private final TipoVeiculo TipoVeiculo = new TipoVeiculo();
    private String state;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Veiculo.setConnection(connection);
        TipoVeiculo.setConnection(connection);
        
        
        carregarTableViewClientes();
        carregarComboBoxTipoVeiculo();
        
        tableViewVeiculos.getSelectionModel().selectedItemProperty().addListener(
                    (observable,oldValue,newValue) -> selecionarItemTableViewClientes(newValue));
    }   
    public void carregarTableViewClientes(){
        //O parametro do PropertyValueFactory é o nome da coluna da tabela
        tablecolumnVeiculoPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tablecolumnVeiculoCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        tablecolumnVeiculoTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        listClientes = Veiculo.listar();
        
        observableListClientes = FXCollections.observableList(listClientes);
        tableViewVeiculos.setItems(observableListClientes);
    }
    
    public void carregarComboBoxTipoVeiculo(){
        List<TipoVeiculo> tpvList = TipoVeiculo.listar();
        ArrayList<String> tipoVeiculo = new ArrayList<String>();
        
        for(int i = 0; i<tpvList.size();i++){
            tipoVeiculo.add(Integer.toString(tpvList.get(i).getCodTV()));
        }
        comboBoxVeiculoTipo.setItems(FXCollections.observableList(tipoVeiculo));
    }
    
    public void carregaDetalhesTipo(){
        try{
            int codtv = Integer.parseInt(comboBoxVeiculoTipo.getValue().toString());
            if (Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()) > 0){
                TipoVeiculo tpv = new TipoVeiculo();
                tpv.setConnection(connection);
                tpv.setCodTV(Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()));
                tpv = tpv.select(tpv);
                lbDetalhesTipoVeiculo.setText(tpv.getCodTV() + " - Veículo " + tpv.getTamanho() + ", capacidade para " + tpv.getNumPassageiros() + " passageiros.");    
            }
            else{
                preencheTextField(false);
                lbDetalhesTipoVeiculo.setText("");
            }            
        }catch(Exception e){
            System.out.println("Exception: codTV é string, provavelmente 'Selecione o tipo..'");
        }


    }
    public void selecionarItemTableViewClientes(Veiculo veiculo){
        if (veiculo != null){
            textFieldVeiculoPlaca.setText(veiculo.getPlaca());
            textFieldVeiculoCor.setText(veiculo.getCor());
            textFieldVeiculoNumChassi.setText(veiculo.getNroChassi());
            textFieldVeiculoNumMotor.setText(veiculo.getNroMotor());
            textFieldVeiculoKmAtual.setText(0+Double.toString(veiculo.getKm_atual()));
            comboBoxVeiculoTipo.setValue(0+Integer.toString(veiculo.getTipoVCod()));
            
            preencheVeiculo();
           
        }else{
            preencheTextField(false);
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
    }
    
    public void btnRemoverClicked(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Atenção!");
        alert.setContentText("Deseja deletar o registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Veiculo.delete(Veiculo);
            preencheTextField(false);
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
        preencheVeiculo();
        
        if(state == "update"){
           Veiculo.update(Veiculo); 
        }
        if(state == "insert"){
           Veiculo.insert(Veiculo); 
        }
        
        carregarTableViewClientes();
        
        preencheTextField(true); 
       
    }
    public void btnCancelClicked(){
        
        btnInserir.setDisable(false);
        btnRemover.setDisable(false);
        btnAlterar.setDisable(false);
        
        btnPost.setVisible(false);
        btnCancel.setVisible(false); 
        
        textFieldsEditable(false);   
         
        preencheTextField(true);       
    }    
    
    public void preencheTextField(boolean preencherFields){
        
        if(preencherFields){
            textFieldVeiculoPlaca.setText(Veiculo.getPlaca());
            textFieldVeiculoCor.setText(Veiculo.getCor());
            textFieldVeiculoNumChassi.setText(Veiculo.getNroChassi());
            textFieldVeiculoNumMotor.setText(Veiculo.getNroMotor());
            textFieldVeiculoKmAtual.setText(0+Double.toString(Veiculo.getKm_atual()));
            comboBoxVeiculoTipo.setValue(Integer.toString(Veiculo.getTipoVCod()));
        }else{
            textFieldVeiculoPlaca.setText("");
            textFieldVeiculoCor.setText("");
            textFieldVeiculoNumChassi.setText("");
            textFieldVeiculoNumMotor.setText("");
            textFieldVeiculoKmAtual.setText("");
            comboBoxVeiculoTipo.setValue("Selecione um tipo...");
            

        }
    }
    
    public void preencheVeiculo(){
        if(!textFieldVeiculoPlaca.getText().trim().equals("")){
            Veiculo.setPlaca(textFieldVeiculoPlaca.getText());
        }        
        Veiculo.setCor(textFieldVeiculoCor.getText());
        Veiculo.setNroChassi(textFieldVeiculoNumChassi.getText());
        Veiculo.setNroMotor(textFieldVeiculoNumMotor.getText());
        Veiculo.setKm_atual(0.0+Double.parseDouble(textFieldVeiculoKmAtual.getText()));
        Veiculo.setTipoVCod(0+Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()));        
    }
    public void textFieldsEditable(boolean editable){
        if (editable){
            textFieldVeiculoPlaca.setEditable(true);
            textFieldVeiculoCor.setEditable(true);
            textFieldVeiculoNumChassi.setEditable(true);
            textFieldVeiculoNumMotor.setEditable(true);
            textFieldVeiculoKmAtual.setEditable(true);
            comboBoxVeiculoTipo.setDisable(false);
        }
        else{
            textFieldVeiculoPlaca.setEditable(false);
            textFieldVeiculoCor.setEditable(false);
            textFieldVeiculoNumChassi.setEditable(false);
            textFieldVeiculoNumMotor.setEditable(false);
            textFieldVeiculoKmAtual.setEditable(false);
            comboBoxVeiculoTipo.setDisable(true);            
        }
    }
}
