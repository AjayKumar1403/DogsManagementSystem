/**
 * Ajay Kumar Dhonthoju
 */


package com.bandiClasses.DMS.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bandiClasses.DMS.Model.Dog;

public interface DogRepository extends CrudRepository<Dog, Integer> {

	
	List<Dog> findByName(String name);
}
