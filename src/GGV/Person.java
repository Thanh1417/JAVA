package GGV;

public abstract class Person {
    private String maDD;
    private String hoTen;
    private String gioiTinh;
    //Constructor
    public Person() {
    }

    public Person(String maDD, String hoTen, String gioiTinh) {
        this.maDD = maDD;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
    }
    //Getter, setter

    public String getMaDD() {
        return maDD;
    }

    public void setMaDD(String maDD) {
        this.maDD = maDD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    //Xetthuong
    public abstract String Xetthuong();

}
