package search;

public class InsertValueSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i +1;
		}
		// �õ���arrΪ1 -100 ������
		

		
		int result = insertValueSearch(arr, 0, arr.length - 1, 10);

		if (result == -1) {
			System.out.println("�����ڸ�����");
		} else {
			System.out.println("�����ҵ������±�Ϊ��    " + result);
		}
		
		
		
		
		
	}
	
	
	// ��ֵ���ң��Ƕ� ���ֲ��ҵ� һ�ָĽ� �� ���ܶ�̬�仯 mid��ֵ
	// ��ͬ�����ڣ� �����mid �Ƚϸ��ӣ� ����û��
	// ����Ĳ��Ҵ��� ��������
	
	public static int insertValueSearch(int[] arr, int left, int right , int val) {
		
		System.out.println("====���Ҵ���=======");
		if (left > right || val > arr[arr.length-1] || val < arr[0]) {
			return -1;
		}
		int mid = left + (right - left) * (val -arr[left]) / (arr[right] - arr[left])  ;
		int midval = arr[mid];

		if (val > midval) {
			// ��Ҫ���ҵݹ�
			return insertValueSearch(arr, mid + 1, right, val);
		} else if (val < midval) {
			return insertValueSearch(arr, left, mid - 1, val);
		} else { // �ҵ��Ļ���ֱ�ӷ�������
			return mid;
		}
	}

}
