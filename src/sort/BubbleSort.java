package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

	public static void main(String[] args) {

		// int arr[] = { 3, 9, -1, 10, -2 };

		//����ð��������ٶ�
		int arr1[] = new int[80000];           //����һ������Ϊ80000������
		for (int i =0 ; i<80000;i++) {
			arr1[i] = (int)(Math.random()*8000000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("����ǰ��ʱ�䣺 " + date1Str);
		
		bubblesort(arr1);
		
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("������ʱ�䣺 " + date2Str);
		
		//System.out.println("���������飺 ");
		//System.out.println(Arrays.toString(arr1));

	}

	public static void bubblesort(int[] arr) {

		boolean flag = false; // ��ʶ������Ƿ���й����� -- �����㷨���Ż�
		// ��һ�����򣬿����ҵ�����Ԫ�أ��������
		for (int i = arr.length - 1; i >= 0; i--) {

			int temp = 0; // ��ʱ����
			for (int j = 0; j < i; j++) {
				// ���ǰ��������ں���������򽻻������򲻽��д���
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			if (flag == false) { // ��һ�������У�һ�ν�����û�з���
				break;
			} else {
				flag = false; // �ǵ�����flag�� ��һ�λ�Ҫ�жϵ�
			}
		}
	}
}
