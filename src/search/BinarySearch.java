package search;

import java.util.ArrayList;

public class BinarySearch {

	public static void main(String[] args) {

		// 使用二分查找的前提是 有序数组
		int arr[] = { 1, 2, 5, 32, 32, 65, 96, 209 };

		int result = binarySearch(arr, 0, arr.length - 1, 249);

		if (result == -1) {
			System.out.println("不存在该数！");
		} else {
			System.out.println("待查找的数的下标为：    " + result);
		}
		
		
		// 第二种方法   -- 升级
		ArrayList<Integer> resIndexList = binarySearch2(arr, 0, arr.length-1, 1);
		if(resIndexList.size() == 0) {
			System.out.println("不存在");
		}else {
			System.out.println("待查找的数的下标数组为： "+ resIndexList);
		}

	}

	/**
	 * 
	 * @param arr
	 *            数组
	 * @param left
	 *            左边的索引
	 * @param right
	 *            右边的索引
	 * @param val
	 *            要查找的值
	 * @return 返回的值
	 */
	public static int binarySearch(int[] arr, int left, int right, int val) {

		// 注意要考虑没有找到的情况
		// 即 递归停止的条件 ！！！！！！！
		if (left > right || val > arr[arr.length-1] || val < arr[0]) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midval = arr[mid];

		if (val > midval) {
			// 需要向右递归
			return binarySearch(arr, mid + 1, right, val);
		} else if (val < midval) {
			return binarySearch(arr, left, mid - 1, val);
		} else { // 找到的话，直接返回索引
			return mid;
		}
	}
	
	
	
	
	

	// 思考题 ： 当有相同的数时，要把下标都列出来
	// 在找到 mid 值时，不要马上返回，将其放到一个数组中，并且继续向左扫描，将符合条件的选出来
	// 再向 右边扫描，将符合条件的选出
	public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int val) {
		System.out.println("====查找次数=======");
		if (left > right || val > arr[arr.length-1] || val < arr[0]) {
			return new ArrayList<>();           // 不以-1的形式返回 ， 返回一个空的arraylist
 		}
		int mid = (left + right) / 2;
		int midval = arr[mid];

		if (val > midval) {
			// 需要向右递归
			return binarySearch2(arr, mid + 1, right, val);
		} else if (val < midval) {
			return binarySearch2(arr, left, mid - 1, val);
		} else { // 找到的话，直接返回索引

			ArrayList<Integer> resIndexlist = new ArrayList<>();
			// 向左扫描
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || arr[temp] != val) { // 向左扫描的过程中需要退出了
					break;
				} else {
					resIndexlist.add(temp);
					temp--;
				}
			}
			resIndexlist.add(mid);

			// 向右扫描
			temp = mid + 1;
			while (true) {
				if (temp > arr.length-1 || arr[temp] != val) { // 向左扫描的过程中需要退出了
					break;
				} else {
					resIndexlist.add(temp);
					temp++;
				}
			}

			return resIndexlist;
		}
	}

}
