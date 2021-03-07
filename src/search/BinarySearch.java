package search;

import java.util.ArrayList;

public class BinarySearch {

	public static void main(String[] args) {

		// ʹ�ö��ֲ��ҵ�ǰ���� ��������
		int arr[] = { 1, 2, 5, 32, 32, 65, 96, 209 };

		int result = binarySearch(arr, 0, arr.length - 1, 249);

		if (result == -1) {
			System.out.println("�����ڸ�����");
		} else {
			System.out.println("�����ҵ������±�Ϊ��    " + result);
		}
		
		
		// �ڶ��ַ���   -- ����
		ArrayList<Integer> resIndexList = binarySearch2(arr, 0, arr.length-1, 1);
		if(resIndexList.size() == 0) {
			System.out.println("������");
		}else {
			System.out.println("�����ҵ������±�����Ϊ�� "+ resIndexList);
		}

	}

	/**
	 * 
	 * @param arr
	 *            ����
	 * @param left
	 *            ��ߵ�����
	 * @param right
	 *            �ұߵ�����
	 * @param val
	 *            Ҫ���ҵ�ֵ
	 * @return ���ص�ֵ
	 */
	public static int binarySearch(int[] arr, int left, int right, int val) {

		// ע��Ҫ����û���ҵ������
		// �� �ݹ�ֹͣ������ ��������������
		if (left > right || val > arr[arr.length-1] || val < arr[0]) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midval = arr[mid];

		if (val > midval) {
			// ��Ҫ���ҵݹ�
			return binarySearch(arr, mid + 1, right, val);
		} else if (val < midval) {
			return binarySearch(arr, left, mid - 1, val);
		} else { // �ҵ��Ļ���ֱ�ӷ�������
			return mid;
		}
	}
	
	
	
	
	

	// ˼���� �� ������ͬ����ʱ��Ҫ���±궼�г���
	// ���ҵ� mid ֵʱ����Ҫ���Ϸ��أ�����ŵ�һ�������У����Ҽ�������ɨ�裬������������ѡ����
	// ���� �ұ�ɨ�裬������������ѡ��
	public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int val) {
		System.out.println("====���Ҵ���=======");
		if (left > right || val > arr[arr.length-1] || val < arr[0]) {
			return new ArrayList<>();           // ����-1����ʽ���� �� ����һ���յ�arraylist
 		}
		int mid = (left + right) / 2;
		int midval = arr[mid];

		if (val > midval) {
			// ��Ҫ���ҵݹ�
			return binarySearch2(arr, mid + 1, right, val);
		} else if (val < midval) {
			return binarySearch2(arr, left, mid - 1, val);
		} else { // �ҵ��Ļ���ֱ�ӷ�������

			ArrayList<Integer> resIndexlist = new ArrayList<>();
			// ����ɨ��
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || arr[temp] != val) { // ����ɨ��Ĺ�������Ҫ�˳���
					break;
				} else {
					resIndexlist.add(temp);
					temp--;
				}
			}
			resIndexlist.add(mid);

			// ����ɨ��
			temp = mid + 1;
			while (true) {
				if (temp > arr.length-1 || arr[temp] != val) { // ����ɨ��Ĺ�������Ҫ�˳���
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
