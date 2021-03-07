package recursion;

public class Queen8 {

	// 定义一个max，表示共有多少个皇后
	int max = 8;
	// 定义一个数组，表示皇后放置的位置结果
	int[] array = new int[max];
    static int count =0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 Queen8 queue8= new Queen8();
		 queue8.check(0);
		 
		 
		System.out.println("一共有的方法数："+count); 
		 
	}

	// 编写一个方法，用来放置第n个皇后
	private void check(int n) {
		if (n == max) {
			print();
			return;
		}

		// 依次放入皇后，并判断是否冲突
		for (int i = 0; i < max; i++) {
			// 先把当前皇后n，放到该行的第一列
			array[n] = i;
			//判断这样放置的话是否和之前的皇后位置冲突
			if(judge(n)) {
				//接着放n+1个皇后，开始递归
				check(n+1);
			}
		}
	}

	// 查看当我们放置第n个皇后时，检测前面n-1个皇后是否与该皇后冲突
	/**
	 * 
	 * @param n
	 * @return
	 */
	private boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			// 说明
			// 1、array[i]==array[n] 前n-1个皇后是否与新的皇后在同一列
			// 2、Math.abs(n-i)== Math.abs(array[n]-array[i]) 表示判断第n个皇后和第i个皇后是否在同一斜线
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// 写一个方法，可以将皇后摆放的位置打印出来
	private void print() {
		count ++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
