package repository;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private List<Student> danhSachSinhVien;
    private final String DUONG_DAN_FILE = "src/data/students.txt";

    public StudentRepository() {
        danhSachSinhVien = new ArrayList<>();
        docFile();
    }

    @Override
    public List<Student> layDanhSachSinhVien() {
        return danhSachSinhVien;
    }

    @Override
    public void themSinhVien(Student sinhVien) {
        danhSachSinhVien.add(sinhVien);
        ghiFile();
    }

    @Override
    public void xoaSinhVien(String id) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getId().equals(id)) {
                danhSachSinhVien.remove(i);
                break;
            }
        }
        ghiFile();
    }

    @Override
    public void ghiFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DUONG_DAN_FILE));

            // Dùng vòng for theo chỉ số quen thuộc
            for (int i = 0; i < danhSachSinhVien.size(); i++) {
                Student s = danhSachSinhVien.get(i);
                bw.write(s.toString());
                bw.newLine();
            }

            bw.close(); // Đóng file thủ công cho đúng kiểu vỡ lòng
        } catch (Exception e) {
            System.err.println("Lỗi ghi file rồi!");
        }
    }

    @Override
    public void docFile() {
        File file = new File(DUONG_DAN_FILE);
        if (!file.exists()) {
            return;
        }

        danhSachSinhVien.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String dong;

            // Đọc từng dòng thủ công bằng vòng lặp while
            while ((dong = br.readLine()) != null) {
                String[] mang = dong.split(";");
                if (mang.length == 5) {
                    String id = mang[0];
                    String ten = mang[1];
                    String lop = mang[2];
                    String monHoc = mang[3];
                    double diem = Double.parseDouble(mang[4]);

                    Student sinhVien = new Student(id, ten, lop, monHoc, diem);
                    danhSachSinhVien.add(sinhVien);
                }
            }

            br.close(); // Đóng luồng đọc thủ công
        } catch (Exception e) {
            System.err.println("Lỗi đọc file rồi!");
        }
    }
}
