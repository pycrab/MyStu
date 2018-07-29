package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �߳�ͬ�������ַ�ʽ��
 * 	1.ͬ�������
 * 	2.ͬ������
 * 	3.������
 * 
 * ������������
 * 	1.ǰ����ʹ��synchronized�ؼ��֣�������ʽ�ڲ���������Ҳֻ�ܵ���һ��Object�ṩ���������󣬼�wait()������notifyAll()����
 * 	2.��������ʽ�����������������������ʹ��������await()������signalAll()����
 * 
 * ͬ������������ȡ�ð�ȫ
 * 
 * ͬ��׼��
 * 	1.ͬ���Ĵ���龡����̣�������Ƶ�����
 * 	2.ͬ������Ϊռ��ʱ�䲻Ҫ̫������������
 * 	3.������ʱ��Ҫ�������������ͬ������
 * 
 * ������monitor��
 * 	���������������������ģ����������������ÿһ��Object�����е�monitor����������
 * 	��ü������ͻ��������̲߳���ִ�У�û�м���������ִ��
 * 	ͬ���������ʵ����ʹ���˲����ļ�������
 * 
 * @author pycrab
 *
 */
public class ThreadDemo2 {

	static class MyThread implements Runnable{

		private Object obj = new Object();
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			
			//ͬ������飨ͬ��ָ������this��ʾ��ǰ���󣬵�����������Ӵ�ʱ����ͬ�������ڵĴ���飩
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
		
		//ͬ������(ͬ����ǰ������ʽ��)
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
		
		//����������ʽ����
		//���ǿ�����ģ�ʹ��һ�������������������
		private final ReentrantLock lock = new ReentrantLock();
		public void fun() throws InterruptedException {
			
			//������������
			Condition condition = lock.newCondition();
			
			//����
			lock.lock();
			
			try {
				boolean something = true;
				
				//��������
				while (something ) {
					//�߳̽������״̬�����ε��ñ��������ͷż������ϵ���
					condition.await();
				}
				
				//do-
				
				//֪ͨ�����̼߳�������
				condition.signalAll();
				
			} finally {
				//����,����finally���У���ֹ�쳣�����߳�һֱ�����ͷţ��������߳�һֱ������
				lock.unlock();
			}
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
