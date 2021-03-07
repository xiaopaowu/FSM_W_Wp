package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {

		int arr[] = { 101, 34, 119, 1, 15 };
		System.out.println("��ʼ���飺 ");
		System.out.println(Arrays.toString(arr));
		insertSort(arr);
		System.out.println("���������飺 ");
		System.out.println(Arrays.toString(arr));

		
		// ���Բ���������ٶ�
		int arr1[] = new int[80000]; // ����һ������Ϊ80000������
		for (int i = 0; i < 80000; i++) {
			arr1[i] = (int) (Math.random() * 8000000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("����ǰ��ʱ�䣺 " + date1Str);

		insertSort(arr1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("������ʱ�䣺 " + date2Str);

	}
	

	// ��������
	// һ������     ���鳤��-1   ������
	// �״�����ʱ������һ��Ԫ�ؿ�������ģ�ʣ��������������
	// ÿ�δӺ�������������У�ȡ����һ���������뵽ǰ��� ����������
	public static void insertSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			// ������������
			int insertVal = arr[i + 1];
			int insertIndex = i; //

			// ��insertVal�ҵ������λ�� [ �Ӻ���ǰɨ�� ]
			// ˵��
			// 1�� insertIndex >=0 ��֤�ڸ�insertVal�ҵ������λ�õ�ʱ�� �±겻Խ��
			// 2�� insertVal < arr[insertIndex] ˵�������������û���ҵ��ʵ���λ��
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex]; // �������
				insertIndex--; // ��ֹԽ��
			}

			// ���˳�whileѭ��ʱ�� ˵��λ���ҵ���
				arr[insertIndex + 1] = insertVal;
		}

	}

}
