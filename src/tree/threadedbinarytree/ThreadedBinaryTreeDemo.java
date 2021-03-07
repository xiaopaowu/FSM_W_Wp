package tree.threadedbinarytree;


// ����
// ��Щ�ڵ������ָ��û����ȫ���ϣ����Խ���Щ��ָ������������
// ��Žڵ��ǰ��������Ϣ


// �������������ı��� -- ������֮ǰ�������ж� == null, ��Ϊ���Ѿ�����ǰ���ڵ�ͺ�̽ڵ���

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {

		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode node2 = new HeroNode(3, "����");
		HeroNode node3 = new HeroNode(6, "¬����");
		HeroNode node4 = new HeroNode(8, "�ֳ�");
		HeroNode node5 = new HeroNode(10, "��ʤ");
		HeroNode node6 = new HeroNode(14, "wjk");
		
		// ����������
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		// ��������������
		ThreadedBinaryTree threadedbinarytree = new ThreadedBinaryTree();
		threadedbinarytree.setRoot(root);
		
		threadedbinarytree.threadedNodes();

		// ���Ժͣ� ��10�ýڵ� --- node5
		HeroNode leftNode = node5.getLeft();   // �����3�Žڵ㣬��˵���ɹ�
		HeroNode rightNode = node5.getRight();  // �����1�Žڵ㣬��˵���ɹ�
		System.out.println(leftNode.toString());
		System.out.println(rightNode.toString());

		
		System.out.println("=== ʹ���������ķ�ʽ����������������= =====");
		threadedbinarytree.threadedList();    // 8,3,10,1,14,6
	}

}


/**
 * 			��̽ڵ㣺�������������������Եĺ�һ��	
 * 
 * @author wuyifan
 *�������������������LChild��ָ�������ӣ�����LChild��ָ����ǰ����㣻
 *�������������������RChild��ָ�����Һ��ӣ�����RChild��ָ�����̽�㡣
 */



// ����������������
class ThreadedBinaryTree {
	private HeroNode root;
	
	// Ϊ��ʵ������������Ҫ����ָ��ǰ�ڵ��ǰ���ڵ��ָ��
	// pre�ڵݹ����������ʱ�����Ǳ���ǰһ���ڵ� -- ��Ϊ���ԭ���Ķ������ǵ����
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	// �����������������ı���
	public void threadedList() {
		// ����һ���������洢��ǰ�����Ľڵ㣬��root��ʼ
		HeroNode temp = root;
		while(temp != null) {
			// ѭ���ҵ�leftType = 1 �Ľڵ㣬��һ���ҵ�����ǰ���ڵ㣬֮��ľͻ� ���ű����仯
			// ��leftType = 1ʱ��˵���ýڵ��ǰ�����������������Ч�ڵ�
			while(temp.getLefttype() == 0) {
				temp = temp.getLeft();
			}
			
			// ��ӡ��ǰ�ڵ�
			System.out.println(temp);
			// �����ǰ�ڵ����ָ�� ָ����Ǻ�̽ڵ㣬��һֱ���
			while(temp.getRighttype() == 1) {
				temp = temp.getRight();
				System.out.println(temp);
			}
			// whileѭ����Ϻ󣬾��滻���temp����
			temp = temp.getRight();
		}
		
	}
	
	
	
	
	
	
	// ����һ��threadedNodes()����
	public void threadedNodes() {
		this.threadedNodes(root);
	}
	
	
	// ��д�Զ����������������ķ���
	/**
	 * 
	 * @param node ���ǵ�ǰ��Ҫ�������Ľڵ�
	 */
	public void threadedNodes(HeroNode node) {
		if(node == null) {
			System.out.println("�޷����������ڵ�Ϊ�գ�");
			return;
		}
		
		// һ����������������
		threadedNodes(node.getLeft());
		
		// ������������ǰ�ڵ�		
		 // ����ǰ�ڵ��ǰ���ڵ�
		 // ��8�ڵ������
		 // 8�ڵ��left = null�����ҽ�8��lefttype��1	 
		if(node.getLeft() == null) {
			// �õ�ǰ�ڵ����ָ��ָ��ǰ���ڵ�
			node.setLeft(pre);
			// �޸ĵ�ǰ�ڵ����ָ������, ָ�����ǰ���ڵ�
			node.setLefttype(1);
		}
		
		 // ����ǰ�ڵ�ĺ�̽ڵ�
		 // ��ߵ� pre != null ������ ��Ϊ�� ���Ǹ�setRight �´δ�����Ϊ�ǵ����
		 // ���� node ָ�������ߣ�Ȼ�� node.setLeft�������һ����
		 if(pre != null && pre.getRight() == null) {
			 // ��ǰ���ڵ����ָ�� ָ��ǰ�ڵ�
			 pre.setRight(node);
			 // �޸�ǰ���ڵ����ָ������
			 pre.setRighttype(1);
		 }
		 // !!! ÿ����һ���ڵ�� �õ�ǰ�ڵ��Ϊ��һ������ ǰ���ڵ�  !!!!!
		 pre = node;
		 
		 
		// ����������������
		threadedNodes(node.getRight());
		
		
		
		
		
		
	}

	// �����ı������������������ʵ��
	// ǰ�����
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	// �������
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	// �������
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}
	
	
	// ǰ���������
	public HeroNode preOrdersearch(int no) {
		if (root != null) {
			return root.preOrdersearch(no);
		}else {
			return null;
		}
	}
	
	
	//�����������
	public HeroNode infixOrdersearch(int no) {
		if (root != null) {
			return root.infixOrdersearch(no);
		}else {
			return null;
		}
	}
	
	//�����������
	public HeroNode postOrdersearch(int no) {
		if (root != null) {
			return root.postOrdersearch(no);
		}else {
			return null;
		}
	}
	
	
	// ɾ���ڵ�
	public void delNode(int no) {
		if(root != null) {
			if(root.getNo() == no) {       // ����������Ҫ�ж� root�Ƿ���Ҫɾ���ģ��������û�л����ˣ�  ��Ϊ�ǵ���� �������������жϸ��ڵ��״̬
				root =null;
			}else {
				root.delNode(no);	
			}
		}else {
			System.out.println("�������޷�ɾ������");
		}
	}
}




// ����HeroNode
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;
	
	// �������� 
	// ˵��
	// 1�����lefttype = 0������ָ����� ������ �� ��� =1ָ�����ǰ���ڵ�
	// 2�����righttype = 0������ָ����� ������ �� ��� =1ָ����Ǻ�̽ڵ�
	private int lefttype;
	private int righttype;

	
	public int getLefttype() {
		return lefttype;
	}

	public void setLefttype(int lefttype) {
		this.lefttype = lefttype;
	}

	public int getRighttype() {
		return righttype;
	}

	public void setRighttype(int righttype) {
		this.righttype = righttype;
	}

	public HeroNode(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}

	// дһЩ�ڵ�ķ���
	// 1��ǰ������ķ���
	public void preOrder() {
		// �������ǰ�ڵ�
		System.out.println(this);
		// ���������ݹ�
		// �ж����ӽڵ��Ƿ�Ϊ��
		if (this.left != null) {
			this.left.preOrder();
		}
		// ���������ݹ�
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 2����������ķ���
	public void infixOrder() {
		// �ȵݹ����������������
		if (this.left != null) {
			this.left.infixOrder();
		}
		// ������ڵ�
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// 3����������ķ���
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	
	
	// ǰ���������
	public HeroNode preOrdersearch(int no) {
		System.out.println("����ǰ�����===");        // ������һ�仰�������Ƚ��˼���      !! д��this.no == no֮ǰ����
		HeroNode resNode = null;
		// �Ƚϵ�ǰ�ڵ�
		if (this.no == no) {
			return this;
		}
		if (this.left != null) {
			resNode = this.left.preOrdersearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// û���ҵ��Ļ�����������
		if (this.right != null) {
			resNode = this.right.preOrdersearch(no);
		}
		return resNode;
	}

	
	// �����������
	public HeroNode infixOrdersearch(int no) {
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.infixOrdersearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("�����������===");        // ������һ�仰�������Ƚ��˼���
		if(this.no == no) {
			return this;
		}
		if(this.right != null) {
			resNode = this.right.infixOrdersearch(no);
		}
		
		return resNode;
	}
	
	// �����������
	public HeroNode postOrdersearch(int no) {
		HeroNode resNode = null;
		
		if(this.left != null) {
			resNode = this.left.postOrdersearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		if(this.right != null) {
			resNode = this.right.postOrdersearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("����������===");        // ������һ�仰�������Ƚ��˼���
		if(this.no == no) {
			return this;
		}
		
		return null;
	}
	
	
	// ɾ������
	public void delNode(int no) {
		
		// �ж����ӽڵ��Ƿ���Ҫɾ����
		if(this.left != null && this.left.no == no ) {
			this.left = null ;         // ������ÿղ������൱��ɾ��
			return;
		}
		
		if(this.right != null && this.right.no == no ) {
			this.right = null;
			return;
		}
		
		// ��ʼ���еݹ�
		if(this.left != null) {
			this.left.delNode(no);
		}
		if(this.right != null) {
			this.right.delNode(no);
		}
		
	}
}