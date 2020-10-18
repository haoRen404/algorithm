package floyd;

import java.util.Arrays;

public class FloydAlgorithm {

	public static void main(String[] args) {
		// floyd�㷨�����������㷨
		
		//������������
		char[] vertex = {'A','B','C','D','E','F','G'};
		//������׾���
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;
		matrix[0] = new int[] {0, 5, 7, N, N, N, 2};
		matrix[1] = new int[] {5, 0, N, 9, N, N, 3};
		matrix[2] = new int[] {7, N, 0, N, 8, N, N};
		matrix[3] = new int[] {N, 9, N, 0, N, 4, N};
		matrix[4] = new int[] {N, N, 8, N, 0, 5, 4};
		matrix[5] = new int[] {N, N, N, 4, 5, 0, 6};
		matrix[6] = new int[] {2, 3, N, N, 4, 6, 0};
		
		//����Graph����
		Graph graph = new Graph(vertex.length, matrix, vertex);
		//���ø��������㷨
		graph.floyd();
		//��ʾ
		graph.show();
	}

}





//����ͼ
class Graph{
	private char[]  vertex;//��Ŷ��������
	private int[][] dis;//����Ӹ��������������������ľ��룬���Ľ�������������
	private int[][] pre;//���浽��Ŀ�궥���ǰ������
	
	//������
	/**
	 * ������
	 * @param lenght	��С
	 * @param matrix	�ڽӾ���
	 * @param vertex	��������
	 */
	public Graph(int lenght, int[][] matrix, char[] vertex) {
		this.vertex = vertex;
		this.dis = matrix;
		this.pre = new int[lenght][lenght];
		
		//��pre������г�ʼ����pre���浽��Ŀ�궥���ǰ������
		for(int i =0; i < lenght; i++) {
			Arrays.fill(pre[i], i);
		}
	}
	
	//��ʾpre�����dis����
	public void show() {
		//Ϊ����ʾ�����Ķ�������д�ñȽ��鷳
		
		//��������
		char[] vertex = {'A','B','C','D','E','F','G'};
		for(int k = 0; k < dis.length; k++) {
			//��pre���������һ������
			for(int i = 0; i < dis.length; i++) {
				System.out.print(vertex[pre[k][i]] + " ");
			}
			System.out.println();
			
			//���dis�����һ������
			for(int i = 0; i < dis.length; i++) {
				System.out.print("��"+ vertex[k] + "��" + vertex[i] + "���·����" + dis[k][i] +"��");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	//���������㷨
	public void floyd() {
		int len = 0;// �����������
		//��֮�䶥�������k���м䶥����±�
		for(int k = 0; k < dis.length; k++) {
			//��i���㿪ʼ����[A��B��C��D��E��F��G]
			for(int i = 0; i < dis.length; i++) {
				for(int j = 0; j < dis.length; j++) {
					len = dis[i][k] + dis[k][j];//��i������������k���㣬����j����ľ���
					if(len < dis[i][j]) {//����������
						dis[i][j] = len;//���¾���
						pre[i][j] = pre[k][j];//����ǰ������
					}
					
				}
			}
		}
	}
	
	
}







