package kruskal;

import java.util.Arrays;

public class KruskalCase {
	
	private int edgeNum;//�ߵĸ���
	private char[] vertexs;//��������
	private int[][] matrix;//�ߵ��ڽӾ���
	//ʹ�ýϴ��INF��ʾ�������㲻����ͨ
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		// ��³˹�����㷨
		
		//����
		char[] vertexs = {'A','B','C','D','E','F','G'};
		//�ڽӾ���
		int matrix[][] = {
				{6,12,INF,INF,INF,16,14},
				{12,0,10,INF,INF,7,INF},
				{INF,10,0,3,5,6,INF},
				{INF,INF,3,0,4,INF,INF},
				{INF,INF,5,4,0,2,8},
				{16,7,6,INF,2,0,9},
				{14,INF,INF,INF,8,9,0}
		};
		
		//����KruskalCase����
		KruskalCase kruskalCase =new KruskalCase(vertexs, matrix);
		//����������ڽӾ���
		kruskalCase.print();
		
		EData[] edges = kruskalCase.getEdges();//ȡ�����б�
		System.out.println("����ǰ=" + Arrays.toString(edges));
		kruskalCase.sortEdges(edges);//����
		System.out.println("�����=" + Arrays.toString(edges));
		
		kruskalCase.kruskal();
	}
	
	//������
	public KruskalCase(char[] vertexs, int[][] matrix) {
		//�������
		int vlen = vertexs.length;
		
		//�����ʼ��
		this.vertexs = new char[vlen];
		for(int i = 0; i < vertexs.length; i++) {
			this.vertexs[i] = vertexs[i];
		}
		
		//�߳�ʼ��
		this.matrix = new int[vlen][vlen];
		for(int i = 0; i < vlen; i++) {
			for(int j = 0; j < vlen; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
		
		//�ߵ�����
		for(int i = 0; i < vlen; i++) {
			for(int j = i + 1; j < vlen; j++) {//j=i+1,ֻ�����ϰ����
				if(this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}
		
	}
	
	public void kruskal() {
		int index = 0;//��ʾ��������������
		int[] ends = new int[edgeNum];//�����յ㣺���ڱ��桰��С���������е�ÿ����������С�������е��յ�
		EData[] rets = new EData[edgeNum];//����������ɵ���С������
		
		//��ȡͼ�е����б�
		EData[] edges = getEdges();
		
		//�Ա߽�������,��С����
		sortEdges(edges);
		
		//����edges���飬������ӵ���С��������ʱ���ж���׼������ı��Ƿ��γ��˻�·�����û�оͼ���rets�����򲻼���
		for(int i = 0; i < edgeNum; i++) {
			//��ȡ����i���ߵĵ�һ�����㣨��㣩
			int p1 = getPosition(edges[i].start);
			//��ȡ����i���ߵĵڶ�������
			int p2 = getPosition(edges[i].end);
			
			//��ȡp1�ڵ�ǰ��С�������ĵ��յ�
			int m = getEnd(ends, p1);
			//��ȡp2�ڵ�ǰ��С�������ĵ��յ�
			int n = getEnd(ends, p2);
			
			//�ж��Ƿ񹹳ɻ�·�����ж�mn�Ƿ�һ��
			if(m != n) {//�����ɻ�·
				ends[m] = n;//����m�ڵ�ǰ��С�������е��յ�
				rets[index++] = edges[i];//��һ���߼��뵽����
			}
		}
		//ͳ�Ʋ���ӡ��С�����������rets
		System.out.println("��С������=");
		for(int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
		
	}
	
	
	
	//��ӡ�ڽӾ���
	public void print() {
		System.out.println("�ڽӾ���");
		for(int i = 0; i < vertexs.length; i++) {
			for(int j = 0; j < vertexs.length; j++) {
				System.out.printf("%12d", matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	//�Ա߽�����������ð�ݰ�
	//edges�Ǳߵļ���
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
	 * ���ض���ch���±�
	 * @param ch	�����ֵ������'A','B'
	 * @return		����ch�����Ӧ���±꣬����Ҳ����򷵻�-1
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
	 * ���ܣ�ȡ��ͼ�����еıߣ��ŵ�EData[]������
	 * ����������Ҫ����������
	 * ͨ��matrix�ڽӾ�������ȡ
	 * EData[]��ʽ��[['A','B',12],����]
	 * @return
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgeNum];
		for(int i = 0; i < vertexs.length; i++) {
			for(int j = i + 1; j < vertexs.length; j++) {//j=j+1�����������ֻ����������ϰ벿��
				if(matrix[i][j] != INF) {
					edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}
	
	/**
	 * �յ�
	 * ��ȡ�±�Ϊi�Ķ�����յ㣬���ں����ж�����������յ��Ƿ���ͬ
	 * @param ends	��¼���������Ӧ���յ㣬���ڱ������������γɵģ���ĳ�����������ʱ�����޸ĸö�����յ�
	 * @param i		��ʾ����Ķ����Ӧ���±�
	 * @return		���صľ����±�Ϊi�Ķ����Ӧ���յ���±�
	 */
	private int getEnd(int[] ends, int i) {
		while(ends[i] != 0) {//������0��˵���Ѿ��������У������յ�
			i = ends[i];//ȡ���յ��±�
		}
		return i;//�����յ��±꣬���������������Ϊ�յ�������
	}
	
}


//����һ����EData�����Ķ���ʵ����ʾһ����
class EData{
	char start;//�ߵ�һ����
	char end;//�ߵ���һ����
	int weight;//�ߵ�Ȩֵ
	
	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	//��дtoString
	public String toString() {
		return "[<" + start + "," + end + ">=" + weight + "]";
	}

}

