package tasks;

public class TimedTask extends Task{

	private float repeatAfterSeconds;
	private float last = 0;
	
	public TimedTask(Runnable runnable, boolean threading, float repeat) {
		super(runnable, threading);
	}
	
	public void fire() {
		if(!enabled)
			return;
		super.fire();
	}

	public float getRepeatAfterSeconds() {
		return repeatAfterSeconds;
	}

	public void setRepeatAfterSeconds(float repeat) {
		this.repeatAfterSeconds = repeat;
	}
	
	@Override
	public boolean ready() {
		float _last = (float)System.nanoTime() / 1000000;
		boolean r =  _last - last > repeatAfterSeconds;
		last = r ? _last : _last;
		return r;
	}
	
	

}
