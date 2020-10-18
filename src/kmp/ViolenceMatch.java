package kmp;

public class ViolenceMatch {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
		
		int i = 0;//ָ��str1������
		int j = 0;//ָ��str2������
		
		while(i < s1Len && j < s2Len) {
			if(s1[i] == s2[j]) {//ƥ��ɹ�
				i++;
				j++;
			}else {//û��ƥ��ɹ�
				//i���ݣ�j��0
				i = i - (j - 1);
				j = 0;
			}
			
		}
		
		//�ж��Ƿ�ƥ��ɹ�
		if(j == s2Len) {
			return i - j;
		}else {
			return -1;
		}
		
	}

}
