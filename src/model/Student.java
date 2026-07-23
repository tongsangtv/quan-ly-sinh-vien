package model;

public class Student extends Person{
    private String lop;
    private String monHoc;
    private double diem;

    public Student(String id, String name, String lop, String monHoc, double diem) {
        super(id, name);
        this.lop = lop;
        this.monHoc = monHoc;
        this.diem = diem;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    //ghi de phuong thuc
    @Override
    public String hienThiThongTin() {
        return "Mã sinh viên : " + getId()
                + "\nHọ tên       : " + getName()
                + "\nLớp          : " + lop
                + "\nMôn học      : " + monHoc
                + "\nĐiểm         : " + diem;
    }

    //chuyen doi tuong thanh chuoi de luu file
    @Override
    public String toString() {
        return getId() + ";" + getName() + ";" + lop + ";" + monHoc + ";" + diem;
    }
}
