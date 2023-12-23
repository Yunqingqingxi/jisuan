package test;

public class InsertionSortWithSentinel {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        // 在数组的末尾添加监视哨
        int sentinel = arr[n - 1];
        int j = n - 2;

        // 开始插入排序
        while (j >= 0 && arr[j] > sentinel) {
            arr[j + 1] = arr[j];
            j--;
        }

        // 将监视哨插入到正确的位置
        arr[j + 1] = sentinel;
    }

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3};

        System.out.println("原始数组:");
        printArray(arr);

        // 调用带监视哨的插入排序算法
        insertionSort(arr);

        System.out.println("排序后的数组:");
        printArray(arr);
    }
}
