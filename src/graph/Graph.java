package graph;


//��Ȼ��������Ϊʲô��Ҫͼ��
//����ͼ������ͼ����Ȩͼ
//�ڽӾ����ڽӱ����������ʾͼ
//�ڽӱ���ӽ�ʡ�ռ䣬�����ڽӾ����еĴ�������ʾ���ɴ�ġ�0�������ֿռ�����˷ѵ��˵�


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	private ArrayList<String> vertexList;          // �洢���㼯��
	private int[][] edges;       //�洢ͼ��Ӧ���ڽӾ���
	private int numOfEdges;      // ��ʾ�ߵ�����
	
	// ����һ�����飬��¼ĳ���ڵ��Ƿ񱻷��ʹ�
	private boolean[] isVisited;
	public static void main(String[] args) {

		int n = 5;   // ���ĸ��ڵ�
		String Vertexs[] = {"A","B","C","D","E"};
		// ����ͼ����
		Graph graph = new Graph(n);
		// ѭ����Ӷ���
		for(String vertex: Vertexs) {
			graph.insertVertex(vertex);
		}
		// ��ӱ�
		// A-B, A-E, B-C, B-D, B-E
		graph.insertEdge(0, 0 , 1);  // A-A
		graph.insertEdge(0, 1 , 1);  // A-B
		graph.insertEdge(0, 4 , 1);
		graph.insertEdge(1, 2 , 1);
		graph.insertEdge(1, 3 , 1);
		graph.insertEdge(1, 4 , 1);

		System.out.println("======= ��ͼ���ڽӾ������� =======");
		graph.showGraph();
		
		
		System.out.println("======= ������ȱ��� ======");
		graph.dfs();
		
		System.out.println();
		System.out.println("======= ������ȱ��� ======");
		graph.bfs();
		
	}

	// ������
	public Graph(int n) {      // n����������
		// ��ʼ�������vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<>(n);
		numOfEdges = 0;   //��ʼΪ0
		isVisited = new boolean[n];
	}
	
	// �õ���һ���ڽӽڵ���±�
	public int getFirstNeighbor(int index) {
		for(int j = 0; j < vertexList.size(); j++) {
			if(edges[index][j] > 0) {     // ˵�������ڽ��
				return j;
			}
		}
		return -1;       // �����ڵĻ��ͷ��� -1
	}
	
	// ����ǰһ���ڽӽڵ���±�����ȡ��һ���ڽӽڵ�
	public int getNextNeighbor(int v1,int v2) {
		for(int j = v2+1; j<vertexList.size();j++) {
			if(edges[v1][j]>0) {
				return j;
			}
		}
		return -1;
	}
	
	
	// ������ȱ����㷨
	// ���������Ĺ����ж���Ҫ�ж� �ýڵ��Ƿ񱻷��ʣ���ע��Ҫ�õ��Ǹ�boolean����
	public void dfs(boolean[] isVisited, int i) {    // i��һ�ν���ֵΪ0
		
		ArrayList<Integer> tmp = new ArrayList<>();;  
		// ���ȷ��ʸýڵ�
//		System.out.print(getValueByIndex(i) + " -> ");
		// �����ʹ��Ľڵ� ��Ϊ �Ѿ����ʹ�
		isVisited[i] = true;
		// ���Ҹý��ĵ�һ���ڽӽڵ�w
		int w = getFirstNeighbor(i);
		while(w != -1) { // ˵�����ڽӽڵ�
			int tmpweight = getWeight(i, w);
			tmp.add(w);
//			tmp.add(tmpweight);
			System.out.println("tmp:"+tmp);

			if(!isVisited[w]) {  // ˵��û�б����ʹ�
				dfs(isVisited,w);
			}else {   // ����Ѿ������ʹ�
				w = getNextNeighbor(i, w);
			}
		}
	
	}
	
	
	// ��dfs�������أ��������нڵ㣬������dfs
	public void dfs() {
		isVisited = new boolean[5];
		for(int i = 0 ; i< getNumOfVertex(); i++) {
			if(!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	
	
	
	// ������ȱ���
	public void bfs(boolean[] isVisited, int i) {
		int u;     // ��ʾ����ͷ�����±�
		int w;     // ��ʾ�ڽӽڵ�w
		// ��¼�ڵ���ʵ�˳��
		// �������ȱ������������һ��queue������ʵ�ֹ�ȣ���removeFirst��addLast����
		LinkedList queue = new LinkedList();
		// ���ȷ��ʸýڵ�
		System.out.print(getValueByIndex(i) + " -> ");
		// �����ʹ��Ľڵ� ��Ϊ �Ѿ����ʹ�
		isVisited[i] = true;
		// ���ڵ�������
		queue.addLast(i);
		
		while( ! queue.isEmpty()) {
			// ȥ�����е�ͷ�ڵ�
			u = (Integer)queue.removeFirst();
			// �õ���һ���ڽ����±�
			w = getFirstNeighbor(u);
			while(w != -1) {   // ˵���ҵ���
				if(!isVisited[w]) {
					System.out.print(getValueByIndex(w) + " => ");
					// ����ѷ���
					isVisited[w] = true;
					// �����
					queue.addLast(w);
				}
				// �����Ѿ����ʹ��ˣ�����Ҫ�Ը÷��ʹ��Ľڵ���Ϊ��ʼ����w�������һ���ڽӵ�
				w = getNextNeighbor(u, w);     // ���ֳ��������
			}
		}
		
	}
	
	
	// �������еĽڵ㣬�����й����������
	public void bfs() {
		isVisited = new boolean[5];
		for(int i = 0; i<getNumOfVertex(); i++) {
			if(! isVisited[i]) {
				bfs(isVisited, i);
			}
		}
	}
	
	
	
	//ͼ�г��õķ���
	// ���ؽڵ�ĸ���
	public int getNumOfVertex() {
		return vertexList.size();
	}
	// ���رߵ�����
	public int getNumOfEdges() {
		return numOfEdges;
	}
	// ���ؽڵ�i���±꣩��Ӧ������
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}
	// ����v1��v2��Ȩֵ
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}
	// ��ʾͼ��Ӧ�ľ���
	public void showGraph() {
		// ���Ǳ�����ά����
		for(int[] link: edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	
	
	
	// ���붥��
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}
	
	// ��ӱ�
	/**
	 * 
	 * @param v1     ��ʾ����±꣬���ǵڼ�������
	 * @param v2     �ڶ���������±� 
	 * @param weight  Ҫô��1��Ҫô��0�������Ƿ�ɴ�
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v2] = weight;   // ����������ͼ����һ�仹��Ҫд��
		numOfEdges ++ ;
	}
}
