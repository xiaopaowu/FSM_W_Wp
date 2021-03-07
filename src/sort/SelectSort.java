package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
		
		int arr[] = { 101, 34, 119, 1 };
		System.out.println("初始数组： ");
		System.out.println(Arrays.toString(arr));
		selectSort(arr);
		System.out.println("排序后的数组： ");
		System.out.println(Arrays.toString(arr));
		
		
		
		
		//测试选择排序的速度
		int arr1[] = new int[80000];           //创建一个长度为80000的数组
		for (int i =0 ; i<80000;i++) {
			arr1[i] = (int)(Math.random()*8000000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间： " + date1Str);
		
		selectSort(arr1);
		
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间： " + date2Str);
	}

	
	
	// 选择排序
	// 一共需要 总长度 -1 次 排序
	// 每次选出未排序数组中的最小值，然后和 未排序数组中的首元素交换

	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			// 第一轮
			int minIndex = i; // 假定的最小值的下标
			int min = arr[i]; // 假定一个最小值
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) { // 说明假定的最小值不是最小的，需要重置
					min = arr[j];
					minIndex = j;
				}
			}

			// 当数据很多的时候，多一步判断，看是否需要交换
			if (minIndex != i) {
				// 将最小值放在未排序数组的最前端，交换
			arr[minIndex] = arr[i];
			arr[i] = min;
			}
			
		}

	}
}
