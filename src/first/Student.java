package first;



// Student.java
class Student {
    private String name;
    private String studentId;
    private int roomNumber;

    public Student(String name, String studentId, int roomNumber) {
        this.name = name;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "姓名: " + name + ", 学号: " + studentId + ", 房号: " + roomNumber;
    }

    // 将学生信息转换为字符串，以便写入文件
    public String toFileString() {
        return name + "," + studentId + "," + roomNumber;
    }

    public void fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length == 3) {
            name = parts[0];
            studentId = parts[1];
            roomNumber = Integer.parseInt(parts[2]);
        }
    }

    // 在 Student 类中的构造函数中
    public Student(String fileString) {
        fromFileString(fileString);
    }
}

