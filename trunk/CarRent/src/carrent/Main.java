/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrent;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Emerson
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLMain.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Car Rent");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static java.sql.Date StringToDate(String s){
        java.sql.Date dte=null;
        if (s.isEmpty()){
            return null;
        }
        try{
            
            String str = s;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dt = formatter.parse(str);
            dte=new java.sql.Date(dt.getTime());
        }catch(Exception e){
            e.printStackTrace();	
        }	

        return dte;
    }
    
    public static java.sql.Date FormatDate(Date date){

        if(date == null){
            return null;
        }
        SimpleDateFormat formatdata = new SimpleDateFormat("dd/MM/yyyy");

        return StringToDate(formatdata.format(date));
        
    }
    public static String formataData(String data){
        return data.substring(8, 10) + "/" + data.substring(5, 7) + "/" + data.substring(0, 4);
    }

}
