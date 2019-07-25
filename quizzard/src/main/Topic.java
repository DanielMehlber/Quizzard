package main;

public class Topic {

	private int id;
	private String name;
	private int[] questions;
	
	public Topic(int id, String name, int[] questions) {
		this.id = id;
		this.name = name;
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getQuestions() {
		return questions;
	}

	public void setQuestions(int[] questions) {
		this.questions = questions;
	}
	
	

}
