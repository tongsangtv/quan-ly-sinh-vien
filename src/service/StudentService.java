package service;

import model.Student;
import repository.IStudentRepository;
import repository.StudentRepository;
import java.util.List;

public class StudentService implements IStudentService {
    private IStudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    @Override
    public List<Student> layDanhSachSinhVien() {
        return studentRepository.layDanhSachSinhVien();
    }

    @Override
    public boolean themSinhVien(Student sinhVien) {
        // Kiểm tra xem mã sinh viên đã có trong danh sách chưa
        List<Student> danhSach = studentRepository.layDanhSachSinhVien();
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getId().equalsIgnoreCase(sinhVien.getId())) {
                return false; // Trùng mã thì dừng và báo không thêm được
            }
        }
        
        studentRepository.themSinhVien(sinhVien);
        return true; 
    }

    @Override
    public boolean xoaSinhVien(String id) {
        Student student = timSinhVienTheoId(id);
        if (student != null) {
            studentRepository.xoaSinhVien(id);
            return true; // Xóa thành công
        }
        return false; // Không tìm thấy để xóa
    }

    @Override
    public Student timSinhVienTheoId(String id) {
        List<Student> danhSach = studentRepository.layDanhSachSinhVien();
        for (int i = 0; i < danhSach.size(); i++) {
            Student s = danhSach.get(i);
            if (s.getId().equalsIgnoreCase(id)) {
                return s; // Tìm thấy thì trả về sinh viên đó
            }
        }
        return null; // Không thấy thì trả về null
    }
}

