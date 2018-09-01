package com.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/** 
* @author: pycrab
* @Date: 2018年9月1日 下午5:00:35 
*/

/**
 * 使用LinkedHashMap实现LRU缓存算法，LRU缓存算法即删除最近最久未使用的数据
 * 
 * 	实现思路为：
 * 		1.每次访问一个数据则把这个数据移到队列尾部
 * 		2.缓存满了则删除队列首部的数据，即删除最久未使用的，
 * 		3.删除后，后面的数据需要向前补位
 * 
 *  综合考虑使用LinkedHashMap来实现，原因为：
 *  	1.使用链表结构，移动数据比较快需要O(1)时间；使用数组需要O(n)时间
 *  	2.使用其带参数的构造函数，设置使用访问顺序遍历
 *  	3.在访问顺序下，其get方法除了返回数据还会将访问的数据放到链表尾部，每次只需remove首部的数据
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>{
	// 1.定义缓存最大容量
	private int maxCapacity;
	private static final long serialVersionUID = 1L;

	// 2.定义带参数的构造器
	public LRUCache(int maxCapacity){
		// 调用LinkedHashMap的带参数构造器，分别传入初始容量、装载因子、true代表启用访问顺序（LRU）
		super(maxCapacity, 0.75f, true);
		this.maxCapacity = maxCapacity;
	}
	
	// 3.重写自动删除最久未使用的数据的方法，使得自定义的缓存容量生效。每次加入元素会自动执行此方法
	@Override
	public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		
		return size() > maxCapacity;
		
	}
	
	public static void main(String[] args) {
		
		// 测试一下，设置缓存最大容量为4
		Map<Integer, String> map = new LRUCache<>(4);
		map.put(1, "h");
		map.put(2, "he");
		map.put(3, "hel");
		map.put(4, "hell");
		map.put(5, "hello");

		map.get(3);
		
		// 将依次输出2、4、5、3，因存储5个数据超出缓存最大容量，自动删除首部数据1；之后访问3，将3移到尾部
		for(Object key : map.keySet()) {
			System.out.println(key);
		}

	}

}
