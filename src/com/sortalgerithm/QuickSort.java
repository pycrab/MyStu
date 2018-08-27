package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018��8��27�� ����8:17:30 
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
	 * ��õĿ�������
	 * @param left
	 * @param right
	 */
	public void quickSort(int left, int right) {
		if(left > right) {
			return;
		}
		// ȡ��һ������Ϊ��׼����i��jΪ�α�
		int temp = arr[left];
		int i = left;
		int j = right;
		
		while (i != j) {
			// ���ж��ұ�
			while (arr[j] >= temp && i < j) {
				j--;
			}
			// ���ж����
			while (arr[i] <= temp && i < j) {
				i++;
			}
			
			// ������������λ��
			if(i < j) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		// ����׼����λ
		arr[left] = arr[i];
		arr[i] = temp;
		
		// �ݹ��������ߺ��ұ�
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
