package com.chirq.sort;

public class MaoPao {

    /**
     * 
     * <b>方法名</b>：halfSeach_2<br>
     * <b>功能</b>：2分查找法<br>
     * 
     * @author <font color='blue'>chirq</font>
     * @date 2017-3-13 下午5:37:08
     * @param arr
     * @param key
     * @return
     */
    public static int halfSeach_2(int[] arr, int key) {
        int min, max, mid;
        min = 0;
        max = arr.length - 1;
        mid = (max + min) >> 1; // (max+min)/2;
        // System.out.println(mid);
        while (arr[mid] != key) {
            if (key > arr[mid]) {
                min = mid + 1;
            } else if (key < arr[mid]) {
                max = mid - 1;
            }
            if (max < min) {
                return -1;
            }
            mid = (max + min) >> 1;
        }
        return mid;
    }

    /**
     * 
     * <b>方法名</b>：maoPaoSort<br>
     * <b>功能</b>：冒泡<br>
     * 
     * @author <font color='blue'>chirq</font>
     * @date 2017-3-13 下午5:37:26
     * @param arr
     * @return
     */
    public static int[] maoPaoSort(int[] arr) {
        int count = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    count++;
                }
            }
        }
        System.out.println("冒泡次数" + count);
        return arr;
    }

    public static int[] kuaiSuSort(int[] arr) {
        int count = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    count++;
                }
            }
        }

        System.out.println("快速次数" + count);
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 11, 19, 34, 56, 2, 1, 3, 4, 23, 10, 423, 12, 312, 434, 232, 12, 14 };

        int[] arr2 = { 3, 5, 1 };

        maoPaoSort(arr);
        kuaiSuSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("******************");
        int mid = halfSeach_2(arr, 12);

        System.out.println(mid);

        System.out.println((2 + 8) >>> 1);
    }

}