package fahadzia.quizzer.entity;

import fahadzia.quizzer.entity.Quiz;

public class Question {

	private Quiz quiz;

	private String prompt;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correct_answer;

	private String chosen_answer;

	public Question(String prompt, String option1, String option2, String option3, String option4, String correct_answer, Quiz q) {
		this.prompt = prompt;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct_answer = correct_answer;
		this.quiz=q;
	}

	public Question(){}

	public String getChosen_answer() {
		return chosen_answer;
	}

	public void setChosen_answer(String chosen_answer) {
		this.chosen_answer = chosen_answer;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public void setQuiz(Quiz quiz){
		this.quiz=quiz;
	}

	public Quiz getQuiz(){
		return this.quiz;
	}



}
