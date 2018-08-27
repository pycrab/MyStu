package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018年8月27日 下午8:17:30 
*/
public class QuickSort {

	private int[] arr;
	public QuickSort() {
		arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
	}
	
	/**
	 * 最常用的快速排序
	 * @param left
	 * @param right
	 */
	public void quickSort(int left, int right) {
		if(left > right) {
			return;
		}
		// 1、取第一个数作为基准数，i和j为左右游标
		int temp = arr[left];
		int i = left;
		int j = right;
		
		while (i != j) {
			// 2、左右游标扫描
			// 先判断右边
			while (arr[j] >= temp && i < j) {
				j--;
			}
			// 再判断左边
			while (arr[i] <= temp && i < j) {
				i++;
			}

			// 3、左右游标相遇，交换两个数的位置
			if(i < j) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		// 4、将基准数归位
		arr[left] = arr[i];
		arr[i] = temp;
		
		// 递归地排序左边和右边
		quickSort(left, i-1);
		quickSort(i+1, right);
	}
	
	/**
	 * 快速排序优化点：
	 * 	1. 对小数组进行插入排序，而不进行递归
	 * 		修改 if(left + M >= right){InsertSort(left, right); return;}
	 *  2. 随机选取基准数并与left位置的数交换
	 *  	修改 int ranIndex = (int)(left+Math.random()*(right-left));exchange(arr, left, ranIndex);
	 *      或者三数取中法，选择首位和中间的数的中位数作为基准数
	 * 
	 */
	
	public static void main(String[] args) {
		QuickSort quick = new QuickSort();
		quick.quickSort(0, quick.arr.length-1);
		for (int a : quick.arr) {
			System.out.print(a + " ");
		}

	}

}
