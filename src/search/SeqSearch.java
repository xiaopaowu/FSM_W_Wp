package search;

public class SeqSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 2, 43, 43, 2, 53, 21 };
		
		int index = seqSearch(arr, 43);
		if (index == -1) {
			System.out.println("�����ڸ���");
		}
		else {
			System.out.println(index);
		}
		

	}
	
	public static int seqSearch(int[] arr, int val) {
		
		// ���Բ��ң�����һ�ȶԣ��ҵ�����
		for (int i = 0 ; i < arr.length; i++) {
			if(arr[i] == val) {
				return i;
			}
		}
		return -1;
	}

}
