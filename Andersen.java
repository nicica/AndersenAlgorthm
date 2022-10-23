package sinhronizacij;

public class Andersen extends Thread {
	
	public int thread_id;
	public static final int number_of_threads=4;
	public static volatile int slot;
	
//	public static volatile boolean[] entry=new boolean[number_of_threads];
	public static volatile boolean [] flag=new boolean[number_of_threads];
	
	
	public Andersen(int id) {
		thread_id=id;
	}
	
	private int FAS() {
		int temp=slot;
		slot++;
		return temp;
	}
	
	
	@Override
	public void run() {
		/*try {
			sleep(3000);
		} catch (InterruptedException e1) {
		}*/
		int myslot;
		
		while (true) {
			//entry protokol
		//entry[thread_id]=true;
			myslot=FAS()%number_of_threads;
		//	entry[thread_id]=false;
			
			//Da nebi doslo do poklapanja
			/*while(entry[myslot]) {//cekaj
				}*/
			
			
			while(!flag[myslot]) {	//cekaj
				
			}
			//Ulazak u kriticnu sekciju
			
			System.out.println("Ja: "+thread_id+" Myslot: "+myslot+" Slot: "+slot);
			
			try {
				sleep((int) (Math.random() * 2));
			} catch (InterruptedException e) { /* nothing */ }
			
			//exit protocol
			flag[myslot]=false;
			flag[(myslot+1)%number_of_threads]=true;
			
			
			
		}
		

	}
	
	
	
	
	public static void main(String[] args) {
		
		//inicijalizacija
		for (int i =0;i<number_of_threads;i++) {
		//	entry[i]=false;
			flag[i]=false;
		}
		flag[0]=true;
		slot=0;
		Andersen[] niti= new Andersen[number_of_threads];
		
		//pokreni
		for(int i=0;i<number_of_threads;i++) {
			niti[i]=new Andersen(i);
			niti[i].start();
		}
	}

}
