package ua.myflights;

import java.lang.Thread.State;

public class SpentTime implements Runnable {
	public int time = 0;
	public boolean message = true; // 0 / 1
	static Object lock = new Object();
	
	public void run() {
		try {
			while(true){				
				synchronized (lock){
					if(message){ lock.wait();} // спит если true	
					System.out.print('.');
					Thread.sleep(1000);					
					
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//Thread.currentThread().interrupt();
			//e.printStackTrace();
		}
	}


	public void stopTimer(){
		this.message = true;
	}

	public void startTimer(){
		this.message = false;
		synchronized(lock){
			lock.notify();
		}				
	}	
	
}
