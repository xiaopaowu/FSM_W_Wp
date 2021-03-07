package linkedlist;

public class Josephu {

	public static void main(String[] args) {

		// 测试开始
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5); // 加入5个小孩节点
		circleSingleLinkedList.show();
		
		
		//测试出圈是否正确
		circleSingleLinkedList.countBOy(1, 2, 5);
	}
	
	

}

// 创建单向环形链表
class CircleSingleLinkedList {
	// 创建一个first节点，没有编号
	private Boy first = new Boy(-1);

	// 继续添加节点，构成环形链表
	public void addBoy(int nums) {
		// 对nums进行一下验证
		if (nums < 1) {
			System.out.println("nums值不正确");
			return;
		}

		Boy curBoy = null; // 这是一个辅助指针，用于移动添加
		// 使用for循环
		for (int i = 1; i <= nums; i++) {
			Boy boy = new Boy(i);
			// 如果是第一个小孩
			if (i == 1) {
				first = boy;
				first.setNext(first); // 构成环
				curBoy = first; // first是不能动的，使用curtBoy来指导
			} else {
				curBoy.setNext(boy);
				boy.setNext(first);
				// !!!!记得将curBoy向后移动一下
				curBoy = boy;
			}

		}
	}

	// 遍历当前环形链表
	public void show() {
		// 判断是否为空
		if (first == null) {
			System.out.println("链表为空");
			return;
		}
		// 仍然需要辅助指针
		Boy curBoy = first;
		while (true) {
			System.out.printf("孩子的编号%d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {
				// 说明遍历完毕，只剩最后一个节点了
				break;
			}
			curBoy = curBoy.getNext(); // curBoy后移
		}
	}

	/**
	 * 
	 * @param startNo
	 *            表示从第几个小孩开始数数
	 * @param countNum
	 *            需要数几下
	 * @param nums
	 *            总人数
	 */
	// 根据用户输入，输出孩子的出去按顺序
	public void countBOy(int startNo, int countNum, int nums) {
		// 检验数据和理性
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数输入有误");
		}
		// 完成出圈，先创建辅助指针
		Boy helper = first;
		while (true) {
			if (helper.getNext() == first) { // 说明helper指向最后小孩节点
				break;
			}
			helper = helper.getNext();
		}

		// 报数之前，先让first和helper移动statNo-1次
		for (int j = 0; j < startNo - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}

		// 开始报数，first和helper需要移动countNum-1次,然后出圈
		// 循环操作，直到只剩最后一个节点
		while (true) {
			if (helper == first) {
				break; // 最后一个节点
			}
			for (int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			// first指向的节点就是要出圈的
			System.out.printf("小孩%d出圈\n", first.getNo());
			// 这时将first指向的节点出去按
			first = first.getNext();
			//  helper = helper.getNext();  helper不需要后移
			helper.setNext(first);   //helper的指针要后移
		}
		System.out.printf("最后留在圈中的小孩编号%d \n",first.getNo());
	}

}

// 先创建一个Boy类，表示节点
class Boy {
	private int no;// 编号
	private Boy next; // 指向下一个节点，默认为null

	public Boy(int no) {
		this.no = no;
	}

	// 因为no、next都是私有变量，可以通过快捷键快速生成方法
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