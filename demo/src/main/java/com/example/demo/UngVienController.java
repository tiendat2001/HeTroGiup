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
import java.util.Collections;
import java.util.Comparator;
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

    @FXML
    private TableColumn<?, ?> col_doPhuHop;

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
        ObservableList<DiemUngVien> listDiemUngVienChuanHoa = FXCollections.observableArrayList();

        // lay cac truong input nguoi dung nhap vao
        int inputDiemKinhNghiem = Integer.parseInt(txt_input_NamKN.getText());
        float inputLuong = Integer.parseInt(txt_input_Luong.getText());
        String inputTrinhDoChuyenMon = txt_input_TrinhDoChuyenMon.getText();
        int inputDiemBangCap = chuyenDiemBangCap(cb_input_BangCap.getValue().toString());

        int chenhLechTrinhDoChuyenMon;

        // tinh toan
        for (UngVien ungVien : list) {
            float diemKinhNghiem= tinhDoPhuHopKinhNghiem(ungVien.getKinhNghiem(), inputDiemKinhNghiem);
           float diemBangCap= tinhDiemPhuHopBangCap(ungVien.getDiemBangCap(), inputDiemBangCap);
           float diemLuong= tinhDoPhuHopLuong(ungVien.getLuong(),inputLuong);
           float diemTrinhDo= tinhDiemDoPhuHopTrinhDoChuyenMon(inputTrinhDoChuyenMon, ungVien.getTrinhDoChuyenMon());

//            if(ungVien.getTrinhDoChuyenMon().toLowerCase().contains(inputTrinhDoChuyenMon.toLowerCase())){
//               chenhLechTrinhDoChuyenMon =1;
//            }else chenhLechTrinhDoChuyenMon=0;
            // luu vao mang
           DiemUngVien duv = new DiemUngVien(ungVien.getMaUngVien(), ungVien.getHoTen(), diemKinhNghiem,diemTrinhDo,
                   diemBangCap,diemLuong);
            listDiemUngVien.add(duv);

            listDiemUngVienChuanHoa=topSis(listDiemUngVien);

//            // test
            for (DiemUngVien diemUngVien : listDiemUngVienChuanHoa) {
                System.out.println("Mã ứng viên: " + diemUngVien.getMaUngVien());
                System.out.println("Họ và tên: " + diemUngVien.getHoTen());
                System.out.println("Chênh lệch điểm kinh nghiệm: " + diemUngVien.getKinhNghiem());
                System.out.println("Thuộc tính 1: " + diemUngVien.getTrinhDoChuyenMon());
                System.out.println("Chênh lệch điểm bằng cấp: " + diemUngVien.getBangCap());
                System.out.println("Chênh lệch lương: " + diemUngVien.getLuong());
                System.out.println("Do Phu Hop: " + diemUngVien.getDiemPhuHop());

                System.out.println("--------------------------------");
            }
            Collections.sort(listDiemUngVienChuanHoa, new Comparator<DiemUngVien>() {
                @Override
                public int compare(DiemUngVien duv1, DiemUngVien duv2) {
                    // So sánh giá trị diemPhuHop
                    return Double.compare(duv1.getDiemPhuHop(), duv2.getDiemPhuHop());
                }
            });
            // hien thi len bang
            col_maUngVien1.setCellValueFactory(new PropertyValueFactory<>("maUngVien"));
            col_hoten1.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            col_kinhNghiem1.setCellValueFactory(new PropertyValueFactory<>("kinhNghiem"));
            col_trinhDoChuyenMon1.setCellValueFactory(new PropertyValueFactory<>("trinhDoChuyenMon"));
            col_bangcap1.setCellValueFactory(new PropertyValueFactory<>("bangCap"));
            col_luong1.setCellValueFactory(new PropertyValueFactory<>("luong"));
            col_doPhuHop.setCellValueFactory(new PropertyValueFactory<>("diemPhuHop"));

            tbl_DiemUngVien.setItems(listDiemUngVienChuanHoa);
        }




    }

    public float tinhDoPhuHopKinhNghiem(int kinhNghiemUngVien, int kinhNghiemYeuCau) {
        int chenhLechSoNam= kinhNghiemUngVien -kinhNghiemYeuCau;
        float doPhuhop = 0;
        if(chenhLechSoNam>7){
            doPhuhop= 10;

            // kinh nghiem thap hon 3 nam so voi yeu cau la 0
        }else if(chenhLechSoNam>-3){

            doPhuhop=chenhLechSoNam+3;
        }else doPhuhop=0;

        return doPhuhop;
    }


    public float tinhDoPhuHopLuong(float luongUngVien, float luongYeuCau) {
        float chenhLechLuong= (luongYeuCau-luongUngVien)/1000000;
        float doPhuhop = 0;
        // neu doi it tien hon thi se la 5 diem
        if(chenhLechLuong>=0){
            doPhuhop= 5;

        // neu doi hon 1-5 trieu thi diem tu 1-5
        }else if(chenhLechLuong<0&& chenhLechLuong>=-5 ) {
            doPhuhop=chenhLechLuong+5;
        }else{
            // doi qua 5 trieu thi la 0
            doPhuhop=0;
        }
        return doPhuhop;
    }

    // moi ky nang trung +1 diem
    public int tinhDiemDoPhuHopTrinhDoChuyenMon(String yeuCauKyNang, String kyNangUngVien) {
        String[] yeuCauArray = yeuCauKyNang.split(","); // Tách chuỗi yêu cầu thành mảng các kỹ năng
        String[] kyNangArray = kyNangUngVien.split(","); // Tách chuỗi kỹ năng ứng viên thành mảng các kỹ năng

        int doPhuhop = 0;

        for (String kyNang : kyNangArray) {
            for (String yeuCau : yeuCauArray) {
                if (kyNang.trim().equalsIgnoreCase(yeuCau.trim())) {
                    doPhuhop++; // Tăng điểm nếu có ngôn ngữ kỹ năng trùng với yêu cầu
                    break; // Thoát khỏi vòng lặp khi đã tìm thấy kỹ năng trùng
                }
            }
        }

        return doPhuhop;
    }

    // thang diem tu 0-12
    public int tinhDiemPhuHopBangCap(int bangCapUngVien, int bangCapYeuCau){
        return (bangCapUngVien-bangCapYeuCau)+6;

    }

    public ObservableList<DiemUngVien> topSis(ObservableList<DiemUngVien> listDiemUngVien){
        ObservableList<DiemUngVien> listDiemChuanHoa = FXCollections.observableArrayList();
        double sumKinhNghiem=0;
        double sumTrinhDo=0;
        double sumBangCap=0;
        double sumLuong=0;
        for (DiemUngVien ungVien : listDiemUngVien) {
            sumKinhNghiem+= Math.pow(ungVien.getKinhNghiem(), 2);
            sumTrinhDo+= Math.pow(ungVien.getTrinhDoChuyenMon(), 2);
            sumBangCap+= Math.pow(ungVien.getBangCap(), 2);
            sumLuong+= Math.pow(ungVien.getLuong(), 2);

        }
        // tinh chuan hoa va nhan trong so (phần mẫu để chia)
        sumKinhNghiem= Math.sqrt(sumKinhNghiem);
        sumTrinhDo= Math.sqrt(sumTrinhDo);
        sumBangCap= Math.sqrt(sumBangCap);
        sumLuong= Math.sqrt(sumLuong);


        // tinh A*
        double maxDiemKinhNghiem=0;
        double maxDiemTrinhDo=0;
        double maxDiemBangCap=0;
        double maxLuong=0;

        //
        for (DiemUngVien ungVien : listDiemUngVien) {

//
            double diemKinhNghiem = Math.round(ungVien.getKinhNghiem()/sumKinhNghiem*0.3* 10000) / 10000.0;
            double diemTrinhDo = Math.round(ungVien.getTrinhDoChuyenMon()/sumTrinhDo*0.4* 10000) / 10000.0;
            double diemBangCap = Math.round(ungVien.getBangCap()/sumBangCap*0.2* 10000) / 10000.0;
            double diemLuong = Math.round(ungVien.getLuong()/sumLuong*0.1* 10000) / 10000.0;

            if(diemKinhNghiem>maxDiemKinhNghiem){
                maxDiemKinhNghiem=diemKinhNghiem;
            }
            if(diemTrinhDo>maxDiemTrinhDo){
                maxDiemTrinhDo=diemTrinhDo;
            }
            if(diemBangCap>maxDiemBangCap){
                maxDiemBangCap=diemBangCap;
            }
            if(diemLuong>maxLuong){
                maxLuong=diemLuong;
            }

            DiemUngVien duvch = new DiemUngVien(ungVien.getMaUngVien(), ungVien.getHoTen(), diemKinhNghiem,diemTrinhDo,
                    diemBangCap,diemLuong);
            listDiemChuanHoa.add(duvch);

        }
        for (DiemUngVien ungVien : listDiemChuanHoa){
            double sumPhuHop = 0;
            sumPhuHop +=Math.pow((ungVien.getKinhNghiem()-maxDiemKinhNghiem),2);
            sumPhuHop +=Math.pow((ungVien.getBangCap()-maxDiemBangCap),2);
            sumPhuHop +=Math.pow((ungVien.getLuong()-maxLuong),2);
            sumPhuHop +=Math.pow((ungVien.getTrinhDoChuyenMon()-maxDiemTrinhDo),2);
            System.out.println(Math.sqrt(sumPhuHop));

            sumPhuHop = Math.round(Math.sqrt(sumPhuHop)*10000) / 10000.0;
            System.out.println(sumPhuHop);

            ungVien.setDiemPhuHop(sumPhuHop);

        }

            return listDiemChuanHoa;
    }



}
