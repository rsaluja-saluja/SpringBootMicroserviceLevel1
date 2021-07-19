package com.example.moviecatalogservice.models;

import java.util.List;

public class UserRating {

	private List<Rating> ratingsList;

	public UserRating(List<Rating> ratingsList) {
		super();
		this.ratingsList = ratingsList;
	}

	public UserRating() {
		super();
	}

	public List<Rating> getRatingsList() {
		return ratingsList;
	}

	public void setRatingsList(List<Rating> ratingsList) {
		this.ratingsList = ratingsList;
	}
	
	
}
