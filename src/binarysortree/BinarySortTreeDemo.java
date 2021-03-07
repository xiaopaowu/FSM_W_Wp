package binarysortree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
		BinarySortTree binarysorttree = new BinarySortTree();
		for(int i : arr) {
			binarysorttree.add(new Node(i));
		}
		
		// ��������������
		System.out.println("�����������������============");
		binarysorttree.infixOrder();

//		// ɾ�� 2 ��� Ҷ�ӽڵ�
//		binarysorttree.delete(2);
//		System.out.println("===== ɾ���ڵ�2��   ===========");
//		binarysorttree.infixOrder();
//		
//		// ɾ�� 1 �ڵ㣬ֻ�����ӽڵ�
//		binarysorttree.delete(1);
//		System.out.println("===== ɾ���ڵ�1��   ===========");
//		binarysorttree.infixOrder();
	
		
		// ɾ��3 �ڵ㣬���������������ڵ�
		binarysorttree.delete(7);
		System.out.println("===== ɾ���ڵ�7��   ===========");
		binarysorttree.infixOrder();

	}

}


//��������������
class BinarySortTree {
	private Node root;
	
	
	
	// ��д����
	/**  ���ڵ���������У��ҵ�����������С��ֵ
	 * 
	 * @param node ����Ľڵ㣬���������������ĸ��ڵ�
	 * @return  ��nodeΪ���ڵ��  ���������� �� ��С�ڵ��ֵ����������ڵ�ɾ��
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		// ѭ���Ĳ�����ڵ㣬�ͻ��ҵ���Сֵ
		while(target.left != null) {
			target = target.left;
		}// ��ʱ��target��ָ������С�ڵ�
		delete(target.value);
		return target.value;
		
	}
	
	// ����Ҫɾ���Ľڵ�
	public Node search(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	
	// ����Ҫɾ���ڵ�ĸ��ڵ�
	public Node searchParent(int value) {
		if(root == null) {
			return null;
		}else {
			return root.searchParent(value);
		}
	}
	
	// ɾ���ڵ����������
	public void delete(int value) {
		if(root == null) {
			return;
		}else {
			// 1�����ҵ�Ҫɾ���Ľڵ�
			Node targetNode = search(value);
			if(targetNode == null) {   // ���û���ҵ�Ҫɾ���Ľڵ�
				return;
			}
			
			
			// �����Ŷ���������ֻ��һ���ڵ㣬��targetNode���ҵ��˵ģ��Ǳض���root
			if(root.left == null && root.right == null ) {
				root = null;
				return;
			}
			
			
			// ȥ�ҵ�targetNode�ĸ��ڵ�
			Node parent = searchParent(value);
			// Ҫɾ������Ҷ�ӽڵ�
			if(targetNode.left == null || targetNode.right == null) {    // ˵��targetNode��Ȼһ��
				// �ж�targetNode �Ǹ��ڵ���� ���� ���ӽڵ�
				if(parent.left != null && parent.left.value == value ) {
					parent.left = null;
				}else if(parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
				
				
				
				
			}else if(targetNode.left != null && targetNode.right != null) {
				// ɾ�������������Ľڵ�
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
				
				
				
			}else {
				// ɾ��ֻ��һ�������Ľڵ�   == ��������ܸ���  -- �����Ȱѵ��������д���������ų�������
				// ���Ҫɾ��������ڵ������ӽڵ�
				if(targetNode.left != null) {
					if(parent != null) {    // ��������    
						if(parent.left.value == value) {
							parent.left = targetNode.left;
						}else { // targetNode��parent�����ӽڵ�
							parent.right = targetNode.left;
						}
					}else {
						root = targetNode.left;
					}
					
				}else { // Ҫɾ���Ľڵ������ӽڵ�
					if(parent != null) {
					if(parent.left.value == value) {
						parent.left = targetNode.right;
					}else {  // targetNode��parent�����ӽڵ�
						parent.right = targetNode.right;
					}
				}
				else {
					root = targetNode.right;
				}
				
				}
			}
			
			
			
			
			
			
		}

	}
	// ��ӽڵ�ķ���
	public  void add(Node node) {
		if(root == null) {
			root = node;      // ֱ����rootָ��node����
		}else {
			root.add(node);
		}
	}
	
	// �������
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
		}else {
			System.out.println("����������Ϊ�գ��޷�������");
		}
	}
}

// ����Node�ڵ�
class Node {
	
	int value;         // �����ֵ
	Node left;
	Node right;
	
	public Node(int value) {
		super();
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}


	// �ݹ�ķ�ʽ��ӽڵ� ����Ҫ���ն����������ķ�ʽ
	public void add(Node node) {
		if(node == null) {
			return;
		}
		
		// �ж���ӽ����Ľڵ��ֵ�͵�ǰ�ڵ��ֵ�Ĵ�С��С
		if(this.value > node.value) {
			if(this.left == null) {
				this.left = node;
			}else {
				// �ݹ������������
				this.left.add(node);
			}
		}else if(this.value < node.value ) {
			if(this.right == null) {
				this.right = node;
			}else {
				// �ݹ������������
				this.right.add(node);
			}
		}
	}
	
	
	//��������ɾ��
	// һ��ɾ������Ҷ�ӽڵ�
	// ����ɾ������ֻ��һ�������Ľڵ�
	// ����ɾ�����������������Ľڵ�
	
	// ˼·һ
	// 1���ȶ�λ��Ҫɾ���Ľڵ� targetNode
	// 2���ҵ�Ŀ����� ���ڵ� parent
	// 3���ж�Ŀ��ڵ� �� ���ڵ�� ��߻����ұߣ����ӽڵ� ���� ���ӽڵ㣩
	
	// ˼·��
	// 1���ȶ�λ��Ҫɾ���Ľڵ� targetNode
    // 2���ҵ�Ŀ����� ���ڵ� parent
	// 3��ȷ��Ŀ��ڵ�� �ӽڵ��� ���ӽڵ㻹�����ӽڵ�
	// 4��targetNode��parent�����ӽڵ㻹�����ӽڵ�
	// 5�����targetNode�����ӽڵ�
	//  -- targetNode��parent�����ӽڵ� 
	//        parent.left = targetNode.left
    //  -- targetNode��parent�����ӽڵ� 
	//        parent.right = targetNode.left
	// 6�����targetNode�����ӽڵ�
	//  -- targetNode��parent�����ӽڵ�
	//        parent.left = targetNode.right
	//  -- targetNode��parent�����ӽڵ�
	//        parent.right = targetNode.right
	
	
	// ˼·��
	// 1���ȶ�λ��Ҫɾ���Ľڵ� targetNode
    // 2���ҵ�Ŀ����� ���ڵ� parent
	// 3����targetNode���������ҵ���С�Ľڵ�
	// 4����һ����ʱ����������С�ڵ��ֵ���浽temp
	// 5��ɾ������С�ڵ� �� ���� targetNode.value = temp 
	
	
	
	/**
	 *  ����Ҫɾ���Ľڵ�
	 * @param value   Ŀ�����ֵ
	 * @return ���ظýڵ�
	 */
	public Node search(int value) {
		if(value == this.value) {  // ���Ǹýڵ�
			return this;
		}else if(value < this.value){  // ������ҵ�ֵС�ڵ�ǰ�ڵ㣬���������ݹ����
			if(this.left == null) {
				return null;
			}
			return this.left.search(value);
		}else{
			if(this.right == null) {
				return null;
			}
			return this.right.search(value);
		}		
	}
	
	
	/**
	 *   ����Ҫɾ���ڵ�ĸ��ڵ�
	 * @param value  Ҫ�ҵĽڵ��ֵ
	 * @return  ����Ҫɾ���ڵ�ĸ��ڵ㣬��û�оͷ���null
	 */
	public Node searchParent(int value) {
		if((this.left != null && this.left.value == value) || (this.right!= null && this.right.value == value)) {
			return this;
		}else {
			// ������ҵ�ֵС�ڵ�ǰ�ڵ��ֵ,���ҵ�ǰ�ڵ�����ӽڵ㲻Ϊ��
			if(value < this.value && this.left != null) {
				// ���������ݹ����
				return this.left.searchParent(value);
			}else if(value >= this.value && this.right != null) {
				return this.right.searchParent(value);
			}else {
				return null;     // û�и��ڵ�
			}
		}	
	}
	

	
	// �������
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	
	
}