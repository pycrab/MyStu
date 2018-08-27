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
		// 取第一个数作为基准数，i和j为游标
		int temp = arr[left];
		int i = left;
		int j = right;
		
		while (i != j) {
			// 先判断右边
			while (arr[j] >= temp && i < j) {
				j--;
			}
			// 再判断左边
			while (arr[i] <= temp && i < j) {
				i++;
			}
			
			// 交换两个数的位置
			if(i < j) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		// 将基准数归位
		arr[left] = arr[i];
		arr[i] = temp;
		
		// 递归地排序左边和右边
		quickSort(left, i-1);
		quickSort(i+1, right);
	}
	
	public static void main(String[] args) {
		QuickSort quick = new QuickSort();
		quick.quickSort(0, quick.arr.length-1);
		for (int a : quick.arr) {
			System.out.print(a + " ");
		}

	}

}
