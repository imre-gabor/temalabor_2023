package webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private int id;
	private String name;
	private double price;
		
	private CategoryDto category;
}
