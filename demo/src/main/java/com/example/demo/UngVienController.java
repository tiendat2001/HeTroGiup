package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UngVienController implements Initializable {
    public static ObservableList<UngVien> list = FXCollections.observableArrayList();


    @FXML
    private TableView<UngVien> tbl_UngVien;

    @FXML
    private TableColumn<UngVien, String> col_maUngVien;

    @FXML
    private TableColumn<UngVien, String> col_hoten;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Thông tin kết nối cơ sở dữ liệu
        String urlDB = "jdbc:mysql://localhost:3306/hetrogiup";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(urlDB, username, password);
            String sql = "Select * from ungvien ";
            Statement Statement = conn.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new UngVien(
                        resultSet.getString("maUngVien"),
                        resultSet.getString("hoTen")
                ));

            }

            col_maUngVien.setCellValueFactory(new PropertyValueFactory<>("maUngVien"));
            col_hoten.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            tbl_UngVien.setItems(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
