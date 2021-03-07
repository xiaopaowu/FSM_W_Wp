package stack;

public class Caculator {

	public static void main(String[] args) {
		// ��һ����ɨ��ı��ʽ
		String expression = "16+6*2/5";
		// ��������ջ���ֱ������ݺ������
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		// ������Ҫ����ر���
		int index = 0; // ����ɨ��
		int num1 = 0;
		int num2 = 0;
		int res = 0;
		int oper = 0;
		char ch = ' '; // ÿ��ɨ��ĵõ���char���浽ch
		String keepNum = ""; // ����ƴ�Ӷ�λ��

		// ��ʼɨ��
		while (true) {
			// ���εõ�expression��ÿһ���ַ�
			ch = expression.substring(index, index + 1).charAt(0);
			// �ж��Ƿ��Ż�������
			if (operStack.isOper(ch)) {
				// Ϊ�գ�ֱ��ѹ��ջ
				if (operStack.isEmpty()) {
					operStack.push(ch);
				} else {
					// ��Ϊ�գ���Ҫ�ж����ȼ�
					if (operStack.priority(ch) <= operStack.priority(operStack.peak())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						numStack.push(res);
						// ������Ľ������ջ
						// ����ǰ������ѹ�����ջ
						operStack.push(ch);
					} else {
						operStack.push(ch);
					}
				}
			} else {

				// numStack.push(ch - 48); // ����ֱ������д��ch��һ���ַ�������ȥ48������asc���
				// ��ʽ�������⣬�����λ�� 70 ��ʶ��7�������ջ�ˣ��������
				// �Ľ���ֱ��ɨ�赽���������ջ
				// ��Ҫ����һ���ַ�������������ƴ�� ��λ��
				keepNum += ch;

				// ��ch�Ѿ���expression�����һλ��ֱ����ջ
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				} else {
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						// ����һλ�����������ֱ����վ
						numStack.push(Integer.parseInt(keepNum));
						// !!!!!! Ҫ���keepNum
						keepNum = "";
					}
				}

			}

			// ��index+1�����Ƿ�ɨ�赽���
			index++;
			if (index >= expression.length()) {
				break;
			}
		}
		while (true) {
			// ������ջ�е����ֺͷ������ε���������
			// ֱ������ջֻʣһ�����֣����߷���ջΪ�գ�������㵽�����
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		// �������pop����
		int res2 = numStack.pop();
		System.out.printf("���ʽ %s = %d", expression, res2);
	}

}

// ����һ���࣬��ʾջ
// ��Ҫ��չ���� ������������ȼ�
class ArrayStack2 {
	private int maxSize; // ջ�Ĵ�С
	private int[] stack; // ����ģ��ջ
	private int top = -1; // ջ������ʼ��Ϊ-1

	// ������
	public ArrayStack2(int maxSize) {
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
			System.out.printf("stack[%d]=%d \n", i, stack[i]);
		}
	}

	// ��������������ȼ����ɳ���Աȷ��
	// ʹ�����ֱ�ʾ������Խ�����ȼ�Խ��

	// �ҵ�ջ����ֵ�����ǲ�������
	public int peak() {
		return stack[top];
	}

	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1; // �����Ϲ淶�������
		}
	}

	// �ж��ǲ���һ�������
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	// ���㷽��
	public int cal(int num1, int num2, int oper) {
		int res = 0;
		switch (oper) {
		case '+': // ����ʹ��˫����""
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1; // ע��˳��
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;

		}
		return res;
	}
}