package service;

import model.Student;
import java.util.List;

public interface IStudentService {
    List<Student> layDanhSachSinhVien();
    boolean themSinhVien(Student sinhVien);
    boolean xoaSinhVien(String id);
    boolean capNhatSinhVien(Student sinhVien);
    Student timSinhVienTheoId(String id);
}
