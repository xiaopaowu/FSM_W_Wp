package tree;

import java.util.Arrays;

// ˼·������
// 1��������仯�� ��/С����

public class HeapSort {

	public static void main(String[] args) {

		// Ҫ����������������� -- �󶥶�
		int arr[] = { 4, 6, 8, 5, 9 };
		heapSort(arr);
	}

	// ��дһ��������ķ���
	public static void heapSort(int[] arr) {
		int temp = 0;
		System.out.println("==== ������ ====");
		
//		// �ֲ����
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("��һ�Σ� "+Arrays.toString(arr));   //  arr[] = { 4, 9, 8, 5, 6 }
//		
//		
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("�ڶ��Σ� "+Arrays.toString(arr));   // arr[] = { 9, 6, 8, 5, 4 }
		
		
		// ����������յĴ���
		// ���������й�����һ���ѣ�������������ѡ��󶥶�
		for(int i = arr.length/2 -1; i>=0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		// ����Ԫ����ĩβԪ�ؽ����������Ԫ�س�������׶�
		for(int j = arr.length-1; j>=0;j--) {
			//����
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
		
		
		System.out.println("���� "+Arrays.toString(arr));
	}
	
	
	
	

	// ��һ�����������һ���󶥶�
	/**
	 * ���ܣ� ��ɽ���i ��Ӧ��  ��Ҷ�ӽڵ���� �����ɴ󶥶� 
	 * ������ arr[] = { 4, 6, 8, 5, 9 } => ��һ����Ҷ�ӽڵ� i=1����Ӧ��������6 => adjustHeap�õ� arr[] = { 4, 9, 8, 5, 6 }
	 *  �ٴε���adjustHeap������� i=0 => arr[] = { 9, 6, 8, 5, 4 }
	 * @param arr
	 *            ������������
	 * @param i
	 *            ��Ҷ�ӽڵ��������е�����
	 * @param length
	 *            �Զ��ٸ�Ԫ�ؽ��е��� ���������𽥼��٣�
	 */
	public static void adjustHeap(int arr[], int i, int length) {

		int temp = arr[i]; // ��ȡ����ǰԪ�ص�ֵ����Ϊ��ʱ����
		// ��ʼ����
		// kΪ��iΪ�������ӽڵ㣬������ѭ��֮��k�ͱ�����Լ���һ�������ӽڵ�
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) { // ˵�����ӽڵ��ֵ С�� ���ӽڵ��ֵ
				k++; // kָ�����ӽڵ�
			}
			if(arr[k] > temp) {   // �ӽڵ���ڸ��ڵ�
				arr[i] = arr[k];    // �ѽϴ��ֵ������ǰ�ڵ�
				i = k;   // ������ iָ��k�� ����ѭ���Ƚ�
			}else {
				break;        // ��������break��ԭ������Ϊ �������� �������ϱ�����
			}
		}
		
		// ��forѭ�������������Ѿ��� ��iΪ���ڵ���������ֵ������� ���ֲ�������ϣ�
		arr[i] = temp;  // ��temp��ֵ�ŵ��������λ��
	}
}
