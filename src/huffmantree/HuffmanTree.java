package huffmantree;

// ˼·
// �����ֽ�һ�����鰴�մ�С�������У���������Comparable����ӿ���ʵ�֣�����ֱ����sort����
// �������е�ÿһ��Ԫ�ض����� Node
// ÿ��ѡ�������е�ǰ����Node������������С�ģ���ƴ��һ���µ� Node�����ҽ�ԭ����������Node��������ɾ��
// ÿ�ι������󣬶�Ҫ��������������һ��
// ʹ��whileѭ���������������ֻʣ��һ��Ԫ��ʱ���Ϳ���ֹͣ��


// Ӧ�ã� �շ������� -- ��Ѷͨ�ţ������ı���ѹ��

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {

		int[] arr = {13, 7, 8, 3, 29, 6, 1};
		
		Node root = createHuffmanTree(arr);
		// ����һ��
		preOrder(root);
		
		
	}

	// ��дһ��ǰ������ķ���
	public static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("�ǿ������޷�����");
		}
	}
	
	// �����շ������ķ���
	public static Node createHuffmanTree(int[] arr) {
		// Ϊ�˲�������
		// 1�� ����arr����
		// 2�� ��arr��ÿ��Ԫ�أ�����Ϊһ��Node
		// 3�� ��Node����ArrayList��
		List<Node> nodes = new ArrayList<>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}
		
		
		// ѭ������Ĺ���
		while(nodes.size() >1) {  // �������ֻʣһ���ڵ�
			
		// �����򣬴�С����
		// ����ʵ���˽ӿڣ���߿���ֱ�ӵ���Comparable�ӿ�
		Collections.sort(nodes);
		
		// ȡ��Ȩֵ��С�����ö�����
		Node leftNode = nodes.get(0);       // �ź����arr
		Node rightNode = nodes.get(1);
		
		// ����һ���µĶ�����
		Node parent = new Node(leftNode.value + rightNode.value);
		parent.left = leftNode;
		parent.right = rightNode;
		
		// ɾ��ArrayList�У�������Ķ�����
		nodes.remove(leftNode);
		nodes.remove(rightNode);
		
		// ��parent����Nodes
		nodes.add(parent);
		
		Collections.sort(nodes);
		//System.out.println("��һ�δ����nodes = "+nodes);

		}
		
		// ���غշ�������root�ڵ�
		return nodes.get(0);
	}
}


// �����ڵ���
// Ϊ����Node֧������Collections��������
// ��Nodeʵ��Comparable�ӿ�
class Node implements Comparable<Node> {
	int value;   // �ڵ�Ȩֵ
	Node left;    // ���ӽڵ�
	Node right;   // ���ӽڵ�
	
	
	// ǰ�����
	public void preOrder() {
	   System.out.println(this);
	   if(this.left != null) {
		   this.left.preOrder();
	   }
	   if(this.right != null) {
		   this.right.preOrder();
	   }
	}
	
	
	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// ��Ҫʵ����Ķ���֧�ֱȽ�(����), �ͱ���ʵ��Comparable�ӿ�
		// ���ø÷���������ָ�� ʹ�� Ȩֵ  ���еıȽ�
		// ��ʾ��С�����������
		return this.value-o.value;
	}
	
	
}