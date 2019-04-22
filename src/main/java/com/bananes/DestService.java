package com.bananes;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bananes.model.Destination;
import com.bananes.repo.DestRepository;

@Service
public class DestService {
	@Autowired
	private DestRepository destRepository;

	@Transactional
	public Destination save(Destination des) {
		List<Destination> l = destRepository.findDestbyCrits(des.getName(), des.getAddress(), des.getPostCode(), des.getCity(), des.getCountry());
		Destination dest = null;
		if (!CollectionUtils.isEmpty(l)) {
			dest = l.stream().filter(d -> des.equals(d)).findFirst().orElse(null);
		}
		if (dest == null) {
			return destRepository.save(des);
		} else
			return dest;
	}

	@Transactional
	public List<Destination> findAll() {
		return destRepository.findAll();
	}

	@Transactional
	public List<Destination> findDestbyCrits(String name, String address, Integer postCode, String city, String country) {
		return destRepository.findDestbyCrits(name, address, postCode, city, country);
	}
	
	@Transactional
	public Destination findDestbyId(Long id) {
		return destRepository.findById(id).orElse(null);
	}

}