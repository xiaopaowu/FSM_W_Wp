package linkedlist;

public class Josephu {

	public static void main(String[] args) {

		// ���Կ�ʼ
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5); // ����5��С���ڵ�
		circleSingleLinkedList.show();
		
		
		//���Գ�Ȧ�Ƿ���ȷ
		circleSingleLinkedList.countBOy(1, 2, 5);
	}
	
	

}

// ��������������
class CircleSingleLinkedList {
	// ����һ��first�ڵ㣬û�б��
	private Boy first = new Boy(-1);

	// ������ӽڵ㣬���ɻ�������
	public void addBoy(int nums) {
		// ��nums����һ����֤
		if (nums < 1) {
			System.out.println("numsֵ����ȷ");
			return;
		}

		Boy curBoy = null; // ����һ������ָ�룬�����ƶ����
		// ʹ��forѭ��
		for (int i = 1; i <= nums; i++) {
			Boy boy = new Boy(i);
			// ����ǵ�һ��С��
			if (i == 1) {
				first = boy;
				first.setNext(first); // ���ɻ�
				curBoy = first; // first�ǲ��ܶ��ģ�ʹ��curtBoy��ָ��
			} else {
				curBoy.setNext(boy);
				boy.setNext(first);
				// !!!!�ǵý�curBoy����ƶ�һ��
				curBoy = boy;
			}

		}
	}

	// ������ǰ��������
	public void show() {
		// �ж��Ƿ�Ϊ��
		if (first == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ��Ȼ��Ҫ����ָ��
		Boy curBoy = first;
		while (true) {
			System.out.printf("���ӵı��%d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {
				// ˵��������ϣ�ֻʣ���һ���ڵ���
				break;
			}
			curBoy = curBoy.getNext(); // curBoy����
		}
	}

	/**
	 * 
	 * @param startNo
	 *            ��ʾ�ӵڼ���С����ʼ����
	 * @param countNum
	 *            ��Ҫ������
	 * @param nums
	 *            ������
	 */
	// �����û����룬������ӵĳ�ȥ��˳��
	public void countBOy(int startNo, int countNum, int nums) {
		// �������ݺ�����
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("������������");
		}
		// ��ɳ�Ȧ���ȴ�������ָ��
		Boy helper = first;
		while (true) {
			if (helper.getNext() == first) { // ˵��helperָ�����С���ڵ�
				break;
			}
			helper = helper.getNext();
		}

		// ����֮ǰ������first��helper�ƶ�statNo-1��
		for (int j = 0; j < startNo - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}

		// ��ʼ������first��helper��Ҫ�ƶ�countNum-1��,Ȼ���Ȧ
		// ѭ��������ֱ��ֻʣ���һ���ڵ�
		while (true) {
			if (helper == first) {
				break; // ���һ���ڵ�
			}
			for (int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			// firstָ��Ľڵ����Ҫ��Ȧ��
			System.out.printf("С��%d��Ȧ\n", first.getNo());
			// ��ʱ��firstָ��Ľڵ��ȥ��
			first = first.getNext();
			//  helper = helper.getNext();  helper����Ҫ����
			helper.setNext(first);   //helper��ָ��Ҫ����
		}
		System.out.printf("�������Ȧ�е�С�����%d \n",first.getNo());
	}

}

// �ȴ���һ��Boy�࣬��ʾ�ڵ�
class Boy {
	private int no;// ���
	private Boy next; // ָ����һ���ڵ㣬Ĭ��Ϊnull

	public Boy(int no) {
		this.no = no;
	}

	// ��Ϊno��next����˽�б���������ͨ����ݼ��������ɷ���
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

}