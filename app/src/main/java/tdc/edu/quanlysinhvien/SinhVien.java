package tdc.edu.quanlysinhvien;

public class SinhVien {
    // members
    private String ma, ten, gioitinh;

    @Override
    public String toString() {
        return "SinhVien{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                '}';
    }

    // constructor
    public SinhVien() {
    }


    //constructor
    public SinhVien(String ma, String ten, String gioitinh) {
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
    }
    // properties
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

}
