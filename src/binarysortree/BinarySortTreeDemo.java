package binarysortree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
		BinarySortTree binarysorttree = new BinarySortTree();
		for(int i : arr) {
			binarysorttree.add(new Node(i));
		}
		
		// 遍历二叉排序树
		System.out.println("中序遍历二叉排序树============");
		binarysorttree.infixOrder();

//		// 删除 2 这个 叶子节点
//		binarysorttree.delete(2);
//		System.out.println("===== 删除节点2后   ===========");
//		binarysorttree.infixOrder();
//		
//		// 删除 1 节点，只有右子节点
//		binarysorttree.delete(1);
//		System.out.println("===== 删除节点1后   ===========");
//		binarysorttree.infixOrder();
	
		
		// 删除3 节点，有左右两个子树节点
		binarysorttree.delete(7);
		System.out.println("===== 删除节点7后   ===========");
		binarysorttree.infixOrder();

	}

}


//创建二叉排序树
class BinarySortTree {
	private Node root;
	
	
	
	// 编写方法
	/**  用于第三种情况中，找到左子树中最小的值
	 * 
	 * @param node 传入的节点，当作二叉排序树的根节点
	 * @return  以node为根节点的  二叉排序树 的 最小节点的值，并将这个节点删除
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		// 循环的查找左节点，就会找到最小值
		while(target.left != null) {
			target = target.left;
		}// 这时候target就指向了最小节点
		delete(target.value);
		return target.value;
		
	}
	
	// 查找要删除的节点
	public Node search(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	
	// 查找要删除节点的父节点
	public Node searchParent(int value) {
		if(root == null) {
			return null;
		}else {
			return root.searchParent(value);
		}
	}
	
	// 删除节点的真正方法
	public void delete(int value) {
		if(root == null) {
			return;
		}else {
			// 1、先找到要删除的节点
			Node targetNode = search(value);
			if(targetNode == null) {   // 如果没有找到要删除的节点
				return;
			}
			
			
			// 如果这颗二叉排序树只有一个节点，且targetNode是找到了的，那必定是root
			if(root.left == null && root.right == null ) {
				root = null;
				return;
			}
			
			
			// 去找到targetNode的父节点
			Node parent = searchParent(value);
			// 要删除的是叶子节点
			if(targetNode.left == null || targetNode.right == null) {    // 说明targetNode孑然一身
				// 判断targetNode 是父节点的左 还是 右子节点
				if(parent.left != null && parent.left.value == value ) {
					parent.left = null;
				}else if(parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
				
				
				
				
			}else if(targetNode.left != null && targetNode.right != null) {
				// 删除有两颗子树的节点
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
				
				
				
			}else {
				// 删除只有一棵子树的节点   == 这种情况很复杂  -- 所以先把第三种情况写出来，用排除法即可
				// 如果要删除的这个节点有左子节点
				if(targetNode.left != null) {
					if(parent != null) {    // ！！！！    
						if(parent.left.value == value) {
							parent.left = targetNode.left;
						}else { // targetNode是parent的右子节点
							parent.right = targetNode.left;
						}
					}else {
						root = targetNode.left;
					}
					
				}else { // 要删除的节点有右子节点
					if(parent != null) {
					if(parent.left.value == value) {
						parent.left = targetNode.right;
					}else {  // targetNode是parent的右子节点
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
	// 添加节点的方法
	public  void add(Node node) {
		if(root == null) {
			root = node;      // 直接让root指向node即可
		}else {
			root.add(node);
		}
	}
	
	// 中序遍历
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
		}else {
			System.out.println("二叉排序树为空，无法遍历！");
		}
	}
}

// 创建Node节点
class Node {
	
	int value;         // 自身的值
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


	// 递归的方式添加节点 ，需要按照二叉排序树的方式
	public void add(Node node) {
		if(node == null) {
			return;
		}
		
		// 判断添加进来的节点的值和当前节点的值的大小关小
		if(this.value > node.value) {
			if(this.left == null) {
				this.left = node;
			}else {
				// 递归向左子树添加
				this.left.add(node);
			}
		}else if(this.value < node.value ) {
			if(this.right == null) {
				this.right = node;
			}else {
				// 递归向右子树添加
				this.right.add(node);
			}
		}
	}
	
	
	//二叉树的删除
	// 一、删除的是叶子节点
	// 二、删除的是只有一颗子树的节点
	// 三、删除的是有两棵子树的节点
	
	// 思路一
	// 1、先定位到要删除的节点 targetNode
	// 2、找到目标结点的 父节点 parent
	// 3、判断目标节点 在 父节点的 左边还是右边（左子节点 还是 右子节点）
	
	// 思路二
	// 1、先定位到要删除的节点 targetNode
    // 2、找到目标结点的 父节点 parent
	// 3、确定目标节点的 子节点是 左子节点还是右子节点
	// 4、targetNode是parent的左子节点还是右子节点
	// 5、如果targetNode有左子节点
	//  -- targetNode是parent的左子节点 
	//        parent.left = targetNode.left
    //  -- targetNode是parent的右子节点 
	//        parent.right = targetNode.left
	// 6、如果targetNode有右子节点
	//  -- targetNode是parent的左子节点
	//        parent.left = targetNode.right
	//  -- targetNode是parent的右子节点
	//        parent.right = targetNode.right
	
	
	// 思路三
	// 1、先定位到要删除的节点 targetNode
    // 2、找到目标结点的 父节点 parent
	// 3、从targetNode的右子树找到最小的节点
	// 4、用一个临时变量，将最小节点的值保存到temp
	// 5、删除该最小节点 ， 并将 targetNode.value = temp 
	
	
	
	/**
	 *  查找要删除的节点
	 * @param value   目标结点的值
	 * @return 返回该节点
	 */
	public Node search(int value) {
		if(value == this.value) {  // 就是该节点
			return this;
		}else if(value < this.value){  // 如果查找的值小于当前节点，向左子树递归查找
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
	 *   查找要删除节点的父节点
	 * @param value  要找的节点的值
	 * @return  返回要删除节点的父节点，如没有就返回null
	 */
	public Node searchParent(int value) {
		if((this.left != null && this.left.value == value) || (this.right!= null && this.right.value == value)) {
			return this;
		}else {
			// 如果查找的值小于当前节点的值,并且当前节点的左子节点不为空
			if(value < this.value && this.left != null) {
				// 向左子树递归查找
				return this.left.searchParent(value);
			}else if(value >= this.value && this.right != null) {
				return this.right.searchParent(value);
			}else {
				return null;     // 没有父节点
			}
		}	
	}
	

	
	// 中序遍历
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