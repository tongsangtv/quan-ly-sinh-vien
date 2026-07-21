package repository;

import model.Student;
import java.util.List;

public interface IStudentRepository {
    List<Student> layDanhSachSinhVien();
    void themSinhVien(Student sinhVien);
    void xoaSinhVien(String id);
    void ghiFile();
    void docFile();
}