package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {

		// �����׺���ʽת���ɺ�׺���ʽ
		// ˵��
		// 1�� 1+����2+3��*4��-5 ==�� 1 2 3 + 4 * + 5-
		// 2��ֱ�Ӷ�str���в��������㣬�Ƚ���׺���ʽ�ŵ�һ��ArrayList��
		// �� [1��+����������2��+3������*��4������-��5]
		// 3����׺���ʽת���ɶ�Ӧ�غ�׺���ʽ��list ==�� [1��2��3��+��4��*��5��-]

		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("��׺���ʽ��Ӧ���б�:   " + infixExpressionList); // ��׺���ʽ�б�
		List<String> parseSuffixExpression = parseSuffixExpressionList(infixExpressionList);
		System.out.println("��׺���ʽ��Ӧ���б�:    " + parseSuffixExpression);
		
		//�����׺���ʽ
		System.out.printf("expression = %d", calculate(parseSuffixExpression));

		// List<String> rpnList = getListString(infixExpression);

		// �ȶ���һ���沨�����ʽ
		// (3+4)*5-6
		// String suffixExpression = "3 4 + 5 * 16 -"; // ���������ʹ�ÿո�ָ�

		// ˼·����
		// 1����suffixExpression����ArrayList��
		// 2����ArrayList����һ�����������ջ���

		// List<String> rpnList = getListString(suffixExpression);
		// System.out.println("rpnList = " + rpnList);

		// int res = calculate(rpnList);
		// System.out.println("����Ľ���� �� " + res);

	}

	// ����������׺���ʽת���ɶ�Ӧ�غ�׺���ʽ��list ==�� [1��2��3��+��4��*��5��-]
	public static List<String> parseSuffixExpressionList(List<String> ls) {

		// ��������ջ
		Stack<String> s1 = new Stack<String>(); // ����ջ
		// Stack<String> s2 = new Stack<String>();
		// //���������У�s2û��pop�κζ����������������Ҫ���������������List
		List<String> s2 = new ArrayList<String>(); // ���ڴ����м�����s2

		// ����ls
		for (String item : ls) {
			// ���һ�������ͼ���s2
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				// ����������ţ��Ͳ�ͣ�ش�s1�е������ݣ��ӵ�s2�У�ֱ������������
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop(); // �������ǣ������������޷���ջ������
			} else {
				// item��һ�����ţ���Ҫ�������ȼ�
				// item����������ȼ�С�ڻ����s1ջ�������ȼ�����s1��ջ�����ŵ���������s2���ٴαȽ�
				while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}

				// ����Ҫ��itemѹ��ջ��
				s1.push(item);
			}
		}

		// ��s1��ʣ������������s2��
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}

		// ��Ϊ��ŵ�List����˰�˳����������沨�����ʽ��Ӧ��list
		return s2;
	}

	// ����׺���ʽת���ɶ�Ӧ��list
	public static List<String> toInfixExpressionList(String s) {
		List<String> ls = new ArrayList<String>();
		int i = 0; // �൱��һ��ָ�룬���ڱ�����׺���ʽ�ַ���
		String str; // ���ڶ�λ����ƴ�ӹ���
		char c;
		do {
			// ���c��һ�������֣�ֱ�Ӽ���ls��
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				ls.add("" + c);
				i++;
			} else { // �����֣�����Ҫƴ��
				// �ǵý���ǰ��str�óɿմ�
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

	// ���沨�����ʽ���ν����ݺ����������ArrayList��
	public static List<String> getListString(String suffixExpression) {
		// ��suffix���зָ�
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) { // ��split���б�����ÿѭ��һ�Σ������һ��ele
			list.add(ele);
		}
		return list;
	}

	// ����沨�����ʽ����
	public static int calculate(List<String> ls) {
		// ����һ��ջ
		Stack<String> stack = new Stack<String>();
		// ����ls
		for (String item : ls) {
			// ʹ��������ʽ
			if (item.matches("\\d+")) { // ƥ����Ƕ�λ��
				stack.push(item);
			} else {
				// pop��������������
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
					throw new RuntimeException("���������");
				}

				stack.push(res + ""); // ������ת��ΪString
			}
		}
		// ��ջ�е����һԪ�ؾ��Ǽ�����
		return Integer.parseInt(stack.pop());
	}

}

// ����һ���࣬���ڷ������ȼ��ĸߵ�
class Operation {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	// дһ���������������ȼ�����
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
			System.out.println("�����ڸ��������");
			break;
		}
		return result;
	}
}
