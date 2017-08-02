
package carrent.controller;

import java.awt.MenuItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import carrent.DAO.Reserva;
import carrent.DAO.Cliente;
import carrent.DAO.TipoVeiculo;
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

public class FXMLAnchorPaneServicosReservasController implements Initializable {

    @FXML
    private TableView<Reserva> tableViewReservas;
    @FXML
    private TableColumn<Reserva, String> tablecolumnReservaDtRetirada;
    @FXML
    private TableColumn<Reserva, String> tablecolumnReservaDtDevolucao;
    @FXML
    private TableColumn<Reserva, String> tablecolumnReservaCodc;
    
    @FXML
    private TextField textFieldReservaCodigo;
    @FXML
    private TextField textFieldReservaDtRetirada;
    @FXML
    private TextField textFieldReservaDtDevolucao;
    @FXML
    private TextField textFieldRetiradaLocal;
    
    @FXML
    private ComboBox comboBoxVeiculoTipo;
    @FXML
    private ComboBox comboBoxCliente;
    
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
    private List<Reserva> listReservas;
    @FXML
    private ObservableList<Reserva> observableListReservas;
    
    private final Connection connection = new ConnectionFactory().getConnection();
    private final Reserva Reserva = new Reserva();
    private final TipoVeiculo TipoVeiculo = new TipoVeiculo();
    private final Cliente Cliente = new Cliente();
    private String state;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reserva.setConnection(connection);
        TipoVeiculo.setConnection(connection);
        Cliente.setConnection(connection);
        carregarTableViewClientes();
        carregarComboBoxTipoVeiculo();
        carregarComboBoxCliente();
        tableViewReservas.getSelectionModel().selectedItemProperty().addListener(
                    (observable,oldValue,newValue) -> selecionarItemTableViewReservas(newValue));
    }   
    public void carregarTableViewClientes(){
        //O parametro do PropertyValueFactory é o nome da coluna da tabela
        tablecolumnReservaDtRetirada.setCellValueFactory(new PropertyValueFactory<>("dataprevistaretirada"));
        tablecolumnReservaDtDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataprevistadevolucao"));
        tablecolumnReservaCodc.setCellValueFactory(new PropertyValueFactory<>("codc"));
        
        listReservas = Reserva.listar();
        
        observableListReservas = FXCollections.observableList(listReservas);
        tableViewReservas.setItems(observableListReservas);
    }
    
    public void carregarComboBoxTipoVeiculo(){
        List<TipoVeiculo> tpvList = TipoVeiculo.listar();
        ArrayList<String> tipoVeiculo = new ArrayList<String>();
        
        for(int i = 0; i<tpvList.size();i++){
            tipoVeiculo.add(Integer.toString(tpvList.get(i).getCodTV()));
        }
        comboBoxVeiculoTipo.setItems(FXCollections.observableList(tipoVeiculo));
    }
    public void carregarComboBoxCliente(){
        List<Cliente> clienteList = Cliente.listar();
        ArrayList<String> cl = new ArrayList<String>();
        for(int i = 0; i<clienteList.size();i++){
            cl.add(Integer.toString(clienteList.get(i).getCodCliente()));
        }
        comboBoxCliente.setItems(FXCollections.observableList((List)cl));
    }
    public void selecionarItemTableViewReservas(Reserva reserva){
        if (reserva != null){
            textFieldReservaCodigo.setText(Integer.toString(reserva.getCodR()));
            textFieldReservaDtRetirada.setText(reserva.getDatPrevRet());
            textFieldReservaDtDevolucao.setText(reserva.getDatPrevDev());
            textFieldRetiradaLocal.setText(reserva.getLocal());
            comboBoxVeiculoTipo.setValue(0+Integer.toString(reserva.getTipoVeicCod()));
            comboBoxCliente.setValue(0+Integer.toString(reserva.getClienteCod()));
//            textFieldClienteEndereco.setText(reserva.getendereco());
//            textFieldClienteCelular.setText(reserva.getTelCelular());
//            textFieldClienteTelefoneFixo.setText(reserva.getTelFixo());
            
            preencheReserva();
           
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
            Reserva.delete(Reserva);
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
        preencheReserva();
        
        if(state == "update"){
           Reserva.update(Reserva); 
        }
        if(state == "insert"){
           Reserva.insert(Reserva); 
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
            textFieldReservaCodigo.setText(Integer.toString(Reserva.getCodR()));
            textFieldReservaDtRetirada.setText(Reserva.getDatPrevRet());
            textFieldReservaDtDevolucao.setText(Reserva.getDatPrevDev());
            textFieldRetiradaLocal.setText(Reserva.getLocal());
            comboBoxVeiculoTipo.setValue(Integer.toString(Reserva.getTipoVeicCod()));
            comboBoxCliente.setValue(Integer.toString(Reserva.getClienteCod()));
        }else{
            textFieldReservaCodigo.setText("");
            textFieldReservaDtRetirada.setText("");
            textFieldReservaDtDevolucao.setText("");
            textFieldRetiradaLocal.setText("");           
            comboBoxVeiculoTipo.setValue("Selecione um tipo...");
            comboBoxCliente.setValue("Selecione um tipo...");
        }
    }
    
    public void preencheReserva(){
        if(!textFieldReservaCodigo.getText().trim().equals("")){
            Reserva.setCodR(Integer.parseInt(textFieldReservaCodigo.getText()));
        }        
        Reserva.setDatPrevRet(textFieldReservaDtRetirada.getText());
        Reserva.setDatPrevDev(textFieldReservaDtDevolucao.getText());
        Reserva.setLocal(textFieldRetiradaLocal.getText());    
        Reserva.setTipoVeicCod(0+Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()));   
        Reserva.setClienteCod(0+Integer.parseInt(comboBoxCliente.getValue().toString()));
    }
    public void textFieldsEditable(boolean editable){
        if (editable){
            textFieldReservaCodigo.setEditable(true);
            textFieldReservaDtRetirada.setEditable(true);
            textFieldReservaDtDevolucao.setEditable(true);
            textFieldRetiradaLocal.setEditable(true);
            comboBoxVeiculoTipo.setDisable(false);
            comboBoxCliente.setDisable(false);
        }
        else{
            textFieldReservaCodigo.setEditable(false);
            textFieldReservaDtRetirada.setEditable(false);
            textFieldReservaDtDevolucao.setEditable(false);
            textFieldRetiradaLocal.setEditable(false); 
            comboBoxVeiculoTipo.setDisable(true); 
            comboBoxCliente.setDisable(false);
        }
    }
}
