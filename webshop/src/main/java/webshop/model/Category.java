package webshop.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

	@Id
	@GeneratedValue
	private int id;
	private String name; 
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;	
	
	public void addProduct(Product p) {
		if(this.products == null)
			this.products = new ArrayList<>();
		
		this.products.add(p);
		p.setCategory(this);
	}
}
