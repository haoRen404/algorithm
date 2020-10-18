package horse;

import java.awt.Point;//Point�ࣺ��ʾ (x, y) ����ռ��е�λ�õĵ㣬������������ָ����
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
	
	private static int X;//���̵�����
	private static int Y;//���̵�����
	//����һ��һά������������̸���λ���Ƿ񱻷��ʹ�����ȻҲ�����ö�ά����
	private static boolean visited[];
	//ʹ��һ�����ԣ�������̵�����λ���Ƿ񱻷��ʹ�
	private static boolean finished;//���Ϊtrue���ʾ�ɹ�
	
	public static void main(String[] args) {
		// ��̤�����㷨
		
		X = 8;
		Y = 8;
		//int row = 1;//�����ʼλ�õ��У���1��ʼ���
		//int column = 1;// �����ʼλ�õ��У���1��ʼ���
		int[][] chessboard = new int[X][Y];//��������
		visited = new boolean[X *Y];//��ʼֵ����flase,��δ���ʹ�
		
		long start = System.currentTimeMillis();
		
		//������̤�����㷨
		traversalChessboard(chessboard, 0, 0, 1);
		
		long end = System.currentTimeMillis();
		System.out.println("����ʱ��" + (end - start) + "����");
		
		//������̵�������
		for(int[] rows: chessboard) {
			for(int step: rows) {
				System.out.print(step + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * ��̤�����㷨
	 * @param chessboard	����
	 * @param row	�����ǰλ�õ��У���0��
	 * @param column	�����ǰλ�õ��У���0��
	 * @param step	��ǰ���е��ǵڼ������ӵ�1����
	 */
	public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {		
		chessboard[row][column] = step;//��ǳ���һ����������
		visited[row * X + column] = true;//�������б�Ǹ�λ���ѷ��ʹ���row * X + column������Ǹ�λ����һά����visited�е��±꣬Ҳ���Ǽ����ǵڼ���λ��
		//��ȡ��ǰλ�ý����������ߵ�λ�õļ���
		ArrayList<Point> ps = next(new Point(column, row));
		
		//��ps������������Ĺ����Ƕ�ps�����е�Point�������һ����λ�õ��������зǵݼ�����
		sort(ps);
		
		//����ps
		while(!ps.isEmpty()) {
			Point p = ps.remove(0);//�ڼ�����ȡ����ɾ��һ�������ߵ�λ��
			//�жϸõ��Ƿ��Ѿ����ʹ�
			if(!visited[p.y * X + p.x]) {//û�з��ʹ�
				//���еݹ�
				traversalChessboard(chessboard, p.y, p.x, step + 1);
			}
		}
		//����
		//�ж�����Ƿ����������ʹ��step��Ӧ���ߵĲ����Ƚ�
		//���û�дﵽ���������ʾ��û��������񣬽���������Ϊ0
		//˵����step < X * Y�������������������1�����̵�Ŀǰλ�ã���û�����꣬2�����̴��ڻ��ݹ�����
		if(step < X * Y && !finished) {
			chessboard[row][column] = 0;
			visited[row *X + column] = false;
		}else {
			finished = true;
		}
	}
	
	
	
	
	/**
	 * ���ܣ����ݵ�ǰλ�ã�Point���󣩣�����������������ߵ�λ��(�������Ƿ��߹�)�������뼯�ϣ�ArrayList���У�����а˸�
	 * @param curPoint	��ǰλ��
	 * @return	���������ߵ�λ�õļ���
	 */
	public static ArrayList<Point> next(Point curPoint){
		//����һ��ArrayList
		ArrayList<Point> ps = new ArrayList<Point>();
		//����һ��Point
		Point p1 = new Point();
		
		//�ж��Ƿ���������Ͻǵ������λ��
		if((p1.x = curPoint.x -2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		// �ж��Ƿ���������Ͻǵ������λ��
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		
		// �ж��Ƿ���������Ͻǵ������λ��
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		// �ж��Ƿ���������Ͻǵ������λ��
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		
		// �ж��Ƿ���������½ǵ������λ��
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		// �ж��Ƿ���������½ǵ������λ��
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		
		// �ж��Ƿ���������½ǵ������λ��
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		// �ж��Ƿ���������½ǵ������λ��
		if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}
	
	//���ݵ�ǰ��һ����������һ������һ�����ߵ�λ�����������зǵݹ����򣬼��ٻ��ݵĴ���
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {//���Ҫ�Լ��϶�����������������򣬿���ʵ��Comparator�ӿ��Դﵽ������Ҫ��Ŀ�ꡣ

			@Override
			public int compare(Point o1, Point o2) {
				// TODO �Զ����ɵķ������
				//��ȡo1��һ������λ�õĸ���
				int count1 = next(o1).size();
				//��ȡo2��һ������λ�õĸ���
				int count2 = next(o2).size();
				if(count1 < count2) {
					return -1;
				}else if(count1 == count2){
					return 0;
				}else {
					return 1;
				}
			}
			
		});
	}
	

}
