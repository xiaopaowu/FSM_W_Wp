package huffmancode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//�շ������� -- Ҳ�Ǳ䳤����
//�䳤������Լ��ٱ���ĳ��ȣ������ڲ���ǰ׺���룬�����ƥ��Ķ�����
//���裺
//1�������е����е���ĸ���ֵĴ����������У��������ո񣩣�����շ�����
//2�����У������·��Ϊ0�����ҵ�·��Ϊ1����������ĸ�涨�ñ���
//3�����պշ������룬���ַ�����Ӧ�ر�����д������Է��ֳ��������Եļ��٣���Ҳ��������


//1��Node{data(�������), weight(Ȩֵ), left, right}
//2���õ�String��Ӧ��byte[]����
//3����Node����List����ʽ  [Node[data= , weight= ], Node[data= , weight= ], ....]
//4��ͨ��List������Ӧ�ĺշ�����




// ��������
// ��������ˣ��ǽ�������ʵ����


public class HuffmanCode {

	public static void main(String[] args) {

		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
//		System.out.println(contentBytes.length);   // 40
		
		// �ֲ�����
		/**
		
		List<Node> nodes = getNodes(contentBytes);
//		System.out.println("nodes = " + nodes);
		
		// ���Դ����Ķ�����
		System.out.println("=====�շ�����======");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
//		System.out.println("===ǰ�����=====");
		preOrder(huffmanTreeRoot);
		
		
		// �����Ƿ����ɶ�Ӧ�Ĺ���������
//		getCodes(huffmanTreeRoot,"",stringBuilder);
		// ʹ�����صķ���
		getCodes(huffmanTreeRoot);
//		System.out.println("���ɵĹ��������룺 "+ huffmanCodes);
		
		
		// ����stringBuilder����ľ����Ǹ��ϳ��Ķ����Ʊ��봮
		byte[] by =  zip(contentBytes,huffmanCodes);
		System.out.println("by���  �ֽ�   ����Ϊ"+Arrays.toString(by));    // ����ԭ��һ������1001.����������ˣ�ѹ��Ч������
		 * 
		 */
		
		
		// һ����λ
		byte[] huffmanCodesBytes = huffmanZip(contentBytes);
		System.out.println("ѹ������ֽ�����Ϊ�� "+ Arrays.toString(huffmanCodesBytes) + "   ���� �� " + huffmanCodesBytes.length);
		
		
		
		
		
		// ���벿�ֵĲ���
		System.out.println();
		System.out.println("-----------   ���벿�ֵĲ��� -----------------");
		System.out.println(bytetoBitString(true, (byte)-1));
		decode(huffmanCodes, huffmanCodesBytes);
		
	}
	
	
	
	
	// ������ݵĽ�ѹ
	/**
	 * 
	 * @param huffmanCodes   �շ��������map
	 * @param huffmanBytes   �շ�������õ����ֽ�����
	 * @return     ԭ�ȵ��ַ�����Ӧ������
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
		
		//1���ȵõ����huffmanBytes��������Ƶ��ַ��� ��100010000.����������
		StringBuilder stringBuilder = new StringBuilder();
		// ��byte����ת�ɶ����Ƶ��ַ���
		for(int i = 0; i <huffmanBytes.length;i++) {
			byte b = huffmanBytes[i];
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(bytetoBitString(!flag, b));
		}
	System.out.println("�շ����ֽ������Ӧ�Ķ������ַ���= "+ stringBuilder.toString());
	return null;
	}
	
	
	
	
	
	
	// ���ݵĽ���
	// ˼·
	// 1����HuffmanCodeBytes������ת�ɺշ��������Ӧ�Ķ������ַ��� 10010001.......
	// 2���շ��������Ӧ�Ķ������ַ��� 10010001.......  => ���պշ������� => "i like like like java do you like a java"
	
	/**
	 * ���ܣ���һ��byte ת��һ�������� �ַ���
	 * @param b  
	 * @param flag ��־�Ƿ���Ҫ����λ�����Ϊtrue����ʾ��Ҫ��λ��  ���flase����ʾ����
	 * @return  �Ǹ�b��Ӧ�Ķ����Ƶ��ַ�����ע���ǰ��ղ����������ģ�
	 */
	private static String bytetoBitString(boolean flag, byte b) {
		// ʹ��һ����������b
		int temp = b;     // Ҳ���ǽ�bת����int
		
		// ���temp��һ�������������һ������λ������
		if(flag) {
			temp  |= 256;      // ��λ�� 256  ��  ����temp = 1 =��  1 0000 0000 | 0000 0001 = 1 0000 0001
		}
		
		String str = Integer.toBinaryString(temp);        // ���ص���temp��Ӧ�Ķ�����  ���������룡������

		if(flag) {
			// ��Ϊֱ������Ļ����ǲ��룬����ֻҪ��ȡ���ּ���
			return str.substring(str.length() -8 );    // Ҳ����ȡ���λ
		}else {
			return str;
		}
	
	}
	
	
	
	
	
	
	
	
	// ʹ��һ����������ǰ��ķ�������װ���� �����ڵ���
	/**
	 * 
	 * @param bytes ԭʼ���ַ�����Ӧ���ֽ����飬������ĵ�contentbytes
	 * @return �����շ������루ѹ����ģ��ֽ�����
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);
		// ����nodes�����շ�����
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		// ���ݺշ��������õ���Ӧ�ĺշ������� 
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		// ���ݺշ������룬��ԭʼ���ֽ��������ѹ��
		byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);
		
		return huffmanCodeBytes;

	}
	
	
	// ��дһ����������һ���ַ�����Ӧ��byte���飬ͨ�����ɵĺշ������������һ����Ӧ�� �ֽ�����
	/**
	 * 
	 * @param bytes  ԭʼ�ַ�����Ӧ��byte[]
	 * @param huffmanCodes ���ɵĺշ�������map
	 * @return  ���غշ������봦���� �ֽ�����
	 * eg��String content = "i like like like java do you like a java"; ==�� byte[] contentBytes = content.getBytes();
	 *     ���ص��Ǹ��ַ���"101000010111111100.����������"��Ӧ��byte���飬��8λ��Ӧһ��byte������huffmanCodes��
	 *     huffmanCodes[0] = 10101000(����) => byte  [���� 10100111  => ԭ�� 11011000 = -88]
	 */
	private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes) {
		
		// 1������huffmanCodes �������ԭʼ��byte���� ת���� �շ��������Ӧ���ַ���
		StringBuilder stringBUilder = new StringBuilder();
		// ����bytes ����
		for(byte b: bytes) {
			stringBUilder.append(huffmanCodes.get(b));
		}
		
		//System.out.println("����stringBuilder = " + stringBUilder.toString());
		// ��"101000010111111100.����������"ת��Ϊbyte[]
		
		// ͳ�Ʒ��ص�byte[] huffmanCodeBytes ����
		int len;
		if(stringBUilder.length() %8 == 0) {
			len = stringBUilder.length()/8;
		}else {
			len = stringBUilder.length()/8 + 1;   // ���ܱ�8 �������Ͱѳ��ȼ���1
		}
		// ���ϵĹ���Ҳ����һ�仰�㶨 int len = (stringBUilder.length + 7) % 8
		
		// �����洢ѹ�����byte����
		byte[] by = new byte[len];
		int index = 0;   //���ڼ�¼�ǵڼ���byte
		for(int i = 0; i < stringBUilder.length(); i+=8 ) {   // ����Ϊ8����Ϊÿ8λ��Ӧһ��byte
			String strByte;
			// Խ�����⣬���i + 8 ��������ô��
			if(i+8 > stringBUilder.length()) {
				strByte = stringBUilder.substring(i);   // �����iȡ�����
			}else {
				strByte = stringBUilder.substring(i,i+8);       // ÿ�ν�ȡ8λ
			}
			// ��strByteתΪһ��byte[]���飬 ���ŵ�by��
			by[index] = (byte)Integer.parseInt(strByte,2);   // ���� ������ �ķ�ʽ���룻  �ǵ�����ת��������ƥ��
			index ++ ;
		}
		
		// forѭ�������󣬾��õ���by����ֽ�����
		return by;
	}

	
	
	
	// ���ɺշ����� ��Ӧ�� �շ�������
	// ˼·
	// 1�����շ�����������Map<Byte,String>��
	//     32->01, 97->100
	static Map<Byte,String> huffmanCodes = new HashMap<>();
	// 2�������ɺշ��������ʱ����Ҫȥƴ��·��������һ��StringBuilder���洢ĳ��Ҷ�ӽڵ��·��
    static StringBuilder stringBuilder = new StringBuilder();
	
    
    // Ϊ�˵��÷��㣬��������getCodes
    private static Map<Byte,String> getCodes(Node root){
    	if(root == null) {
    		return null;
    	}else {
    		// ����root��������
    		getCodes(root.left, "0",stringBuilder);
    		// ����root��������
    		getCodes(root.right,"1",stringBuilder);
    		return huffmanCodes;
    	}
    }
    /**
     * ���ܣ� �������node�ڵ������Ҷ�ӽڵ�ĺշ��������ŵ�huffmanCodes������
     * @param node ����Ľڵ�
     * @param code ����ڵ��·��������Ϊ0������Ϊ1
     * @param stringBuilder  ����ƴ��·��
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
    	StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
    	// ��code����String Builder2��
    	stringBuilder2.append(code);
    	if(node != null) {
    		// �жϵ�ǰnode��Ҷ�ӽڵ㣬���Ƿ�Ҷ�ӽڵ�
    		if(node.data == null) {
    			// ��Ҷ�ӽڵ㣬��Ҫ�ݹ�
    			getCodes(node.left, "0", stringBuilder2);
    			getCodes(node.right, "1", stringBuilder2);
    		}else {
    			// ˵����һ��Ҷ�ӽڵ㣬���Ѿ��ҵ������
    			huffmanCodes.put(node.data, stringBuilder2.toString());
    		}
    	}
    }
    
	// ǰ������ķ���
	private static void preOrder(Node root) {
		if(root == null) {
			System.out.println("�շ�����Ϊ��");
		}else{
			root.preOrder();
		}
	}
	private static List <Node> getNodes(byte[] bytes){
		// ����һ��ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();

		// ��������bytes,ͳ�� ÿһ��byte���ֵĴ��� --�� map
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) { // ˵��Ϊ�գ�Integer��û��������
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		
		// ��ÿ����ֵ��ת����һ��Node���󣬲����뵽nodes����
		for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		
		return nodes;
	}

	
	// ͨ��list�����շ�����
	private static Node createHuffmanTree(List<Node> nodes) {
		
		while(nodes.size() > 1) {
			// ��С��������
			Collections.sort(nodes);
			// ȡ����һ��������С�Ķ�����
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			// ����һ���µĶ�����,��û��data��ֻ��Ȩֵ
			Node parent  = new Node (null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			// ���Ѿ�����Ķ������Ƴ�
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// ���µĶ��������뵽nodes��
			nodes.add(parent);
			
		}
		// nodes���Ľڵ���ǹ��������ĸ��ڵ�
		return nodes.get(0);
	}
}

class Node implements Comparable<Node> {
	Byte data;    // ������ݱ���
	int weight;   // Ȩֵ��ĳ��Ԫ�س��ֵĴ���
	Node left;
	Node right;
	
	public Node(Byte data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}

	@Override
	public int compareTo(huffmancode.Node o) {
		// ����Ƚ�ʱ���Ǵ�С�����
		return this.weight-o.weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	
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
	
	
	
}