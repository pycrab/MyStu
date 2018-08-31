package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018年8月31日 下午6:38:18 
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
	 * 希尔排序算法主体
	 */
	public void shellSort() {
		
		// 1.设置增量每次为原来的一半，直到为增量为1
		for (int det = arr.length/2; det > 0; det /= 2) {
			
			// 2.使用插入排序法从增量那组开始 向前 对每个子序列排序
			// 如果有奇数个数，从下标为增量处开始；如果为偶数个，从下标为增量+1处开始，才能不漏数据。综述，从增量处开始保证不漏数据。
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
