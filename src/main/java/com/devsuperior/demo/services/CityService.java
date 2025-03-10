package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;

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
}
