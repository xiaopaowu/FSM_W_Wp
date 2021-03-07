package stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		// ����ArrayStack�Ƿ���ȷ
		ArrayStack stack = new ArrayStack(4);
		String key ="";
		boolean loop= true;
		Scanner scanner = new Scanner(System.in);
		
		while(loop) {
			System.out.println("show:��ʾ��ʾջ");
			System.out.println("exit:�˳�����");
			System.out.println("push:��ʾ������ѹ��ջ");
			System.out.println("pop:��ʾ��ջȡ������");
			System.out.println("������ѡ��");
			key = scanner.next();
			
			switch(key) {
			case"show":
				stack.show();
				break;
			case"exit":       //���һϵ�йرղ���
				scanner.close();
				loop = false;
				break;
			case "push":
				System.out.println("������Ҫѹ��ջ�����ݣ�");
				int value = scanner.nextInt();
				stack.push(value);
				break;
			case"pop":
				try {
					int res = stack.pop();
					System.out.printf("��ջ������Ϊ%d \n", res);
					} catch (Exception e) {
					// TODO: handle exception
						System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
		System.out.println("�����˳���");

	}

}

// ����һ���࣬��ʾջ
class ArrayStack {
	private int maxSize; // ջ�Ĵ�С
	private int[] stack; // ����ģ��ջ
	private int top = -1; // ջ������ʼ��Ϊ-1

	// ������
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize]; // ����ջ���
	}

	// ջ��
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// ջ��
	public boolean isEmpty() {
		return top == -1;
	}

	// ��ջ
	public void push(int value) {
		// ���ж��Ƿ�ջ��
		if (isFull()) {
			System.out.println("----------ջ��----------");
			return;
		}
		top++;
		stack[top] = value;
	}

	// ��ջ
	public int pop() {
		// ���ж�ջ�Ƿ��
		if (isEmpty()) {
			// �׳��쳣
			throw new RuntimeException("----------ջ��----------");
		}
		// ��ջ����ֵ����һ��
		int value = stack[top];
		top--;
		return value;
	}

	// ����ջ
	// ��Ҫ��ջ����ʼ��ʾ����
	public void show() {
		if (isEmpty()) {
			System.out.println("----------ջ��----------");
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d \n", i,stack[i]);
		}
	}
}