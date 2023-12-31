package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.model.exam.QuizResults;

public interface QuizResultService {
	
	public QuizResults addQuizresult(QuizResults quizResults);
	public QuizResults updateQuizresult(QuizResults quizResults);
	public QuizResults getQuizresult(Long id);
	public List<QuizResults> getAllQuizresults();
	public void deleteQuizresult(Long id);

}
