package service;

import model.Student;
import java.util.List;

public interface IStudentService {
    List<Student> layDanhSachSinhVien();
    boolean themSinhVien(Student sinhVien);
    boolean xoaSinhVien(String id);
    Student timSinhVienTheoId(String id);
}
