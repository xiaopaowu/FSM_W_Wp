package recursion;

public class Queen8 {

	// ����һ��max����ʾ���ж��ٸ��ʺ�
	int max = 8;
	// ����һ�����飬��ʾ�ʺ���õ�λ�ý��
	int[] array = new int[max];
    static int count =0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 Queen8 queue8= new Queen8();
		 queue8.check(0);
		 
		 
		System.out.println("һ���еķ�������"+count); 
		 
	}

	// ��дһ���������������õ�n���ʺ�
	private void check(int n) {
		if (n == max) {
			print();
			return;
		}

		// ���η���ʺ󣬲��ж��Ƿ��ͻ
		for (int i = 0; i < max; i++) {
			// �Ȱѵ�ǰ�ʺ�n���ŵ����еĵ�һ��
			array[n] = i;
			//�ж��������õĻ��Ƿ��֮ǰ�Ļʺ�λ�ó�ͻ
			if(judge(n)) {
				//���ŷ�n+1���ʺ󣬿�ʼ�ݹ�
				check(n+1);
			}
		}
	}

	// �鿴�����Ƿ��õ�n���ʺ�ʱ�����ǰ��n-1���ʺ��Ƿ���ûʺ��ͻ
	/**
	 * 
	 * @param n
	 * @return
	 */
	private boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			// ˵��
			// 1��array[i]==array[n] ǰn-1���ʺ��Ƿ����µĻʺ���ͬһ��
			// 2��Math.abs(n-i)== Math.abs(array[n]-array[i]) ��ʾ�жϵ�n���ʺ�͵�i���ʺ��Ƿ���ͬһб��
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// дһ�����������Խ��ʺ�ڷŵ�λ�ô�ӡ����
	private void print() {
		count ++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
