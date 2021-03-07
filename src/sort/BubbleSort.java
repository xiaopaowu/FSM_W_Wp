package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

	public static void main(String[] args) {

		// int arr[] = { 3, 9, -1, 10, -2 };

		//测试冒泡排序的速度
		int arr1[] = new int[80000];           //创建一个长度为80000的数组
		for (int i =0 ; i<80000;i++) {
			arr1[i] = (int)(Math.random()*8000000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间： " + date1Str);
		
		bubblesort(arr1);
		
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间： " + date2Str);
		
		//System.out.println("排序后的数组： ");
		//System.out.println(Arrays.toString(arr1));

	}

	public static void bubblesort(int[] arr) {

		boolean flag = false; // 标识，标记是否进行过交换 -- 用于算法的优化
		// 第一趟排序，可以找到最大的元素，排在最后
		for (int i = arr.length - 1; i >= 0; i--) {

			int temp = 0; // 临时变量
			for (int j = 0; j < i; j++) {
				// 如果前面的数大于后面的数，则交换，否则不进行处理
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			if (flag == false) { // 在一趟排序中，一次交换都没有发生
				break;
			} else {
				flag = false; // 记得重置flag， 下一次还要判断的
			}
		}
	}
}
