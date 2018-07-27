package com.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * �߳�ͬ�������ַ�ʽ��
 * 1.ͬ�������
 * 2.ͬ������
 * 3.��
 * ͬ������������ȡ�ð�ȫ
 * 
 * ͬ��׼��
 * 1.ͬ���Ĵ���龡����̣�������Ƶ�����
 * 2.ͬ������Ϊռ��ʱ�䲻Ҫ̫������������
 * 3.������ʱ��Ҫ�������������ͬ������
 * @author pycrab
 *
 */
public class ThreadDemo2 {

	static class MyThread implements Runnable{

		private Object obj = new Object();
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			
			//ͬ������飨ͬ��ָ������һ����this��ʾ��ǰ����
			synchronized (obj) {
				for (int i = 0; i < 5; ++i) {
					System.out.println("��ʼ-" + "0");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("����-" + "1");
				}
			}
		}
		
		//ͬ������(ͬ����ǰ����)
		public synchronized void go() {
			for (int i = 0; i < 5; ++i) {
				System.out.println("��ʼ-" + "0");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("����-" + "1");
			}
		}
		
		//������
		private final ReentrantLock lock = new ReentrantLock();
		public void fun() {
			//����
			lock.lock();
			
			//��Ϊ
			System.out.println();
			
			//����
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		
		MyThread tr = new MyThread();
		Thread t1 = new Thread(tr);
		Thread t2 = new Thread(tr);
		Thread t3 = new Thread(tr);

		t1.start();
		t2.start();
		t3.start();
	}
}
