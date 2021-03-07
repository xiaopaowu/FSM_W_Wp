package avl;


public class AVLTreeDemo {

	public static void main(String[] args) {
		
		// int[] arr = {4, 3, 6, 5, 7, 8};
		int[] arr = {10, 12,8, 9, 7, 6 };
		AVLTree avlTree = new AVLTree();
		// 添加节点
		for(int i : arr) {
			avlTree.add(new Node(i));
		}
		
		// 遍历
		System.out.println("== 中序遍历 ==");
		avlTree.infixOrder();

	    System.out.println("== 平衡处理之前的效果 ==");
	    System.out.println("树的高度：   "+ avlTree.getRoot().height());          // 4
	    System.out.println("左子树的高度：   "+ avlTree.getRoot().leftHeight());       // 1
	    System.out.println("右子树的高度：   "+ avlTree.getRoot().rightHeight());    // 3
	    System.out.println("当前根节点为：   "+ avlTree.getRoot());  
	    
	    
	}

}


// 创建AVL树
class AVLTree {
private Node root;
	
	
	
	public Node getRoot() {
	return root;
    }

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
			
			
			// 如果这颗二叉排序树这有一个节点
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





//创建Node节点
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

	// 返回左子树的高度
	public int leftHeight() {
		if(left == null) {
			return 0;
		}else {
			return left.height();
		}
	}
	
	// 返回右子树的高度
	public int rightHeight() {
		if(right == null) {
			return 0;
		}else {
			return right.height();
		}
	}
	
	// 返回以该节点为根节点的树的高度
	public int height() {
		return Math.max(left==null ? 0 : left.height(), right==null ? 0 : right.height())+1;
		//左子树为空？是的话就返回0  ； 然后再选出左右子树中较高的那棵树的高度
	}
	
	
	// 左旋转的方法
	private void leftRotate() {
		
		// 1、创建新的节点 （以当前根节点的值作为这个新节点的值）
		Node newNode = new Node(value);
		// 2、把新结点的左子树设置为当前节点的左子树
		newNode.left = this.left;
		// 3、将新节点的右子树 设置为 当前节点右子树的左子树
		newNode.right = this.right.left;
		// 4、将当前节点的值替换成右子节点的值
		value = right.value;
		// 5、把当前节点的右子树设置为当前节点的右子树的右子树  （就是跳一级）
		right = right.right;
		// 6、把当前节点的左子节点 设置为 新的节点
		left = newNode;
		
	}
	

	
	// 右旋转的方法
	private void rightRotate() {
		
		// 1、创建新的节点 （以当前根节点的值作为这个新节点的值）
		Node newNode = new Node(value);
		// 2、把新结点的左子树设置为当前节点的右子树
		newNode.left = this.left;
		// 3、将新节点的左子树 设置为 当前节点   左子树的右子树
		newNode.left = this.left.right;
		// 4、将当前节点的值替换成左子节点的值
		value = left.value;
		// 5、把当前节点的左子树设置为当前节点的左子树的左子树  （就是跳一级）
		left = left.left;
		// 6、把当前节点的右子节点 设置为 新的节点
		right = newNode;
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
		
		
		// 当添加完一个节点后，如果满足 （右子树的高度 - 左子树的高度）大于1，就需要进行左旋转
		if (rightHeight() - leftHeight() > 1) {
		// ！！有些树在进行 左旋转后 仍然没有平衡
		// 1、如果它的 右子树的左子树高度 大于 它的 右子树的右子树的高度
		if (right != null && right.rightHeight() > right.rightHeight()) {
			right.rightRotate();
			leftRotate();
		}else {
	     	leftRotate();
		  }
		    return;         // !!! 不要忘记这个return，因为add一次就处理一次旋转，就不需要继续向下判断了
		}

		// 当添加完一个节点后，如果满足 （左子树的高度 - 右子树的高度）大于1，就需要进行右旋转
		if(leftHeight() - rightHeight() > 1) {
			
			// ！！有些树在进行 右旋转后 仍然没有平衡
			// 1、如果它的  左子树的右子树高度    大于 它的   左子树的左子树的高度
			if(left != null && left.rightHeight()> left.leftHeight()) {
				left.leftRotate();
				rightRotate();
			}else {
				rightRotate();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	//二叉树的删除
	// 一、删除的是叶子节点
	// 二、删除的是只有一颗子树的节点
	// 三、删除的是有两棵子树的节点

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
	

	
	// 前序遍历
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