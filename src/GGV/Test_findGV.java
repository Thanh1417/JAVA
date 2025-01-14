package GGV;

import java.security.Guard;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Test_findGV {
    public static List<Giangvien> dogetGV(String donVi, int soCT) {
        List<Giangvien> listGV = new ArrayList<>();
        XLGV gv = new XLGV();
        gv.getCon();

        ResultSet rs = gv.getGV(donVi, soCT);

        try {
            while (rs.next()) {
                String maDD = rs.getNString("MaDD");
                String hoTen = rs.getNString("Hoten");
                String gioiTinh = rs.getNString("GT");
                String dv = rs.getNString("Donvi");
                int soct = rs.getInt("Soct");

                Giangvien gv1 = new Giangvien(maDD, hoTen,gioiTinh, dv, soct);
                listGV.add(gv1);
            }

        }catch (Exception e) {
            System.out.println("Loi 1: " + e);
        }
        return listGV;
    }
    public static List<Giangvien> dogetFullGV() {
        List<Giangvien> listGV = new ArrayList<>();
        XLGV gv = new XLGV();
        gv.getCon();
        ResultSet rs = gv.getGV();

        try {
            while (rs.next()) {
                String maDD = rs.getNString("MaDD");
                String hoten = rs.getNString("Hoten");
                String gt = rs.getNString("GT");
                String dv = rs.getNString("Donvi");
                int soct = rs.getInt("Soct");

                Giangvien gv1 = new Giangvien(maDD, hoten, gt, dv, soct);
                listGV.add(gv1);
            }
        }catch (Exception e) {
            System.out.println("Loi 2: " + e);
        }
        return listGV;
    }

    public static void main(String[] args) {
        GUIGiangvien guiGV = new GUIGiangvien();
        guiGV.setVisible(true);

    }

}
