package search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {

		int[] arr = { 1, 8, 10, 89, 1000, 1234 };
		int result = fibSearch(arr, 1234);

		if (result == -1) {
			System.out.println("不存在该数！");
		} else {
			System.out.println("待查找的数的下标为：    " + result);
		}

	}

	// mid = low + F(k-1) - 1
	// 需要使用到斐波那契数列
	// 使用非递归方法
	public static int[] fib() { // 返回的是数组
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;

	}

	// 编写斐波那契查找算法
	public static int fibSearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;
		int k = 0; // 标识斐波那契分割数值的 下标
		int mid = 0; // 存放mid值

		int f[] = fib(); // 获取到斐波那契数列

		// 获取到分割下标
		while (high > f[k] - 1) {
			k++;
		}

		// 因为f[k]大于数组的长度，即arr 的长度， 需要使用Arrays类，构造一个新的数组，并指向temp[]
		// 不足的部分会使用0填充
		int[] temp = Arrays.copyOf(arr, f[k]);
		// 用 a[high]来填充 temp
		// 举例:
		// temp = {1, 8, 10, 89, 1000, 1234, 0, 0, 0} =》 temp = {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234};
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}

		// 使用while循环来处理，找到key
		while (low <= high) {
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) { // 说明应该继续向数组的左边继续查找
				high = mid - 1;
				// 1、全部元素 = 前边元素 + 后面元素
				// 2、 f[k] = f[k-1] + f[k-2]
				// 3、 前面有k-1个元素，所以可以继续拆分，f[k-1] = f[k-2] + f[k-3]
				// 4、即在f[k-1]的前方继续查找，k--
				// 即下次循环的时候 mid = f[k-1-1] -1
				k--;
			} else if (key > temp[mid]) {
				low = mid + 1;
				// 1、全部元素 = 前边元素 + 后面元素
				// 2、 f[k] = f[k-1] + f[k-2]
				// 3、 后面有k-2个元素，所以可以继续拆分，f[k-1] = f[k-3] + f[k-4]
				// 4、即在f[k-2]的前方继续查找，k-=2
				// 即下次循环的时候 mid = f[k-1-2] -1
				k -= 2;
			} else {
				// 需要确定返回哪个下标
				if(mid <= high) {
					return mid;
				}else {
					return high;
				}
		
			}
		}
		return -1;
	}
}
