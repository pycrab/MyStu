package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018��8��31�� ����4:01:08 
*/
public class HeapSort {

	private int[] arr;
	
	public HeapSort() {
		arr = new int[10];
		for(int i = 0; i< 10; ++i) {
			arr[i] = (int) (Math.random()*10);
		}
	}
	
	/**
	 * ����������
	 */
	private void heapSort() {
		
		// 1.���ȹ����󶥶�
		// �����һ����Ҷ�ӽڵ㿪ʼ��������
		for(int curRoot = arr.length/2-1; curRoot >= 0; --curRoot) {
			adjustHeap(curRoot, arr.length-1);
		}
		
		// 2.�����Ѷ�����������ĩβ����,�����������µ���ʣ��Ķ�
		for(int tail = arr.length-1; tail > 0; --tail) {
			swap(0, tail);
			adjustHeap(0, tail-1);
		}
		
		// 3.���
		for(int i = 0;i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
	}
	
	/**
	 * �����ѣ�����������ڵ��β�ڵ�
	 * @param curRoot
	 * @param tail
	 */
	private void adjustHeap(int curRoot, int tail) {
		if(curRoot < arr.length/2) {
			int left = 2 * curRoot + 1;
			int right = 2 * curRoot + 2;
			if(left <= tail) {
				if(arr[curRoot] < arr[left]) {
					swap(curRoot, left);
					adjustHeap(left, tail);
				}
			}
			if(right <= tail) {
				if(arr[curRoot] < arr[right]) {
					swap(curRoot, right);
					adjustHeap(right, tail);
				}
			}
		}
		
	}
	private void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	public static void main(String[] args) {
		new HeapSort().heapSort();
	}

}
