package com.sortalgerithm;

/** 
* @author: pycrab
* @Date: 2018年8月10日 下午9:54:50 
*/
public class BubbleSort {

	private int[] arr;
	// 排序次数标记，用于打印数组
	static int cnt = 0;
	// 比较次数标记，用于打印时显式体现version3的效果
	static int compare = 0;
	
	public BubbleSort() {
		arr = new int[10];
		for (int i = 0; i < 10; ++i) {
			arr[i] = (int) (Math.random() * 10);
		}
		
		// 有序区优化样例，少几次内层循环
//		arr[0] = 4;
//		arr[1] = 5;
//		arr[2] = 8;
//		arr[3] = 6;
//		arr[4] = 3;
//		arr[5] = 2;
//		arr[6] = 1;
//		arr[7] = 9;
//		arr[8] = 0;
//		arr[9] = 7;
	}
	
	/**
	 * 普通冒泡排序算法
	 * 无任何优化，是稳定排序
	 * 外层循环控制循环趟数，内层循环控制每一趟的比较及交换处理，每趟排序后数组后边不变
	 */
	private void bubbleVersion1() {
		for (int i = arr.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				compare++;

				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					
				}
			}
			print();
		}
	}
	
	/**
	 * 冒泡排序第一次优化
	 * 如果遍历时发现未排序的已经有序，则不需要继续执行循环，直接退出
	 * 设置一个标记sorted，默认为true，如果交换则设置为false，内层循环结束判断标记，决定是否有序是否可以退出
	 * 样例：2134567890
	 */
	private void bubbleVersion2() {
		boolean sorted;
		for (int i = arr.length - 1; i > 0; --i) {
			sorted = true;
			for (int j = 0; j < i; ++j) {
				compare++;

				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					
					sorted = false;

				}
			}
			print();
			
			if (sorted) {
				break;
			}
		}
	}
	
	/**
	 * 冒泡排序第二次优化
	 * 如果有序区之前的数据也是有序，则需要扩大有序区，避免无意义的比较
	 * 设置一个标记记录最后一次交换的位置，作为下一次循环的边界
	 * 样例：4586321907
	 */
	private void bubbleVersion3() {
		boolean sorted;
		int border = arr.length - 1;
		int lastExchangeIndex = 0;
		for (int i = arr.length - 1; i > 0; --i) {
			sorted = true;
			for (int j = 0; j < border; ++j) {
				compare++;

				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					
					// 减少一次比较，但是多了很多compare比较，得不偿失
//					if (compare != 1) {
//						sorted = false;
//					}
					
					lastExchangeIndex = j;
					sorted = false;

				}
			}
			print();
			
			if (sorted) {
				break;
			}
			border = lastExchangeIndex;

		}
	}
	
	/**
	 * 冒泡排序第三次优化
	 * 使用双向比较和交换交叉进行
	 * 适用于大部分元素已经有序的情况，优点是在特定情况下减少循环次数，缺点是代码量扩大了一倍
	 * 针对有序区的优化，需要设置两个边界
	 * 样例：1234567890
	 */
	private void bubbleVersion4() {
		boolean sorted;
		int rightBorder = arr.length - 1;
		int leftBorder = 0;
		int rightLastExchangeIndex = 0;
		int leftLastExchangeIndex = 0;

		for (int i = arr.length - 1; i > 0; --i) {
			sorted = true;
			
			// 从左往右
			for (int j = leftBorder; j < rightBorder; ++j) {
				compare++;

				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					
					rightLastExchangeIndex = j;
					sorted = false;

				}
			}
			print();
			
			if (sorted) {
				break;
			}
			rightBorder = rightLastExchangeIndex;
			
			// 从右往左
			sorted = true;
			for (int j = rightBorder; j > leftBorder; --j) {
				compare++;

				if (arr[j] < arr[j-1]) {
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
					
					leftLastExchangeIndex = j;
					sorted = false;

				}
			}
			print();
			
			if (sorted) {
				break;
			}
			leftBorder = leftLastExchangeIndex;

		}
	}
	
	private void print() {
		cnt++;
		String string = "";
		for (int a : arr) {
			string += a;
			string += " ";
		}
		System.out.println(string + "第" + cnt + "次排序结束-共进行" + compare + "次比较");
		compare = 0;

	}
	public static void main(String[] args) {
		
		new BubbleSort().bubbleVersion1();
		System.out.println("------------------------------------------------");
		
		cnt = 0;
		new BubbleSort().bubbleVersion2();
		System.out.println("------------------------------------------------");

		cnt = 0;
		new BubbleSort().bubbleVersion3();
		System.out.println("------------------------------------------------");
		
		cnt = 0;
		new BubbleSort().bubbleVersion4();
		System.out.println("------------------------------------------------");

	}

}
