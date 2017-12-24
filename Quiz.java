package fahadzia.quizzer.entity;

import java.util.List;
import fahadzia.quizzer.entity.*;



public class Quiz {

	private String qz_name;

	private List<Question> questions;

	public Quiz(){}

	public Quiz(String qz_name, List<Question>questions){
		this.questions=questions;
		this.qz_name=qz_name;
	}

	public String getQzName() {
		return qz_name;
	}

	public void setQzId(String qz_name) {
		this.qz_name = qz_name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}   
}
