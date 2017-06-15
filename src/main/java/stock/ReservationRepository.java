package stock;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
	
	Collection<Reservation> findByIdClientAndIdProduct(int idClient, int idProduct);
	
	Collection<Reservation> findByIdProduct(int idProduct);
	
}
