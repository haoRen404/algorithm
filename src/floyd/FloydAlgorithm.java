package floyd;

import java.util.Arrays;

public class FloydAlgorithm {

	public static void main(String[] args) {
		// floyd算法，弗洛伊德算法
		
		//创建顶点数组
		char[] vertex = {'A','B','C','D','E','F','G'};
		//创建零阶矩阵
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;
		matrix[0] = new int[] {0, 5, 7, N, N, N, 2};
		matrix[1] = new int[] {5, 0, N, 9, N, N, 3};
		matrix[2] = new int[] {7, N, 0, N, 8, N, N};
		matrix[3] = new int[] {N, 9, N, 0, N, 4, N};
		matrix[4] = new int[] {N, N, 8, N, 0, 5, 4};
		matrix[5] = new int[] {N, N, N, 4, 5, 0, 6};
		matrix[6] = new int[] {2, 3, N, N, 4, 6, 0};
		
		//创建Graph对象
		Graph graph = new Graph(vertex.length, matrix, vertex);
		//调用弗洛伊德算法
		graph.floyd();
		//显示
		graph.show();
	}

}





//创建图
class Graph{
	private char[]  vertex;//存放顶点的数组
	private int[][] dis;//保存从各个顶点出发到其他顶点的距离，最后的结果就是这个数组
	private int[][] pre;//保存到达目标顶点的前驱顶点
	
	//构造器
	/**
	 * 构造器
	 * @param lenght	大小
	 * @param matrix	邻接矩阵
	 * @param vertex	顶点数组
	 */
	public Graph(int lenght, int[][] matrix, char[] vertex) {
		this.vertex = vertex;
		this.dis = matrix;
		this.pre = new int[lenght][lenght];
		
		//对pre数组进行初始化，pre保存到达目标顶点的前驱顶点
		for(int i =0; i < lenght; i++) {
			Arrays.fill(pre[i], i);
		}
	}
	
	//显示pre数组和dis数组
	public void show() {
		//为了显示方便阅读，这里写得比较麻烦
		
		//顶点数组
		char[] vertex = {'A','B','C','D','E','F','G'};
		for(int k = 0; k < dis.length; k++) {
			//先pre数组输出的一行数据
			for(int i = 0; i < dis.length; i++) {
				System.out.print(vertex[pre[k][i]] + " ");
			}
			System.out.println();
			
			//输出dis数组的一行数据
			for(int i = 0; i < dis.length; i++) {
				System.out.print("（"+ vertex[k] + "到" + vertex[i] + "最短路径是" + dis[k][i] +"）");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	//弗洛伊德算法
	public void floyd() {
		int len = 0;// 变量保存距离
		//对之间顶点遍历，k是中间顶点的下标
		for(int k = 0; k < dis.length; k++) {
			//从i顶点开始出发[A，B，C，D，E，F，G]
			for(int i = 0; i < dis.length; i++) {
				for(int j = 0; j < dis.length; j++) {
					len = dis[i][k] + dis[k][j];//从i顶点出发，结果k顶点，到达j顶点的距离
					if(len < dis[i][j]) {//如果距离更短
						dis[i][j] = len;//更新距离
						pre[i][j] = pre[k][j];//更新前驱顶点
					}
					
				}
			}
		}
	}
	
	
}







