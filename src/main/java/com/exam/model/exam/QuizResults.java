package com.exam.model.exam;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.exam.model.User;

@Entity
public class QuizResults {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "qId")
    private Quiz quiz;

    private int marks;
 
    private Date submissionDate;

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public QuizResults(Long id, User user, Quiz quiz, int marks) {
		super();
		this.id = id;
		this.user = user;
		this.quiz = quiz;
		this.marks = marks;
	}

	public QuizResults() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizResults(User user, Quiz quiz, int marks) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.marks = marks;
	}
	
	
	
    
    


}
