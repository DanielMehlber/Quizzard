package tasks;

public class Task {

	private Runnable runnable;
	private boolean threading;
	protected boolean enabled;
	
	public Task(Runnable _runnable, boolean _threading) {
		runnable = _runnable;
		threading = _threading;
	}
	
	public void fire() {
		if(!enabled)
			return;
		Thread t = new Thread(runnable);
		if(threading)
			t.start();
		else
			t.run();
	}

	protected boolean isEnabled() {
		return enabled;
	}

	protected void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	public boolean ready() {
		return true;
	}

}
