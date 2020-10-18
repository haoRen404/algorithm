package dac;

public class Hanoitower {

	public static void main(String[] args) {
		// 分治算法
		//汉诺塔的实现
		
		hanoiTower(2, 'a', 'b', 'c');
	}
	
	/**
	 * 汉诺塔的实现
	 * @param num	一个有num个盘
	 * @param a b c		从a塔移动到c塔，中间借助b塔来实现		
	 */
	public static void hanoiTower(int num, char a, char b, char c) {
		//如果只有一个盘，则直接移过去
		if(num == 1) {
			System.out.println("第1个盘从" + a + "->"+ c);
		}else {
			//有n>=2个盘时，总把所有盘看成两个盘，最下面的盘为一个，上面的所有盘为一个
			//1、先把上面的所有盘移动到B塔A->B，移动过程会使用到C塔
			hanoiTower(num - 1, a, c, b);
			//2、把最下面的盘移动到C塔A->C
			System.out.println("第" + num + "个盘从" + a + "->" + c);
			//3、把B塔所有的盘移动到C
			hanoiTower(num - 1, b, a, c);
		}
		
	}

}
