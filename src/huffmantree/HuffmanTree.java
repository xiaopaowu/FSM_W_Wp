package huffmantree;

// 思路
// 就是现将一个数组按照从小到大排列（这里用了Comparable这个接口来实现，可以直接用sort方法
// 将数组中的每一个元素都看作 Node
// 每次选出数组中的前两个Node（就是两个最小的），拼成一个新的 Node，并且将原来的这两个Node从数组中删掉
// 每次构造树后，都要将数组重新排列一下
// 使用while循环，当数组中最后只剩下一个元素时，就可以停止了


// 应用： 赫夫曼编码 -- 电讯通信，数据文本的压缩

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {

		int[] arr = {13, 7, 8, 3, 29, 6, 1};
		
		Node root = createHuffmanTree(arr);
		// 测试一把
		preOrder(root);
		
		
	}

	// 编写一个前序遍历的方法
	public static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("是空树，无法遍历");
		}
	}
	
	// 创建赫夫曼树的方法
	public static Node createHuffmanTree(int[] arr) {
		// 为了操作方便
		// 1、 遍历arr数组
		// 2、 将arr的每个元素，构建为一个Node
		// 3、 将Node放入ArrayList中
		List<Node> nodes = new ArrayList<>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}
		
		
		// 循环处理的过程
		while(nodes.size() >1) {  // 处理到最后只剩一个节点
			
		// 先排序，从小到大
		// 由于实现了接口，这边可以直接调用Comparable接口
		Collections.sort(nodes);
		
		// 取出权值最小的两棵二叉树
		Node leftNode = nodes.get(0);       // 排好序的arr
		Node rightNode = nodes.get(1);
		
		// 构建一颗新的二叉树
		Node parent = new Node(leftNode.value + rightNode.value);
		parent.left = leftNode;
		parent.right = rightNode;
		
		// 删除ArrayList中，处理过的二叉树
		nodes.remove(leftNode);
		nodes.remove(rightNode);
		
		// 将parent加入Nodes
		nodes.add(parent);
		
		Collections.sort(nodes);
		//System.out.println("第一次处理后nodes = "+nodes);

		}
		
		// 返回赫夫曼树的root节点
		return nodes.get(0);
	}
}


// 创建节点类
// 为了让Node支持排序Collections集合排序
// 让Node实现Comparable接口
class Node implements Comparable<Node> {
	int value;   // 节点权值
	Node left;    // 左子节点
	Node right;   // 右子节点
	
	
	// 前序遍历
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
		// 想要实现类的对象支持比较(排序), 就必须实现Comparable接口
		// 调用该方法，用于指定 使用 权值  进行的比较
		// 表示从小到大进行排序
		return this.value-o.value;
	}
	
	
}