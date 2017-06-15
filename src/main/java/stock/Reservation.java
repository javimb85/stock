package stock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	private int idClient;
	private int idProduct;
 
    public Reservation() {
	}
    
    public Reservation(int idClient, int idProduct) {
		super();
		this.idClient = idClient;
		this.idProduct = idProduct;
	}
    
    public Reservation(int id, int idClient, int idProduct) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.idProduct = idProduct;
	}
    
	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", idClient=" + idClient + ", idProduct=" + idProduct + "]";
	}
}