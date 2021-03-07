package tree;

// ˳��洢������
public class ArrBinaryTreeDemo {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		
		ArrBinaryTree arrbinarytree = new ArrBinaryTree(arr);
		arrbinarytree.preOrder();      //1, 2, 4, 5, 3, 6, 7 
		
		System.out.println();
		System.out.println("��������Ϊ�յ����");
		// Ҫ���Ǳ߼��� return������Ϊ�յ�����ᱨ���޷��˳�
		
		int[] arr_null = {};
		ArrBinaryTree arrbinarytree_null = new ArrBinaryTree(arr_null);
		arrbinarytree_null.preOrder();
		
	}

}


// ʵ��˳��洢����������
class ArrBinaryTree {
	private int[] arr;
	
	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	
	// ���� preOrder
	public void preOrder() {
		this.preOrder(0);    // ����ʹ�ô�����Ӹɾ�
	}
	
	// ���ǰ�����
	/**
	 * 
	 * @param index  ����������±�
	 */
	public void preOrder(int index) {
		if (arr.length == 0 || arr== null) {
			System.out.println("����Ϊ�գ����ܰ��ն����� ǰ�����");
			return;
		}
		// ���������ǰԪ��
		System.out.println(arr[index]);
		// ����ݹ����
		if(2*index +1<arr.length) {
			preOrder(2*index +1);
		}
		if(2*index +2 < arr.length) {
			preOrder(2*index + 2);
		}
	}
}