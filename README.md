# stock
Stock Management

Technologies used: Spring Boot and Spring Data JPA.

Instructions:  

- To run the application, use "./mvnw spring-boot:run" inside of the project folder.

- Now that the service is up we can use it: 

		REFILL PRODUCT
		http://localhost:8080/refillProduct?idProduct=1&quantity=2
		Parameters:
			- idProduct -> Product id 
			- quantity  -> Quantity to increase in stock
		
		BUY PRODUCT
		http://localhost:8080/buyProduct?idProduct=1&idClient=1
		Parameters:
			- idProduct -> Product id 
			- idClient  -> Id of the client who is purchasing
		
		RESERVE PRODUCT
		http://localhost:8080/reserveProduct?idProduct=1&idClient=1	
		Parameters:
			- idProduct -> Product id 
			- idClient  -> Id of the client who is reserving

- The database is initialized with three products:
	Product [id=1, quantity=3]
	Product [id=2, quantity=3]
	Product [id=3, quantity=3]
	
