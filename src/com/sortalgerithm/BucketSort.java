package com.sortalgerithm;
/** 
* @author: pycrab
* @Date: 2018年8月27日 下午12:05:23 
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
	 * 简单的桶排序
	 */
	public void bucketSort() {
		// 创建桶
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
