package search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {

		int[] arr = { 1, 8, 10, 89, 1000, 1234 };
		int result = fibSearch(arr, 1234);

		if (result == -1) {
			System.out.println("�����ڸ�����");
		} else {
			System.out.println("�����ҵ������±�Ϊ��    " + result);
		}

	}

	// mid = low + F(k-1) - 1
	// ��Ҫʹ�õ�쳲���������
	// ʹ�÷ǵݹ鷽��
	public static int[] fib() { // ���ص�������
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;

	}

	// ��д쳲����������㷨
	public static int fibSearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;
		int k = 0; // ��ʶ쳲������ָ���ֵ�� �±�
		int mid = 0; // ���midֵ

		int f[] = fib(); // ��ȡ��쳲���������

		// ��ȡ���ָ��±�
		while (high > f[k] - 1) {
			k++;
		}

		// ��Ϊf[k]��������ĳ��ȣ���arr �ĳ��ȣ� ��Ҫʹ��Arrays�࣬����һ���µ����飬��ָ��temp[]
		// ����Ĳ��ֻ�ʹ��0���
		int[] temp = Arrays.copyOf(arr, f[k]);
		// �� a[high]����� temp
		// ����:
		// temp = {1, 8, 10, 89, 1000, 1234, 0, 0, 0} =�� temp = {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234};
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}

		// ʹ��whileѭ���������ҵ�key
		while (low <= high) {
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) { // ˵��Ӧ�ü������������߼�������
				high = mid - 1;
				// 1��ȫ��Ԫ�� = ǰ��Ԫ�� + ����Ԫ��
				// 2�� f[k] = f[k-1] + f[k-2]
				// 3�� ǰ����k-1��Ԫ�أ����Կ��Լ�����֣�f[k-1] = f[k-2] + f[k-3]
				// 4������f[k-1]��ǰ���������ң�k--
				// ���´�ѭ����ʱ�� mid = f[k-1-1] -1
				k--;
			} else if (key > temp[mid]) {
				low = mid + 1;
				// 1��ȫ��Ԫ�� = ǰ��Ԫ�� + ����Ԫ��
				// 2�� f[k] = f[k-1] + f[k-2]
				// 3�� ������k-2��Ԫ�أ����Կ��Լ�����֣�f[k-1] = f[k-3] + f[k-4]
				// 4������f[k-2]��ǰ���������ң�k-=2
				// ���´�ѭ����ʱ�� mid = f[k-1-2] -1
				k -= 2;
			} else {
				// ��Ҫȷ�������ĸ��±�
				if(mid <= high) {
					return mid;
				}else {
					return high;
				}
		
			}
		}
		return -1;
	}
}
