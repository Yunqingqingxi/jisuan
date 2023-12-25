// DormitoryManagementSystem.java
package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class DormitoryManagementSystem {
    private StudentController studentController = new StudentController();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DormitoryManagementSystem system = new DormitoryManagementSystem();
            system.loadStudents();  // 初始化示例数据
            system.showMainMenu();
        });
    }

    private void showMainMenu() {
        JFrame frame = new JFrame("宿舍管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JButton insertButton = new JButton("插入学生信息");
        insertButton.addActionListener(e -> {
            frame.dispose();
            showInsertPage();
        });
        panel.add(insertButton, gbc);

        gbc.gridy++;
        JButton deleteButton = new JButton("删除学生信息");
        deleteButton.addActionListener(e -> {
            frame.dispose();
            showDeletePage();
        });
        panel.add(deleteButton, gbc);

        gbc.gridy++;
        JButton modifyButton = new JButton("修改学生信息");
        modifyButton.addActionListener(e -> {
            frame.dispose();
            showModifyPage();
        });
        panel.add(modifyButton, gbc);

        gbc.gridy++;
        JButton queryButton = new JButton("查询学生信息");
        queryButton.addActionListener(e -> {
            frame.dispose();
            showQueryPage();
        });
        panel.add(queryButton, gbc);

        gbc.gridy++;
        JButton exitButton = new JButton("退出系统");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton, gbc);

        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setVisible(true);
    }



    private void showInsertPage() {
        JFrame frame = new JFrame("插入学生信息");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JLabel nameLabel = new JLabel("姓名:");
        panel.add(nameLabel, gbc);

        gbc.gridx++;
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(150, 20)); // Set preferred size
        gbc.anchor = GridBagConstraints.WEST; // Align text field with the left
        panel.add(nameField, gbc);
        gbc.anchor = GridBagConstraints.CENTER; // Reset anchor to default

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel idLabel = new JLabel("学号:");
        panel.add(idLabel, gbc);

        gbc.gridx++;
        JTextField idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(150, 20)); // Set preferred size
        gbc.anchor = GridBagConstraints.WEST; // Align text field with the left
        panel.add(idField, gbc);
        gbc.anchor = GridBagConstraints.CENTER; // Reset anchor to default

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel roomLabel = new JLabel("房号:");
        panel.add(roomLabel, gbc);

        gbc.gridx++;
        JTextField roomField = new JTextField(20);
        roomField.setPreferredSize(new Dimension(150, 20)); // Set preferred size
        gbc.anchor = GridBagConstraints.WEST; // Align text field with the left
        panel.add(roomField, gbc);
        gbc.anchor = GridBagConstraints.CENTER; // Reset anchor to default

        gbc.gridx = 0;
        gbc.gridy++;
        JButton insertButton = new JButton("插入");
        insertButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            int room = Integer.parseInt(roomField.getText());
            Student student = new Student(name, id, room);
            studentController.insertStudent(student);
            studentController.saveStudentsToFile();  // 保存学生信息到文件中
            JOptionPane.showMessageDialog(null, "学生信息插入成功！");
            frame.dispose();
            showMainMenu();
        });
        gbc.gridwidth = 3; // Make the button span 3 columns
        panel.add(insertButton, gbc);
        gbc.gridwidth = 1; // Reset gridwidth to default

        frame.getRootPane().setDefaultButton(insertButton);

        // 新增：返回上一页按钮
        JButton backButton = new JButton("返回上一页");
        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        // 新增：注销按钮
        JButton logoutButton = new JButton("回到主界面");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);// 将返回上一页按钮添加到界面中
        buttonPanel.add(logoutButton);  // 将注销按钮添加到界面中

        gbc.gridy++;
        gbc.gridwidth = 3; // Make the button panel span 3 columns
        panel.add(buttonPanel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth to default

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void showDeletePage() {
        JFrame frame = new JFrame("删除学生信息");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JTextField idField = new JTextField(20);

        JButton deleteButton = new JButton("删除");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                studentController.deleteStudent(id);
                studentController.saveStudentsToFile();   // 保存学生信息到文件中
                JOptionPane.showMessageDialog(null, "学生信息删除成功！");
                frame.dispose();
                showMainMenu();
            }
        });

        frame.getRootPane().setDefaultButton(deleteButton);

        // 新增：返回上一页按钮
        JButton backButton = new JButton("返回上一页");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showMainMenu();
            }
        });

        // 新增：注销按钮
        JButton logoutButton = new JButton("回到主界面");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showMainMenu();
            }
        });

        panel.add(new JLabel("学号:"));
        panel.add(idField);
        panel.add(deleteButton);
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(backButton);// 将返回上一页按钮添加到界面中
        buttonPanel.add(logoutButton);  // 将注销按钮添加到界面中

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showModifyPage() {
        JFrame frame = new JFrame("修改学生信息");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("姓名:");
        panel.add(nameLabel, gbc);

        gbc.gridx++;
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(150, 20));
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel idLabel = new JLabel("学号:");
        panel.add(idLabel, gbc);

        gbc.gridx++;
        JTextField idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(150, 20));
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel propertyLabel = new JLabel("选择要修改的属性:");
        panel.add(propertyLabel, gbc);

        gbc.gridx++;
        JComboBox<String> propertyComboBox = new JComboBox<>(new String[]{"姓名", "学号", "房号"});
        propertyComboBox.setPreferredSize(new Dimension(150, 20));
        panel.add(propertyComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel valueLabel = new JLabel("新值:");
        gbc.gridwidth = 2;  // 让标签跨两列
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(valueLabel, gbc);

        gbc.gridwidth = 1;  // 恢复默认的列宽

        gbc.gridx++;
        JTextField valueField = new JTextField(20);
        valueField.setPreferredSize(new Dimension(150, 20));
        panel.add(valueField, gbc);

        // Map to store dynamically created labels for "新值"
        Map<String, JLabel> valueLabels = new HashMap<>();
        valueLabels.put("姓名", new JLabel("新姓名:"));
        valueLabels.put("学号", new JLabel("新学号:"));
        valueLabels.put("房号", new JLabel("新房号:"));

        // Initially hide all value labels
        valueLabels.values().forEach(label -> label.setVisible(false));

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel dynamicValueLabel = valueLabels.get("姓名");
        panel.add(dynamicValueLabel, gbc);

        gbc.gridx++;
        panel.add(valueField, gbc);

        propertyComboBox.addActionListener(e -> {
            // Hide all value labels
            valueLabels.values().forEach(label -> label.setVisible(false));

            // Show the value label for the selected property
            String selectedProperty = (String) propertyComboBox.getSelectedItem();
            JLabel selectedValueLabel = valueLabels.get(selectedProperty);
            selectedValueLabel.setVisible(true);

            // Update the dynamic label based on the selected property
            dynamicValueLabel.setText(selectedValueLabel.getText());

            // Show selected value label
            dynamicValueLabel.setVisible(true);
        });

        gbc.gridx = 0;
        gbc.gridy++;
        JButton modifyButton = new JButton("修改");
        nameField.addActionListener(e -> modifyButton.doClick());
        idField.addActionListener(e -> modifyButton.doClick());
        modifyButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String property = (String) propertyComboBox.getSelectedItem();
            String newValue = valueField.getText();

            switch (property) {
                case "姓名":
                    studentController.modifyStudentByNameAndID(name, id, newValue, null, 0);
                    break;
                case "学号":
                    studentController.modifyStudentByNameAndID(name, id, null, newValue, 0);
                    break;
                case "房号":
                    int room = Integer.parseInt(newValue);
                    studentController.modifyStudentByNameAndID(name, id, null, null, room);
                    break;
                default:
                    break;
            }

            studentController.saveStudentsToFile();
            JOptionPane.showMessageDialog(null, "学生信息修改成功！");
            frame.dispose();
            showMainMenu();
        });
        gbc.gridwidth = 2;
        panel.add(modifyButton, gbc);
        gbc.gridwidth = 1;

        frame.getRootPane().setDefaultButton(modifyButton);

        // 新增：返回上一页按钮
        JButton backButton = new JButton("返回上一页");
        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        // 新增：注销按钮
        JButton logoutButton = new JButton("回到主界面");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    private void showQueryPage() {
        JFrame frame = new JFrame("查询学生信息");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JTextField keywordField = new JTextField(20);

        JComboBox<String> searchTypeComboBox = new JComboBox<>(new String[]{"姓名", "学号", "房号"});
        panel.add(new JLabel("选择查询属性:"));
        panel.add(searchTypeComboBox);

        JButton queryButton = new JButton("查询");
        keywordField.addActionListener(e -> queryButton.doClick());
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = keywordField.getText();
                String searchType = (String) searchTypeComboBox.getSelectedItem();
                List<Student> result = studentController.queryStudents(keyword, searchType);
                showQueryResult(result);
                frame.dispose();
            }
        });

        // 新增：返回上一页按钮
        JButton backButton = new JButton("返回上一页");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showMainMenu();
            }
        });

        // 新增：注销按钮
        JButton logoutButton = new JButton("回到主界面");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showMainMenu();
            }
        });

        panel.add(new JLabel("输入要查询的数据:"));
        panel.add(keywordField);
        panel.add(queryButton);
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(backButton);// 将返回上一页按钮添加到界面中
        buttonPanel.add(logoutButton);  // 将注销按钮添加到界面中

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showQueryResult(List<Student> students) {
        JFrame frame = new JFrame("查询结果");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Increased the size

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        JTextArea resultArea = new JTextArea();
        if (!students.isEmpty()) {
            for (Student student : students) {
                resultArea.append(student.toString() + "\n");
            }
        } else {
            resultArea.setText("未找到匹配的学生信息");
        }

        // 新增：返回上一页按钮
        JButton backButton = new JButton("返回上一页");
        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        // 新增：注销按钮
        JButton logoutButton = new JButton("回到主界面");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER); // Use a JScrollPane for scrolling
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(backButton);// 将返回上一页按钮添加到界面中
        buttonPanel.add(logoutButton);  // 将注销按钮添加到界面中

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void loadStudents() {
        studentController.loadStudentsFromFile();
        // 如果没有加载到学生信息，则初始化一些示例数据
        if (studentController.getAllStudents().isEmpty()) {
            studentController.insertStudent(new Student("张三", "1001", 101));
            studentController.insertStudent(new Student("李四", "1002", 102));
            studentController.insertStudent(new Student("王五", "1003", 103));
        }
    }
}
