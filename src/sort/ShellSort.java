package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// ϣ�����򽻻��Ĵ���̫�࣬����Ч�ʵ��£�  ��Ҫ�Ľ�
public class ShellSort {

	public static void main(String[] args) {

		int arr[] = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		shellSort2(arr);
		System.out.println(Arrays.toString(arr));

	/*
	 * 	
	
		// ����ϣ������ԭʼ�������ٶ�
		int arr1[] = new int[80000]; // ����һ������Ϊ80000������
		for (int i = 0; i < 80000; i++) {
			arr1[i] = (int) (Math.random() * 8000000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("����ǰ��ʱ�䣺 " + date1Str);

		shellSort2(arr1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("������ʱ�䣺 " + date2Str);
 */
	}

	// ������
	public static void shellSort(int[] arr) {
		int temp = 0;
		int count = 0;
		// gap ���ǲ���
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				// ���������е�����Ԫ�أ�����5
				for (int j = i - gap; j >= 0; j -= gap) {
					// �����ǰԪ�ش��ڼ��ϲ������Ԫ�أ�˵������
					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
			// System.out.println("��" + (++count) + "�����������飺 ");
			// System.out.println(Arrays.toString(arr));
		}
		// ϣ������ĵ�һ��
		// ��һ������ �� 10�����ݷֳ�������

		/*
		 * 
		 * // ϣ������ڶ��� for (int i = 2; i < arr.length; i++) { // ���������е�����Ԫ�أ�����5 for (int
		 * j = i - 2; j >= 0; j -= 2) { // �����ǰԪ�ش��ڼ��ϲ������Ԫ�أ�˵������ if (arr[j] > arr[j +
		 * 2]) { temp = arr[j]; arr[j] = arr[j + 2]; arr[j + 2] = temp; } } }
		 * 
		 * System.out.println("�ڶ������������飺 "); System.out.println(Arrays.toString(arr));
		 * 
		 */
	}
  
	// �Ľ�ϣ������ -- ��λ��  ���Ľ��Ĳ�������
	public static void shellSort2(int[] arr) {

		// gapΪ������������С
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// �ӵ�gap��Ԫ�ؿ�ʼ������������ڵ������ֱ�Ӳ�������
			for (int i = gap; i < arr.length; i++) {
				int j = i; // ����һ�� �±�
				int temp = arr[j];
				if (arr[j] < arr[j - gap]) { // ���Ƿ���Ҫ����
					while (j - gap >= 0 && temp < arr[j - gap]) { // ��whileѭ�������󣬲�˵���ҵ���λ��
						arr[j] = arr[j - gap];
						j -= gap; // j��Ҫ��ǰ�ƶ� gap ����
					}
					// �˳�whileѭ���� ���ҵ���temp��λ��
					arr[j] = temp;
				}
			}
		}

	}

}
