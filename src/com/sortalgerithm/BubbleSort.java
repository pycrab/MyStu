package com.sortalgerithm;

/** 
* @author: pycrab
* @Date: 2018��8��10�� ����9:54:50 
*/
public class BubbleSort {

	private int[] arr;
	// ���������ǣ����ڴ�ӡ����
	static int cnt = 0;
	// �Ƚϴ�����ǣ����ڴ�ӡʱ��ʽ����version3��Ч��
	static int compare = 0;
	
	public BubbleSort() {
		arr = new int[10];
		for (int i = 0; i < 10; ++i) {
			arr[i] = (int) (Math.random() * 10);
		}
		
		// �������Ż��������ټ����ڲ�ѭ��
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
	 * ��ͨð�������㷨
	 * ���κ��Ż������ȶ�����
	 * ���ѭ������ѭ���������ڲ�ѭ������ÿһ�˵ıȽϼ���������ÿ������������߲���
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
	 * ð�������һ���Ż�
	 * �������ʱ����δ������Ѿ���������Ҫ����ִ��ѭ����ֱ���˳�
	 * ����һ�����sorted��Ĭ��Ϊtrue���������������Ϊfalse���ڲ�ѭ�������жϱ�ǣ������Ƿ������Ƿ�����˳�
	 * ������2134567890
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
	 * ð������ڶ����Ż�
	 * ���������֮ǰ������Ҳ����������Ҫ����������������������ıȽ�
	 * ����һ����Ǽ�¼���һ�ν�����λ�ã���Ϊ��һ��ѭ���ı߽�
	 * ������4586321907
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
					
					// ����һ�αȽϣ����Ƕ��˺ܶ�compare�Ƚϣ��ò���ʧ
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
	 * ð������������Ż�
	 * ʹ��˫��ȽϺͽ����������
	 * �����ڴ󲿷�Ԫ���Ѿ������������ŵ������ض�����¼���ѭ��������ȱ���Ǵ�����������һ��
	 * ������������Ż�����Ҫ���������߽�
	 * ������1234567890
	 */
	private void bubbleVersion4() {
		boolean sorted;
		int rightBorder = arr.length - 1;
		int leftBorder = 0;
		int rightLastExchangeIndex = 0;
		int leftLastExchangeIndex = 0;

		for (int i = arr.length - 1; i > 0; --i) {
			sorted = true;
			
			// ��������
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
			
			// ��������
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
		System.out.println(string + "��" + cnt + "���������-������" + compare + "�αȽ�");
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
