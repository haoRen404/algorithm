package kruskal;

import java.util.Arrays;

public class KruskalCase {
	
	private int edgeNum;//边的个数
	private char[] vertexs;//顶点数组
	private int[][] matrix;//边的邻接矩阵
	//使用较大的INF表示两个顶点不能连通
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		// 克鲁斯卡尔算法
		
		//顶点
		char[] vertexs = {'A','B','C','D','E','F','G'};
		//邻接矩阵
		int matrix[][] = {
				{6,12,INF,INF,INF,16,14},
				{12,0,10,INF,INF,7,INF},
				{INF,10,0,3,5,6,INF},
				{INF,INF,3,0,4,INF,INF},
				{INF,INF,5,4,0,2,8},
				{16,7,6,INF,2,0,9},
				{14,INF,INF,INF,8,9,0}
		};
		
		//构建KruskalCase对象
		KruskalCase kruskalCase =new KruskalCase(vertexs, matrix);
		//输出构建的邻接矩阵
		kruskalCase.print();
		
		EData[] edges = kruskalCase.getEdges();//取出所有边
		System.out.println("排序前=" + Arrays.toString(edges));
		kruskalCase.sortEdges(edges);//排序
		System.out.println("排序后=" + Arrays.toString(edges));
		
		kruskalCase.kruskal();
	}
	
	//构造器
	public KruskalCase(char[] vertexs, int[][] matrix) {
		//顶点个数
		int vlen = vertexs.length;
		
		//顶点初始化
		this.vertexs = new char[vlen];
		for(int i = 0; i < vertexs.length; i++) {
			this.vertexs[i] = vertexs[i];
		}
		
		//边初始化
		this.matrix = new int[vlen][vlen];
		for(int i = 0; i < vlen; i++) {
			for(int j = 0; j < vlen; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
		
		//边的数量
		for(int i = 0; i < vlen; i++) {
			for(int j = i + 1; j < vlen; j++) {//j=i+1,只遍历上半矩阵
				if(this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}
		
	}
	
	public void kruskal() {
		int index = 0;//表示最后结果数组的索引
		int[] ends = new int[edgeNum];//保存终点：用于保存“最小生成树”中的每个顶点在最小生成树中的终点
		EData[] rets = new EData[edgeNum];//保存最后生成的最小生成树
		
		//获取图中的所有边
		EData[] edges = getEdges();
		
		//对边进行排序,从小到大
		sortEdges(edges);
		
		//遍历edges数组，将边添加到最小生成树中时，判断是准备加入的边是否形成了回路，如果没有就加入rets，否则不加入
		for(int i = 0; i < edgeNum; i++) {
			//获取到第i条边的第一个顶点（起点）
			int p1 = getPosition(edges[i].start);
			//获取到第i条边的第二个顶点
			int p2 = getPosition(edges[i].end);
			
			//获取p1在当前最小生成树的的终点
			int m = getEnd(ends, p1);
			//获取p2在当前最小生成树的的终点
			int n = getEnd(ends, p2);
			
			//判断是否构成回路，即判断mn是否一样
			if(m != n) {//不构成回路
				ends[m] = n;//设置m在当前最小生成树中的终点
				rets[index++] = edges[i];//有一条边加入到树中
			}
		}
		//统计并打印最小生成树，输出rets
		System.out.println("最小生成树=");
		for(int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
		
	}
	
	
	
	//打印邻接矩阵
	public void print() {
		System.out.println("邻接矩阵");
		for(int i = 0; i < vertexs.length; i++) {
			for(int j = 0; j < vertexs.length; j++) {
				System.out.printf("%12d", matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	//对边进行排序处理，用冒泡吧
	//edges是边的集合
	private void sortEdges(EData[] edges) {
		for(int i =0; i < edges.length - 1; i++) {
			for(int j = 0; j < edges.length - 1 - i; j++) {
				if(edges[j].weight > edges[j + 1].weight) {
					EData tmp = edges[j];
					edges[j] = edges[j + 1];
					edges[j + 1] = tmp;
				}
			}
		}
	}
	
	/**
	 * 返回顶点ch的下标
	 * @param ch	顶点的值，比如'A','B'
	 * @return		返回ch顶点对应的下标，如果找不到则返回-1
	 */
	public int getPosition(char ch) {
		for(int i = 0; i < vertexs.length; i++) {
			if(vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 功能：取出图中所有的边，放到EData[]数组中
	 * 后面我们需要遍历该数组
	 * 通过matrix邻接矩阵来获取
	 * EData[]形式是[['A','B',12],……]
	 * @return
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgeNum];
		for(int i = 0; i < vertexs.length; i++) {
			for(int j = i + 1; j < vertexs.length; j++) {//j=j+1很奇妙，这样就只遍历矩阵的上半部分
				if(matrix[i][j] != INF) {
					edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}
	
	/**
	 * 终点
	 * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
	 * @param ends	记录各个顶点对应的终点，是在遍历过程中逐步形成的，在某顶点加入树中时，会修改该顶点的终点
	 * @param i		表示传入的顶点对应的下标
	 * @return		返回的就是下标为i的顶点对应的终点的下标
	 */
	private int getEnd(int[] ends, int i) {
		while(ends[i] != 0) {//不等于0，说明已经加入树中，有了终点
			i = ends[i];//取出终点下标
		}
		return i;//返回终点下标，如果加入树中则认为终点是自身
	}
	
}


//创建一个类EData，它的对象实例表示一条边
class EData{
	char start;//边的一个点
	char end;//边的另一个点
	int weight;//边的权值
	
	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	//重写toString
	public String toString() {
		return "[<" + start + "," + end + ">=" + weight + "]";
	}

}

