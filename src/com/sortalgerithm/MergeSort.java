package com.sortalgerithm;

import java.util.Arrays;

/** 
* @author: pycrab
* @Date: 2018��8��31�� ����9:07:34 
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
	 * �ݹ���ʽ�Ĺ鲢����
	 * @param array
	 */
	private void mergeSort(int[] array) {
		int len = array.length;
		int middle = array.length/2;
		if(len > 1) {
			// 1.��������ж��֣�ֱ��ÿ������ֻ��һ���������
			int[] left = Arrays.copyOfRange(array, 0, middle);
			int[] right = Arrays.copyOfRange(array, middle, len);
			mergeSort(left);
			mergeSort(right);
			
			// 2.�������н��кϲ�
			merge(array, left, right);
		}
	}
	
	/**
	 * �ϲ�������
	 * 	��������ָ��l��r�ֱ�ָ������������ͷ����һ��ָ��iָ��������ͷ��
	 * 	�Ƚ�l��r����ֵ����i���������ָ��ֱ�����ƶ�
	 * 	�����һ��������û�����꣬�����������ɸ���
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
