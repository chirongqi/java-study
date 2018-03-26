package com.chirq.sort;

public class KuaiSu {

    int count = 0;

    // 快速排序
    public void quick_sort(int s[], int start, int end) {
        if (start > end) {
            return;
        }
        // Swap(s[start], s[(start + end) / 2]); //将中间的这个数和第一个数交换 参见注1
        int i = start, j = end, x = s[start];
        while (i < j) {
            count++;
            while (i < j && s[j] >= x) {
                count++;
                // 从右向左找第一个小于x的数
                j--;
            }
            if (i < j) {
                s[i++] = s[j];
            }
            while (i < j && s[i] < x) {
                count++;
                // 从左向右找第一个大于等于x的数
                i++;
            }
            if (i < j) {
                s[j--] = s[i];
            }
        }

        s[i] = x;
        quick_sort(s, start, i - 1); // 递归调用
        quick_sort(s, i + 1, end);

        System.out.println("快速次数" + count);
    }

    public static void main(String[] args) {
        KuaiSu ks = new KuaiSu();
        int[] s = { 11, 19, 34, 56, 2, 1, 3, 4, 23, 10, 423, 12, 312, 434, 232, 12, 14 };
        // int s[] = { 23, 1, 56, 32, 16, 84, 65, 21 };
        ks.quick_sort(s, 0, s.length - 1);

        for (int i : s) {
            System.out.println(i);
        }
    }
}
