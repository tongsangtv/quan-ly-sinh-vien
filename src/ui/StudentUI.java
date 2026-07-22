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
            System.out.println("4. Cập nhật sinh viên");
            System.out.println("5. Xóa sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            while (true) {
                try {
                    System.out.print("Chọn: ");
                    choice = Integer.parseInt(sc.nextLine());

                    if (choice >= 0 && choice <= 5) {
                        break;
                    } else {
                        System.out.println("Chỉ được nhập từ 0 đến 5!");
                    }

                } catch (Exception e) {
                    System.out.println("Vui lòng nhập số!");
                }
            }

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
                    updateStudent();
                    break;

                case 5:
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

        String id = nhapId();

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

        double diem = nhapDiem();

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

    //Cap nhat sinh vien
    private void updateStudent() {

        System.out.print("Nhập mã sinh viên cần cập nhật: ");
        String id = sc.nextLine();

        Student student = service.timSinhVienTheoId(id);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        System.out.print("Họ tên mới: ");
        String name = sc.nextLine();

        System.out.print("Lớp mới: ");
        String lop = sc.nextLine();

        System.out.print("Môn học mới: ");
        String monHoc = sc.nextLine();

        double diem;

        while (true) {
            try {
                System.out.print("Điểm mới: ");
                diem = Double.parseDouble(sc.nextLine());

                if (diem >= 0 && diem <= 10) {
                    break;
                } else {
                    System.out.println("Điểm phải từ 0 đến 10!");
                }

            } catch (Exception e) {
                System.out.println("Điểm không hợp lệ!");
            }
        }

        Student newStudent = new Student(id, name, lop, monHoc, diem);

        if (service.capNhatSinhVien(newStudent)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
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

    private String nhapId() {
        while (true) {
            try {
                System.out.print("Mã sinh viên: ");
                int id = Integer.parseInt(sc.nextLine());

                if (id > 0) {
                    return String.valueOf(id);
                } else {
                    System.out.println("ID phải lớn hơn 0!");
                }

            } catch (Exception e) {
                System.out.println("ID chỉ được nhập số!");
            }
        }
    }

    private double nhapDiem() {
        while (true) {
            try {
                System.out.print("Điểm: ");
                double diem = Double.parseDouble(sc.nextLine());

                if (diem >= 0 && diem <= 10) {
                    return diem;
                } else {
                    System.out.println("Điểm phải từ 0 đến 10!");
                }

            } catch (Exception e) {
                System.out.println("Điểm không hợp lệ!");
            }
        }
    }
}
