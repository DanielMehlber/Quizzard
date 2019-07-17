package tasks;

import java.util.ArrayList;

/**
 * Organizes a few Tasks
 * @author mehlber
 *
 */
public class TaskManager {

	private ArrayList<Task> tasks;
	private int ups;
	private boolean running;
	
	public TaskManager(int ups) {
		this.ups = ups;
		this.tasks = new ArrayList<Task>();
	}

	public int getUps() {
		return ups;
	}

	public void setUps(int ups) {
		this.ups = ups;
	}
	
	public void start() {
		running = true;
		new Thread(() -> {
			while(running) {
				for(Task task : tasks) {
					if(task.ready())
						task.fire();
				}
			}
		}).start();
	}
	
	public void stop() {
		running = false;
	}
	
	public void add(Task task) {
		tasks.add(task);
	}

}
