package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018��8��31�� ����6:38:18 
*/
public class ShellSort {

	private int[] arr;
	
	public ShellSort() {
		arr = new int[10];
		for(int i = 0; i< 10; ++i) {
			arr[i] = (int) (Math.random()*10);
		}
	}
	
	/**
	 * ϣ�������㷨����
	 */
	public void shellSort() {
		
		// 1.��������ÿ��Ϊԭ����һ�룬ֱ��Ϊ����Ϊ1
		for (int det = arr.length/2; det > 0; det /= 2) {
			
			// 2.ʹ�ò������򷨴��������鿪ʼ ��ǰ ��ÿ������������
			// ������������������±�Ϊ��������ʼ�����Ϊż���������±�Ϊ����+1����ʼ�����ܲ�©���ݡ�����������������ʼ��֤��©���ݡ�
			for(int i = det; i < arr.length; ++i) {
				int j = i;
				int temp = arr[j];
				while (j-det >= 0 && arr[j-det] > temp) {
					arr[j] = arr[j-det];
					j -= det;
				}
				arr[j] = temp;
			}
		}
		
		for(int i = 0;i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void maiin(String[] args) {
		new ShellSort().shellSort();
	}
}
