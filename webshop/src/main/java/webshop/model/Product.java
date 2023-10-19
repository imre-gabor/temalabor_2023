package webshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private int id;
	private String name;
	private double price;
	
	
	private Category category;
}
