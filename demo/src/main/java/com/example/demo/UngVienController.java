package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TableColumn<?, ?> col_bangcap;

    @FXML
    private TableColumn<?, ?> col_diachi;

    @FXML
    private TableColumn<?, ?> col_hoten;

    @FXML
    private TableColumn<?, ?> col_kinhNghiem;

    @FXML
    private TableColumn<?, ?> col_luong;

    @FXML
    private TableColumn<?, ?> col_maUngVien;

    @FXML
    private TableColumn<?, ?> col_ngaySinh;

    @FXML
    private TableColumn<?, ?> col_quequan;

    @FXML
    private TableColumn<?, ?> col_trinhDoChuyenMon;

    @FXML
    private Button btn_search;
    // bang so 2
    @FXML
    private TableView<DiemUngVien> tbl_DiemUngVien;
    @FXML
    private TableColumn<?, ?> col_maUngVien1;
    @FXML

    private TableColumn<?, ?> col_hoten1;

    @FXML
    private TableColumn<?, ?> col_kinhNghiem1;

    @FXML
    private TableColumn<?, ?> col_bangcap1;

    @FXML
    private TableColumn<?, ?> col_trinhDoChuyenMon1;

    @FXML
    private TableColumn<?, ?> col_luong1;

    // input nguoi dung
    @FXML
    private TextField txt_input_NamKN;

    @FXML
    private TextField txt_input_TrinhDoChuyenMon;

    @FXML
    private TextField txt_input_Luong;

    @FXML
    ComboBox<String> cb_input_BangCap;

    public int chuyenDiemBangCap(String bangCap){
        if(bangCap.equalsIgnoreCase("Xuất sắc")) {
            return 10;
        }else if(bangCap.equalsIgnoreCase("Giỏi")){
            return 8;
        }else if(bangCap.equalsIgnoreCase("Khá")){
            return 6;
        }else if(bangCap.equalsIgnoreCase("Trung bình")){
            return 4;
        }
        return 0;
    }
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
                        resultSet.getString("hoTen"),
                        resultSet.getDate("ngaySinh"),
                        resultSet.getString("queQuan"),
                        resultSet.getString("diaChi"),
                        resultSet.getInt("kinhNghiem"),
                        resultSet.getString("trinhDoChuyenMon"),
                        resultSet.getString("bangCap"),
                        resultSet.getFloat("luong")

                ));

            }

            col_maUngVien.setCellValueFactory(new PropertyValueFactory<>("maUngVien"));
            col_hoten.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            col_ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            col_quequan.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
            col_diachi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            col_kinhNghiem.setCellValueFactory(new PropertyValueFactory<>("kinhNghiem"));
            col_trinhDoChuyenMon.setCellValueFactory(new PropertyValueFactory<>("trinhDoChuyenMon"));
            col_bangcap.setCellValueFactory(new PropertyValueFactory<>("bangCap"));
//            col_bangcap.setCellValueFactory(new PropertyValueFactory<>("diemBangCap"));
            col_luong.setCellValueFactory(new PropertyValueFactory<>("luong"));

            tbl_UngVien.setItems(list);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // combo box input
        cb_input_BangCap.getItems().add("Xuất sắc");
        cb_input_BangCap.getItems().add("Giỏi");
        cb_input_BangCap.getItems().add("Khá");
        cb_input_BangCap.getItems().add("Trung bình");

        // set cac gtri mac dinh
        cb_input_BangCap.setValue("Xuất sắc");
        txt_input_Luong.setText("5000000");
        txt_input_NamKN.setText("5");
        txt_input_TrinhDoChuyenMon.setText("java");



    }
    @FXML
    void search(ActionEvent event) {
        ObservableList<DiemUngVien> listDiemUngVien = FXCollections.observableArrayList();
        // lay cac truong input nguoi dung nhap vao
        int inputDiemKinhNghiem = Integer.parseInt(txt_input_NamKN.getText());
        float inputLuong = Integer.parseInt(txt_input_Luong.getText());
        String inputTrinhDoChuyenMon = txt_input_TrinhDoChuyenMon.getText();
        int inputDiemBangCap = chuyenDiemBangCap(cb_input_BangCap.getValue().toString());

        int chenhLechTrinhDoChuyenMon;

        // tinh toan
        for (UngVien ungVien : list) {
           int chenhLechDiemKinhNghiem= ungVien.getKinhNghiem() -inputDiemKinhNghiem;
           int chenhLechDiemBangCap= ungVien.getDiemBangCap()-inputDiemBangCap ;
           float chenhLechLuong= ungVien.getLuong()- inputLuong;

            // tinh diem chuyen mon neu co +1
            if(ungVien.getTrinhDoChuyenMon().toLowerCase().contains(inputTrinhDoChuyenMon.toLowerCase())){
               chenhLechTrinhDoChuyenMon =1;
            }else chenhLechTrinhDoChuyenMon=0;

           DiemUngVien duv = new DiemUngVien(ungVien.getMaUngVien(), ungVien.getHoTen(), chenhLechDiemKinhNghiem,chenhLechTrinhDoChuyenMon,
                   chenhLechDiemBangCap,chenhLechLuong);
            listDiemUngVien.add(duv);

//            // test
//            for (DiemUngVien diemUngVien : listDiemUngVien) {
//                System.out.println("Mã ứng viên: " + diemUngVien.getMaUngVien());
//                System.out.println("Họ và tên: " + diemUngVien.getHoTen());
//                System.out.println("Chênh lệch điểm kinh nghiệm: " + diemUngVien.getKinhNghiem());
//                System.out.println("Thuộc tính 1: " + diemUngVien.getTrinhDoChuyenMon());
//                System.out.println("Chênh lệch điểm bằng cấp: " + diemUngVien.getBangCap());
//                System.out.println("Chênh lệch lương: " + diemUngVien.getLuong());
//                System.out.println("--------------------------------");
//            }

            // hien thi len bang
            col_maUngVien1.setCellValueFactory(new PropertyValueFactory<>("maUngVien"));
            col_hoten1.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            col_kinhNghiem1.setCellValueFactory(new PropertyValueFactory<>("kinhNghiem"));
            col_trinhDoChuyenMon1.setCellValueFactory(new PropertyValueFactory<>("trinhDoChuyenMon"));
            col_bangcap1.setCellValueFactory(new PropertyValueFactory<>("bangCap"));
            col_luong1.setCellValueFactory(new PropertyValueFactory<>("luong"));
            tbl_DiemUngVien.setItems(listDiemUngVien);
        }

    }


}
