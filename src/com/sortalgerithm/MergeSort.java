package com.sortalgerithm;

import java.util.Arrays;

/** 
* @author: pycrab
* @Date: 2018年8月31日 下午9:07:34 
*/
public class MergeSort {

	private int[] arr;
	
	public MergeSort() {
		arr = new int[10];
		for(int i = 0; i< 10; ++i) {
			arr[i] = (int) (Math.random()*10);
		}
	}
	
	/**
	 * 递归形式的归并排序
	 * @param array
	 */
	private void mergeSort(int[] array) {
		int len = array.length;
		int middle = array.length/2;
		if(len > 1) {
			// 1.对数组进行二分，直到每个序列只有一个有序的数
			int[] left = Arrays.copyOfRange(array, 0, middle);
			int[] right = Arrays.copyOfRange(array, middle, len);
			mergeSort(left);
			mergeSort(right);
			
			// 2.对子序列进行合并
			merge(array, left, right);
		}
	}
	
	/**
	 * 合并子序列
	 * 	采用两个指针l和r分别指向两个子序列头部，一个指针i指向结果数组头部
	 * 	比较l和r处的值填入i处，填入后指针分别向后移动
	 * 	如果有一个子序列没遍历完，则继续遍历完成复制
	 * @param array
	 * @param left
	 * @param right
	 */
	private void merge(int[] array, int[] left, int[] right) {
		int i = 0, l = 0, r = 0;
		while(l < left.length && r < right.length) {
			if(left[l] < right[r]) {
				array[i] = left[l];
				l++;
				i++;
			}else {
				array[i] = right[r];
				r++;
				i++;
			}
		}
		
		while (l < left.length) {
			array[i] = left[l];
			l++;
			i++;
		}
		while (r < right.length) {
			array[i] = right[r];
			r++;
			i++;
		}
	}
	
	private void printf() {
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		ms.mergeSort(ms.arr);
		ms.printf();
	}



}
