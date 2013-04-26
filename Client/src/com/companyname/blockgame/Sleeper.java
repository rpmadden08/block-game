package com.companyname.blockgame;

public class Sleeper {
	private static final int NO_DELAYS_PER_YIELD = 16;
	private static final int MAX_FRAME_SKIPS = 4;
	
	private static int fps = 60;
	public static long period = (long) 1000000000L/fps;
	

	
	private long beforeTime, afterTime, timeDiff, sleepTime;
	private long overSleepTime;
	private int noDelays;
	private long excess;
	
	public Sleeper() {
		overSleepTime = 0L;
		noDelays = 0;
		excess = 0L;
		beforeTime = java.lang.System.nanoTime(); // gameStartTime;
	}
	
	public void sleep() {
		afterTime = java.lang.System.nanoTime();
		timeDiff = afterTime - beforeTime;
		sleepTime = (period - timeDiff) - overSleepTime;  

		if (sleepTime > 0) {
			try {
				Thread.sleep(sleepTime/1000000L);  // nano -> ms
			}
			catch(InterruptedException ex){}
			overSleepTime = (java.lang.System.nanoTime() - afterTime) - sleepTime;
		} else {    // if sleepTime <= 0 -- the frame took longer than the period
			excess -= sleepTime;  // store excess time value
			overSleepTime = 0L;

			if (++noDelays >= NO_DELAYS_PER_YIELD) {
				Thread.yield();   // give another thread a chance to run
				noDelays = 0;
			}
		}

		beforeTime = java.lang.System.nanoTime();

		/* Tick but don't render if we're falling behind. */
		int skips = 0;
		while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
			excess -= period;
			Component.currentState.tick();
			skips++;
		}
		Debugger.framesSkipped += skips;
	}
}
