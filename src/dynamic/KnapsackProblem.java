package dynamic;

public class KnapsackProblem {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] w = {1, 4, 3};//物品的重量
		int[] val = {1500, 3000, 2000};// 物品的价值
		int m = 4;//背包的容量
		int n = val.length;//物品的个数
		
		//为了记录放入商品的情况，定义一个二维数组
		int[][] path = new int[n+1][m+1];
		
		//创建二维数组，表示我们所填写的表
		//v[i][j]表示前i个物品中能够装入容量为j的背包的最大价值
		int[][] v = new int[n + 1][m + 1];
		
		//初始化表的第一行和第一列
		//本程序可以不处理此步骤，因为默认值为0，为了体现出思路，这里进行初始化
		for(int i = 0; i < v.length; i++) {
			v[i][0] = 0;//第一列设置为0
		}
		for(int i = 1; i < v[0].length; i++) {
			v[0][i] = 0;//第一行设置为0
		}
		
		//根据公式进行动态规划处理
		for(int i = 1; i < v.length; i++) {//跳过第一行
			for(int j = 1; j < v[0].length; j++) {//跳过第一列
				//套用公式
				if(w[i - 1] > j) {//因为这里i从1起，而公式里i从0起，所以w[i-1]
					v[i][j] = v[i - 1][j];
				}else {
					//因为这里i从1起，所以下面公式进行了调整
					//v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
					//为了记录商品放入的情况，不能简单的使用上面这条公式，需要使用if来体现公式
					if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
						v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
						//把当前的情况记录到path数组中，也就是记录商品放入的情况
						path[i][j] = 1;
					}else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}
		
		//输出v
		for(int i = 0; i < v.length; i++) {
			for(int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		
		//输出放入的商品
		//遍历path
		//不是很理解
		int i = path.length - 1;//行的最大下标
		int j = path[0].length - 1;//列的最大下标
		while(i > 0 && j > 0) {//从path的最后开始找
			if(path[i][j] == 1) {
				System.out.printf("第%d个商品放入背包\n", i);
				j -= w[i - 1];
			}
			i--;
		}
	}

}
