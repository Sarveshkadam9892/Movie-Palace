package com.cdac.movies.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cdac.movies.pojo.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
