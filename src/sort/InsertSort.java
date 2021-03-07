package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {

		int arr[] = { 101, 34, 119, 1, 15 };
		System.out.println("初始数组： ");
		System.out.println(Arrays.toString(arr));
		insertSort(arr);
		System.out.println("排序后的数组： ");
		System.out.println(Arrays.toString(arr));

		
		// 测试插入排序的速度
		int arr1[] = new int[80000]; // 创建一个长度为80000的数组
		for (int i = 0; i < 80000; i++) {
			arr1[i] = (int) (Math.random() * 8000000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间： " + date1Str);

		insertSort(arr1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间： " + date2Str);

	}
	

	// 插入排序
	// 一共进行     数组长度-1   次排序
	// 首次排序时，将第一个元素看作有序的，剩余的数看作无序的
	// 每次从后面的无序数组中，取出第一个数，插入到前面的 有序数组中
	public static void insertSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			// 定义待插入的数
			int insertVal = arr[i + 1];
			int insertIndex = i; //

			// 给insertVal找到插入的位置 [ 从后向前扫描 ]
			// 说明
			// 1、 insertIndex >=0 保证在给insertVal找到插入的位置的时候， 下标不越界
			// 2、 insertVal < arr[insertIndex] 说明待插入的数还没有找到适当的位置
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex]; // 将其后移
				insertIndex--; // 防止越界
			}

			// 当退出while循环时， 说明位置找到了
				arr[insertIndex + 1] = insertVal;
		}

	}

}
