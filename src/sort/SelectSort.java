package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
		
		int arr[] = { 101, 34, 119, 1 };
		System.out.println("��ʼ���飺 ");
		System.out.println(Arrays.toString(arr));
		selectSort(arr);
		System.out.println("���������飺 ");
		System.out.println(Arrays.toString(arr));
		
		
		
		
		//����ѡ��������ٶ�
		int arr1[] = new int[80000];           //����һ������Ϊ80000������
		for (int i =0 ; i<80000;i++) {
			arr1[i] = (int)(Math.random()*8000000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("����ǰ��ʱ�䣺 " + date1Str);
		
		selectSort(arr1);
		
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("������ʱ�䣺 " + date2Str);
	}

	
	
	// ѡ������
	// һ����Ҫ �ܳ��� -1 �� ����
	// ÿ��ѡ��δ���������е���Сֵ��Ȼ��� δ���������е���Ԫ�ؽ���

	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			// ��һ��
			int minIndex = i; // �ٶ�����Сֵ���±�
			int min = arr[i]; // �ٶ�һ����Сֵ
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) { // ˵���ٶ�����Сֵ������С�ģ���Ҫ����
					min = arr[j];
					minIndex = j;
				}
			}

			// �����ݺܶ��ʱ�򣬶�һ���жϣ����Ƿ���Ҫ����
			if (minIndex != i) {
				// ����Сֵ����δ�����������ǰ�ˣ�����
			arr[minIndex] = arr[i];
			arr[i] = min;
			}
			
		}

	}
}
