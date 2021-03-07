package graph;


//既然有了链表、为什么需要图？
//有向图、无向图、带权图
//邻接矩阵、邻接表均可用来表示图
//邻接表更加节省空间，由于邻接矩阵中的存在许多表示不可达的“0”，这种空间就是浪费掉了的


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	private ArrayList<String> vertexList;          // 存储顶点集合
	private int[][] edges;       //存储图对应的邻接矩阵
	private int numOfEdges;      // 表示边的数量
	
	// 定义一个数组，记录某个节点是否被访问过
	private boolean[] isVisited;
	public static void main(String[] args) {

		int n = 5;   // 有四个节点
		String Vertexs[] = {"A","B","C","D","E"};
		// 创建图对象
		Graph graph = new Graph(n);
		// 循环添加顶点
		for(String vertex: Vertexs) {
			graph.insertVertex(vertex);
		}
		// 添加边
		// A-B, A-E, B-C, B-D, B-E
		graph.insertEdge(0, 0 , 1);  // A-A
		graph.insertEdge(0, 1 , 1);  // A-B
		graph.insertEdge(0, 4 , 1);
		graph.insertEdge(1, 2 , 1);
		graph.insertEdge(1, 3 , 1);
		graph.insertEdge(1, 4 , 1);

		System.out.println("======= 该图的邻接矩阵如下 =======");
		graph.showGraph();
		
		
		System.out.println("======= 深度优先遍历 ======");
		graph.dfs();
		
		System.out.println();
		System.out.println("======= 广度优先遍历 ======");
		graph.bfs();
		
	}

	// 构造器
	public Graph(int n) {      // n代表顶点数量
		// 初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<>(n);
		numOfEdges = 0;   //初始为0
		isVisited = new boolean[n];
	}
	
	// 得到第一个邻接节点的下标
	public int getFirstNeighbor(int index) {
		for(int j = 0; j < vertexList.size(); j++) {
			if(edges[index][j] > 0) {     // 说明存在邻结点
				return j;
			}
		}
		return -1;       // 不存在的话就返回 -1
	}
	
	// 根据前一个邻接节点的下标来获取下一个邻接节点
	public int getNextNeighbor(int v1,int v2) {
		for(int j = v2+1; j<vertexList.size();j++) {
			if(edges[v1][j]>0) {
				return j;
			}
		}
		return -1;
	}
	
	
	// 深度优先遍历算法
	// 整个便利的过程中都需要判断 该节点是否被访问，所注意要拿到那个boolean数组
	public void dfs(boolean[] isVisited, int i) {    // i第一次进来值为0
		
		ArrayList<Integer> tmp = new ArrayList<>();;  
		// 首先访问该节点
//		System.out.print(getValueByIndex(i) + " -> ");
		// 将访问过的节点 置为 已经访问过
		isVisited[i] = true;
		// 查找该结点的第一个邻接节点w
		int w = getFirstNeighbor(i);
		while(w != -1) { // 说明有邻接节点
			int tmpweight = getWeight(i, w);
			tmp.add(w);
//			tmp.add(tmpweight);
			System.out.println("tmp:"+tmp);

			if(!isVisited[w]) {  // 说明没有被访问过
				dfs(isVisited,w);
			}else {   // 如果已经被访问过
				w = getNextNeighbor(i, w);
			}
		}
	
	}
	
	
	// 对dfs进行重载，遍历所有节点，并进行dfs
	public void dfs() {
		isVisited = new boolean[5];
		for(int i = 0 ; i< getNumOfVertex(); i++) {
			if(!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	
	
	
	// 广度优先遍历
	public void bfs(boolean[] isVisited, int i) {
		int u;     // 表示队列头结点的下标
		int w;     // 表示邻接节点w
		// 记录节点访问的顺序
		// 相比于深度遍历，多借助了一个queue，用来实现广度，来removeFirst和addLast操作
		LinkedList queue = new LinkedList();
		// 首先访问该节点
		System.out.print(getValueByIndex(i) + " -> ");
		// 将访问过的节点 置为 已经访问过
		isVisited[i] = true;
		// 将节点加入队列
		queue.addLast(i);
		
		while( ! queue.isEmpty()) {
			// 去除队列的头节点
			u = (Integer)queue.removeFirst();
			// 得到第一个邻结点的下标
			w = getFirstNeighbor(u);
			while(w != -1) {   // 说明找到了
				if(!isVisited[w]) {
					System.out.print(getValueByIndex(w) + " => ");
					// 标记已访问
					isVisited[w] = true;
					// 入队列
					queue.addLast(w);
				}
				// 加入已经访问过了，还是要以该访问过的节点作为起始点找w后面的下一个邻接点
				w = getNextNeighbor(u, w);     // 体现出广度优先
			}
		}
		
	}
	
	
	// 遍历所有的节点，都进行广度优先搜索
	public void bfs() {
		isVisited = new boolean[5];
		for(int i = 0; i<getNumOfVertex(); i++) {
			if(! isVisited[i]) {
				bfs(isVisited, i);
			}
		}
	}
	
	
	
	//图中常用的方法
	// 返回节点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}
	// 返回边的数量
	public int getNumOfEdges() {
		return numOfEdges;
	}
	// 返回节点i（下标）对应的数据
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}
	// 返回v1和v2的权值
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}
	// 显示图对应的矩阵
	public void showGraph() {
		// 就是遍历二维数组
		for(int[] link: edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	
	
	
	// 插入顶点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}
	
	// 添加边
	/**
	 * 
	 * @param v1     表示点的下标，即是第几个顶点
	 * @param v2     第二个顶点的下标 
	 * @param weight  要么是1，要么是0，代表是否可达
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v2] = weight;   // 由于是无向图，这一句还是要写的
		numOfEdges ++ ;
	}
}
