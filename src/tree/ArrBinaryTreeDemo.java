package tree;

// 顺序存储二叉树
public class ArrBinaryTreeDemo {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		
		ArrBinaryTree arrbinarytree = new ArrBinaryTree(arr);
		arrbinarytree.preOrder();      //1, 2, 4, 5, 3, 6, 7 
		
		System.out.println();
		System.out.println("测试数组为空的情况");
		// 要在那边加上 return，否则为空的情况会报错，无法退出
		
		int[] arr_null = {};
		ArrBinaryTree arrbinarytree_null = new ArrBinaryTree(arr_null);
		arrbinarytree_null.preOrder();
		
	}

}


// 实现顺序存储二叉树遍历
class ArrBinaryTree {
	private int[] arr;
	
	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	
	// 重载 preOrder
	public void preOrder() {
		this.preOrder(0);    // 这样使得代码更加干净
	}
	
	// 完成前序遍历
	/**
	 * 
	 * @param index  就是数组的下标
	 */
	public void preOrder(int index) {
		if (arr.length == 0 || arr== null) {
			System.out.println("数组为空，不能按照二叉树 前序遍历");
			return;
		}
		// 首先输出当前元素
		System.out.println(arr[index]);
		// 向左递归遍历
		if(2*index +1<arr.length) {
			preOrder(2*index +1);
		}
		if(2*index +2 < arr.length) {
			preOrder(2*index + 2);
		}
	}
}