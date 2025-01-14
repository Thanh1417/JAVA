package GGV;

import java.sql.*;

public class XLGV {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    public void getCon() {
        String user = "sa";
        String pw = "Thanh123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DLGV1;encrypt=true;trustServerCertificate=true;";
        try {
            conn = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            System.out.println("Loi ket noi: " + e);
        }

    }
    //Lay thong tin giang vien (khong dieu kien)
    public ResultSet getGV() {
        String sql = "select * from tbGiangvien";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println("Loi getGV(): " + e);
        }
        return rs;
    }

    public ResultSet getGV(String donVi, int soCT) {
        String sql = "select * from tbGiangvien where Donvi = ? And Soct = ?";
        try {
            st = conn.prepareStatement(sql);
            //Gan gia tri cho tham so
            st.setString(1, donVi); //Gán donVi vào vị trí thứ nhất (?)
            st.setInt(2, soCT); //Gán soCT vào vị trí thứ hai (?)

            rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println("Loi getGV: " + e);
        }
        return rs;
    }

    //Them
    public boolean insertGV(Giangvien gv) {
        String sql = "insert into [dbo].[tbGiangvien] ([MaDD], [Hoten], [GT], [Donvi], [Soct]) values (?, ?, ?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, gv.getMaDD());
            st.setString(2, gv.getHoTen());
            st.setString(3, gv.getGioiTinh());
            st.setString(4, gv.getDonVi());
            st.setInt(5, gv.getSoCT());

            int row = st.executeUpdate();
            return row > 0;
        }catch (Exception e) {
            System.out.println("Loi them: " + e);
            return false;
        }
    }

    //Sua
    public boolean updateGV(Giangvien gv) {
        String sql = "update [dbo].[tbGiangvien] set [Hoten] = ?, [GT] = ?, [Donvi] = ?, [Soct] = ? where [MaDD] = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, gv.getHoTen());
            st.setString(2, gv.getGioiTinh());
            st.setString(3, gv.getDonVi());
            st.setInt(4, gv.getSoCT());
            st.setString(5, gv.getMaDD());

            int rows = st.executeUpdate();
            return rows > 0;
        }catch (Exception e) {
            System.out.println("Loi sua: " + e);
            return false;
        }
    }

    //Xoa
    public boolean deleteGV(Giangvien gv) {
        String sql = "delete from [dbo].[tbGiangvien] where [MaDD] = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, gv.getMaDD());

            int rows = st.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Loi xoa: " + e);
            return false;
        }
    }

}
