package com.example.movieinfoservice.models;

public class Movie {

	private String movieId;
	private String name;
	private String movieDesc;
	
	public Movie(String movieId, String name, String movieDesc) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.movieDesc = movieDesc;
	}
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovieDesc() {
		return movieDesc;
	}
	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}
	
}
