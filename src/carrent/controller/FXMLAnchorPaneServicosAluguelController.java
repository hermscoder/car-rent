
package carrent.controller;

import carrent.DAO.Aluguel;
import java.awt.MenuItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import carrent.DAO.Cliente;
import carrent.DAO.TipoVeiculo;
import carrent.DAO.Veiculo;
import carrent.Main;
import static carrent.Main.StringToDate;
import carrent.database.ConnectionFactory;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.sql.Date;
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
import java.time.LocalDate;
import javafx.scene.control.DatePicker;

public class FXMLAnchorPaneServicosAluguelController implements Initializable {

    @FXML
    private TableView<Aluguel> tableViewReservas;
    @FXML
    private TableColumn<Aluguel, Date> tablecolumnAluguelDtRetirada;
    @FXML
    private TableColumn<Aluguel, Date> tablecolumnAluguelDtDevolucao;
    @FXML
    private TableColumn<Aluguel, String> tablecolumnAluguelPlaca;
    @FXML
    private TableColumn<Aluguel, String> tablecolumnAluguelCliente;
    @FXML
    private TextField textFieldAluguelTipoFranquia;
    @FXML
    private DatePicker dtPickertextAluguelDtRetirada;
    @FXML
    private DatePicker dtPickertextAluguelDtDevolucao;
    @FXML
    private TextField textFieldAluguelNumCNH;    
    @FXML
    private DatePicker dtPickertextAluguelDtVencimentoCNH;
    
    @FXML
    private ComboBox comboBoxPlaca;
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
    private Label lbDetalhesCliente;
    
    @FXML
    private List<Aluguel> listAluguel;
    @FXML
    private ObservableList<Aluguel> observableListAluguel;
    @FXML
    private List<Cliente> listClientes = new ArrayList<>(); 
    @FXML
    private ObservableList<Cliente> observableListClientes;
    
    
    private final Connection connection = new ConnectionFactory().getConnection();
    
    private final Veiculo Veiculo = new Veiculo();
    private final Cliente Cliente = new Cliente();
    private final Aluguel Aluguel = new Aluguel();
    private String state; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Aluguel.setConnection(connection);
        Cliente.setConnection(connection);
        Veiculo.setConnection(connection);
        carregarTableViewClientes();
        carregarComboBoxPlaca();
        carregarComboBoxCliente();
        tableViewReservas.getSelectionModel().selectedItemProperty().addListener(
                    (observable,oldValue,newValue) -> selecionarItemTableViewAluguel(newValue));
    }   
    public void carregarTableViewClientes(){
        //O parametro do PropertyValueFactory é o nome da coluna da tabela
        tablecolumnAluguelDtRetirada.setCellValueFactory(new PropertyValueFactory<>("datRet"));
        tablecolumnAluguelDtDevolucao.setCellValueFactory(new PropertyValueFactory<>("datDev"));
        tablecolumnAluguelCliente.setCellValueFactory(new PropertyValueFactory<>("NomeCliente"));
        tablecolumnAluguelPlaca.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
        listAluguel = Aluguel.listar();
        
        observableListAluguel = FXCollections.observableList(listAluguel);
        tableViewReservas.setItems(observableListAluguel);
    }
    
    public void carregarComboBoxPlaca(){
        List<Veiculo> veiculoList = Veiculo.listar();
        ArrayList<String> placaVeiculo = new ArrayList<String>();
        
        for(int i = 0; i<veiculoList.size();i++){
            placaVeiculo.add(veiculoList.get(i).getPlaca());
        }
        comboBoxPlaca.setItems(FXCollections.observableList(placaVeiculo));
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

    public void selecionarItemTableViewAluguel(Aluguel aluguel){
        if (aluguel != null){
            comboBoxPlaca.setValue(aluguel.getPlacaVeiculo());
            comboBoxCliente.setValue(0+Integer.toString(aluguel.getCodCliente()));
//            textFieldAluguelDtRetirada.setText(carrent.Main.FormatDate(aluguel.getDatRet()).toString());
            if(aluguel.getDatRet()!= null){
                dtPickertextAluguelDtRetirada.setValue(LocalDate.parse(aluguel.getDatRet().toString()));
            }
            else{
                dtPickertextAluguelDtRetirada.setValue(null);
            }
//            textFieldAluguelDtDevolucao.setText(aluguel.getDatDev().toString());        
            if(aluguel.getDatDev()!= null){
                dtPickertextAluguelDtDevolucao.setValue(LocalDate.parse(aluguel.getDatDev().toString()));
            }else{
                dtPickertextAluguelDtDevolucao.setValue(null);
            }
          
            textFieldAluguelTipoFranquia.setText(aluguel.getTipoFranquia());
            textFieldAluguelNumCNH.setText(Long.toString(aluguel.getNroCNH()));
            
//            textFieldAluguelDtVencimentoCNH.setText(aluguel.getDatVencCNH().toString());
            if(aluguel.getDatVencCNH()!= null){
                dtPickertextAluguelDtVencimentoCNH.setValue(LocalDate.parse(aluguel.getDatVencCNH().toString()));
            }else{
                dtPickertextAluguelDtVencimentoCNH.setValue(null);
            }            

           
            preencheAluguel();
           
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
            Aluguel.delete(Aluguel);
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
        preencheAluguel();
        
        if(state == "update"){
           Aluguel.update(Aluguel); 
        }
        if(state == "insert"){
           Aluguel.insert(Aluguel); 
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
            comboBoxPlaca.setValue(Aluguel.getPlacaVeiculo());
            comboBoxCliente.setValue(Aluguel.getCodCliente());
//            textFieldAluguelDtRetirada.setText(carrent.Main.FormatDate(Aluguel.getDatRet()).toString());
            dtPickertextAluguelDtRetirada.setValue(LocalDate.parse(Aluguel.getDatRet().toString()));
//            textFieldAluguelDtDevolucao.setText(Aluguel.getDatDev().toString());
            dtPickertextAluguelDtDevolucao.setValue(LocalDate.parse(Aluguel.getDatDev().toString()));
            textFieldAluguelTipoFranquia.setText(Aluguel.getTipoFranquia());
            textFieldAluguelNumCNH.setText(Long.toString(Aluguel.getNroCNH()));
//            textFieldAluguelDtVencimentoCNH.setText(Aluguel.getDatVencCNH().toString());
            dtPickertextAluguelDtVencimentoCNH.setValue(LocalDate.parse(Aluguel.getDatVencCNH().toString()));

        }else{
            comboBoxPlaca.setValue("Selecione uma placa..");
            comboBoxCliente.setValue("Selecione um cliente..");            
//            textFieldAluguelDtRetirada.setText("");
            dtPickertextAluguelDtRetirada.setValue(LocalDate.now());
//            textFieldAluguelDtDevolucao.setText("");
            dtPickertextAluguelDtDevolucao.setValue(LocalDate.now().plusDays(5));
            textFieldAluguelTipoFranquia.setText("");
            textFieldAluguelNumCNH.setText(""); 
//            textFieldAluguelDtVencimentoCNH.setText("");           
            dtPickertextAluguelDtVencimentoCNH.setValue(LocalDate.now());
        }
    }
    
    public void preencheAluguel(){
        Aluguel.setPlacaVeiculo(comboBoxPlaca.getValue().toString());
        Aluguel.setCodCliente(Integer.parseInt(comboBoxCliente.getValue().toString()));
       //Aluguel.setDatRet(StringToDate(textFieldAluguelDtRetirada.getText()));
       if(dtPickertextAluguelDtRetirada.getValue() != null ){
           Aluguel.setDatRet(StringToDate(dtPickertextAluguelDtRetirada.getValue().toString()));
       }else{
           Aluguel.setDatRet(null);
       }
        
//        Aluguel.setDatDev(StringToDate(textFieldAluguelDtDevolucao.getText()));
       if(dtPickertextAluguelDtDevolucao.getValue() != null ){
           Aluguel.setDatDev(StringToDate(dtPickertextAluguelDtDevolucao.getValue().toString()));
       }else{
           Aluguel.setDatDev(null);
       }
        Aluguel.setTipoFranquia(textFieldAluguelTipoFranquia.getText());  
        Aluguel.setNroCNH(Long.parseLong(textFieldAluguelNumCNH.getText()));
       if(dtPickertextAluguelDtVencimentoCNH.getValue() != null ){
           Aluguel.setDatVencCNH(StringToDate(dtPickertextAluguelDtVencimentoCNH.getValue().toString()));    
       }else{
           Aluguel.setDatVencCNH(null);
       }        
        
        
        
    }
    public void textFieldsEditable(boolean editable){
        if (editable){
            comboBoxPlaca.setDisable(false);
            comboBoxCliente.setDisable(false);
            dtPickertextAluguelDtRetirada.setDisable(false);
            dtPickertextAluguelDtDevolucao.setDisable(false);
            textFieldAluguelTipoFranquia.setEditable(true);
            textFieldAluguelNumCNH.setEditable(true);
            dtPickertextAluguelDtVencimentoCNH.setDisable(false);
            
        }
        else{
            comboBoxPlaca.setDisable(true);
            comboBoxCliente.setDisable(true);
//            textFieldAluguelDtRetirada.setEditable(false);
            dtPickertextAluguelDtRetirada.setDisable(true);     
//            textFieldAluguelDtDevolucao.setEditable(false);
            dtPickertextAluguelDtDevolucao.setDisable(true); 
            textFieldAluguelTipoFranquia.setEditable(false);
            textFieldAluguelNumCNH.setEditable(false);
            dtPickertextAluguelDtVencimentoCNH.setDisable(true);
        }
    }
}
