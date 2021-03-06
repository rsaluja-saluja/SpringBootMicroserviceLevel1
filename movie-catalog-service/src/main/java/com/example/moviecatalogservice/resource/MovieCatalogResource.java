package com.example.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

		
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
		
		//return Collections.singletonList(new CatalogItem("Transformer", "Transformer Desc", 4));
		
		//Call Rating service to get all movie ids and their ratings
//		 UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratings/"+userId, UserRating.class) ;

		//Using service discovery - give service name instead of port etc- calls Eureka server to get host and port for service name given
		 UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratings/"+userId, UserRating.class) ;
		
		//For every movie id call Movie service to get movie information and Put them all together
		List<CatalogItem> catalogItem = userRating.getRatingsList().stream()
				.map(rating -> getCatalogItem(rating))
				.collect(Collectors.toList());
		
		
		return catalogItem;
	}

	private CatalogItem getCatalogItem(Rating rating) {
		//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

		return new CatalogItem(movie.getName(), movie.getMovieDesc(),rating.getRating());
	}
}
