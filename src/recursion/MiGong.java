package recursion;


//˼����  ������·����


public class MiGong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ������ά���飬ģ���Թ�
		// ��ͼ
		int[][] map = new int[8][7];
		// 1��ʾǽ
		// ����ȫ��1
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}

		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}

		// ���õ���
		map[3][1] = 1;
		map[3][2] = 1;

		// �����ͼ
		System.out.println("��ͼ���£�  ");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		
		// ʹ�õݹ���ݸ�С����·
		setWay2(map,1,1);
		
		//����µ�ͼ
		System.out.println("С���߹��ĵ�ͼ���£�  ");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}


	
	
	
	
	// ˵��
	// ��С���ܵ���nap[6][5]��˵���ɵ���
	// Լ��map[i][j] = 0����ʾ��û�߹�
	// Լ��map[i][j] = 2����ʾ������
	// Լ��map[i][j] = 3����ʾ�߹������߲�ͨ ������ʱ������������·��
	// ���Թ�ǰ����Ҫȷ��һ�����ԣ��� -- �� -- �� ---�����õ��߲�ͨ���ڻ���
	/**
	 * 
	 * @param map
	 *            ��ʾ��ͼ
	 * @param i
	 *            ���ĸ�λ�ÿ�ʼ����
	 * @param j
	 * @return
	 */

	public static boolean setWay(int[][] map, int i, int j) {
		if (map[6][5] == 2) {
			return true;
		} else {
			// �ȿ�·��û���߹�
			if (map[i][j] == 0) {
				// ��������
				map[i][j] = 2; // �ٶ��õ������ͨ
				if (setWay(map, i + 1, j)) { // ������
					return true;
				} else if (setWay(map, i, j + 1)) { // ������
					return true;
				} else if (setWay(map, i - 1, j)) { // ������
					return true;
				} else if (setWay(map, i, j - 1)) { // ������
					return true;
				} else {
					map[i][j] = 3; // ����·���߲�ͨ����Ϊ3
					return false;
				}
			} else { // ���map[i][j]��Ϊ0������Ϊ1��2��3
				return false;

			}
		}
	}

	
	
	//�޸Ĳ��ԣ�   �� -- �� -- �� ---��
	public static boolean setWay2(int[][] map, int i, int j) {
		if (map[6][5] == 2) {
			return true;
		} else {
			// �ȿ�·��û���߹�
			if (map[i][j] == 0) {
				// ��������
				map[i][j] = 2; // �ٶ��õ������ͨ
				if (setWay2(map, i - 1, j)) { // ������
					return true;
				} else if (setWay2(map, i, j + 1)) { // ������
					return true;
				} else if (setWay2(map, i + 1, j)) { // ������
					return true;
				} else if (setWay2(map, i, j - 1)) { // ������
					return true;
				} else {
					map[i][j] = 3; // ����·���߲�ͨ����Ϊ3
					return false;
				}
			} else { // ���map[i][j]��Ϊ0������Ϊ1��2��3
				return false;

			}
		}
	}

}
