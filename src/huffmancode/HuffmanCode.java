package huffmancode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//赫夫曼编码 -- 也是变长编码
//变长编码可以减少编码的长度，但由于不是前缀编码，会造成匹配的二义性
//步骤：
//1、将所有单词中的字母出现的次数进行排列，（包括空格），构造赫夫曼树
//2、树中，向左的路径为0，向右的路径为1，给各个字母规定好编码
//3、按照赫夫曼编码，将字符串对应地编码进行处理，可以发现长度有明显的减少，但也是无损处理


//1、Node{data(存放数据), weight(权值), left, right}
//2、得到String对应地byte[]数组
//3、将Node放入List，形式  [Node[data= , weight= ], Node[data= , weight= ], ....]
//4、通过List创建对应的赫夫曼树




// 问题来了
// 编码完成了，那解码该如何实现呢


public class HuffmanCode {

	public static void main(String[] args) {

		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
//		System.out.println(contentBytes.length);   // 40
		
		// 分步过程
		/**
		
		List<Node> nodes = getNodes(contentBytes);
//		System.out.println("nodes = " + nodes);
		
		// 测试创建的二叉树
		System.out.println("=====赫夫曼树======");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
//		System.out.println("===前序遍历=====");
		preOrder(huffmanTreeRoot);
		
		
		// 测试是否生成对应的哈夫曼编码
//		getCodes(huffmanTreeRoot,"",stringBuilder);
		// 使用重载的方法
		getCodes(huffmanTreeRoot);
//		System.out.println("生成的哈夫曼编码： "+ huffmanCodes);
		
		
		// 测试stringBuilder输出的就是那个较长的二进制编码串
		byte[] by =  zip(contentBytes,huffmanCodes);
		System.out.println("by这个  字节   数组为"+Arrays.toString(by));    // 看到原先一长串的1001.。。。变短了，压缩效果明显
		 * 
		 */
		
		
		// 一步到位
		byte[] huffmanCodesBytes = huffmanZip(contentBytes);
		System.out.println("压缩后的字节数组为： "+ Arrays.toString(huffmanCodesBytes) + "   长度 ： " + huffmanCodesBytes.length);
		
		
		
		
		
		// 解码部分的测试
		System.out.println();
		System.out.println("-----------   解码部分的测试 -----------------");
		System.out.println(bytetoBitString(true, (byte)-1));
		decode(huffmanCodes, huffmanCodesBytes);
		
	}
	
	
	
	
	// 完成数据的解压
	/**
	 * 
	 * @param huffmanCodes   赫夫曼编码表map
	 * @param huffmanBytes   赫夫曼编码得到的字节数组
	 * @return     原先的字符串对应的数组
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
		
		//1、先得到这个huffmanBytes这个二进制的字符串 ‘100010000.。。。。’
		StringBuilder stringBuilder = new StringBuilder();
		// 将byte数组转成二进制的字符串
		for(int i = 0; i <huffmanBytes.length;i++) {
			byte b = huffmanBytes[i];
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(bytetoBitString(!flag, b));
		}
	System.out.println("赫夫曼字节数组对应的二进制字符串= "+ stringBuilder.toString());
	return null;
	}
	
	
	
	
	
	
	// 数据的解码
	// 思路
	// 1、将HuffmanCodeBytes重新先转成赫夫曼编码对应的二进制字符串 10010001.......
	// 2、赫夫曼编码对应的二进制字符串 10010001.......  => 对照赫夫曼编码 => "i like like like java do you like a java"
	
	/**
	 * 功能：将一个byte 转成一个二进制 字符串
	 * @param b  
	 * @param flag 标志是否需要补高位，如果为true，表示需要高位；  如果flase，表示不补
	 * @return  是该b对应的二进制的字符串（注意是按照补码来操作的）
	 */
	private static String bytetoBitString(boolean flag, byte b) {
		// 使用一个变量保存b
		int temp = b;     // 也就是将b转成了int
		
		// 如果temp是一个正数，则存在一个补高位的问题
		if(flag) {
			temp  |= 256;      // 按位与 256  ，  假设temp = 1 =》  1 0000 0000 | 0000 0001 = 1 0000 0001
		}
		
		String str = Integer.toBinaryString(temp);        // 返回的是temp对应的二进制  ！！！补码！！！！

		if(flag) {
			// 因为直接输出的话就是补码，所以只要截取部分即可
			return str.substring(str.length() -8 );    // 也就是取后八位
		}else {
			return str;
		}
	
	}
	
	
	
	
	
	
	
	
	// 使用一个方法，将前面的方法都封装起来 ，便于调用
	/**
	 * 
	 * @param bytes 原始的字符串对应的字节数组，即上面的的contentbytes
	 * @return 经过赫夫曼编码（压缩后的）字节数组
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);
		// 根据nodes创建赫夫曼树
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		// 根据赫夫曼树，得到对应的赫夫曼编码 
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		// 根据赫夫曼编码，对原始的字节数组进行压缩
		byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);
		
		return huffmanCodeBytes;

	}
	
	
	// 编写一个方法，将一个字符串对应的byte数组，通过生成的赫夫曼编码表，返回一个对应的 字节数组
	/**
	 * 
	 * @param bytes  原始字符串对应的byte[]
	 * @param huffmanCodes 生成的赫夫曼编码map
	 * @return  返回赫夫曼编码处理后的 字节数组
	 * eg、String content = "i like like like java do you like a java"; ==》 byte[] contentBytes = content.getBytes();
	 *     返回的是该字符串"101000010111111100.。。。。。"对应的byte数组，及8位对应一个byte，放入huffmanCodes中
	 *     huffmanCodes[0] = 10101000(补码) => byte  [反码 10100111  => 原码 11011000 = -88]
	 */
	private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes) {
		
		// 1、利用huffmanCodes 将传入的原始的byte数组 转换成 赫夫曼编码对应的字符串
		StringBuilder stringBUilder = new StringBuilder();
		// 遍历bytes 数组
		for(byte b: bytes) {
			stringBUilder.append(huffmanCodes.get(b));
		}
		
		//System.out.println("测试stringBuilder = " + stringBUilder.toString());
		// 将"101000010111111100.。。。。。"转化为byte[]
		
		// 统计返回的byte[] huffmanCodeBytes 长度
		int len;
		if(stringBUilder.length() %8 == 0) {
			len = stringBUilder.length()/8;
		}else {
			len = stringBUilder.length()/8 + 1;   // 不能被8 整除，就把长度加上1
		}
		// 以上的过程也可以一句话搞定 int len = (stringBUilder.length + 7) % 8
		
		// 创建存储压缩后的byte数组
		byte[] by = new byte[len];
		int index = 0;   //用于记录是第几个byte
		for(int i = 0; i < stringBUilder.length(); i+=8 ) {   // 步长为8，因为每8位对应一个byte
			String strByte;
			// 越界问题，如果i + 8 超过了怎么办
			if(i+8 > stringBUilder.length()) {
				strByte = stringBUilder.substring(i);   // 代表从i取到最后
			}else {
				strByte = stringBUilder.substring(i,i+8);       // 每次截取8位
			}
			// 将strByte转为一个byte[]数组， 并放到by中
			by[index] = (byte)Integer.parseInt(strByte,2);   // 按照 二进制 的方式放入；  记得类型转换，否则不匹配
			index ++ ;
		}
		
		// for循环结束后，就拿到了by这个字节数组
		return by;
	}

	
	
	
	// 生成赫夫曼树 对应的 赫夫曼编码
	// 思路
	// 1、将赫夫曼编码表放在Map<Byte,String>中
	//     32->01, 97->100
	static Map<Byte,String> huffmanCodes = new HashMap<>();
	// 2、在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
	
    
    // 为了调用方便，我们重载getCodes
    private static Map<Byte,String> getCodes(Node root){
    	if(root == null) {
    		return null;
    	}else {
    		// 处理root的左子树
    		getCodes(root.left, "0",stringBuilder);
    		// 处理root的右子树
    		getCodes(root.right,"1",stringBuilder);
    		return huffmanCodes;
    	}
    }
    /**
     * 功能： 将传入的node节点的所有叶子节点的赫夫曼编码存放到huffmanCodes集合中
     * @param node 传入的节点
     * @param code 代表节点的路径，向左为0，向右为1
     * @param stringBuilder  用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
    	StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
    	// 将code加入String Builder2中
    	stringBuilder2.append(code);
    	if(node != null) {
    		// 判断当前node是叶子节点，还是非叶子节点
    		if(node.data == null) {
    			// 非叶子节点，需要递归
    			getCodes(node.left, "0", stringBuilder2);
    			getCodes(node.right, "1", stringBuilder2);
    		}else {
    			// 说明是一个叶子节点，就已经找到了最后
    			huffmanCodes.put(node.data, stringBuilder2.toString());
    		}
    	}
    }
    
	// 前序遍历的方法
	private static void preOrder(Node root) {
		if(root == null) {
			System.out.println("赫夫曼树为空");
		}else{
			root.preOrder();
		}
	}
	private static List <Node> getNodes(byte[] bytes){
		// 创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();

		// 遍历数组bytes,统计 每一个byte出现的次数 --》 map
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) { // 说明为空，Integer都没创建起来
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		
		// 把每个键值对转化成一个Node对象，并加入到nodes集合
		for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		
		return nodes;
	}

	
	// 通过list创建赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		
		while(nodes.size() > 1) {
			// 从小到大排序
			Collections.sort(nodes);
			// 取出第一、二颗最小的二叉树
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			// 创建一颗新的二叉树,他没有data，只有权值
			Node parent  = new Node (null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			// 将已经处理的二叉树移除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// 将新的二叉树加入到nodes中
			nodes.add(parent);
			
		}
		// nodes最后的节点就是哈夫曼树的根节点
		return nodes.get(0);
	}
}

class Node implements Comparable<Node> {
	Byte data;    // 存放数据本身
	int weight;   // 权值，某个元素出现的次数
	Node left;
	Node right;
	
	public Node(Byte data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}

	@Override
	public int compareTo(huffmancode.Node o) {
		// 代表比较时，是从小到大的
		return this.weight-o.weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	
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
	
	
	
}