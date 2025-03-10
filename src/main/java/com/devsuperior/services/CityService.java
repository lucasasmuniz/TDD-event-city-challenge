package com.devsuperior.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.repositories.CityRepository;

@Service
public class CityService {

	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		return repository.findAll(Sort.by("name")).stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
}
