package sample;


import java.beans.EventHandler;
import java.io.IOException;
import java.lang.reflect.*;
import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCarController implements Initializable {

    private static  Connection connection;
    private static  ConnectionClass connectionClass;
    private static  Statement statement;

    @FXML
    public ComboBox<String> modelChoice;
    public ComboBox<Integer> yearChoice;
    public ComboBox<String> gasolineChoice;
    public ComboBox<Integer> wheelSizeChoice;
    public TextField engineModel;
    public TextField cylindersNum;
    public TextField doorsNum;
    public TextField torque;
    public TextField weight;
    public TextField horsePowers;
    public Button addToDB_btn;
    public Button goBack_btn;
    public TextField colorBox;


    @Override
    //LOAD INFO FROM DB FOR COMBO BOXES
    public void initialize(URL location, ResourceBundle resources) {

        try {
            getCarModelsFromDB();
            getCarYearsFromDB();
            getGasolineTypeFromDB();
            getWheelSizeFromDB();

            //Go back to showroom from Add car section
            goBackButton();





        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getCarModelsFromDB() throws SQLException {
        // get models from DB for comboBox

        connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();

        String sql = "SELECT model FROM models";
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<String> arr = new ArrayList<>();
        while (resultSet.next()) {
            String currModel = resultSet.getString("model");
            arr.add(currModel);

        }
        ObservableList<String> mList = FXCollections.observableArrayList(arr);
        modelChoice.setItems(mList);
    }

    private void getCarYearsFromDB() throws SQLException {
        connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();

        String sql = "SELECT year FROM caryears";
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Integer> arr = new ArrayList<>();
        while (resultSet.next()) {
            Integer currYear = resultSet.getInt("year");
            arr.add(currYear);

        }
        ObservableList<Integer> mList = FXCollections.observableArrayList(arr);
        yearChoice.setItems(mList);

    }

    private void getGasolineTypeFromDB() throws SQLException {
     connectionClass = new ConnectionClass();
     connection = connectionClass.getConnection();

     String sql = "SELECT type FROM gasolinetype";
     statement = connection.createStatement();

     ResultSet resultSet = statement.executeQuery(sql);
     ArrayList<String> arr = new ArrayList<>();
     while (resultSet.next()){
         String currType = resultSet.getString("type");
         arr.add(currType);
     }
     ObservableList<String> typeList = FXCollections.observableArrayList(arr);
     gasolineChoice.setItems(typeList);
    }

    private void getWheelSizeFromDB() throws SQLException {
        connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();

        String sql = "SELECT size FROM wheelsize";
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Integer> arr = new ArrayList<>();
        while (resultSet.next()){
            Integer currType = resultSet.getInt("size");
            arr.add(currType);
        }
        ObservableList<Integer> typeList = FXCollections.observableArrayList(arr);
        wheelSizeChoice.setItems(typeList);
    }

    @FXML
    public void buttonAddToDB(){

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "INSERT INTO cars(`model`, `year`, `engineModel`," +
                " `gasolineType`, `cylindersNum`, `doorsNumber`, " +
                "`torque`, `weight`,`wheelSize`, `horsePowers`, `Color`) " +
                "VALUES ('" + modelChoice.getValue()+ "'," +
                "" + yearChoice.getValue() + "," +
                "'" + engineModel.getText() + "'," +
                "'" + gasolineChoice.getValue() + "'," +
                "" + cylindersNum.getText() + "," +
                "" + doorsNum.getText() + "," +
                "" + torque.getText() + "," +
                "" + weight.getText() + "," +
                "" + wheelSizeChoice.getValue() + "," +
                "" + horsePowers.getText() + "," +
                "'" + colorBox.getText() + "')";


        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sql);
            statement.close();

            //CLEAR ALL FIELDS
           clearAllFields();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private void clearAllFields(){
        modelChoice.getSelectionModel().clearSelection();
        yearChoice.getSelectionModel().clearSelection();
        engineModel.clear();
        gasolineChoice.getSelectionModel().clearSelection();
        cylindersNum.clear();
        doorsNum.clear();
        torque.clear();
        weight.clear();
        wheelSizeChoice.getSelectionModel().clearSelection();
        horsePowers.clear();
        colorBox.clear();
    }


    public void goBackButton() throws IOException {
        goBack_btn.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Parent tableViewParent = null;
                try {
                    tableViewParent = FXMLLoader.load(getClass().getResource("showroom.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene tableView = new Scene(tableViewParent);

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableView);
                window.show();

            }
        });
    }
}


