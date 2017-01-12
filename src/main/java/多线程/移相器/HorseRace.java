package 多线程.移相器;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 移相器(Phaser)案例(赛马游戏)
 * 
 * @author 高鹤
 *
 * @date 2016年12月9日
 */
public class HorseRace {
	private final int NUMBER_OF_HORSE = 12;
	private static final int INIT_PARTIES = 1;
	private static final Phaser manager = new Phaser(INIT_PARTIES);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread raceMonitor = new Thread(new RaceMonitor());
		raceMonitor.setDaemon(true);
		raceMonitor.start();
		new HorseRace().manageRace();

	}

	private void manageRace() {
		// TODO Auto-generated method stub
		ArrayList<Horse> horseArray = new ArrayList<Horse>();
		for(int i=0;i<NUMBER_OF_HORSE;i++){
			horseArray.add(new Horse());
		}
         runRace(horseArray);
	}
    private void runRace(ArrayList<Horse> horseArray) {
		// TODO Auto-generated method stub
    	log("Assign all horses,then start race");
    	for (Horse horse : horseArray) {
			  String dev=horse.toString();
			  log("assign"+dev+"to the race");
			  manager.register();
			  new Thread(){
				  public void run(){
					  try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  log(dev+",please await all horses");
					  manager.arriveAndAwaitAdvance();
					  horse.run();
				  }
			  }.start();
			  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  log("All arrived at starting gate,starting race");
			  manager.arriveAndDeregister();
			 
		}
		
	}

	private static void log(String message){
    	System.out.println(message);
    }
	private static class Horse implements Runnable {
		private final static AtomicInteger idSource=new AtomicInteger();
		private final int id=idSource.incrementAndGet();

		@Override
		public void run() {
			// TODO Auto-generated method stub
			log(toString()+":running");
			
		}

		@Override
		public String toString() {
			return "horse #" + id ;
		}
 
	}

	private static class RaceMonitor implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				System.out.println("Number of horses ready to run:"+HorseRace.manager.getArrivedParties());
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}

	}
}
