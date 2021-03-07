package stack;

public class Caculator {

	public static void main(String[] args) {
		// 有一个被扫描的表达式
		String expression = "16+6*2/5";
		// 创建两个栈，分别存放数据和运算符
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		// 定义需要的相关变量
		int index = 0; // 用于扫描
		int num1 = 0;
		int num2 = 0;
		int res = 0;
		int oper = 0;
		char ch = ' '; // 每次扫描的得到的char保存到ch
		String keepNum = ""; // 用于拼接多位数

		// 开始扫描
		while (true) {
			// 依次得到expression的每一个字符
			ch = expression.substring(index, index + 1).charAt(0);
			// 判断是符号还是数字
			if (operStack.isOper(ch)) {
				// 为空，直接压入栈
				if (operStack.isEmpty()) {
					operStack.push(ch);
				} else {
					// 不为空，需要判断优先级
					if (operStack.priority(ch) <= operStack.priority(operStack.peak())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						numStack.push(res);
						// 将运算的结果入数栈
						// 将当前操作符压入符号栈
						operStack.push(ch);
					} else {
						operStack.push(ch);
					}
				}
			} else {

				// numStack.push(ch - 48); // 不能直接这样写，ch是一个字符串，减去48，符合asc码表
				// 上式存在问题，比如多位数 70 ，识别到7立马就入栈了，错误操作
				// 改进，直到扫描到运算符才入栈
				// 需要定义一个字符串变量，用于拼接 多位数
				keepNum += ch;

				// 若ch已经是expression的最后一位，直接入栈
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				} else {
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						// 若后一位是运算符，就直接入站
						numStack.push(Integer.parseInt(keepNum));
						// !!!!!! 要清空keepNum
						keepNum = "";
					}
				}

			}

			// 让index+1，看是否扫描到最后
			index++;
			if (index >= expression.length()) {
				break;
			}
		}
		while (true) {
			// 将两个栈中的数字和符号依次弹出，计算
			// 直到数字栈只剩一个数字，或者符号栈为空，代表计算到最后结果
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		// 将最后结果pop出来
		int res2 = numStack.pop();
		System.out.printf("表达式 %s = %d", expression, res2);
	}

}

// 定义一个类，表示栈
// 需要扩展功能 ：定义符号优先级
class ArrayStack2 {
	private int maxSize; // 栈的大小
	private int[] stack; // 数组模拟栈
	private int top = -1; // 栈顶，初始化为-1

	// 构造器
	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize]; // 构造栈完成
	}

	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈
	public void push(int value) {
		// 先判断是否栈满
		if (isFull()) {
			System.out.println("----------栈满----------");
			return;
		}
		top++;
		stack[top] = value;
	}

	// 出栈
	public int pop() {
		// 先判断栈是否空
		if (isEmpty()) {
			// 抛出异常
			throw new RuntimeException("----------栈空----------");
		}
		// 将栈顶的值保存一下
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历栈
	// 需要从栈顶开始显示数据
	public void show() {
		if (isEmpty()) {
			System.out.println("----------栈空----------");
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d \n", i, stack[i]);
		}
	}

	// 返回运算符的优先级，由程序员确定
	// 使用数字表示，数字越大，优先级越高

	// 找到栈顶的值，但是不弹出来
	public int peak() {
		return stack[top];
	}

	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1; // 不符合规范的运算符
		}
	}

	// 判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	// 计算方法
	public int cal(int num1, int num2, int oper) {
		int res = 0;
		switch (oper) {
		case '+': // 不能使用双引号""
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1; // 注意顺序
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