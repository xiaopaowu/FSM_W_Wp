package tree.threadedbinarytree;


// 介绍
// 有些节点的左右指针没有完全用上，可以将这些空指针域利用起来
// 存放节点的前驱或后继信息


// 线索化二叉树的遍历 -- 不能像之前那样，判断 == null, 因为都已经有其前驱节点和后继节点了

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {

		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(3, "吴用");
		HeroNode node3 = new HeroNode(6, "卢俊义");
		HeroNode node4 = new HeroNode(8, "林冲");
		HeroNode node5 = new HeroNode(10, "关胜");
		HeroNode node6 = new HeroNode(14, "wjk");
		
		// 创建二叉树
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		// 测试中序线索化
		ThreadedBinaryTree threadedbinarytree = new ThreadedBinaryTree();
		threadedbinarytree.setRoot(root);
		
		threadedbinarytree.threadedNodes();

		// 测试和： 以10好节点 --- node5
		HeroNode leftNode = node5.getLeft();   // 如果是3号节点，就说明成功
		HeroNode rightNode = node5.getRight();  // 如果是1号节点，就说明成功
		System.out.println(leftNode.toString());
		System.out.println(rightNode.toString());

		
		System.out.println("=== 使用线索化的方式遍历线索化二叉树= =====");
		threadedbinarytree.threadedList();    // 8,3,10,1,14,6
	}

}


/**
 * 			后继节点：就是中序遍历排序中相对的后一个	
 * 
 * @author wuyifan
 *若结点有左子树，则其LChild域指向其左孩子，否则LChild域指向其前驱结点；
 *若结点有右子树，则其RChild域指向其右孩子，否则RChild域指向其后继结点。
 */



// 创建线索化二叉树
class ThreadedBinaryTree {
	private HeroNode root;
	
	// 为了实现线索化，需要创建指向当前节点的前驱节点的指针
	// pre在递归进行线索化时，总是保留前一个节点 -- 因为这个原来的二叉树是单向的
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	// 中序线索化二叉树的遍历
	public void threadedList() {
		// 定义一个变量，存储当前遍历的节点，从root开始
		HeroNode temp = root;
		while(temp != null) {
			// 循环找到leftType = 1 的节点，第一个找到的是前驱节点，之后的就会 随着遍历变化
			// 当leftType = 1时，说明该节点是按照线索化处理后的有效节点
			while(temp.getLefttype() == 0) {
				temp = temp.getLeft();
			}
			
			// 打印当前节点
			System.out.println(temp);
			// 如果当前节点的右指针 指向的是后继节点，就一直输出
			while(temp.getRighttype() == 1) {
				temp = temp.getRight();
				System.out.println(temp);
			}
			// while循环完毕后，就替换这个temp继续
			temp = temp.getRight();
		}
		
	}
	
	
	
	
	
	
	// 重载一把threadedNodes()方法
	public void threadedNodes() {
		this.threadedNodes(root);
	}
	
	
	// 编写对二叉树中序线索化的方法
	/**
	 * 
	 * @param node 就是当前需要线索化的节点
	 */
	public void threadedNodes(HeroNode node) {
		if(node == null) {
			System.out.println("无法线索化，节点为空！");
			return;
		}
		
		// 一、先线索化左子树
		threadedNodes(node.getLeft());
		
		// 二、线索化当前节点		
		 // 处理当前节点的前驱节点
		 // 以8节点来理解
		 // 8节点的left = null，并且将8的lefttype置1	 
		if(node.getLeft() == null) {
			// 让当前节点的左指针指向前驱节点
			node.setLeft(pre);
			// 修改当前节点的左指针类型, 指向的是前驱节点
			node.setLefttype(1);
		}
		
		 // 处理当前节点的后继节点
		 // 这边的 pre != null 条件， 是为了 让那个setRight 下次处理，因为是单向的
		 // 就是 node 指针向上走，然后 node.setLeft来完成这一步骤
		 if(pre != null && pre.getRight() == null) {
			 // 让前驱节点的右指针 指向当前节点
			 pre.setRight(node);
			 // 修改前驱节点的右指针类型
			 pre.setRighttype(1);
		 }
		 // !!! 每处理一个节点后， 让当前节点成为下一个结点的 前驱节点  !!!!!
		 pre = node;
		 
		 
		// 三、线索化右子树
		threadedNodes(node.getRight());
		
		
		
		
		
		
	}

	// 真正的遍历操作在这个类里面实现
	// 前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 后序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	
	// 前序遍历搜索
	public HeroNode preOrdersearch(int no) {
		if (root != null) {
			return root.preOrdersearch(no);
		}else {
			return null;
		}
	}
	
	
	//中序遍历搜索
	public HeroNode infixOrdersearch(int no) {
		if (root != null) {
			return root.infixOrdersearch(no);
		}else {
			return null;
		}
	}
	
	//后序遍历搜索
	public HeroNode postOrdersearch(int no) {
		if (root != null) {
			return root.postOrdersearch(no);
		}else {
			return null;
		}
	}
	
	
	// 删除节点
	public void delNode(int no) {
		if(root != null) {
			if(root.getNo() == no) {       // 在这里立马要判断 root是否是要删除的，否则后面没有机会了，  因为是单向的 二叉树，不能判断父节点的状态
				root =null;
			}else {
				root.delNode(no);	
			}
		}else {
			System.out.println("空树，无法删除！！");
		}
	}
}




// 创建HeroNode
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;
	
	// 定义属性 
	// 说明
	// 1、如果lefttype = 0，代表指向的是 左子树 ， 如果 =1指向的是前驱节点
	// 2、如果righttype = 0，代表指向的是 右子树 ， 如果 =1指向的是后继节点
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

	// 写一些节点的方法
	// 1、前序遍历的方法
	public void preOrder() {
		// 先输出当前节点
		System.out.println(this);
		// 向左子树递归
		// 判断左子节点是否为空
		if (this.left != null) {
			this.left.preOrder();
		}
		// 向右子树递归
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 2、中序遍历的方法
	public void infixOrder() {
		// 先递归向左子树中序遍历
		if (this.left != null) {
			this.left.infixOrder();
		}
		// 输出父节点
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// 3、后序遍历的方法
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	
	
	// 前序遍历查找
	public HeroNode preOrdersearch(int no) {
		System.out.println("进入前序遍历===");        // 加上这一句话，来看比较了几次      !! 写在this.no == no之前即可
		HeroNode resNode = null;
		// 比较当前节点
		if (this.no == no) {
			return this;
		}
		if (this.left != null) {
			resNode = this.left.preOrdersearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 没有找到的话，继续向右
		if (this.right != null) {
			resNode = this.right.preOrdersearch(no);
		}
		return resNode;
	}

	
	// 中序遍历查找
	public HeroNode infixOrdersearch(int no) {
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.infixOrdersearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("进入中序遍历===");        // 加上这一句话，来看比较了几次
		if(this.no == no) {
			return this;
		}
		if(this.right != null) {
			resNode = this.right.infixOrdersearch(no);
		}
		
		return resNode;
	}
	
	// 后序遍历查找
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
		System.out.println("进入后序遍历===");        // 加上这一句话，来看比较了几次
		if(this.no == no) {
			return this;
		}
		
		return null;
	}
	
	
	// 删除操作
	public void delNode(int no) {
		
		// 判断左子节点是否是要删除的
		if(this.left != null && this.left.no == no ) {
			this.left = null ;         // 这里的置空操作就相当于删除
			return;
		}
		
		if(this.right != null && this.right.no == no ) {
			this.right = null;
			return;
		}
		
		// 开始进行递归
		if(this.left != null) {
			this.left.delNode(no);
		}
		if(this.right != null) {
			this.right.delNode(no);
		}
		
	}
}