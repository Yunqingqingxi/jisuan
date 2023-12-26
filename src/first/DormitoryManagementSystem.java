// DormitoryManagementSystem.java
package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class DormitoryManagementSystem {
    private static final StudentController studentController = new StudentController();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DormitoryManagementSystem system = new DormitoryManagementSystem();
            system.loadStudents();  // 初始化示例数据
            system.showMainMenu();
        });
    }



    private void showMainMenu() {
        JFrame frame = new JFrame("宿舍管理系统");

        ImageIcon icon = new ImageIcon("F:\\List\\sd\\src\\image\\huaji.jpg"); // Replace with the actual path

        // Set the icon for the frame
        frame.setIconImage(icon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JButton insertButton = new JButton("(1)插入学生信息 ");
        insertButton.addActionListener(e -> {
            frame.dispose();
            showInsertPage();
        });
        panel.add(insertButton, gbc);

        gbc.gridy++;
        JButton deleteButton = new JButton("(2)删除学生信息 ");
        deleteButton.addActionListener(e -> {
            frame.dispose();
            showDeletePage();
        });
        panel.add(deleteButton, gbc);

        gbc.gridy++;
        JButton modifyButton = new JButton("(3)修改学生信息 ");
        modifyButton.addActionListener(e -> {
            frame.dispose();
            showModifyPage();
        });
        panel.add(modifyButton, gbc);

        gbc.gridy++;
        JButton queryButton = new JButton("(4)查询学生信息 ");
        queryButton.addActionListener(e -> {
            frame.dispose();
            showQueryPage();
        });
        panel.add(queryButton, gbc);

        gbc.gridy++;
        JButton exitButton = new JButton("(5)退出系统 ");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton, gbc);

        // 添加按数字键的监听器
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1':
                        insertButton.doClick();
                        break;
                    case '2':
                        deleteButton.doClick();
                        break;
                    case '3':
                        modifyButton.doClick();
                        break;
                    case '4':
                        queryButton.doClick();
                        break;
                    case '5':
                        exitButton.doClick();
                        break;
                    default:
                        break;
                }
            }
        });

        // 设置焦点在主窗口上
        frame.setFocusable(true);
        frame.requestFocus();

        frame.setLocationRelativeTo(null);
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
        JButton logoutButton = new JButton("刷新");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showInsertPage();
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

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 让下拉列表框居中
        gbc.gridwidth = 2;
        JComboBox<String> propertyComboBox = new JComboBox<>(new String[]{"姓名", "学号", "房号"});
        panel.add(propertyComboBox, gbc);

        // 重置gridwidth
        gbc.gridwidth = 1;

        gbc.gridy++;
        JTextField idField = new JTextField(20);
        JLabel idLabel = new JLabel("新值:");
        panel.add(idLabel, gbc);

        gbc.gridx++;
        panel.add(idField, gbc);

        JButton deleteButton = new JButton("删除");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);

        frame.getRootPane().setDefaultButton(deleteButton);

        // 新增：返回上一页按钮
        JButton backButton = new JButton("返回上一页");
        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        // 新增：注销按钮
        JButton logoutButton = new JButton("刷新");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showDeletePage();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // 添加监听器，当选择的属性改变时，更新标签
        propertyComboBox.addActionListener(e -> {
            String selectedProperty = (String) propertyComboBox.getSelectedItem();
            // 设置标签文字 "新" + selectedProperty + ":"
            idLabel.setText("新" + selectedProperty + ":");
        });

        // 删除按钮的动作
        deleteButton.addActionListener(e -> {
            String id = idField.getText();
            String selectedProperty = (String) propertyComboBox.getSelectedItem();
            studentController.deleteStudentByProperty(id, selectedProperty);
            studentController.saveStudentsToFile();
            JOptionPane.showMessageDialog(null, "学生信息删除成功！");
            frame.dispose();
            showMainMenu();
        });

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

        gbc.gridwidth = 1;  // 恢复默认的列宽

        gbc.gridx++;
        JTextField valueField = new JTextField(20);
        valueField.setPreferredSize(new Dimension(150, 20));
        panel.add(valueField, gbc);

        // Map to store dynamically created labels for "新值"
        Map<String, JLabel> valueLabels = new HashMap<>();
        JLabel nameLabel1 = new JLabel("新姓名:");
        JLabel idLabel1 = new JLabel("新学号:");
        JLabel roomLabel = new JLabel("新房号:");
        valueLabels.put("姓名", nameLabel1);
        valueLabels.put("学号", idLabel1);
        valueLabels.put("房号", roomLabel);

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

            dynamicValueLabel.setText("新" + selectedProperty + ":");

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
        JButton logoutButton = new JButton("刷新");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showModifyPage();
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
        JButton logoutButton = new JButton("刷新");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showQueryPage();
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
            showQueryPage();
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
        studentController.createDataDirectory();
        studentController.loadStudentsFromFile();
        // 如果没有加载到学生信息，则初始化一些示例数据
        if (studentController.getAllStudents().isEmpty()) {
            studentController.insertStudent(new Student("张三", "1001", 101));
            studentController.insertStudent(new Student("李四", "1002", 102));
            studentController.insertStudent(new Student("王五", "1003", 103));
        }
    }
}
