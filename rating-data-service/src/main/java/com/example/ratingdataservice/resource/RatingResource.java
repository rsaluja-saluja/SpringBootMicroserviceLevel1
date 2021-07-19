package com.example.ratingdataservice.resource;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingdataservice.models.Rating;
import com.example.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

//	@RequestMapping("/{userId}")
//	public List<Rating> getRatings(@PathVariable("userId") String userId) {
//		return Arrays.asList(new Rating("movie1", 2),
//							new Rating("movie2", 4));
//	}
	
	@RequestMapping("/{userId}")
	public UserRating getRatings(@PathVariable("userId") String userId) {
		return new UserRating(Arrays.asList(
					new Rating("movie1",2),
					new Rating("movie2",4)));
	}
}
