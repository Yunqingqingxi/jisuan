package test;

import java.util.Objects;
import java.util.Scanner;

public class Manage {
    public static Student[] students = new Student[100];
    static int count = 0;
    public static void main(String[] args) {
        manage();
    }
    // Manage
    public static void manage() {

        // 功能菜单
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的选择：");
        System.out.println("1.查询菜单 2.添加菜单 3.删除菜单 4.修改菜单");
        String swi = sc.next();

        switch(swi){
            case "1":
                System.out.println("查询菜单");
                System.out.println("A.按姓名查询 B.按学号查询 C.按房号查询");
                System.out.println("请输入你的选择：");
                String choice = sc.next();

                if (choice != null && (choice.equals("A") || choice.equals("a"))) {
                    System.out.println("按姓名查询");
                    System.out.println("请输入你的姓名：");
                    String name = sc.next();
                    binarySearch(name, 1);
                    manage();
                }else if (choice != null && (choice.equals("B") || choice.equals("b"))) {
                    System.out.println("按学号查询");
                    System.out.println("请输入你的学号：");
                    String id = sc.next();
                    binarySearch(id, 2);
                    manage();
                }else if (choice != null && (choice.equals("C") || choice.equals("c"))) {
                    System.out.println("按房号查询");
                    System.out.println("请输入你的房号：");
                    String room = sc.next();
                    binarySearch(room, 3);
                    manage();
                }else {
                    System.out.println("输入错误");
                    manage();
                }
                break;
            case "2":
                // 添加菜单
                System.out.println("输入名字:");
                String name = sc.next();
                System.out.println("输入学号:");
                String id = sc.next();
                System.out.println("输入房号:");
                String room = sc.next();

                addStudents(name,id,room);
                break;
            case "3":
                // 删除菜单
                System.out.println("输入id:");
                String id1 = sc.next();
                deleteStudent(id1);
                break;
            case "4":
                // 修改菜单
            default:
                System.out.println("输入错误,重新输入");
                manage();
        }
    }

    public static class Student {
        private String name;
        private String id;
        private String room;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public Student(String name, String id, String room) {
            this.name = name;
            this.id = id;
            this.room = room;
        }
    }
    // 按名字查询
    public static void binarySearch(String key, int choice) {
        int left = 0;
        int right = count - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            switch (choice) {
                case 1:
                    if (students[mid].name.compareTo(key) == 0) {
                        System.out.println("学生信息 - 姓名: " + students[mid].name + ", 学号: " + students[mid].id + ", 房号: " + students[mid].room);
                        return;
                    } else if (students[mid].name.compareTo(key) < 0) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                    break;
                case 2:
                    if (students[mid].id.compareTo(key) == 0) {
                        System.out.println("学生信息 - 姓名: " + students[mid].name + ", 学号: " + students[mid].id + ", 房号: " + students[mid].room);
                        return;
                    } else if (students[mid].id.compareTo(key) < 0) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                    break;
                case 3:
                    if (students[mid].room.compareTo(key) == 0) {
                        System.out.println("学生信息 - 姓名: " + students[mid].name + ", 学号: " + students[mid].id + ", 房号: " + students[mid].room);
                        return;
                    } else if (students[mid].room.compareTo(key) < 0) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                    break;
            }
        }
        System.out.println("查无此人");
    }
    // 添加学生
    private static void addStudents(String name, String id, String room) {
        Student newStudent = new Student(name, id, room);

        // 找到插入位置
        int insertIndex = 0;
        while (insertIndex < count && students[insertIndex].name.compareTo(name) < 0) {
            insertIndex++;
        }

        // 将元素插入数组
        System.arraycopy(students, insertIndex, students, insertIndex + 1, count - insertIndex);
        students[insertIndex] = newStudent;

        count++;
        System.out.println("学生信息添加成功");
        manage();
    }
    // 删除学生
    private static void deleteStudent(String id) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (Objects.equals(students[i].id, id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            System.arraycopy(students, index + 1, students, index, count - index - 1);
            count--;
            System.out.println("学生信息删除成功");
        } else {
            System.out.println("查无此人");
        }
        manage();
    }
}
