package com.cdac.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.First;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cdac.movies.pojo.Movie;
import com.cdac.movies.pojo.Review;
import com.cdac.movies.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	public MongoTemplate mongoTemplate;
	
	public Review createReview(String reviewBody, String imdbId) {
		
		Review review = reviewRepository.insert(new Review(reviewBody)); 
		
		
		
		mongoTemplate.update(Movie.class)
		             .matching(Criteria.where("imdbId").is(imdbId))
		             .apply(new Update().push("reviews").value(review))
		             .first();
		             
		             return review;
		
	}

}
