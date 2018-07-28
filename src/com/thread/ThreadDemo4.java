package com.thread;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者-消费者问题四种解决方案：
 * 	1.使用synchronized和Object类的wait方法和notify方法
 * 	2.使用重入锁ReenTrantLock和Condition的await方法和signal方法
 * 	3.使用BlockingQueue阻塞队列的方法
 * 		已经在内部使用await和signal实现了同步的队列，
 * 		使用if来进行条件判断
 * 		队列两个方法put和take，put在容量最大时自动阻塞，take在容量为0时自动阻塞
 * 	4.管道方法	
 * 
 * wait方法和sleep方法
 * 	相同：线程暂停，释放CPU资源
 * 	区别：前者释放当前锁，后者不释放当前锁
 *  Thread.sleep()让线程从 【running】 到【阻塞态】 时间结束/interrupt到 【runnable】
 *  Object.wait()让线程从 【running】 到 【等待队列】notify 到 【锁池】 到 【runnable】
 *  
 * 使用锁为什么使用where循环而不用if？
 * 	线程在wait状态下被唤醒，需要重新获得锁，如果竞争获取锁失败，需要继续停留在wait状态，所以用where
 * 
 * notify方法和notifyAll方法
 * 	notify方法通知一个线程，notifyAll方法通知所有相关线程
 * 		如果有两个个生产者和一个消费者，缓冲区为最大为1，
 * 		消费者先阻塞，notify通知生产者1，生产者1生产1，然后阻塞，notify生产者2，生产者2也阻塞，
 * 		这时已经没有线程可以执行notify，因此全部在等待无法被唤醒，产生死锁
 * 		因此使用notifyAll避免死锁
 * 	 
 * @author pycrab
 *
 */
public class ThreadDemo4 {

	static class ProductBuffer{
		//设置缓冲区
		private LinkedList<Integer> list = new LinkedList<>();
		//设置最大容量
		private final int MAX_SIZE = 10;
		//设置产品标号(原子操作)
		private int cnt = 0;
		
		public int getCnt() {
			return cnt;
		}

		//1.第一种方法
		public synchronized void produce() throws InterruptedException {
			while (list.size() >= MAX_SIZE) {
				System.out.println("producer" + Thread.currentThread().getName() + "-缓冲区已满，不能生产");
				wait();
			}
			
			Thread.sleep(500);
			
			cnt++;
			list.add(getCnt());
			System.out.println("producer" + Thread.currentThread().getName() + "-生产了" + getCnt());
			notifyAll();
			
		}
		public synchronized void consume() throws InterruptedException {
			while (list.size() <= 0) {
				System.out.println("consumer" + Thread.currentThread().getName() + "-缓冲区为空，不能消费");
				wait();
			}
			
			Thread.sleep(500);
			
			cnt--;
			int temp = list.pollLast();
			System.out.println("consumer" + Thread.currentThread().getName() + "-消费了" + temp);
			notifyAll();
		}
		
		
		//2.第二种方法
		ReentrantLock lock = new ReentrantLock();
		Condition full = lock.newCondition();
		Condition empty = lock.newCondition();

		public void produce2() {
			lock.lock();
			
			while (list.size() >= MAX_SIZE) {
				System.out.println("producer" + Thread.currentThread().getName() + "-缓冲区已满，不能生产");
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
				System.out.println("producer" + Thread.currentThread().getName() + "-生产了" + getCnt());
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
				System.out.println("consumer" + Thread.currentThread().getName() + "-缓冲区为空，不能消费");
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
				System.out.println("consumer" + Thread.currentThread().getName() + "-消费了" + temp);
				full.signalAll();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		
		
		//第三种方法
		private LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();
		
		public void produce3() throws InterruptedException {
			if (lbq.size() >= MAX_SIZE) {
				System.out.println("producer" + Thread.currentThread().getName() + "-缓冲区已满，不能生产");
			}
			
			Thread.sleep(500);
			
			cnt++;
			lbq.put(getCnt());
			System.out.println("producer" + Thread.currentThread().getName() + "-生产了" + getCnt());
		}
		public void consume3() throws InterruptedException {
			if (lbq.size() <= 0) {
				System.out.println("consumer" + Thread.currentThread().getName() + "-缓冲区为空，不能消费");
			}
			
			Thread.sleep(500);
			
			cnt--;
			int temp = lbq.take();
			System.out.println("consumer" + Thread.currentThread().getName() + "-消费了" + temp);
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
		
		//多个生产者多 个消费者
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
