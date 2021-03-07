package tree;

import java.util.Arrays;

// 思路分析：
// 1、将数组变化成 大顶/小顶堆

public class HeapSort {

	public static void main(String[] args) {

		// 要求将数组进行升序排列 -- 大顶堆
		int arr[] = { 4, 6, 8, 5, 9 };
		heapSort(arr);
	}

	// 编写一个堆排序的方法
	public static void heapSort(int[] arr) {
		int temp = 0;
		System.out.println("==== 堆排序 ====");
		
//		// 分步完成
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("第一次： "+Arrays.toString(arr));   //  arr[] = { 4, 9, 8, 5, 6 }
//		
//		
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("第二次： "+Arrays.toString(arr));   // arr[] = { 9, 6, 8, 5, 4 }
		
		
		// 完成我们最终的代码
		// 将无序序列构建成一个堆，根据升序需求选择大顶堆
		for(int i = arr.length/2 -1; i>=0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		// 将顶元素与末尾元素交换，将最大元素沉到数组底端
		for(int j = arr.length-1; j>=0;j--) {
			//交换
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
		
		
		System.out.println("数组 "+Arrays.toString(arr));
	}
	
	
	
	

	// 将一个数组调整成一个大顶堆
	/**
	 * 功能： 完成将以i 对应的  非叶子节点的树 调整成大顶堆 
	 * 举例： arr[] = { 4, 6, 8, 5, 9 } => 第一个非叶子节点 i=1，对应的数字是6 => adjustHeap得到 arr[] = { 4, 9, 8, 5, 6 }
	 *  再次调用adjustHeap，传入的 i=0 => arr[] = { 9, 6, 8, 5, 4 }
	 * @param arr
	 *            待调整的数组
	 * @param i
	 *            非叶子节点在数组中的索引
	 * @param length
	 *            对多少个元素进行调整 （个数在逐渐减少）
	 */
	public static void adjustHeap(int arr[], int i, int length) {

		int temp = arr[i]; // 先取出当前元素的值，存为临时变量
		// 开始调整
		// k为以i为根的左子节点，而经过循环之后，k就变成了自己下一级的左子节点
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) { // 说明左子节点的值 小于 右子节点的值
				k++; // k指向右子节点
			}
			if(arr[k] > temp) {   // 子节点大于父节点
				arr[i] = arr[k];    // 把较大的值赋给当前节点
				i = k;   // ！！！ i指向k， 继续循环比较
			}else {
				break;        // ！！！敢break的原因是因为 本来就是 从下至上遍历的
			}
		}
		
		// 当for循环结束后，我们已经将 以i为父节点的树的最大值放在了最顶 （局部调整完毕）
		arr[i] = temp;  // 将temp赋值放到调整后的位置
	}
}
