package avl;


public class AVLTreeDemo {

	public static void main(String[] args) {
		
		// int[] arr = {4, 3, 6, 5, 7, 8};
		int[] arr = {10, 12,8, 9, 7, 6 };
		AVLTree avlTree = new AVLTree();
		// ��ӽڵ�
		for(int i : arr) {
			avlTree.add(new Node(i));
		}
		
		// ����
		System.out.println("== ������� ==");
		avlTree.infixOrder();

	    System.out.println("== ƽ�⴦��֮ǰ��Ч�� ==");
	    System.out.println("���ĸ߶ȣ�   "+ avlTree.getRoot().height());          // 4
	    System.out.println("�������ĸ߶ȣ�   "+ avlTree.getRoot().leftHeight());       // 1
	    System.out.println("�������ĸ߶ȣ�   "+ avlTree.getRoot().rightHeight());    // 3
	    System.out.println("��ǰ���ڵ�Ϊ��   "+ avlTree.getRoot());  
	    
	    
	}

}


// ����AVL��
class AVLTree {
private Node root;
	
	
	
	public Node getRoot() {
	return root;
    }

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
			
			
			// �����Ŷ�������������һ���ڵ�
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





//����Node�ڵ�
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

	// �����������ĸ߶�
	public int leftHeight() {
		if(left == null) {
			return 0;
		}else {
			return left.height();
		}
	}
	
	// �����������ĸ߶�
	public int rightHeight() {
		if(right == null) {
			return 0;
		}else {
			return right.height();
		}
	}
	
	// �����Ըýڵ�Ϊ���ڵ�����ĸ߶�
	public int height() {
		return Math.max(left==null ? 0 : left.height(), right==null ? 0 : right.height())+1;
		//������Ϊ�գ��ǵĻ��ͷ���0  �� Ȼ����ѡ�����������нϸߵ��ǿ����ĸ߶�
	}
	
	
	// ����ת�ķ���
	private void leftRotate() {
		
		// 1�������µĽڵ� ���Ե�ǰ���ڵ��ֵ��Ϊ����½ڵ��ֵ��
		Node newNode = new Node(value);
		// 2�����½�������������Ϊ��ǰ�ڵ��������
		newNode.left = this.left;
		// 3�����½ڵ�������� ����Ϊ ��ǰ�ڵ���������������
		newNode.right = this.right.left;
		// 4������ǰ�ڵ��ֵ�滻�����ӽڵ��ֵ
		value = right.value;
		// 5���ѵ�ǰ�ڵ������������Ϊ��ǰ�ڵ����������������  ��������һ����
		right = right.right;
		// 6���ѵ�ǰ�ڵ�����ӽڵ� ����Ϊ �µĽڵ�
		left = newNode;
		
	}
	

	
	// ����ת�ķ���
	private void rightRotate() {
		
		// 1�������µĽڵ� ���Ե�ǰ���ڵ��ֵ��Ϊ����½ڵ��ֵ��
		Node newNode = new Node(value);
		// 2�����½�������������Ϊ��ǰ�ڵ��������
		newNode.left = this.left;
		// 3�����½ڵ�������� ����Ϊ ��ǰ�ڵ�   ��������������
		newNode.left = this.left.right;
		// 4������ǰ�ڵ��ֵ�滻�����ӽڵ��ֵ
		value = left.value;
		// 5���ѵ�ǰ�ڵ������������Ϊ��ǰ�ڵ����������������  ��������һ����
		left = left.left;
		// 6���ѵ�ǰ�ڵ�����ӽڵ� ����Ϊ �µĽڵ�
		right = newNode;
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
		
		
		// �������һ���ڵ��������� ���������ĸ߶� - �������ĸ߶ȣ�����1������Ҫ��������ת
		if (rightHeight() - leftHeight() > 1) {
		// ������Щ���ڽ��� ����ת�� ��Ȼû��ƽ��
		// 1��������� ���������������߶� ���� ���� ���������������ĸ߶�
		if (right != null && right.rightHeight() > right.rightHeight()) {
			right.rightRotate();
			leftRotate();
		}else {
	     	leftRotate();
		  }
		    return;         // !!! ��Ҫ�������return����Ϊaddһ�ξʹ���һ����ת���Ͳ���Ҫ���������ж���
		}

		// �������һ���ڵ��������� ���������ĸ߶� - �������ĸ߶ȣ�����1������Ҫ��������ת
		if(leftHeight() - rightHeight() > 1) {
			
			// ������Щ���ڽ��� ����ת�� ��Ȼû��ƽ��
			// 1���������  ���������������߶�    ���� ����   ���������������ĸ߶�
			if(left != null && left.rightHeight()> left.leftHeight()) {
				left.leftRotate();
				rightRotate();
			}else {
				rightRotate();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	//��������ɾ��
	// һ��ɾ������Ҷ�ӽڵ�
	// ����ɾ������ֻ��һ�������Ľڵ�
	// ����ɾ�����������������Ľڵ�

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
	

	
	// ǰ�����
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