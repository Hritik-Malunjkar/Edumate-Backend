package com.exam.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.model.exam.QuizResults;

public interface QuizResultRepository extends JpaRepository<QuizResults, Long> {

	@Query( nativeQuery = true, value = "select u.username ,q.title,qr.marks, qr.submission_date from quiz_results qr join users u on u.id = qr.user_id  join quiz q on q.q_id =qr.quiz_id")
	List<QuizResults> getAllResults();

}
