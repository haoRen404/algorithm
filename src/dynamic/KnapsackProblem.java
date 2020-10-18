package dynamic;

public class KnapsackProblem {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int[] w = {1, 4, 3};//��Ʒ������
		int[] val = {1500, 3000, 2000};// ��Ʒ�ļ�ֵ
		int m = 4;//����������
		int n = val.length;//��Ʒ�ĸ���
		
		//Ϊ�˼�¼������Ʒ�����������һ����ά����
		int[][] path = new int[n+1][m+1];
		
		//������ά���飬��ʾ��������д�ı�
		//v[i][j]��ʾǰi����Ʒ���ܹ�װ������Ϊj�ı���������ֵ
		int[][] v = new int[n + 1][m + 1];
		
		//��ʼ����ĵ�һ�к͵�һ��
		//��������Բ�����˲��裬��ΪĬ��ֵΪ0��Ϊ�����ֳ�˼·��������г�ʼ��
		for(int i = 0; i < v.length; i++) {
			v[i][0] = 0;//��һ������Ϊ0
		}
		for(int i = 1; i < v[0].length; i++) {
			v[0][i] = 0;//��һ������Ϊ0
		}
		
		//���ݹ�ʽ���ж�̬�滮����
		for(int i = 1; i < v.length; i++) {//������һ��
			for(int j = 1; j < v[0].length; j++) {//������һ��
				//���ù�ʽ
				if(w[i - 1] > j) {//��Ϊ����i��1�𣬶���ʽ��i��0������w[i-1]
					v[i][j] = v[i - 1][j];
				}else {
					//��Ϊ����i��1���������湫ʽ�����˵���
					//v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
					//Ϊ�˼�¼��Ʒ�������������ܼ򵥵�ʹ������������ʽ����Ҫʹ��if�����ֹ�ʽ
					if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
						v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
						//�ѵ�ǰ�������¼��path�����У�Ҳ���Ǽ�¼��Ʒ��������
						path[i][j] = 1;
					}else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}
		
		//���v
		for(int i = 0; i < v.length; i++) {
			for(int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		
		//����������Ʒ
		//����path
		//���Ǻ����
		int i = path.length - 1;//�е�����±�
		int j = path[0].length - 1;//�е�����±�
		while(i > 0 && j > 0) {//��path�����ʼ��
			if(path[i][j] == 1) {
				System.out.printf("��%d����Ʒ���뱳��\n", i);
				j -= w[i - 1];
			}
			i--;
		}
	}

}
