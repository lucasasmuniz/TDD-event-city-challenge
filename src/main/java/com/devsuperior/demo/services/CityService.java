package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		return repository.findAll(Sort.by("name")).stream().map(x -> new CityDTO(x)).toList();
	}
	
	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City entity = new City();
		entity.setName(cityDTO.getName());
		repository.save(entity);
		return new CityDTO(entity);
	}
	
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id) {
    	if(!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Resource not found");
    	}
    	try {
    		repository.deleteById(id);
    	}catch(DataIntegrityViolationException e){
    		throw new DatabaseException("Referential integrity error");
    	}
    }
}
