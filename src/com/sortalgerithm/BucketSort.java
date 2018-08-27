package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018��8��27�� ����12:05:23 
*/
public class BucketSort {

	private int[] arr;
	
	public BucketSort() {
		arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
	}
	
	/**
	 * �򵥵�Ͱ����
	 */
	public void bucketSort() {
		// ����Ͱ
		int[] buckets = new int[11];
		for (int i = 0; i < arr.length; i++) {
			buckets[arr[i]] += 1;
		}
		
		for (int i = 0; i < buckets.length; i++) {
			for (int j = 0; j < buckets[i]; j++) {
				System.out.println(i + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		BucketSort bucket = new BucketSort();
		bucket.bucketSort();

	}

}
