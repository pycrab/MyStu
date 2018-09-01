package com.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/** 
* @author: pycrab
* @Date: 2018��9��1�� ����5:00:35 
*/

/**
 * ʹ��LinkedHashMapʵ��LRU�����㷨��LRU�����㷨��ɾ��������δʹ�õ�����
 * 
 * 	ʵ��˼·Ϊ��
 * 		1.ÿ�η���һ�����������������Ƶ�����β��
 * 		2.����������ɾ�������ײ������ݣ���ɾ�����δʹ�õģ�
 * 		3.ɾ���󣬺����������Ҫ��ǰ��λ
 * 
 *  �ۺϿ���ʹ��LinkedHashMap��ʵ�֣�ԭ��Ϊ��
 *  	1.ʹ������ṹ���ƶ����ݱȽϿ���ҪO(1)ʱ�䣻ʹ��������ҪO(n)ʱ��
 *  	2.ʹ����������Ĺ��캯��������ʹ�÷���˳�����
 *  	3.�ڷ���˳���£���get�������˷������ݻ��Ὣ���ʵ����ݷŵ�����β����ÿ��ֻ��remove�ײ�������
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>{
	// 1.���建���������
	private int maxCapacity;
	private static final long serialVersionUID = 1L;

	// 2.����������Ĺ�����
	public LRUCache(int maxCapacity){
		// ����LinkedHashMap�Ĵ��������������ֱ����ʼ������װ�����ӡ�true�������÷���˳��LRU��
		super(maxCapacity, 0.75f, true);
		this.maxCapacity = maxCapacity;
	}
	
	// 3.��д�Զ�ɾ�����δʹ�õ����ݵķ�����ʹ���Զ���Ļ���������Ч��ÿ�μ���Ԫ�ػ��Զ�ִ�д˷���
	@Override
	public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		
		return size() > maxCapacity;
		
	}
	
	public static void main(String[] args) {
		
		// ����һ�£����û����������Ϊ4
		Map<Integer, String> map = new LRUCache<>(4);
		map.put(1, "h");
		map.put(2, "he");
		map.put(3, "hel");
		map.put(4, "hell");
		map.put(5, "hello");

		map.get(3);
		
		// ���������2��4��5��3����洢5�����ݳ�����������������Զ�ɾ���ײ�����1��֮�����3����3�Ƶ�β��
		for(Object key : map.keySet()) {
			System.out.println(key);
		}

	}

}
