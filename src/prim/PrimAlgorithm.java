package prim;

import java.util.Arrays;

public class PrimAlgorithm {

	public static void main(String[] args) {
		// ����ķ�㷨
		
		//����
		char[] data = new char[] {'A','B','C','D','E','F','G'};
		//�������
		int verxs = data.length;
		//�ߣ�ʹ�ö�ά�����ʾ,������ͨ�൱��·��������ø�������ʾ���У�������1000��
		int[][] weight = new int[][] {
			{10000,5,7,10000,10000,10000,2},
			{5,10000,10000,9,10000,10000,3},
			{7,10000,10000,10000,8,10000,10000},
			{10000,9,10000,10000,10000,4,10000},
			{10000,10000,8,10000,10000,5,4},
			{10000,10000,10000,4,5,10000,6},
			{2,3,10000,10000,4,6,10000}
		};
		//����ͼ����
		MGraph graph = new MGraph(verxs);
		//����һ����С����������
		MinTree minTree = new MinTree();
		//����ͼ���ڽӾ���
		minTree.createGraph(graph, verxs, data, weight);
		//���ͼ
		minTree.showGraph(graph);
		
		//����������С������
		minTree.prim(graph, 0);
	}

}

//������С��������
class MinTree{
	/**
	 * ����ͼ���ڽӾ���
	 * @param graph	ͼ����
	 * @param verxs	ͼ��Ӧ�Ķ������
	 * @param data	ͼ��Ӧ�Ķ������
	 * @param weight	ͼ���ڽӾ���
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
	
	//��ʾͼ�ڽӾ���ķ���
	public void showGraph(MGraph graph) {
		for(int[] link: graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	/**
	 * ������С������
	 * ����ķ�㷨
	 * @param graph	ͼ
	 * @param v		��v���㿪ʼ����
	 */
	public void prim(MGraph graph, int v) {
		//����һ����Ƕ����Ƿ񱻷��ʹ�������,Ĭ��ֵΪ0����ʾû�з��ʹ�
		int visited[] = new int[graph.verx];
		/*
		 * javaĬ�������ʼֵΪ0����������Ͳ���ʼ����
		 */
		
		//�ѵ�һ��������Ϊ�ѷ���
		visited[v] = 1;
		
		int h1 = -1;//h1��h2������¼��С�ߵ�����������±�
		int h2 = -1;
		int minWeight = 10000;//��¼��С�ߵ�Ȩֵ
		
		//�ҳ��ߣ���С�������� graph.verx-1���ߣ�����k��1�𼴿�
		for(int k = 1; k < graph.verx; k++) {
			//��������forѭ����֮��ͻ��ҳ�һ����̵ı�
			for(int i = 0; i < graph.verx; i++) {
				for(int j = 0; j < graph.verx; j++) {
					//�����ǣ�i������ʹ���j����û�з��ʹ������������ߵ�Ȩֵ��С
					if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			//�ҵ���һ����С�ı�
			System.out.println("��<"+graph.data[h1]+","+graph.data[h2]+">Ȩֵ��"+minWeight);
			//����ǰ�ҵ��Ľڵ���Ϊ�ѷ���
			visited[h2] = 1;
			//����minWeightΪ���ֵ10000
			minWeight = 10000;
		}
		
		
	}
	
}




//����һ��ͼ��
class MGraph{
	int verx;//��ʾͼ�Ľڵ����
	char[] data;//��Žڵ�����
	int[][] weight;//�ڽӾ��󣬴�ű�
	
	public MGraph(int verxs) {
		this.verx = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
	
	
}
