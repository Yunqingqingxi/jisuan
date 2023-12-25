// StudentController.java
package first;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentController {
    private final List<Student> students = new ArrayList<>();
    private static final String FILE_PATH = "F:\\List\\sd\\students.txt";

    public void loadStudentsFromFile() {
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

    public void insertStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(String studentId) {
        List<Student> matchingStudents = binarySearchByStudentId(studentId);
        students.removeAll(matchingStudents);
    }

    public void modifyStudentByNameAndID(String name, String studentId, String newName, String newStudentId, int newRoomNumber) {
        for (Student student : students) {
            if (student.getName().equals(name) && student.getStudentId().equals(studentId)) {
                if (newName != null) {
                    student.setName(newName);
                }
                if (newStudentId != null) {
                    student.setStudentId(newStudentId);
                }
                if (newRoomNumber != 0) {
                    student.setRoomNumber(newRoomNumber);
                }
            }
        }
    }




    public List<Student> queryStudents(String keyword, String searchType) {
        sortStudentsByAttribute(searchType);  // 根据查询属性重新排序

        switch (searchType) {
            case "姓名":
                return binarySearchByName(keyword);
            case "学号":
                return binarySearchByStudentId(keyword);
            case "房号":
                return binarySearchByRoomNumber(keyword);
            default:
                // 处理未知的 searchType
                System.out.println("未知的搜索类型：" + searchType);
                return new ArrayList<>();  // 或者抛出异常，取决于你的需求
        }
    }

    private List<Student> binarySearchByName(String keyword) {
        int low = 0;
        int high = students.size() - 1;
        int mid;

        List<Student> matchingStudents = new ArrayList<>();

        while (low <= high) {
            mid = (low + high) / 2;
            Student student = students.get(mid);
            String name = student.getName();

            if (name.equals(keyword)) {
                matchingStudents.add(student);
                int left = mid - 1;
                int right = mid + 1;

                // 向左查找匹配项
                while (left >= 0 && students.get(left).getName().equals(keyword)) {
                    matchingStudents.add(students.get(left));
                    left--;
                }

                // 向右查找匹配项
                while (right < students.size() && students.get(right).getName().equals(keyword)) {
                    matchingStudents.add(students.get(right));
                    right++;
                }

                return matchingStudents;
            } else if (name.compareTo(keyword) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return matchingStudents;
    }

    private List<Student> binarySearchByStudentId(String keyword) {
        int low = 0;
        int high = students.size() - 1;
        int mid;

        List<Student> matchingStudents = new ArrayList<>();

        while (low <= high) {
            mid = (low + high) / 2;
            Student student = students.get(mid);
            String studentId = student.getStudentId();

            if (studentId.equals(keyword)) {
                matchingStudents.add(student);
                int left = mid - 1;
                int right = mid + 1;

                // 向左查找匹配项
                while (left >= 0 && students.get(left).getStudentId().equals(keyword)) {
                    matchingStudents.add(students.get(left));
                    left--;
                }

                // 向右查找匹配项
                while (right < students.size() && students.get(right).getStudentId().equals(keyword)) {
                    matchingStudents.add(students.get(right));
                    right++;
                }

                return matchingStudents;
            } else if (studentId.compareTo(keyword) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return matchingStudents;
    }


    private List<Student> binarySearchByRoomNumber(String keyword) {
        int targetRoomNumber = Integer.parseInt(keyword);

        int low = 0;
        int high = students.size() - 1;
        int mid;

        List<Student> matchingStudents = new ArrayList<>();

        while (low <= high) {
            mid = (low + high) / 2;
            Student student = students.get(mid);
            int roomNumber = student.getRoomNumber();

            if (roomNumber == targetRoomNumber) {
                matchingStudents.add(student);
                int left = mid - 1;
                int right = mid + 1;

                // 向左查找匹配项
                while (left >= 0 && students.get(left).getRoomNumber() == targetRoomNumber) {
                    matchingStudents.add(students.get(left));
                    left--;
                }

                // 向右查找匹配项
                while (right < students.size() && students.get(right).getRoomNumber() == targetRoomNumber) {
                    matchingStudents.add(students.get(right));
                    right++;
                }

                return matchingStudents;
            } else if (roomNumber < targetRoomNumber) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return matchingStudents;
    }


    private void sortStudentsByAttribute(String attribute) {
        switch (attribute) {
            case "姓名":
                students.sort(Comparator.comparing(Student::getName));
                break;
            case "学号":
                students.sort(Comparator.comparing(Student::getStudentId));
                break;
            case "房号":
                students.sort(Comparator.comparingInt(Student::getRoomNumber));
                break;
            default:
                // 处理未知的属性
                System.out.println("未知的属性：" + attribute);
                // 不进行排序或者抛出异常，取决于你的需求
        }
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private void sortStudentsByStudentId() {
        students.sort(Comparator.comparing(Student::getStudentId));
    }

    public void saveStudentsToFile() {
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

