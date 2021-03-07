package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {

		int arr[] = { 25, 101, 34, 20, 1, 15, 59 };
		System.out.println("��ʼ���飺 ");
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length - 1);
		System.out.println("���������飺 ");
		System.out.println(Arrays.toString(arr));

		
		
		
		// ���Կ���������ٶ�
		int arr1[] = new int[80000]; // ����һ������Ϊ80000������
		for (int i = 0; i < 80000; i++) {
			arr1[i] = (int) (Math.random() * 8000000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("����ǰ��ʱ�䣺 " + date1Str);

		quickSort(arr1, 0, arr1.length - 1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("������ʱ�䣺 " + date2Str);

	}

	
	
	
	public static void quickSort(int[] arr, int left, int right) {
		int l = left; // ���±�
		int r = right; // ���±�
		int pivot = arr[(right + left) / 2]; // ���������ֵ
		int temp = 0; // ��Ϊ����ʱ����ʱֵ
		// while ѭ����Ŀ�����ñ� pivotС�� ���ŵ���ߣ� ������ķŵ��ұ�
		while (l < r) {
			// ��pivot�����һֱ�ң� �ҵ����ڻ��ߵ���pivot��ֵ���˳�
			while (arr[l] < pivot) {
				l += 1;
			}
			// ��pivot���ұ�һֱ�ң� �ҵ�С�ڻ��ߵ���pivot��ֵ���˳�
			while (arr[r] > pivot) {
				r -= 1;
			}
			// ���l >= r������˵��pivot���ߵ�ֵ ���Ѿ��������ȫ����С�ڵ���pivot�ģ��ұ�ȫ���Ǵ��ڵ���pivotֵ
			if (l >= r) {
				break;
			}

			// ����
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

			// ����������֮�󣬷��� arr[l] == pivot �� ������ r -- ��ǰ�ƣ�
			if (arr[l] == pivot) {
				r--;
			}
			// ����������֮�󣬷��� arr[r] == pivot �� ������ l ++ �����ƣ�
			if (arr[r] == pivot) {
				l++;
			}
		}

		// �ݹ�֮ǰ���ж�
		// ��� l == r ������l++��r--�� �����ջ���
		if (l == r) {
			l++;
			r--;
		}

		// ����ݹ�
		if (left < r) {
			quickSort(arr, left, r);
		}

		// ���ҵݹ�
		if (right > l) {
			quickSort(arr, l, right);
		}
	}
}
