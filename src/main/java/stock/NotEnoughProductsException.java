package stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotEnoughProductsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotEnoughProductsException(Product product, int totalReserves) {
		super("Not enough available products. Product: '" + product.getId() + "' - Products Available: " + (product.getQuantity()-totalReserves) + ".");
	}

}
