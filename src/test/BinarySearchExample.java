package test;

import java.util.Arrays;

public class BinarySearchExample {

    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid; // 找到关键字，返回位置
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1; // 关键字未找到
    }

    public static void main(String[] args) {
        int[] keyArray = {2, 5, 8, 12, 16, 23, 38, 42, 50};
        int searchKey = 23;

        System.out.println("关键字序列：" + Arrays.toString(keyArray));
        System.out.println("要查找的关键字：" + searchKey);

        int result = binarySearch(keyArray, searchKey);

        if (result != -1) {
            System.out.println("关键字 " + searchKey + " 在数组中的位置是：" + result);
        } else {
            System.out.println("查找失败，关键字 " + searchKey + " 不在数组中。");
        }
    }
}

