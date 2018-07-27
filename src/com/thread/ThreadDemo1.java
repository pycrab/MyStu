package com.thread;

/**
 *ʵ�ֶ��̵߳����ַ�ʽ��
 *1.�̳�Thread��
 *2.ʵ��Runnable�ӿڣ��Ƽ���
 * @author pycrab
 *
 */
public class ThreadDemo1 {

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
				System.out.println(Thread.currentThread().getName() + "-" + i + "-" + System.currentTimeMillis());
			}
		}
	}
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
				System.out.println(Thread.currentThread().getName() + "-" + i + "-" + System.currentTimeMillis());
			}		
		}
	}
	
	public static void main(String[] args) {
		
		//����No enclosing instance of type ThreadDemo is accessible. Must qualify the allocation with an enclosing instance of type ThreadDemo (e.g. x.new A() where x is an instance of ThreadDemo).
		//��Ҫʹ�þ�̬�ڲ���
		//1.�����̶߳���
		MyThread tr1 = new MyThread();
		
		//2.��ǰ�߳��Ƿ��ڻ״̬
		System.out.println("isAlive=" + tr1.isAlive());
		
		//3.�����߳����ȼ���ֻ�Ǹı伸��
		//tr1.setPriority(Thread.MAX_PRIORITY);
		
		//4.�����߳�Ϊ�ػ��߳�
		//��Java�̷߳�Ϊ�û��̺߳��ػ��̣߳�һ��ǰ̨һ����̨��
		//��ֻ��һ���ػ��̣߳��û��̶߳����˳�����ʱ��JVM�ͻ����
		//�����ػ��̱߳���������֮ǰ���ػ��߳��в��������߳�Ҳ���ػ��̣߳��������е�Ӧ�ö����Է����ػ��߳�Ϊ֮�������д����������߼�
//		tr1.setDaemon(true);
		
		//5.�����̣߳������������
		tr1.start();
		System.out.println("isAlive=" + tr1.isAlive());

		
		MyRunnable tr2 = new MyRunnable();
		new Thread(tr2).start();

		
		for(int i = 0;i < 10; ++i) {
			System.out.println(Thread.currentThread().getName() + i);
			try {
				//6.�߳����߰���
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(i == 6) {
				
				//7.��ͣ��ǰִ�е��̶߳����ó�����CPU��ִ��ʱ��
				Thread.yield();
				
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
