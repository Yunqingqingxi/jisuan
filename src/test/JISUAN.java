package test;

import java.util.Scanner;

public class JISUAN {

    public static void main(String[] args) {
        createCalculator();
    }

    // 创建计算器
    public static void createCalculator() {
        // 实现计算器功能，采用交互工作方式，功能菜单下至少2项功能菜单（输入多项式、退出）并可进行连续操作，直至选择退出（可丰富功能；
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择功能：1.输入多项式 2.退出");
        int num = sc.nextInt();


        if (num == 1) {
            // 在选择输入多项式后，输入多项式，通过栈等数据结构实现至少+-*/（）等基本计算器的相关运算操作，回车代表输入结束并计算打印多项式运算结果。
            System.out.println("请输入一个数：");
            String str = sc.next();
            System.out.println("请输入一个运算符：");
            String str1 = sc.next();
            System.out.println("请输入一个数：");
            String str2 = sc.next();
            System.out.println("输入的多项式为：" + str+str1+str2);
            // 调用计算的方法
            str = Calculator.calculate(str,str1,str2); // 计算结果赋值给str，返回给str，以便打印计算结果。
            System.out.println("计算结果为：" + str);
            createCalculator(); // 递归调用
            System.out.println("计算结束");

        } else if (num == 2) {
            System.out.println("退出计算器");
        }
        sc.close();
    }

    public static class Calculator {
        public static String calculate(String str,String str1,String str2) {
            // 主体要求：编写一个计算器软件, 程序设计要求为：
            //（1）采用交互工作方式，功能菜单下至少2项功能菜单（输入多项式、退出）并可进行连续操作，直至选择退出（可丰富功能）；
            //（2）在选择输入多项式后，输入多项式，通过栈等数据结构实现至少+-*/（）等基本计算器的相关运算操作，回车
            // 代表输入结束并计算打印多项式运算结果。
            //（3）多项式运算结果保留两位小数。
            //（4）多项式运算结果中，不能出现负号。
            //（5）多项式运算结果中，如果有多个连续相同项，只计算一个。

            // 读取传来的字符串
            double num1 = Integer.parseInt(str);
            double num2 = Integer.parseInt(str2);

            // 计算结果赋值给str，返回给str，以便打印计算结果。
            if (str1.equals("+")) {
                str = String.valueOf((num1 + num2) * 1.0);
            }
            if (str1.equals("-")) {
                str = String.valueOf((num1 - num2) * 1.0);
            }
            if (str1.equals("*")) {
                str = String.valueOf(num1 * num2 * 1.0);
            }
            if (str1.equals("/")) {
                str = String.valueOf((num1 / num2)/1.0);
            }
            System.out.println("计算结果为：" + str);
            return str;
        }
    }
}
