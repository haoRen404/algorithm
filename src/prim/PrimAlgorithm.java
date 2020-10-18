package prim;

import java.util.Arrays;

public class PrimAlgorithm {

	public static void main(String[] args) {
		// 普里姆算法
		
		//顶点
		char[] data = new char[] {'A','B','C','D','E','F','G'};
		//顶点个数
		int verxs = data.length;
		//边，使用二维数组表示,（不连通相当于路程无穷大，用个大数表示就行，这里用1000）
		int[][] weight = new int[][] {
			{10000,5,7,10000,10000,10000,2},
			{5,10000,10000,9,10000,10000,3},
			{7,10000,10000,10000,8,10000,10000},
			{10000,9,10000,10000,10000,4,10000},
			{10000,10000,8,10000,10000,5,4},
			{10000,10000,10000,4,5,10000,6},
			{2,3,10000,10000,4,6,10000}
		};
		//创建图对象
		MGraph graph = new MGraph(verxs);
		//创建一个最小生成树对象
		MinTree minTree = new MinTree();
		//创建图的邻接矩阵
		minTree.createGraph(graph, verxs, data, weight);
		//输出图
		minTree.showGraph(graph);
		
		//测试生成最小生成树
		minTree.prim(graph, 0);
	}

}

//创建最小生成树类
class MinTree{
	/**
	 * 创建图的邻接矩阵
	 * @param graph	图对象
	 * @param verxs	图对应的顶点个数
	 * @param data	图对应的顶点个数
	 * @param weight	图的邻接矩阵
	 */
	public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
		int i, j;
		for(i = 0; i < verxs; i++) {
			graph.data[i] = data[i];
			for(j = 0; j < verxs; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}
	
	//显示图邻接矩阵的方法
	public void showGraph(MGraph graph) {
		for(int[] link: graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	/**
	 * 生成最小生成树
	 * 普里姆算法
	 * @param graph	图
	 * @param v		从v顶点开始生产
	 */
	public void prim(MGraph graph, int v) {
		//定义一个标记顶点是否被访问过的数组,默认值为0，表示没有访问过
		int visited[] = new int[graph.verx];
		/*
		 * java默认数组初始值为0，所以这里就不初始化了
		 */
		
		//把第一个顶点标记为已访问
		visited[v] = 1;
		
		int h1 = -1;//h1和h2用来记录最小边的两个顶点的下标
		int h2 = -1;
		int minWeight = 10000;//记录最小边的权值
		
		//找出边，最小生成树有 graph.verx-1条边，所以k从1起即可
		for(int k = 1; k < graph.verx; k++) {
			//下面两个for循环完之后就会找出一条最短的边
			for(int i = 0; i < graph.verx; i++) {
				for(int j = 0; j < graph.verx; j++) {
					//条件是：i顶点访问过，j顶点没有访问过，并且这条边的权值更小
					if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			//找到了一条最小的边
			System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">权值："+minWeight);
			//将当前找到的节点标记为已访问
			visited[h2] = 1;
			//重置minWeight为最大值10000
			minWeight = 10000;
		}
		
		
	}
	
}




//创建一个图类
class MGraph{
	int verx;//表示图的节点个数
	char[] data;//存放节点数据
	int[][] weight;//邻接矩阵，存放边
	
	public MGraph(int verxs) {
		this.verx = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
	
	
}
