package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author pycrab
 *
 */
public class ThreadDemo1 {

	/**
	 * ��ʽһ
	 */
	static class MyThread extends Thread{
		//��Ҫ��дrun����
		@Override
		public void run() {
			//��ȡ��ǰ�̶߳�������ֺ�ϵͳʱ��
			for(int i = 0;i < 10; ++i) {
				try {
					//�߳����߰�������ִ��
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-" + i + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority() + "-" + System.currentTimeMillis());
			}
		}
	}
	
	/**
	 * ��ʽ��
	 */
	static class MyRunnable implements Runnable{
		//��Ҫ��дrun����
		@Override
		public void run() {
			//��ȡ��ǰ�̶߳�������ֺ�ϵͳʱ��
			for(int i = 0;i < 10; ++i) {
				try {
					//�߳����߰�������ִ��
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-" + i + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority() + "-" + System.currentTimeMillis());
			}		
		}
	}
	
	/**
	 * ��ʽ��
	 */
	static class MyCallable implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			System.out.println("Implement Callable");
			return 1;
		}
		
	}
	
	public static void main(String[] args) {
		
		//����No enclosing instance of type ThreadDemo is accessible. Must qualify the allocation with an enclosing instance of type ThreadDemo (e.g. x.new A() where x is an instance of ThreadDemo).
		//��Ҫʹ�þ�̬�ڲ���
		
		//1.�����̶߳���
		MyThread tr1 = new MyThread();
		//2.�����̣߳������������
		tr1.start();
		//3.��ǰ�߳��Ƿ��ڻ״̬
		System.out.println("isAlive=" + tr1.isAlive());
		//4.�����߳����ȼ���ֻ�Ǹı伸��
//		tr1.setPriority(Thread.MAX_PRIORITY);
		//5.�����߳�Ϊ�ػ��߳�
		//��Java�̷߳�Ϊ�û��̺߳��ػ��̣߳�һ��ǰ̨һ����̨��
		//��ֻ��һ���ػ��̣߳��û��̶߳����˳�����ʱ��JVM�ͻ����
		//�����ػ��̱߳���������֮ǰ���ػ��߳��в��������߳�Ҳ���ػ��̣߳��������е�Ӧ�ö����Է����ػ��߳�Ϊ֮�������д����������߼�
//		tr1.setDaemon(true);
		
		MyRunnable tr = new MyRunnable();
		Thread tr2 = new Thread(tr);
		tr2.start();

		MyCallable mc = new MyCallable();
		FutureTask<Integer> fTask = new FutureTask<>(mc);
		new Thread(fTask).start();

		for(int i = 0;i < 10; ++i) {
			System.out.println(i + "-" + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority());
			try {
				//6.�߳����߰���
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(i == 6) {
				
				//7.��ͣ��ǰִ�е��̶߳����ó�����CPU��ִ��ʱ���ͬ���ȼ����߳�
//				Thread.yield();
				
				try {
					//8.�ȴ����߳�ִ�����
					tr1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
