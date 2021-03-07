package recursion;


//思考：  求出最短路径。


public class MiGong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 创建二维数组，模拟迷宫
		// 地图
		int[][] map = new int[8][7];
		// 1表示墙
		// 四周全是1
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}

		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}

		// 设置挡板
		map[3][1] = 1;
		map[3][2] = 1;

		// 输出地图
		System.out.println("地图如下：  ");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		
		// 使用递归回溯给小球找路
		setWay2(map,1,1);
		
		//输出新地图
		System.out.println("小球走过的地图如下：  ");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}


	
	
	
	
	// 说明
	// 若小球能到达nap[6][5]，说明可到达
	// 约定map[i][j] = 0，表示还没走过
	// 约定map[i][j] = 2，表示可以走
	// 约定map[i][j] = 3，表示走过但是走不通 （回溯时无需再走这条路）
	// 走迷宫前，需要确定一个策略，下 -- 右 -- 上 ---左，若该点走不通，在回溯
	/**
	 * 
	 * @param map
	 *            表示地图
	 * @param i
	 *            从哪个位置开始出发
	 * @param j
	 * @return
	 */

	public static boolean setWay(int[][] map, int i, int j) {
		if (map[6][5] == 2) {
			return true;
		} else {
			// 先看路有没有走过
			if (map[i][j] == 0) {
				// 按策略走
				map[i][j] = 2; // 假定该点可以走通
				if (setWay(map, i + 1, j)) { // 向下走
					return true;
				} else if (setWay(map, i, j + 1)) { // 向右走
					return true;
				} else if (setWay(map, i - 1, j)) { // 向上走
					return true;
				} else if (setWay(map, i, j - 1)) { // 向左走
					return true;
				} else {
					map[i][j] = 3; // 是死路，走不通，置为3
					return false;
				}
			} else { // 如果map[i][j]不为0，可能为1，2，3
				return false;

			}
		}
	}

	
	
	//修改策略：   上 -- 右 -- 下 ---左
	public static boolean setWay2(int[][] map, int i, int j) {
		if (map[6][5] == 2) {
			return true;
		} else {
			// 先看路有没有走过
			if (map[i][j] == 0) {
				// 按策略走
				map[i][j] = 2; // 假定该点可以走通
				if (setWay2(map, i - 1, j)) { // 向上走
					return true;
				} else if (setWay2(map, i, j + 1)) { // 向右走
					return true;
				} else if (setWay2(map, i + 1, j)) { // 向下走
					return true;
				} else if (setWay2(map, i, j - 1)) { // 向左走
					return true;
				} else {
					map[i][j] = 3; // 是死路，走不通，置为3
					return false;
				}
			} else { // 如果map[i][j]不为0，可能为1，2，3
				return false;

			}
		}
	}

}
