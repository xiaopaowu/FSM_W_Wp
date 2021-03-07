import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class selfPractice {

	private ArrayList<String> vertexList = new ArrayList<>();
	private int[][] edges;
	private int numOfEdges;
	private boolean[] isVisited;   
	
	public static void main(String[] args) {
		
		// 顶点合计
		int n = 5;
		String vertex[] = {"A","B","c","D","E"};
		selfPractice graph = new selfPractice(5);
		for(String v: vertex) {
			graph.insertVertex(v);
		}
		
		// A-B, A-C, B-C, B-D, B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		
		System.out.println("====== 图的邻接矩阵 ========");
		graph.show();

		
		
	}
	

	public int getFirstNeighbor(int index) {
		for(int j = 0;j<vertexList.size();j++) {
			if(edges[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	
	public int getNextNeighbor(int v1, int v2) {
		for(int j = v2+1; j<vertexList.size();j++) {
			if(edges[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	
	
	
	// 深度遍历
	public void dfs(boolean[] isVisited, int i) {
		System.out.print(getValueByIndex(i) + " => ");
		isVisited[i] = true;
		int w =  getFirstNeighbor(i);
		while(w != -1) {
			if(!isVisited[w]) {
				dfs(isVisited, w);
			}else {
				w = getNextNeighbor(i, w);
			}
		}
	}
	
	public void dfs() {
		isVisited = new boolean[5];
		for(int i =0; i< vertexList.size();i++) {
			if(!isVisited[i]) {
				dfs(isVisited,i);
			}
		}
	}
	
	
	// 广度优先遍历
	public void bfs(boolean[] isVisited,int i) {		
		LinkedList queue = new LinkedList();
		System.out.println(getValueByIndex(i) + " -> ");
		isVisited[i] = true;
		queue.addLast(i);
		
		while (! queue.isEmpty()) {
			int u = (Integer)queue.removeFirst();
			int w = getFirstNeighbor(u);
			while( w != -1) {
				if(!isVisited[w]) {
					System.out.println(getValueByIndex(w) + " -> ");
					
					isVisited[w] = true;
					queue.addLast(w);
				}
			    w = getNextNeighbor(u, w);
			}
			
		}
			
		
		
		
		
	}
	
	
	public void bfs() {
		for(int i = 0; i < vertexList.size(); i++) {
			if(!isVisited[i]) {
				bfs(isVisited, i);
			}
		}
	}
	
	
	// 构造器
	public selfPractice(int n) {
		vertexList = new ArrayList<>(n);
		edges = new int[n][n];
		numOfEdges = 0;
	    isVisited = new boolean[n];
	}
	
	
	// 图中常用方法
	public int getNumOfVertex() {
		return vertexList.size();
	}
	public int getNumOfEdges() {
		return numOfEdges;
	}
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}
	public int getWeight(int v1,int v2) {
		return edges[v1][v2];
	}
	public void show() {
		for(int[] link:edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	
	// 插入顶点
	public void insertVertex(String verex) {
		vertexList.add(verex);
	}
	// 添加边
	public void insertEdge(int v1, int v2,int weight) {
		edges[v1][v2] = 1;
		edges[v2][v1] = 1;
		numOfEdges ++ ;
	}
}

	
	
