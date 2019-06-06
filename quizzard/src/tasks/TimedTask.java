package tasks;

public class TimedTask extends Task{

	private float repeat;
	private float last = 0;
	
	public TimedTask(Runnable runnable, boolean threading, float repeat) {
		super(runnable, threading);
	}
	
	public void fire() {
		if(!enabled)
			return;
		last = (float)System.nanoTime() / 1000000;
		if(last >= repeat)
			super.fire();
	}

	public float getRepeat() {
		return repeat;
	}

	public void setRepeat(float repeat) {
		this.repeat = repeat;
	}
	
	

}
