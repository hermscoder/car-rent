/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent.controller;


import carrent.DAO.TipoVeiculo;
import carrent.database.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Emerson
 */
public class FXMLAnchorPaneCadastrosTipoVeiculosController implements Initializable {
    
    @FXML
    private TableView<TipoVeiculo> tableViewTipoVeiculo;
    @FXML
    private TableColumn<TipoVeiculo, String> tablecolumnTipoVeiculoCodtv;
    @FXML
    private TableColumn<TipoVeiculo, String> tablecolumnTipoVeiculoTamanho;
    @FXML
    private TableColumn<TipoVeiculo, String> tablecolumnTipoVeiculoValorDiario;
    
    
    @FXML
    private ComboBox comboBoxTipoVeiculoTamanho;
    
    @FXML
    private TextField textFieldTipoVeiculoCodtv;
    @FXML
    private TextField textFieldTipoVeiculoNumPassageiros;
    @FXML
    private TextField textFieldTipoVeiculoNumPortas;
    @FXML
    private TextField textFieldTipoVeiculoValorDiarioLocacao;
    @FXML
    private TextField textFieldTipoVeiculoValorKMRodado;
    @FXML
    private TextField textFieldTipoVeiculoValorFranqNormal;
    @FXML
    private TextField textFieldTipoVeiculoValorFranqReduzida;
    
    @FXML
    private CheckBox chkTipoVeiculoArCondicionado;
    
    @FXML
    private Group groupNav;
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
    private List<TipoVeiculo> listTipoVeiculo;
    @FXML
    private ObservableList<TipoVeiculo> observableListTipoVeiculo;
    
    private final Connection connection = new ConnectionFactory().getConnection();
    private final TipoVeiculo TipoVeiculo = new TipoVeiculo();
    private String state;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        verificaPermissoes();
         //Carregando itens do combobox           
         comboBoxTipoVeiculoTamanho.setItems(FXCollections.observableArrayList(
                                                            "Pequeno",
                                                            "Médio",
                                                            "Grande"
                                                            ));

        TipoVeiculo.setConnection(connection);
        carregarTableViewTipoVeiculo();
        
        
        //para a troca de registros, dispare a funçãoselecionarItemTableViewTipoVeiculo(newvalue) 
        tableViewTipoVeiculo.getSelectionModel().selectedItemProperty().addListener(
                    (observable,oldValue,newValue) -> selecionarItemTableViewTipoVeiculo(newValue));
    }    
    
    public void carregarTableViewTipoVeiculo(){
        //O parametro do PropertyValueFactory é o nome da coluna da tabela
        tablecolumnTipoVeiculoCodtv.setCellValueFactory(new PropertyValueFactory<>("CodTV"));
        tablecolumnTipoVeiculoTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        tablecolumnTipoVeiculoValorDiario.setCellValueFactory(new PropertyValueFactory<>("valorDiarioLocacao"));
        listTipoVeiculo = TipoVeiculo.listar();
        
        observableListTipoVeiculo = FXCollections.observableList(listTipoVeiculo);
        tableViewTipoVeiculo.setItems(observableListTipoVeiculo);
    }
        
    public void selecionarItemTableViewTipoVeiculo(TipoVeiculo tpVeiculo){
        if (tpVeiculo != null){
            textFieldTipoVeiculoCodtv.setText(Integer.toString(tpVeiculo.getCodTV()));
            comboBoxTipoVeiculoTamanho.setValue(tpVeiculo.getTamanho());
            textFieldTipoVeiculoNumPassageiros.setText(Integer.toString(0+tpVeiculo.getNumPassageiros()));
            textFieldTipoVeiculoNumPortas.setText(Integer.toString(0+tpVeiculo.getNumPortas()));
            textFieldTipoVeiculoValorDiarioLocacao.setText(Double.toString(0+tpVeiculo.getValorDiarioLocacao()));
            textFieldTipoVeiculoValorKMRodado.setText(Double.toString(0+tpVeiculo.getValorKMROdado()));
            textFieldTipoVeiculoValorFranqNormal.setText(Double.toString(0+tpVeiculo.getValorFranqNormal()));
            textFieldTipoVeiculoValorFranqReduzida.setText(Double.toString(0+tpVeiculo.getValorFranqReduzida()));
            chkTipoVeiculoArCondicionado.setSelected(tpVeiculo.getArCondicionado());
            
            preencheTipoVeiculo();
           
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atenção!");
        alert.setContentText("Deseja deletar o registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            TipoVeiculo.delete(TipoVeiculo);
            preencheTextField(false);//esvazia os campos
            carregarTableViewTipoVeiculo();//refresh da table view
        }    
    }
    public void btnPostClicked(){
        
        btnInserir.setDisable(false);
        btnRemover.setDisable(false);
        btnAlterar.setDisable(false);
        
        btnPost.setVisible(false);
        btnCancel.setVisible(false); 
        
        textFieldsEditable(false);   
        preencheTipoVeiculo();
        
        if(state.equals("update")){
           TipoVeiculo.update(TipoVeiculo); 
        }
        if(state.equals("insert")){
           TipoVeiculo.insert(TipoVeiculo); 
        }
        
        //refresh da tableview
        carregarTableViewTipoVeiculo();
        //preeche com as informações atualizadas
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
            textFieldTipoVeiculoCodtv.setText(Integer.toString(TipoVeiculo.getCodTV()));
            
            comboBoxTipoVeiculoTamanho.setPromptText(TipoVeiculo.getTamanho());
            //textFieldTipoVeiculoTamanho.setText(TipoVeiculo.getTamanho());
            textFieldTipoVeiculoNumPassageiros.setText(Integer.toString(TipoVeiculo.getNumPassageiros()));
            textFieldTipoVeiculoNumPortas.setText(Integer.toString(TipoVeiculo.getNumPortas()));
            textFieldTipoVeiculoValorDiarioLocacao.setText(Double.toString(TipoVeiculo.getValorDiarioLocacao()));
            textFieldTipoVeiculoValorKMRodado.setText(Double.toString(TipoVeiculo.getValorKMROdado()));
            textFieldTipoVeiculoValorFranqNormal.setText(Double.toString(TipoVeiculo.getValorFranqNormal()));
            textFieldTipoVeiculoValorFranqReduzida.setText(Double.toString(TipoVeiculo.getValorFranqReduzida()));
            chkTipoVeiculoArCondicionado.setSelected(TipoVeiculo.getArCondicionado());
             
        }else{
            textFieldTipoVeiculoCodtv.setText("");
            
            comboBoxTipoVeiculoTamanho.setPromptText("Selecione um tamanho");
            //textFieldTipoVeiculoTamanho.setText("");
            textFieldTipoVeiculoNumPassageiros.setText("");
            textFieldTipoVeiculoNumPortas.setText("");
            textFieldTipoVeiculoValorDiarioLocacao.setText("");
            textFieldTipoVeiculoValorKMRodado.setText("");
            textFieldTipoVeiculoValorFranqNormal.setText("");
            textFieldTipoVeiculoValorFranqReduzida.setText("");
            chkTipoVeiculoArCondicionado.setSelected(false);
        }
    }
    
    public void preencheTipoVeiculo(){
        if(!textFieldTipoVeiculoCodtv.getText().trim().equals("")){
            TipoVeiculo.setCodTV(Integer.parseInt(textFieldTipoVeiculoCodtv.getText()));
        }        
        
        TipoVeiculo.setTamanho(comboBoxTipoVeiculoTamanho.getValue().toString());
        TipoVeiculo.setNumPassageiros(Integer.parseInt(0+textFieldTipoVeiculoNumPassageiros.getText()));
        TipoVeiculo.setNumPortas(Integer.parseInt(0+textFieldTipoVeiculoNumPortas.getText()));
        TipoVeiculo.setValorDiarioLocacao(Double.parseDouble(0+textFieldTipoVeiculoValorDiarioLocacao.getText()));
        TipoVeiculo.setValorKMROdado(Double.parseDouble(0+textFieldTipoVeiculoValorKMRodado.getText()));
        TipoVeiculo.setValorFranqNormal(Double.parseDouble(0+textFieldTipoVeiculoValorFranqNormal.getText()));         
        TipoVeiculo.setValorFranqReduzida(Double.parseDouble(0+textFieldTipoVeiculoValorFranqReduzida.getText()));    
        TipoVeiculo.setArCondicionado(chkTipoVeiculoArCondicionado.isSelected());    
    }
    public void textFieldsEditable(boolean editable){
        if (editable){
            comboBoxTipoVeiculoTamanho.setDisable(false);
            textFieldTipoVeiculoNumPassageiros.setEditable(true);
            textFieldTipoVeiculoNumPortas.setEditable(true);
            textFieldTipoVeiculoValorDiarioLocacao.setEditable(true);
            textFieldTipoVeiculoValorKMRodado.setEditable(true);
            textFieldTipoVeiculoValorFranqNormal.setEditable(true);
            textFieldTipoVeiculoValorFranqReduzida.setEditable(true);
            chkTipoVeiculoArCondicionado.setDisable(false);
        }
        else{
            comboBoxTipoVeiculoTamanho.setDisable(true);
            textFieldTipoVeiculoNumPassageiros.setEditable(false);
            textFieldTipoVeiculoNumPortas.setEditable(false);
            textFieldTipoVeiculoValorDiarioLocacao.setEditable(false);
            textFieldTipoVeiculoValorKMRodado.setEditable(false);
            textFieldTipoVeiculoValorFranqNormal.setEditable(false); 
            textFieldTipoVeiculoValorFranqReduzida.setEditable(false);
            chkTipoVeiculoArCondicionado.setDisable(true);
        }
    }
    public void verificaPermissoes(){
        if(!FXMLMainController.UsuarioAtual.getehgerente()){
            groupNav.setVisible(false);
        }
    }   
}
