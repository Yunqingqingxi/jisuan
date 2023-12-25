package test;

import java.io.*;
import java.util.*;

class Student {
    String name;
    String studentId;
    int roomNumber;

    public Student(String name, String studentId, int roomNumber) {
        this.name = name;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
    }

    // 从文件中读取学生信息的构造函数
    public Student(String line) {
        String[] parts = line.split(",");
        this.name = parts[0];
        this.studentId = parts[1];
        this.roomNumber = Integer.parseInt(parts[2]);
    }

    // 将学生信息转换为字符串，以便写入文件
    public String toFileString() {
        return name + "," + studentId + "," + roomNumber;
    }
}

public class DormitoryManagementSystem {
    private static List<Student> students = new ArrayList<>();

    private static String FILE_PATH = "F:\\List\\sd\\src\\test\\students";

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入学生信息保存文件路径（按回车使用默认路径 \"students.txt\"）：");
        String inputFilePath = scanner.nextLine();
        if (!inputFilePath.isEmpty()) {
            FILE_PATH = inputFilePath;
        }

        loadStudentsFromFile();  // 加载学生信息

        // 主菜单
        while (true) {
            System.out.println("********** 宿舍管理系统 **********");
            System.out.println("1. 插入学生信息");
            System.out.println("2. 删除学生信息");
            System.out.println("3. 修改学生信息");
            System.out.println("4. 查询学生信息");
            System.out.println("5. 退出系统");
            System.out.print("请选择操作（1-5）：");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    modifyStudent();
                    break;
                case 4:
                    queryStudent();
                    break;
                case 5:
                    System.out.println("感谢使用宿舍管理系统，再见！");
                    saveStudentsToFile();  // 保存学生信息到文件
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }

    // 添加学生信息的方法
    private static void insertStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();

        System.out.print("请输入学号：");
        String studentId = scanner.nextLine();

        System.out.print("请输入房号：");
        int roomNumber = scanner.nextInt();

        Student newStudent = new Student(name, studentId, roomNumber);
        students.add(newStudent);

        System.out.println("学生信息插入成功！");
        saveStudentsToFile();  // 保存学生信息到文件
        sortStudents();
    }

    // 删除学生信息的方法
    private static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要删除学生的学号：");
        String studentId = scanner.nextLine();

        List<Student> matchingStudents = findStudentsById(studentId);

        if (!matchingStudents.isEmpty()) {
            System.out.println("找到以下匹配的学生信息：");
            printStudentList(matchingStudents);

            System.out.print("请选择要删除的学生序号：");
            int index = scanner.nextInt();
            students.remove(matchingStudents.get(index));

            System.out.println("学生信息删除成功！");
            saveStudentsToFile();  // 保存学生信息到文件
        } else {
            System.out.println("未找到该学生信息！");
        }
    }

    // 修改学生信息的方法
    private static void modifyStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要修改学生的学号：");
        String studentId = scanner.nextLine();

        List<Student> matchingStudents = findStudentsById(studentId);

        if (!matchingStudents.isEmpty()) {
            System.out.println("选择要修改的属性：");
            System.out.println("1. 姓名");
            System.out.println("2. 学号");
            System.out.println("3. 房号");
            System.out.print("请输入选项（1-3）：");

            int modifyOption = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (Student student : matchingStudents) {
                switch (modifyOption) {
                    case 1:
                        System.out.print("请输入新的学生姓名：");
                        student.name = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("请输入新的学号：");
                        student.studentId = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("请输入新的房号：");
                        student.roomNumber = scanner.nextInt();
                        break;
                    default:
                        System.out.println("无效的选项！");
                        return;
                }
            }

            System.out.println("学生信息修改成功！");
            saveStudentsToFile();
            sortStudents();
        } else {
            System.out.println("未找到该学生信息！");
        }
    }

    // 查询学生信息的方法
    private static void queryStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("********** 查询菜单 **********");
        System.out.println("1. 按姓名查询");
        System.out.println("2. 按学号查询");
        System.out.println("3. 按房号查询");
        System.out.print("请选择查询方式（1-3）：");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("请输入查询关键字：");
        String keyword = scanner.nextLine();

        sortStudents();

        switch (choice) {
            case 1:
                binarySearchAndPrintResult(keyword, "姓名", "name");
                break;
            case 2:
                binarySearchAndPrintResult(keyword, "学号", "studentId");
                break;
            case 3:
                binarySearchAndPrintResult(keyword, "房号", "roomNumber");
                break;
            default:
                System.out.println("无效的选择，请重新输入！");
        }
    }

    // 查询和打印学生信息(1)
    private static void queryAndPrintResult(String keyword, String searchType, String fieldName) {
        List<Student> matchingStudents = findStudentsByField(keyword, fieldName);

        if (!matchingStudents.isEmpty()) {
            System.out.println(searchType + "为 " + keyword + " 的学生信息如下：");
            printStudentList(matchingStudents);
        } else {
            System.out.println("未找到 " + searchType + " 为 " + keyword + " 的学生信息！");
        }
    }

    // 查询和打印学生信息(2)
    private static void binarySearchAndPrintResult(String keyword, String searchType, String fieldName) {
        int index = binarySearch(keyword, fieldName);

        if (index != -1) {
            System.out.println(searchType + "为 " + keyword + " 的学生信息如下：");
            System.out.println("姓名：" + students.get(index).name);
            System.out.println("学号：" + students.get(index).studentId);
            System.out.println("房号：" + students.get(index).roomNumber);
        } else {
            System.out.println("未找到 " + searchType + " 为 " + keyword + " 的学生信息！");
        }
    }


    // 二分查找
    private static int binarySearch(String keyword, String fieldName) {
        int low = 0;
        int high = students.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            String fieldValue = getField(students.get(mid), fieldName);

            if (fieldValue.compareTo(keyword) == 0) {
                return mid; // 找到匹配项
            } else if (fieldValue.compareTo(keyword) < 0) {
                low = mid + 1; // 在右半部分继续查找
            } else {
                high = mid - 1; // 在左半部分继续查找
            }
        }

        return -1; // 未找到匹配项
    }
    // 从文件中加载学生信息
    private static void printStudentList(List<Student> studentList) {
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println("序号：" + i);
            System.out.println("姓名：" + studentList.get(i).name);
            System.out.println("学号：" + studentList.get(i).studentId);
            System.out.println("房号：" + studentList.get(i).roomNumber);
            System.out.println("--------------------");
        }
    }

    // 保存学生信息到文件
    private static List<Student> findStudentsById(String studentId) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.studentId.equals(studentId)) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

    // 根据关键字和字段名查找学生信息，并返回排序后的学生列表。字段名可以为 "name", "studentId", "roomNumber"。
    private static List<Student> findStudentsByField(String keyword, String fieldName) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : students) {
            String fieldValue = getField(student, fieldName);
            if (fieldValue.contains(keyword)) {
                matchingStudents.add(student);
            }
        }
        // 对查询结果按照学号排序
        matchingStudents.sort(Comparator.comparing(student -> student.studentId));
        return matchingStudents;
    }

    // 获取学生信息
    private static String getField(Student student, String fieldName) {
        return switch (fieldName) {
            case "name" -> student.name;
            case "studentId" -> student.studentId;
            case "roomNumber" -> String.valueOf(student.roomNumber);
            default -> "";
        };
    }

    // 排序
    private static void sortStudents() {
        students.sort(Comparator.comparing(student -> student.name));
    }

    private static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = new Student(line);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("加载学生信息出错：" + e.getMessage());
        }
    }

    private static void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("保存学生信息出错：" + e.getMessage());
        }
    }
}
