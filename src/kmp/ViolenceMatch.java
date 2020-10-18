package kmp;

public class ViolenceMatch {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String str1 = "adsbdjbsjdbjdwbdjsbdjwud";
		String str2 = "jdbgg";
		
		int a = violenceMatch(str1, str2);
		System.out.println("a=" + a);
	}
	
	
	public static int violenceMatch(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		
		int s1Len = s1.length;
		int s2Len = s2.length;
		
		int i = 0;//指向str1的索引
		int j = 0;//指向str2的索引
		
		while(i < s1Len && j < s2Len) {
			if(s1[i] == s2[j]) {//匹配成功
				i++;
				j++;
			}else {//没有匹配成功
				//i回溯，j置0
				i = i - (j - 1);
				j = 0;
			}
			
		}
		
		//判断是否匹配成功
		if(j == s2Len) {
			return i - j;
		}else {
			return -1;
		}
		
	}

}
