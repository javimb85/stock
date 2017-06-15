package stock;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class StockController.
 * @author Javier Marin
 *
 */
@RestController
public class StockController {

	private final ProductRepository productRepository;
	private final ReservationRepository reservationRepository;

	@Autowired
	StockController(ProductRepository productRepository, ReservationRepository reservationRepository) {
		this.productRepository = productRepository;
		this.reservationRepository = reservationRepository;
	}
	
	/**
	 * Product stock increasing.
	 * @param idProduct
	 * @param quantity
	 * @return result
	 */
	@RequestMapping("/refillProduct")
	public String increaseStock(@RequestParam(value="idProduct") int idProduct, @RequestParam(value="quantity") int quantity) {
		
		Product product = productRepository.findOne(idProduct);		
		if(product == null)
			throw new ProductNotFoundException(idProduct);

		// Increasing the quantity of the product.
		product.setQuantity(product.getQuantity() + quantity);
		productRepository.save(product);
		return "Product '" + idProduct + "' increased. Total stock " + product.getQuantity() + printStock();
	}

	/**
	 * Product stock decreasing. 
	 * @param idProduct
	 * @param idClient
	 * @return result
	 */
	@RequestMapping("/buyProduct")
	public String decreaseStock(@RequestParam(value="idProduct") int idProduct, @RequestParam(value="idClient") int idClient) {
		
		Product product = productRepository.findOne(idProduct);
		if(product == null)
			throw new ProductNotFoundException(idProduct);
		
		
		// Checking if the client has product reservation.
		Collection<Reservation> reservations = reservationRepository.findByIdClientAndIdProduct(idClient, idProduct);
		Reservation reservation = null;
		if (reservations.size() > 0)
			reservation = reservations.iterator().next();
		
		if (reservation != null) {
			// Deleting the reservation of the product.
			reservationRepository.delete(reservation);
		}
		else {
			// The client does not have product reservation so
			// checking if there are enough available products.
			int totalReserves = reservationRepository.findByIdProduct(idProduct).size();
			if (product.getQuantity() - totalReserves < 1)
				throw new NotEnoughProductsException(product, totalReserves);
		}
		
		// Decreasing the quantity of the product.
		product.setQuantity(product.getQuantity() - 1);
		productRepository.save(product);
		
		return "Purchase of the product '" + idProduct + "' made by the customer '" + idClient + "'." + printStock();
	}
	
	/**
	 * Product reservation.
	 * @param idProduct
	 * @param idClient
	 * @return result
	 */
	@RequestMapping("/reserveProduct")
	public String reserveProduct(@RequestParam(value="idProduct") int idProduct, @RequestParam(value="idClient") int idClient) {
		
		Product product = productRepository.findOne(idProduct);
		if(product == null)
			throw new ProductNotFoundException(idProduct);
		
		int totalReserves = reservationRepository.findByIdProduct(idProduct).size();
		if (product.getQuantity() - totalReserves < 1)
			throw new NotEnoughProductsException(product, totalReserves);
		
		// Creating a reservation of the product.
		Reservation reservation = new Reservation(idClient, idProduct);
		reservationRepository.save(reservation);
		
		return "Reservation made for product '" + idProduct + "' by customer '" + idClient + "'." + printStock();
	}
	
	private String printStock() {
		String stockStr = " - STOCK - ";
		for (Product p : productRepository.findAll())
			stockStr += p.toString() + " ";
		for (Reservation r : reservationRepository.findAll())
			stockStr += r.toString() + " ";
		return stockStr;
	}
}
