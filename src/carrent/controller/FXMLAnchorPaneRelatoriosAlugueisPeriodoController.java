
package carrent.controller;


import carrent.DAO.Veiculo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import static carrent.Main.formataData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import carrent.database.ConnectionFactory;
import java.io.File;
import java.io.IOException;

public class FXMLAnchorPaneRelatoriosAlugueisPeriodoController implements Initializable {


    @FXML
    private DatePicker dtPickertDtInicial;
    @FXML
    private DatePicker dtPickerDtFinal;    
    @FXML
    private Button btnGerarRelatorio;
    
    private Connection connection = new ConnectionFactory().getConnection();;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void btnGerarRelatorioClicked(){
        Document docPDF = new Document();
        try {
            //cria uma instancia do documento e da o nome do pdf
            PdfWriter.getInstance(docPDF, new FileOutputStream("C:\\RelatorioAlugueisPeriodo.pdf"));
            
            docPDF.open();
            
            //setando tamanho da pagina
            docPDF.setPageSize(PageSize.A4);
            
            //adicionando o paragrafo titulo
            Paragraph tittle = new Paragraph("Relatório de Aluguéis");
            tittle.setAlignment(1);
            docPDF.add(tittle);
            tittle = new Paragraph("Período: " + formataData(dtPickertDtInicial.getValue().toString()) + " a " + formataData(dtPickerDtFinal.getValue().toString()));
            tittle.setAlignment(1);
            docPDF.add(tittle);
            
            //adicioanndo o paraggrafo cabeçalho
            Paragraph cabecalho = new Paragraph("   Data       Placa       Cliente");
            docPDF.add(cabecalho);
            
            //adicionando o conteudo
            Paragraph conteudo = new Paragraph();
            
            conteudo = getData();
           
            docPDF.add(conteudo);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatoriosAlugueisPeriodoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatoriosAlugueisPeriodoController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            docPDF.close();
            try {
                java.awt.Desktop.getDesktop().open(new File("C:\\RelatorioAlugueisPeriodo.pdf") );
            } catch (IOException ex) {
                Logger.getLogger(FXMLAnchorPaneRelatoriosAlugueisPeriodoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Paragraph getData(){
        Paragraph data = new Paragraph();
        
        String sql = "SELECT * FROM ALUGUEL";
        String dados="";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                dados += formataData(resultado.getDate("dataretirada").toString())
                       + "     " + resultado.getString("placa") + "\n";
            }         
            data.add(dados);
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }        
        
        return data;
    }

}
