package com.exam.service.Impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.QuizResults;
import com.exam.repo.QuizResultRepository;
import com.exam.service.QuizResultService;


@Service
public class QuizResultServiceImpl implements QuizResultService {
	
	
	@Autowired
	private QuizResultRepository quizResultRepository;

	@Override
	public QuizResults addQuizresult(QuizResults quizResults) {
		return this.quizResultRepository.save(quizResults);
	}

	@Override
	public QuizResults updateQuizresult(QuizResults quizResults) {
		return this.quizResultRepository.save(quizResults);
	}

	@Override
	public QuizResults getQuizresult(Long id) {
		return this.quizResultRepository.findById(id).get();
	}

	@Override
	public void deleteQuizresult(Long id) {
		QuizResults QuizResults = new QuizResults();
		QuizResults.setId(id);
		this.quizResultRepository.delete(QuizResults);
	}

	@Override
	public List<QuizResults> getAllQuizresults() {
		return this.quizResultRepository.findAll();
	}

}
