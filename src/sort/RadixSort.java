package sort;

import java.util.Arrays;

public class RadixSort {

	public static void main(String args[]) {
		int[] arr = { 53, 3, 542, 748, 14, 214 };

		radixSort(arr);
	}

	// ��������
	public static void radixSort(int[] arr) {

		// ��1�֣���Ը�λ��

		// ����һ����ά���飬��ʾʮ��Ͱ��ÿ��Ͱ����һά����
		// Ϊ�˷�ֹͰ�з�����ʱ�������ÿ��Ͱ�Ĵ�СΪarr.length
		int[][] bucket = new int[10][arr.length]; // �ռ任ʱ��

		// Ϊ�˼�¼ÿ��Ͱ��ʵ�ʴ���˼������ݣ����Ƕ���һ��һά���飬����¼
		int[] bucletelementCounts = new int[10];

		// ��һ�֣�ȡ��Ԫ�ظ�λ
		for (int j = 0; j < arr.length; j++) {
			// ȡ����λԪ��
			int digitOfElement = arr[j] % 10;
			// �����Ӧ��Ͱ��
			bucket[digitOfElement][bucletelementCounts[digitOfElement]] = arr[j];
			bucletelementCounts[digitOfElement]++; // �´ν������Ͱ�е�����Ĭ��������

		}

		// ����Ͱ��˳������ȡ����
		int index = 0;
		// ����ÿһ��Ͱ������Ͱ�е����ݣ� ����ԭ����
		for (int k = 0; k < bucletelementCounts.length; k++) {
			// �ж�Ͱ����û������
			if (bucletelementCounts[k] != 0) {
				// ѭ����Ͱ
				// ����k��һά���飬����
				for (int l = 0; l < bucletelementCounts[k]; l++) {
					arr[index++] = bucket[k][l]; // indexҪ�仯
				}
			}
			
			// ��һ�ִ������Ҫ��bucletelementCounts[k]��0 ������������������������������
			bucletelementCounts[k] = 0;
		}

		System.out.println("��һ�ֶԸ�λ��������" + Arrays.toString(arr));
		
		
		
		
		
		

		// ==========================================

		// ��2�֣����ʮλ��

		for (int j = 0; j < arr.length; j++) {
			// ȡ��ʮλԪ��
			int digitOfElement = arr[j] / 10 % 10;
			// �����Ӧ��Ͱ��
			bucket[digitOfElement][bucletelementCounts[digitOfElement]] = arr[j];
			bucletelementCounts[digitOfElement]++; // �´ν������Ͱ�е�����Ĭ��������

		}

		// ����Ͱ��˳������ȡ����
		int index1 = 0;
		// ����ÿһ��Ͱ������Ͱ�е����ݣ� ����ԭ����
		for (int k = 0; k < bucletelementCounts.length; k++) {
			// �ж�Ͱ����û������
			if (bucletelementCounts[k] != 0) {
				// ѭ����Ͱ
				// ����k��һά���飬����
				for (int l = 0; l < bucletelementCounts[k]; l++) {
					arr[index1++] = bucket[k][l]; // indexҪ�仯
				}
			}
			
			// ��2�ֹ���Ҫ��ÿ��bucketElementCounts[k]����
			bucletelementCounts[k] = 0 ;
		}

		System.out.println("�ڶ��ֶ�ʮλ��������" + Arrays.toString(arr));
		
		
		
		

		// ==========================================

		// ��2�֣����ʮλ��

		for (int j = 0; j < arr.length; j++) {
			// ȡ��ʮλԪ��
			int digitOfElement = arr[j] / 100 % 10;
			// �����Ӧ��Ͱ��
			bucket[digitOfElement][bucletelementCounts[digitOfElement]] = arr[j];
			bucletelementCounts[digitOfElement]++; // �´ν������Ͱ�е�����Ĭ��������

		}

		// ����Ͱ��˳������ȡ����
		int index2 = 0;
		// ����ÿһ��Ͱ������Ͱ�е����ݣ� ����ԭ����
		for (int k = 0; k < bucletelementCounts.length; k++) {
			// �ж�Ͱ����û������
			if (bucletelementCounts[k] != 0) {
				// ѭ����Ͱ
				// ����k��һά���飬����
				for (int l = 0; l < bucletelementCounts[k]; l++) {
					arr[index2++] = bucket[k][l]; // indexҪ�仯
				}
			}
			// ��3�ֹ���Ҫ��ÿ��bucketElementCounts[k]����
						bucletelementCounts[k] = 0 ;
		}

		System.out.println("��3�ֶ԰�λ��������" + Arrays.toString(arr));

	}
}
