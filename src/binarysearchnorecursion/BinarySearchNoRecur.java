package binarysearchnorecursion;



public class BinarySearchNoRecur {

	public static void main(String[] args) {
		// 二分查找的非递归实现

		int[] arr = {1, 3, 8, 10, 11, 67, 100};
		int index = binarySearch(arr, 10);
		System.out.println("index=" + index);
	}


	//二分查找的非递归实现
	/**
	 * 二分查找的非递归实现
	 * @param arr		待查找的数组,需要是升序的数组
	 * @param target	需要查找的数
	 * @return			找到则返回下标，没找到则返回-1
	 */
	public static int binarySearch(int[] arr, int target) {
		int left = 0;//数组最左边元素的下标
		int right = arr.length - 1;//数组最右边元素的下标
		while(left <= right) {
			int mid = (left + right) / 2;//计算中点
			if(arr[mid] == target) {//找到
				return mid;
			}else if(arr[mid] > target) {//在左半段数组里
				right = mid - 1;
			}else {//在右半段数组里
				left = mid + 1;
			}
		}
		return -1;//没有找到
	}

}
