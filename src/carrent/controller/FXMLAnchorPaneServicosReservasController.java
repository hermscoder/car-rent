
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
import javafx.scene.control.SingleSelectionModel;
import carrent.Main;
import static carrent.Main.StringToDate;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;

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
//    @FXML
//    private TextField textFieldReservaDtRetirada;
//    @FXML
//    private TextField textFieldReservaDtDevolucao;
    @FXML
    private DatePicker dtPickertextReservaDtRetirada;
    @FXML
    private DatePicker dtPickertextReservaDtDevolucao;    
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
    private Label lbDetalhesTipoVeiculo;
    
    @FXML
    private Label lbDetalhesCliente;
    
    @FXML
    private List<Reserva> listReservas;
    @FXML
    private ObservableList<Reserva> observableListReservas;
    @FXML
    private List<Cliente> listClientes = new ArrayList<>(); 
    @FXML
    private ObservableList<Cliente> observableListClientes;
    
    
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
        tablecolumnReservaDtRetirada.setCellValueFactory(new PropertyValueFactory<>("datPrevRet"));
        tablecolumnReservaDtDevolucao.setCellValueFactory(new PropertyValueFactory<>("datPrevDev"));
        tablecolumnReservaCodc.setCellValueFactory(new PropertyValueFactory<>("NomeCliente"));
        
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

//        listClientes = Cliente.listar();;
//        observableListClientes = FXCollections.observableArrayList(listClientes);
//        comboBoxCliente.setItems(observableListClientes);       
        
    }
    public void carregaDetalhesCliente(){
        try{
            int codtv = Integer.parseInt(comboBoxCliente.getValue().toString());
            if (Integer.parseInt(comboBoxCliente.getValue().toString()) > 0){
                Cliente cli = new Cliente();
                cli.setConnection(connection);
                cli.setCodCliente(Integer.parseInt(comboBoxCliente.getValue().toString()));
                cli = cli.select(cli);
                lbDetalhesCliente.setText(cli.getCodCliente() + " - " + cli.getNome());    
            }
            else{
                preencheTextField(false);
                lbDetalhesCliente.setText("");
            }            
        }catch(Exception e){
            System.out.println("Exception: codC é string, provavelmente 'Selecione o tipo..'");
        }


    }
    public void carregaDetalhesTipo(){
        try{
            int codtv = Integer.parseInt(comboBoxVeiculoTipo.getValue().toString());
            if (Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()) > 0){
                TipoVeiculo tpv = new TipoVeiculo();
                tpv.setConnection(connection);
                tpv.setCodTV(Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()));
                tpv = tpv.select(tpv);
                lbDetalhesTipoVeiculo.setText(tpv.getCodTV() + " - Veículo " + tpv.getTamanho() + ", para " + tpv.getNumPassageiros() + " pessoas.");    
            }
            else{
                preencheTextField(false);
                lbDetalhesTipoVeiculo.setText("");
            }            
        }catch(Exception e){
            System.out.println("Exception: codTV é string, provavelmente 'Selecione o tipo..'");
        }


    }
    public void selecionarItemTableViewReservas(Reserva reserva){
        if (reserva != null){
            textFieldReservaCodigo.setText(Integer.toString(reserva.getCodR()));
//            textFieldReservaDtRetirada.setText(reserva.getDatPrevRet());
//            textFieldReservaDtDevolucao.setText(reserva.getDatPrevDev());
            if(reserva.getDatPrevRet()!= null){
                dtPickertextReservaDtRetirada.setValue(LocalDate.parse(reserva.getDatPrevRet().toString()));
            }
            if(reserva.getDatPrevDev()!= null){
                dtPickertextReservaDtDevolucao.setValue(LocalDate.parse(reserva.getDatPrevDev().toString()));
            }            
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
//            textFieldReservaDtRetirada.setText(Reserva.getDatPrevRet());
//            textFieldReservaDtDevolucao.setText(Reserva.getDatPrevDev());
            dtPickertextReservaDtRetirada.setValue(LocalDate.parse(Reserva.getDatPrevRet().toString()));
            dtPickertextReservaDtDevolucao.setValue(LocalDate.parse(Reserva.getDatPrevDev().toString()));
            textFieldRetiradaLocal.setText(Reserva.getLocal());
            comboBoxVeiculoTipo.setValue(Integer.toString(Reserva.getTipoVeicCod()));
            comboBoxCliente.setValue(Integer.toString(Reserva.getClienteCod()));
            comboBoxCliente.setValue(Reserva.getClienteCod());
        }else{
            textFieldReservaCodigo.setText("");
//            textFieldReservaDtRetirada.setText("");
//            textFieldReservaDtDevolucao.setText("");
            dtPickertextReservaDtRetirada.setValue(LocalDate.now());
            dtPickertextReservaDtDevolucao.setValue(LocalDate.now().plusDays(5));          
            textFieldRetiradaLocal.setText("");           
            comboBoxVeiculoTipo.setValue("Selecione um tipo...");
            comboBoxCliente.setValue("Selecione um cliente...");
        }
    }
    
    public void preencheReserva(){
        if(!textFieldReservaCodigo.getText().trim().equals("")){
            Reserva.setCodR(Integer.parseInt(textFieldReservaCodigo.getText()));
        }        
//        Reserva.setDatPrevRet(textFieldReservaDtRetirada.getText());
//        Reserva.setDatPrevDev(textFieldReservaDtDevolucao.getText());
        Reserva.setDatPrevRet(StringToDate(dtPickertextReservaDtRetirada.getValue().toString()));
        Reserva.setDatPrevDev(StringToDate(dtPickertextReservaDtDevolucao.getValue().toString()));
        
        Reserva.setLocal(textFieldRetiradaLocal.getText());    
        Reserva.setTipoVeicCod(0+Integer.parseInt(comboBoxVeiculoTipo.getValue().toString()));   
        Reserva.setClienteCod(0+(Integer.parseInt(comboBoxCliente.getValue().toString())));
        
    }
    public void textFieldsEditable(boolean editable){
        if (editable){

//            textFieldReservaDtRetirada.setEditable(true);
//            textFieldReservaDtDevolucao.setEditable(true);
            dtPickertextReservaDtRetirada.setDisable(false);
            dtPickertextReservaDtDevolucao.setDisable(false);
            textFieldRetiradaLocal.setEditable(true);
            comboBoxVeiculoTipo.setDisable(false);
            comboBoxCliente.setDisable(false);
        }
        else{

//            textFieldReservaDtRetirada.setEditable(false);
//            textFieldReservaDtDevolucao.setEditable(false);
            dtPickertextReservaDtRetirada.setDisable(true);
            dtPickertextReservaDtDevolucao.setDisable(true);            
            textFieldRetiradaLocal.setEditable(false); 
            comboBoxVeiculoTipo.setDisable(true); 
            comboBoxCliente.setDisable(true);
        }
    }
}
