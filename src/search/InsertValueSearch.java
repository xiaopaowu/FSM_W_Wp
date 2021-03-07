package search;

public class InsertValueSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i +1;
		}
		// 得到的arr为1 -100 的数组
		

		
		int result = insertValueSearch(arr, 0, arr.length - 1, 10);

		if (result == -1) {
			System.out.println("不存在该数！");
		} else {
			System.out.println("待查找的数的下标为：    " + result);
		}
		
		
		
		
		
	}
	
	
	// 插值查找，是对 二分查找的 一种改进 ， 他能动态变化 mid的值
	// 不同点在于， 这里的mid 比较复杂， 其余没变
	// 整体的查找次数 大大减少了
	
	public static int insertValueSearch(int[] arr, int left, int right , int val) {
		
		System.out.println("====查找次数=======");
		if (left > right || val > arr[arr.length-1] || val < arr[0]) {
			return -1;
		}
		int mid = left + (right - left) * (val -arr[left]) / (arr[right] - arr[left])  ;
		int midval = arr[mid];

		if (val > midval) {
			// 需要向右递归
			return insertValueSearch(arr, mid + 1, right, val);
		} else if (val < midval) {
			return insertValueSearch(arr, left, mid - 1, val);
		} else { // 找到的话，直接返回索引
			return mid;
		}
	}

}
