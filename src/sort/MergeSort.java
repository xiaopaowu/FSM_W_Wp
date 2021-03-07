package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

	public static void main(String[] args) {
		//int arr[] = { 25, 101, 34, 20, 1, 15, 59 };
	
		
		// �����ٶ�
		int arr[] = new int[800000]; // ����һ������Ϊ80000������
		for (int i = 0; i < 800000; i++) {
			arr[i] = (int) (Math.random() * 8000000);
		}

		int temp[] = new int[arr.length];   // �鲢������Ҫһ������Ŀռ�
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("����ǰ��ʱ�䣺 " + date1Str);
		
		mergeSort(arr, 0, arr.length-1, temp);

		//System.out.println("�鲢��"+ Arrays.toString(arr));  
		

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("������ʱ�䣺 " + date2Str);
		
	}

	
	
	
	
	
	
	// �� + �ϵķ���
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left <right) {
			int mid  = (left+right)/2;
			// ����ݹ���зֽ�
			mergeSort(arr, left, mid, temp);
			// ���ҵݹ���зֽ�
			mergeSort(arr, mid+1, right, temp);    // ����� mid +1
			
			
			// ÿ�ֽ�һ�ξͺͲ�һ��
			merge(arr, left, mid, right, temp);
		}
	}
	
	
	
	
	
	
	
	
	// �ϲ��Ĺ���
	/**
	 * 
	 * @param arr
	 *            �����������
	 * @param left
	 *            ����������еĳ�ʼ����
	 * @param mid
	 *            �м�����
	 * @param right
	 *            �ұ�����
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left; // ��ʼ��i������������еĳ�ʼ����
		int j = mid + 1; // �ұ��������еĳ�ʼ����
		int t = 0; // ָ��temp����ĵ�ǰ����

		// �Ȱ��������ߣ��Ѿ�����ģ������ݰ�������䵽temp������ȥ
		// ֪���������ߵ��������У���һ�ߴ������Ϊֹ
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				// ��С���Ǹ��ŵ�temp��ȥ
				temp[t] = arr[i];
				// �ǵ�Ҫ����
				i += 1;
				t += 1;
			} else {
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}

		// ����ʣ�����ݵ�һ�ߵ� ʣ�����ݣ� һ��ȫ����䵽temp��ȥ
		while (i <= mid) { // ˵����ߵ������� ����ʣ��Ԫ��
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}
		while (j <= right) {
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		// ��temp�����ԭ�������� ԭ����
		t = 0;
		int templeft = left;
		while (templeft <= right) {
			arr[templeft] = temp[t];
			t += 1;
			templeft += 1;
		}

	}
}
