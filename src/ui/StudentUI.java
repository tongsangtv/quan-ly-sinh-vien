package ui;

import java.util.List;
import java.util.Scanner;

import model.Student;
import service.IStudentService;
import service.StudentService;

public class StudentUI {

    private IStudentService service;
    private Scanner sc;

    public StudentUI() {
        service = new StudentService();
        sc = new Scanner(System.in);
    }

    public void menu() {

        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("      QUẢN LÝ SINH VIÊN");
            System.out.println("==============================");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    showStudents();
                    break;

                case 3:
                    findStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 0:
                    System.out.println("Tạm biệt!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    // Them sinh vien
    private void addStudent() {

        System.out.println("\n------ THÊM SINH VIÊN ------");

        System.out.print("Mã sinh viên: ");
        String id = sc.nextLine();

        if (service.timSinhVienTheoId(id) != null) {
            System.out.println("Mã sinh viên đã tồn tại!");
            return;
        }

        System.out.print("Họ tên: ");
        String name = sc.nextLine();

        System.out.print("Lớp: ");
        String lop = sc.nextLine();

        System.out.print("Môn học: ");
        String monHoc = sc.nextLine();

        System.out.print("Điểm: ");
        double diem = Double.parseDouble(sc.nextLine());

        Student student = new Student(id, name, lop, monHoc, diem);

        if (service.themSinhVien(student)) {
            System.out.println("Thêm thành công!");
        } else {
            System.out.println("Thêm thất bại!");
        }
    }

    // Hien thi danh sach
    private void showStudents() {

        List<Student> list = service.layDanhSachSinhVien();

        if (list.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        System.out.println("\n===== DANH SÁCH SINH VIÊN =====");

        for (Student student : list) {
            student.display();
        }
    }

    // Tim sinh vien
    private void findStudent() {

        System.out.print("Nhập mã sinh viên: ");
        String id = sc.nextLine();

        Student student = service.timSinhVienTheoId(id);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên!");
        } else {
            System.out.println("\nThông tin sinh viên:");
            student.display();
        }
    }

    // Xoa sinh vien
    private void deleteStudent() {

        System.out.print("Nhập mã sinh viên cần xóa: ");
        String id = sc.nextLine();

        if (service.xoaSinhVien(id)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên!");
        }
    }
}
