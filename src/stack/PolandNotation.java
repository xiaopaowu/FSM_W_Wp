package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {

		// 完成中缀表达式转换成后缀表达式
		// 说明
		// 1、 1+（（2+3）*4）-5 ==》 1 2 3 + 4 * + 5-
		// 2、直接对str进行操作不方便，先将中缀表达式放到一个ArrayList中
		// 即 [1，+，（，（，2，+3，），*，4，），-，5]
		// 3、中缀表达式转换成对应地后缀表达式的list ==》 [1，2，3，+，4，*，5，-]

		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("中缀表达式对应的列表:   " + infixExpressionList); // 中缀表达式列表
		List<String> parseSuffixExpression = parseSuffixExpressionList(infixExpressionList);
		System.out.println("后缀表达式对应的列表:    " + parseSuffixExpression);
		
		//计算后缀表达式
		System.out.printf("expression = %d", calculate(parseSuffixExpression));

		// List<String> rpnList = getListString(infixExpression);

		// 先定义一个逆波兰表达式
		// (3+4)*5-6
		// String suffixExpression = "3 4 + 5 * 16 -"; // 数字与符号使用空格分隔

		// 思路分析
		// 1、将suffixExpression放入ArrayList中
		// 2、将ArrayList传给一个方法，配合栈完成

		// List<String> rpnList = getListString(suffixExpression);
		// System.out.println("rpnList = " + rpnList);

		// int res = calculate(rpnList);
		// System.out.println("计算的结果是 ： " + res);

	}

	// 方法：将中缀表达式转换成对应地后缀表达式的list ==》 [1，2，3，+，4，*，5，-]
	public static List<String> parseSuffixExpressionList(List<String> ls) {

		// 定义两个栈
		Stack<String> s1 = new Stack<String>(); // 符号栈
		// Stack<String> s2 = new Stack<String>();
		// //整个过程中，s2没有pop任何东西，而且最后结果还要逆序输出，不如用List
		List<String> s2 = new ArrayList<String>(); // 用于储存中间结果的s2

		// 遍历ls
		for (String item : ls) {
			// 如果一个数，就加入s2
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				// 如果是右括号，就不停地从s1中弹出数据，加到s2中，直到遇到左括号
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop(); // 不能忘记，否则左括号无法出栈，消除
			} else {
				// item是一个符号，需要考虑优先级
				// item的运算符优先级小于或等于s1栈顶的优先级，将s1的栈顶符号弹出，加入s2，再次比较
				while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}

				// 还需要将item压入栈顶
				s1.push(item);
			}
		}

		// 将s1中剩余的运算符加入s2中
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}

		// 因为存放到List，因此按顺序输出就是逆波兰表达式对应地list
		return s2;
	}

	// 将中缀表达式转换成对应地list
	public static List<String> toInfixExpressionList(String s) {
		List<String> ls = new ArrayList<String>();
		int i = 0; // 相当于一个指针，用于遍历中缀表达式字符串
		String str; // 用于多位数的拼接工作
		char c;
		do {
			// 如果c是一个非数字，直接加入ls中
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				ls.add("" + c);
				i++;
			} else { // 是数字，就需要拼接
				// 记得将先前的str置成空串
				str = "";
				while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c;
					i++;
				}

				ls.add(str);
			}

		} while (i < s.length());
		return ls;
	}

	// 将逆波兰表达式依次将数据和运算符放入ArrayList中
	public static List<String> getListString(String suffixExpression) {
		// 将suffix进行分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) { // 对split进行遍历，每循环一次，就输出一个ele
			list.add(ele);
		}
		return list;
	}

	// 完成逆波兰表达式运算
	public static int calculate(List<String> ls) {
		// 创建一个栈
		Stack<String> stack = new Stack<String>();
		// 遍历ls
		for (String item : ls) {
			// 使用正则表达式
			if (item.matches("\\d+")) { // 匹配的是多位数
				stack.push(item);
			} else {
				// pop出两个数并运算
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				} else if (item.equals("-")) {
					res = num2 - num1;
				} else if (item.equals("*")) {
					res = num1 * num2;
				} else if (item.equals("/")) {
					res = num2 / num1;
				} else {
					throw new RuntimeException("运算符有误");
				}

				stack.push(res + ""); // 将整数转换为String
			}
		}
		// 数栈中的最后一元素就是计算结果
		return Integer.parseInt(stack.pop());
	}

}

// 增加一个类，用于返回优先级的高低
class Operation {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	// 写一个方法，返回优先级数字
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("不存在该运算符！");
			break;
		}
		return result;
	}
}
