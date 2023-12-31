package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.model.exam.QuizResults;
import com.exam.service.QuizResultService;

@RestController
@RequestMapping("/quizresult")
@CrossOrigin("*")
public class QuizResultController {

	@Autowired
	private QuizResultService quizResultService;

	@PostMapping("/")
	public ResponseEntity<QuizResults> addQuizresult(@RequestBody QuizResults quizResults) {
		QuizResults QuizResults1 = this.quizResultService.addQuizresult(quizResults);
		return ResponseEntity.ok(QuizResults1);
	}

	// Get QuizResults by id

	@GetMapping("/{id}")
	public QuizResults getQuizresult(@PathVariable("id") Long id) {
		return this.quizResultService.getQuizresult(id);
	}

	// Get All getAllQuizresults

	@GetMapping("/")
	public ResponseEntity<?> getAllQuizresults() {
		return ResponseEntity.ok(this.quizResultService.getAllQuizresults());
	}
	
	//update QuizResults
	@PutMapping("/")
	public QuizResults updateQuizresult(@RequestBody QuizResults quizResults) {
		return this.quizResultService.updateQuizresult(quizResults);
	}
	
	//delete QuizResults
	@DeleteMapping("/{id}")
	public void deleteQuizresult(@PathVariable("id") Long id) {
		this.quizResultService.deleteQuizresult(id);
	}
	

}
