package com.bananes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.CollectionUtils;

import com.bananes.model.Destination;

public interface DestRepository extends JpaRepository<Destination, Long> {
	
	@Query("SELECT d FROM Destination d WHERE LOWER(d.name) = LOWER(:name) and LOWER(d.address) = LOWER(:address) and"
			+ "  d.postCode = :postCode and LOWER(d.city) = LOWER(:city) and LOWER(d.country) = LOWER(:country)")
	List<Destination> findDestbyCrits(@Param("name")String name,@Param("address")String address,@Param("postCode")Integer postCode,
			@Param("city")String city,@Param("country")String country);
	
	
	/*static Destination saveDest(Destination des){
		List<Destination> l = findDestbyCrits(des.getName(),des.getAddress(),des.getPostCode(),des.getCity(),des.getCountry());
    	Destination dest = null;
    	if(!CollectionUtils.isEmpty(l) ){	
    		dest = l.stream().filter(d -> des.equals(d)).findFirst().orElse(null);	
    	}
    	if(dest == null){
    		return save(des);
    	}else return dest;
	}*/
	
	
}
