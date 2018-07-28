package com.thread;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������-�������������ֽ��������
 * 	1.ʹ��synchronized��Object���wait������notify����
 * 	2.ʹ��������ReenTrantLock��Condition��await������signal����
 * 	3.ʹ��BlockingQueue�������еķ���
 * 		�Ѿ����ڲ�ʹ��await��signalʵ����ͬ���Ķ��У�
 * 		ʹ��if�����������ж�
 * 		������������put��take��put���������ʱ�Զ�������take������Ϊ0ʱ�Զ�����
 * 	4.�ܵ�����	
 * 
 * wait������sleep����
 * 	��ͬ���߳���ͣ���ͷ�CPU��Դ
 * 	����ǰ���ͷŵ�ǰ�������߲��ͷŵ�ǰ��
 *  Thread.sleep()���̴߳� ��running�� ��������̬�� ʱ�����/interrupt�� ��runnable��
 *  Object.wait()���̴߳� ��running�� �� ���ȴ����С�notify �� �����ء� �� ��runnable��
 *  
 * ʹ����Ϊʲôʹ��whereѭ��������if��
 * 	�߳���wait״̬�±����ѣ���Ҫ���»���������������ȡ��ʧ�ܣ���Ҫ����ͣ����wait״̬��������where
 * 
 * notify������notifyAll����
 * 	notify����֪ͨһ���̣߳�notifyAll����֪ͨ��������߳�
 * 		����������������ߺ�һ�������ߣ�������Ϊ���Ϊ1��
 * 		��������������notify֪ͨ������1��������1����1��Ȼ��������notify������2��������2Ҳ������
 * 		��ʱ�Ѿ�û���߳̿���ִ��notify�����ȫ���ڵȴ��޷������ѣ���������
 * 		���ʹ��notifyAll��������
 * 	 
 * @author pycrab
 *
 */
public class ThreadDemo4 {

	static class ProductBuffer{
		//���û�����
		private LinkedList<Integer> list = new LinkedList<>();
		//�����������
		private final int MAX_SIZE = 10;
		//���ò�Ʒ���(ԭ�Ӳ���)
		private int cnt = 0;
		
		public int getCnt() {
			return cnt;
		}

		//1.��һ�ַ���
		public synchronized void produce() throws InterruptedException {
			while (list.size() >= MAX_SIZE) {
				System.out.println("producer" + Thread.currentThread().getName() + "-��������������������");
				wait();
			}
			
			Thread.sleep(500);
			
			cnt++;
			list.add(getCnt());
			System.out.println("producer" + Thread.currentThread().getName() + "-������" + getCnt());
			notifyAll();
			
		}
		public synchronized void consume() throws InterruptedException {
			while (list.size() <= 0) {
				System.out.println("consumer" + Thread.currentThread().getName() + "-������Ϊ�գ���������");
				wait();
			}
			
			Thread.sleep(500);
			
			cnt--;
			int temp = list.pollLast();
			System.out.println("consumer" + Thread.currentThread().getName() + "-������" + temp);
			notifyAll();
		}
		
		
		//2.�ڶ��ַ���
		ReentrantLock lock = new ReentrantLock();
		Condition full = lock.newCondition();
		Condition empty = lock.newCondition();

		public void produce2() {
			lock.lock();
			
			while (list.size() >= MAX_SIZE) {
				System.out.println("producer" + Thread.currentThread().getName() + "-��������������������");
				try {
					full.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(500);
				cnt++;
				list.add(getCnt());
				System.out.println("producer" + Thread.currentThread().getName() + "-������" + getCnt());
				empty.signalAll();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		public void consume2(){
			lock.lock();
			
			while (list.size() <= 0) {
				System.out.println("consumer" + Thread.currentThread().getName() + "-������Ϊ�գ���������");
				try {
					empty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(500);
				cnt--;
				int temp = list.pollLast();
				System.out.println("consumer" + Thread.currentThread().getName() + "-������" + temp);
				full.signalAll();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		
		
		//�����ַ���
		private LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();
		
		public void produce3() throws InterruptedException {
			if (lbq.size() >= MAX_SIZE) {
				System.out.println("producer" + Thread.currentThread().getName() + "-��������������������");
			}
			
			Thread.sleep(500);
			
			cnt++;
			lbq.put(getCnt());
			System.out.println("producer" + Thread.currentThread().getName() + "-������" + getCnt());
		}
		public void consume3() throws InterruptedException {
			if (lbq.size() <= 0) {
				System.out.println("consumer" + Thread.currentThread().getName() + "-������Ϊ�գ���������");
			}
			
			Thread.sleep(500);
			
			cnt--;
			int temp = lbq.take();
			System.out.println("consumer" + Thread.currentThread().getName() + "-������" + temp);
		}
	}
	
	static class Producer implements Runnable{
	
		private ProductBuffer pBuffer;

		public Producer(ProductBuffer pBuffer) {
			this.pBuffer = pBuffer;
		}

		@Override
		public void run() {
			while (true) {
				try {
					pBuffer.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	static class Consumer implements Runnable{

		private ProductBuffer pBuffer;

		public Consumer(ProductBuffer pBuffer) {
			this.pBuffer = pBuffer;
		}

		@Override
		public void run() {
			while (true) {
				try {
					pBuffer.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		//��������߶� ��������
		ProductBuffer pBuffer = new ProductBuffer();
		Producer producer = new Producer(pBuffer);
		Producer producer2 = new Producer(pBuffer);
		Consumer consumer = new Consumer(pBuffer);
		Consumer consumer2 = new Consumer(pBuffer);
		
		Thread t1 = new Thread(producer, "1");
		Thread t3 = new Thread(producer2, "2");
		Thread t2 = new Thread(consumer, "1");
		Thread t4 = new Thread(consumer2, "2");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	

}
