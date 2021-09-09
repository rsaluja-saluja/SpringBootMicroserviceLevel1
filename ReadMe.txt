Discovery server - 8761
Rating data service - 8083
	/ratings/{userid}
		UserRating - List of Ratings(movieId; rating;)
Movie info Service - 8082 - Calls MovieDB to get movie information
	/movies/{movieId}
		Movie(movieId, name, movieDesc)
Movie Catalog Service - 8081
	/catalog/{userId}
	List of CatalogItem (name, desc, rating)
	Call Rating service and gets list of movies and their ratings given by provided user.
	For every rated movie call Movie-Info service to get movie information.
	return movie info with rating back to client.

LEVEL 2 Details:
	
Fault Tolerance/Resilience
3 ways to handle outage:
1. Run Multiple instances
2. Implement Intelligent Circuit breaker
3. Bulkhead pattern
 
Scenario 1 - A microservice instance goes down
Solution - Run Multiple instances

Scenario 2 - A microservice instance is slow
		If one service is slow it will make other service slow also as no of threads goes in pending state because of service 1 and no threads free to serve requests for service 2 that would make that service 2 response slow also. Maximum no of threads that can be created by web server is fixed.
Solution - implement Timeouts
	Way 1: Setting timeout in RestTemplate - It will partially solve the issue.
	Way 2: Identify service which is slow and not send request to that service for a bit. After period of time check if that is recovered or not and accordingly decide to send or not send request.
		Detect something is wrong.
		Take temp steps to avoid the situations getting worse.
		Deactivate the problem component so that it doesn't affect downstream components.
Circuit Breaker Pattern:
		Required for failing fast, fallback functionality and automatic recovery.
		Apply this pattern wherever service is calling another service, more importantly when service is calling 2 other services so that beacuse of one service, another service is not impacted.
	When to break the circuit: Based on some parameters:
		Last n requests to consider for decision
		How many of those should fail?
		Timeout duration
	When to resume the requests:
		how long to wait (sleep window) -( when circuit untrip)
	What to do with incoming requests when circuit breaks:
		Fallback mechanism
			Throw an error and send error response
			Return Fallback default response
			Save previous response(cache) and use that when possible - recommended one
Hystrix Framework - deprecated and replaced by Resilience4j
	Open Source lib created by netflix
	Implements Circuit breaker pattern based on configured parameters
	Works with Spring Boot
	Steps:
		Add dependency - spring-cloud-starter-netflix-hystrix
		@EnableCircuitBreaker add this to application class
		@HystrixCommnad add this to methods that needs circuit breaker - not exist in cutrent spring boot.
		Configure Hystrix behavior
		
Bulkhead pattern:
	Separate thread pool and limits for all services.
		if service 1 s slow and consuming all threads, service 2 wont be effected as it has its own thread pools.
		Can be configured at HystrixCommand level in case of Hystrix
			
	
		
