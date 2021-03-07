package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {

		int arr[] = { 25, 101, 34, 20, 1, 15, 59 };
		System.out.println("初始数组： ");
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length - 1);
		System.out.println("排序后的数组： ");
		System.out.println(Arrays.toString(arr));

		
		
		
		// 测试快速排序的速度
		int arr1[] = new int[80000]; // 创建一个长度为80000的数组
		for (int i = 0; i < 80000; i++) {
			arr1[i] = (int) (Math.random() * 8000000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间： " + date1Str);

		quickSort(arr1, 0, arr1.length - 1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间： " + date2Str);

	}

	
	
	
	public static void quickSort(int[] arr, int left, int right) {
		int l = left; // 左下标
		int r = right; // 右下标
		int pivot = arr[(right + left) / 2]; // 设置中轴的值
		int temp = 0; // 作为交换时的临时值
		// while 循环的目的是让比 pivot小的 都放到左边， 比它大的放到右边
		while (l < r) {
			// 在pivot的左边一直找， 找到大于或者等于pivot的值才退出
			while (arr[l] < pivot) {
				l += 1;
			}
			// 在pivot的右边一直找， 找到小于或者等于pivot的值才退出
			while (arr[r] > pivot) {
				r -= 1;
			}
			// 如果l >= r成立，说明pivot两边的值 ，已经按照左边全部是小于等于pivot的，右边全部是大于等于pivot值
			if (l >= r) {
				break;
			}

			// 交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

			// 如果交换完毕之后，发现 arr[l] == pivot ， 我们让 r -- （前移）
			if (arr[l] == pivot) {
				r--;
			}
			// 如果交换完毕之后，发现 arr[r] == pivot ， 我们让 l ++ （后移）
			if (arr[r] == pivot) {
				l++;
			}
		}

		// 递归之前的判断
		// 如果 l == r ，必须l++，r--， 否则会栈溢出
		if (l == r) {
			l++;
			r--;
		}

		// 向左递归
		if (left < r) {
			quickSort(arr, left, r);
		}

		// 向右递归
		if (right > l) {
			quickSort(arr, l, right);
		}
	}
}
