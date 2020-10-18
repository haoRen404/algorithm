package horse;

import java.awt.Point;//Point类：表示 (x, y) 坐标空间中的位置的点，以整数精度来指定。
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
	
	private static int X;//棋盘的列数
	private static int Y;//棋盘的行数
	//创建一个一维数组来标记棋盘各个位置是否被访问过，当然也可以用二维数组
	private static boolean visited[];
	//使用一个属性，标记棋盘的所有位置是否被访问过
	private static boolean finished;//如果为true则表示成功
	
	public static void main(String[] args) {
		// 马踏棋盘算法
		
		X = 8;
		Y = 8;
		//int row = 1;//马儿初始位置的行，从1开始编号
		//int column = 1;// 马儿初始位置的列，从1开始编号
		int[][] chessboard = new int[X][Y];//创建棋盘
		visited = new boolean[X *Y];//初始值都是flase,即未访问过
		
		long start = System.currentTimeMillis();
		
		//调用马踏棋盘算法
		traversalChessboard(chessboard, 0, 0, 1);
		
		long end = System.currentTimeMillis();
		System.out.println("共耗时：" + (end - start) + "毫秒");
		
		//输出棋盘的最后情况
		for(int[] rows: chessboard) {
			for(int step: rows) {
				System.out.print(step + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 马踏棋盘算法
	 * @param chessboard	棋盘
	 * @param row	马儿当前位置的行，从0起
	 * @param column	马儿当前位置的列，从0起
	 * @param step	当前进行的是第几步，从第1步起
	 */
	public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {		
		chessboard[row][column] = step;//标记成这一步走了这里
		visited[row * X + column] = true;//在数组中标记该位置已访问过，row * X + column计算的是该位置在一维数组visited中的下标，也就是计算是第几个位置
		//获取当前位置接下来可以走的位置的集合
		ArrayList<Point> ps = next(new Point(column, row));
		
		//对ps进行排序，排序的规则是对ps的所有的Point对象的下一步的位置的数量进行非递减排序
		sort(ps);
		
		//遍历ps
		while(!ps.isEmpty()) {
			Point p = ps.remove(0);//在集合中取出并删除一个可以走的位置
			//判断该点是否已经访问过
			if(!visited[p.y * X + p.x]) {//没有访问过
				//进行递归
				traversalChessboard(chessboard, p.y, p.x, step + 1);
			}
		}
		//回溯
		//判断马儿是否完成了任务，使用step和应该走的步数比较
		//如果没有达到数量，则表示还没有完成任务，将该棋盘置为0
		//说明：step < X * Y成立有两种两种情况：1、棋盘到目前位置，仍没有走完，2、棋盘处于回溯过程中
		if(step < X * Y && !finished) {
			chessboard[row][column] = 0;
			visited[row *X + column] = false;
		}else {
			finished = true;
		}
	}
	
	
	
	
	/**
	 * 功能：根据当前位置（Point对象），计算马儿接下来能走的位置(不考虑是否走过)，并放入集合（ArrayList）中，最多有八个
	 * @param curPoint	当前位置
	 * @return	接下来能走的位置的集合
	 */
	public static ArrayList<Point> next(Point curPoint){
		//创建一个ArrayList
		ArrayList<Point> ps = new ArrayList<Point>();
		//创建一个Point
		Point p1 = new Point();
		
		//判断是否可以走左上角的下面的位置
		if((p1.x = curPoint.x -2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		// 判断是否可以走左上角的上面的位置
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		
		// 判断是否可以走右上角的上面的位置
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		// 判断是否可以走右上角的下面的位置
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		
		// 判断是否可以走右下角的上面的位置
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		// 判断是否可以走右下角的下面的位置
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		
		// 判断是否可以走左下角的下面的位置
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		// 判断是否可以走左下角的上面的位置
		if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}
	
	//根据当前这一步的所有下一步的下一步可走的位置数量，进行非递归排序，减少回溯的次数
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {//如果要对集合对象或数组对象进行排序，可以实现Comparator接口以达到我们想要的目标。

			@Override
			public int compare(Point o1, Point o2) {
				// TODO 自动生成的方法存根
				//获取o1下一步所有位置的个数
				int count1 = next(o1).size();
				//获取o2下一步所有位置的个数
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
