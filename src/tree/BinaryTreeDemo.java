package tree;

public class BinaryTreeDemo {

	public static void main(String[] args) {

		// 现需要创建一个二叉树
		BinaryTree binarytree = new BinaryTree();
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5, "关胜");

		// 说明
		// 先手动创建该二叉树，后面学习递归的方式创建二叉树
		root.setLeft(node2); // 因为right变成了私有变量，不能直接用 root.right = node2
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);

		binarytree.setRoot(root);

		// 测试
		System.out.println("====前序遍历====");
		binarytree.preOrder();

		System.out.println("====中序遍历====");
		binarytree.infixOrder();

		System.out.println("====后续遍历====");
		binarytree.postOrder();
		
		System.out.println();
		System.out.println("====前序遍历搜索====");
		HeroNode resNode = binarytree.preOrdersearch(5);
		if(resNode == null) {
			System.out.printf("没有找到no = %d的英雄", 5);
		}else {
			System.out.printf("该英雄的id=%d name=%s\n",resNode.getNo(), resNode.getName());
		}
	

		System.out.println();
		System.out.println("====中序遍历搜索====");
		resNode = binarytree.infixOrdersearch(5);
		if(resNode == null) {
			System.out.printf("没有找到no = %d的英雄", 5);
		}else {
			System.out.printf("该英雄的id=%d name=%s\n",resNode.getNo(), resNode.getName());
		}
	

		System.out.println();
		System.out.println("====后续遍历搜索====");
		resNode = binarytree.postOrdersearch(5);
		if(resNode == null) {
			System.out.printf("没有找到no = %d的英雄", 5);
		}else {
			System.out.printf("该英雄的id=%d name=%s\n",resNode.getNo(), resNode.getName());
		}
		
		
		
		System.out.println("\n\n\n\n\n");
		System.out.println("=========删除测试========");
		System.out.println("删除前，前序遍历：");
		binarytree.preOrder();
		binarytree.delNode(5);
		System.out.println("删除后，前序遍历");
		binarytree.preOrder();
	}

}

// 定义二叉树
class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
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


// 英雄节点定义类
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
		
		// ！！！ 记得多加一部判断 ！！！！
		// 如果左子树已经找到了，就要退出，否则会出现空指针异常
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
		
		// 递归完毕后，若还是没有找到应该删除的，要判断 root
	}
}