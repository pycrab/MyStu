package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018��8��11�� ����10:17:04 
*/
public class SelectSort {

	private int[] arr;
	private static int cnt = 0;
	private int compare = 0;
	private int exchange = 0;
	
	public SelectSort() {
		arr = new int[10];
		for (int i = 0; i < 10; ++i) {
			arr[i] = (int) (Math.random() * 10);
		}
	}
	
	/**
	 * ��ͨѡ�������㷨�����κ��Ż�
	 */
	private void selectVersion1() {
		int min;
		for (int i = 0; i < arr.length - 1; ++i) {
			min = i;
			for (int j = i + 1; j < arr.length; ++j) {
				compare++;
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
				exchange++;
			}
			print();
		}
	}
	
	private void print() {
		cnt++;
		String string = "";
		for (int a : arr) {
			string += a;
			string += " ";
		}
		System.out.println(string + "��" + cnt + "���������-������" + compare + "�αȽ�-��" + exchange + "�ν���");
		compare = 0;
		exchange = 0;

	}
	public static void main(String[] args) {
		new SelectSort().selectVersion1();
		System.out.println("------------------------------------------------");

	}

}
