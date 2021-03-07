package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// 希尔排序交换的次数太多，导致效率低下，  需要改进
public class ShellSort {

	public static void main(String[] args) {

		int arr[] = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		shellSort2(arr);
		System.out.println(Arrays.toString(arr));

	/*
	 * 	
	
		// 测试希尔排序原始方法的速度
		int arr1[] = new int[80000]; // 创建一个长度为80000的数组
		for (int i = 0; i < 80000; i++) {
			arr1[i] = (int) (Math.random() * 8000000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间： " + date1Str);

		shellSort2(arr1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间： " + date2Str);
 */
	}

	// 交换法
	public static void shellSort(int[] arr) {
		int temp = 0;
		int count = 0;
		// gap 就是步长
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				// 遍历各组中的所有元素，步长5
				for (int j = i - gap; j >= 0; j -= gap) {
					// 如果当前元素大于加上步长后的元素，说明交换
					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
			// System.out.println("第" + (++count) + "次排序后的数组： ");
			// System.out.println(Arrays.toString(arr));
		}
		// 希尔排序的第一轮
		// 第一轮排序 将 10个数据分成了五组

		/*
		 * 
		 * // 希尔排序第二轮 for (int i = 2; i < arr.length; i++) { // 遍历各组中的所有元素，步长5 for (int
		 * j = i - 2; j >= 0; j -= 2) { // 如果当前元素大于加上步长后的元素，说明交换 if (arr[j] > arr[j +
		 * 2]) { temp = arr[j]; arr[j] = arr[j + 2]; arr[j + 2] = temp; } } }
		 * 
		 * System.out.println("第二次排序后的数组： "); System.out.println(Arrays.toString(arr));
		 * 
		 */
	}
  
	// 改进希尔排序 -- 移位法  （改进的插入排序）
	public static void shellSort2(int[] arr) {

		// gap为增量，并逐步缩小
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// 从第gap个元素开始，逐个对其所在的组进行直接插入排序
			for (int i = gap; i < arr.length; i++) {
				int j = i; // 保存一下 下标
				int temp = arr[j];
				if (arr[j] < arr[j - gap]) { // 看是否需要交换
					while (j - gap >= 0 && temp < arr[j - gap]) { // 等while循环结束后，才说明找到了位置
						arr[j] = arr[j - gap];
						j -= gap; // j需要向前移动 gap 长度
					}
					// 退出while循环后， 就找到了temp的位置
					arr[j] = temp;
				}
			}
		}

	}

}
