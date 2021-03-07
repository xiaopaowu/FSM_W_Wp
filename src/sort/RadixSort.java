package sort;

import java.util.Arrays;

public class RadixSort {

	public static void main(String args[]) {
		int[] arr = { 53, 3, 542, 748, 14, 214 };

		radixSort(arr);
	}

	// 基数排序
	public static void radixSort(int[] arr) {

		// 第1轮，针对个位数

		// 定义一个二维数组，表示十个桶，每个桶都是一维数组
		// 为了防止桶中放数的时候溢出，每个桶的大小为arr.length
		int[][] bucket = new int[10][arr.length]; // 空间换时间

		// 为了记录每个桶，实际存放了几个数据，我们定义一个一维数组，来记录
		int[] bucletelementCounts = new int[10];

		// 第一轮，取出元素个位
		for (int j = 0; j < arr.length; j++) {
			// 取出个位元素
			int digitOfElement = arr[j] % 10;
			// 放入对应的桶中
			bucket[digitOfElement][bucletelementCounts[digitOfElement]] = arr[j];
			bucletelementCounts[digitOfElement]++; // 下次进入这个桶中的数据默认往下排

		}

		// 按照桶的顺序（依次取出）
		int index = 0;
		// 遍历每一个桶，并将桶中的数据， 放入原数组
		for (int k = 0; k < bucletelementCounts.length; k++) {
			// 判断桶里有没有数据
			if (bucletelementCounts[k] != 0) {
				// 循环该桶
				// 即第k个一维数组，放入
				for (int l = 0; l < bucletelementCounts[k]; l++) {
					arr[index++] = bucket[k][l]; // index要变化
				}
			}
			
			// 第一轮处理后，需要将bucletelementCounts[k]置0 ！！！！！！！！！！！！！！！
			bucletelementCounts[k] = 0;
		}

		System.out.println("第一轮对个位的排序处理：" + Arrays.toString(arr));
		
		
		
		
		
		

		// ==========================================

		// 第2轮，针对十位数

		for (int j = 0; j < arr.length; j++) {
			// 取出十位元素
			int digitOfElement = arr[j] / 10 % 10;
			// 放入对应的桶中
			bucket[digitOfElement][bucletelementCounts[digitOfElement]] = arr[j];
			bucletelementCounts[digitOfElement]++; // 下次进入这个桶中的数据默认往下排

		}

		// 按照桶的顺序（依次取出）
		int index1 = 0;
		// 遍历每一个桶，并将桶中的数据， 放入原数组
		for (int k = 0; k < bucletelementCounts.length; k++) {
			// 判断桶里有没有数据
			if (bucletelementCounts[k] != 0) {
				// 循环该桶
				// 即第k个一维数组，放入
				for (int l = 0; l < bucletelementCounts[k]; l++) {
					arr[index1++] = bucket[k][l]; // index要变化
				}
			}
			
			// 第2轮过后，要将每个bucketElementCounts[k]清零
			bucletelementCounts[k] = 0 ;
		}

		System.out.println("第二轮对十位的排序处理：" + Arrays.toString(arr));
		
		
		
		

		// ==========================================

		// 第2轮，针对十位数

		for (int j = 0; j < arr.length; j++) {
			// 取出十位元素
			int digitOfElement = arr[j] / 100 % 10;
			// 放入对应的桶中
			bucket[digitOfElement][bucletelementCounts[digitOfElement]] = arr[j];
			bucletelementCounts[digitOfElement]++; // 下次进入这个桶中的数据默认往下排

		}

		// 按照桶的顺序（依次取出）
		int index2 = 0;
		// 遍历每一个桶，并将桶中的数据， 放入原数组
		for (int k = 0; k < bucletelementCounts.length; k++) {
			// 判断桶里有没有数据
			if (bucletelementCounts[k] != 0) {
				// 循环该桶
				// 即第k个一维数组，放入
				for (int l = 0; l < bucletelementCounts[k]; l++) {
					arr[index2++] = bucket[k][l]; // index要变化
				}
			}
			// 第3轮过后，要将每个bucketElementCounts[k]清零
						bucletelementCounts[k] = 0 ;
		}

		System.out.println("第3轮对百位的排序处理：" + Arrays.toString(arr));

	}
}
