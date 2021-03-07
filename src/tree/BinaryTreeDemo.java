package tree;

public class BinaryTreeDemo {

	public static void main(String[] args) {

		// ����Ҫ����һ��������
		BinaryTree binarytree = new BinaryTree();
		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode node2 = new HeroNode(2, "����");
		HeroNode node3 = new HeroNode(3, "¬����");
		HeroNode node4 = new HeroNode(4, "�ֳ�");
		HeroNode node5 = new HeroNode(5, "��ʤ");

		// ˵��
		// ���ֶ������ö�����������ѧϰ�ݹ�ķ�ʽ����������
		root.setLeft(node2); // ��Ϊright�����˽�б���������ֱ���� root.right = node2
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);

		binarytree.setRoot(root);

		// ����
		System.out.println("====ǰ�����====");
		binarytree.preOrder();

		System.out.println("====�������====");
		binarytree.infixOrder();

		System.out.println("====��������====");
		binarytree.postOrder();
		
		System.out.println();
		System.out.println("====ǰ���������====");
		HeroNode resNode = binarytree.preOrdersearch(5);
		if(resNode == null) {
			System.out.printf("û���ҵ�no = %d��Ӣ��", 5);
		}else {
			System.out.printf("��Ӣ�۵�id=%d name=%s\n",resNode.getNo(), resNode.getName());
		}
	

		System.out.println();
		System.out.println("====�����������====");
		resNode = binarytree.infixOrdersearch(5);
		if(resNode == null) {
			System.out.printf("û���ҵ�no = %d��Ӣ��", 5);
		}else {
			System.out.printf("��Ӣ�۵�id=%d name=%s\n",resNode.getNo(), resNode.getName());
		}
	

		System.out.println();
		System.out.println("====������������====");
		resNode = binarytree.postOrdersearch(5);
		if(resNode == null) {
			System.out.printf("û���ҵ�no = %d��Ӣ��", 5);
		}else {
			System.out.printf("��Ӣ�۵�id=%d name=%s\n",resNode.getNo(), resNode.getName());
		}
		
		
		
		System.out.println("\n\n\n\n\n");
		System.out.println("=========ɾ������========");
		System.out.println("ɾ��ǰ��ǰ�������");
		binarytree.preOrder();
		binarytree.delNode(5);
		System.out.println("ɾ����ǰ�����");
		binarytree.preOrder();
	}

}

// ���������
class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
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


// Ӣ�۽ڵ㶨����
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;

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
		
		// ������ �ǵö��һ���ж� ��������
		// ����������Ѿ��ҵ��ˣ���Ҫ�˳����������ֿ�ָ���쳣
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
		
		// �ݹ���Ϻ�������û���ҵ�Ӧ��ɾ���ģ�Ҫ�ж� root
	}
}