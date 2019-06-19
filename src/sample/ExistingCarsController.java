package sample;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ExistingCarsController implements Initializable {

    private static ConnectionClass connectionClass = new ConnectionClass();
    private static Connection connection = connectionClass.getConnection();

    public TableView<ModelTable> table;

    public Button deleteCar;
    public Button goBackFromTable;

    @FXML
    private TableColumn<ModelTable,String> col_model = new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_year= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,String> col_engine= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,String> col_gasoline= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_cylinders= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_doors= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_torque= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_weight= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_wheelSize= new TableColumn<>();
    @FXML
    private TableColumn<ModelTable,Integer> col_hp= new TableColumn<>();
    @FXML
    public TableColumn<ModelTable,String> col_color = new TableColumn<>();

    private ObservableList<ModelTable> obList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();

        //Populating App table from DB table
        String query = "SELECT * FROM cars";
        try {
            ResultSet rs = connection.createStatement().executeQuery(query);
            List<Button> buttons = new ArrayList<>() ;


            while (rs.next()){

                obList.add(new ModelTable(
                        rs.getString("model"),
                        rs.getString("engineModel"),
                        rs.getString("gasolineType"),//3
                        rs.getInt("id"),//4
                        rs.getInt("year"),//5
                        rs.getInt("cylindersNum"),
                        rs.getInt("doorsNumber"),
                        rs.getInt("torque"),
                        rs.getInt("weight"),
                        rs.getInt("wheelSize"),
                        rs.getInt("horsePowers"),
                        rs.getString("color")
                        )
                );
            }

            table.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        col_engine.setCellValueFactory(new PropertyValueFactory<>("engineModel"));
        col_gasoline.setCellValueFactory(new PropertyValueFactory<>("gasolineType"));
        col_cylinders.setCellValueFactory(new PropertyValueFactory<>("cylinderNum"));
        col_doors.setCellValueFactory(new PropertyValueFactory<>("doorsNum"));
        col_torque.setCellValueFactory(new PropertyValueFactory<>("torque"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col_wheelSize.setCellValueFactory(new PropertyValueFactory<>("wheelSize"));
        col_hp.setCellValueFactory(new PropertyValueFactory<>("hp"));
        col_color.setCellValueFactory(new PropertyValueFactory<>("color"));

        deleteCar.setOnAction(e -> {
            try {
                deleteCarButtonClicked();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        try {
            goBackButton();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goBackButton() throws IOException {
        goBackFromTable.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
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

    //Delete button clicked
    private void deleteCarButtonClicked() throws SQLException {
        ObservableList<ModelTable> allCars, selectedCars;
        allCars = table.getItems();
        selectedCars = table.getSelectionModel().getSelectedItems();

        int idToDelete = selectedCars.get(0).getId();;


        selectedCars.forEach(allCars::remove);

        String deleteQuery = "DELETE FROM cars WHERE id =" + idToDelete;
        connection.createStatement().executeUpdate(deleteQuery);

    }
}
