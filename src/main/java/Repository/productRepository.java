package Repository;

import org.springframework.data.repository.CrudRepository;

import Entity.Product;

public interface productRepository extends CrudRepository<Product, String>  
{
	
}