package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

	public static void main(String[] args) {
		//int arr[] = { 25, 101, 34, 20, 1, 15, 59 };
	
		
		// 测试速度
		int arr[] = new int[800000]; // 创建一个长度为80000的数组
		for (int i = 0; i < 800000; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}

		int temp[] = new int[arr.length];   // 归并排序需要一个额外的空间
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间： " + date1Str);
		
		mergeSort(arr, 0, arr.length-1, temp);

		//System.out.println("归并后："+ Arrays.toString(arr));  
		

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间： " + date2Str);
		
	}

	
	
	
	
	
	
	// 分 + 合的方法
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left <right) {
			int mid  = (left+right)/2;
			// 向左递归进行分解
			mergeSort(arr, left, mid, temp);
			// 向右递归进行分解
			mergeSort(arr, mid+1, right, temp);    // 这边是 mid +1
			
			
			// 每分解一次就和并一次
			merge(arr, left, mid, right, temp);
		}
	}
	
	
	
	
	
	
	
	
	// 合并的过程
	/**
	 * 
	 * @param arr
	 *            待排序的数组
	 * @param left
	 *            左边有序序列的初始索引
	 * @param mid
	 *            中间索引
	 * @param right
	 *            右边索引
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left; // 初始化i，左边有序序列的初始索引
		int j = mid + 1; // 右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引

		// 先把左右两边（已经有序的）的数据按规则填充到temp数组中去
		// 知道左右两边的有序序列，有一边处理完毕为止
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				// 把小的那个放到temp中去
				temp[t] = arr[i];
				// 记得要后移
				i += 1;
				t += 1;
			} else {
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}

		// 把有剩余数据的一边的 剩余数据， 一次全部填充到temp中去
		while (i <= mid) { // 说明左边的序列中 还有剩余元素
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}
		while (j <= right) {
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		// 将temp数组的原数拷贝到 原数组
		t = 0;
		int templeft = left;
		while (templeft <= right) {
			arr[templeft] = temp[t];
			t += 1;
			templeft += 1;
		}

	}
}
