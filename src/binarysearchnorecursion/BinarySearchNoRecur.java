package binarysearchnorecursion;



public class BinarySearchNoRecur {

	public static void main(String[] args) {
		// ���ֲ��ҵķǵݹ�ʵ��

		int[] arr = {1, 3, 8, 10, 11, 67, 100};
		int index = binarySearch(arr, 10);
		System.out.println("index=" + index);
	}


	//���ֲ��ҵķǵݹ�ʵ��
	/**
	 * ���ֲ��ҵķǵݹ�ʵ��
	 * @param arr		�����ҵ�����,��Ҫ�����������
	 * @param target	��Ҫ���ҵ���
	 * @return			�ҵ��򷵻��±꣬û�ҵ��򷵻�-1
	 */
	public static int binarySearch(int[] arr, int target) {
		int left = 0;//���������Ԫ�ص��±�
		int right = arr.length - 1;//�������ұ�Ԫ�ص��±�
		while(left <= right) {
			int mid = (left + right) / 2;//�����е�
			if(arr[mid] == target) {//�ҵ�
				return mid;
			}else if(arr[mid] > target) {//������������
				right = mid - 1;
			}else {//���Ұ��������
				left = mid + 1;
			}
		}
		return -1;//û���ҵ�
	}

}
