package at.fh.swenga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.BrandModel;
import at.fh.swenga.model.SmartphoneModel;

@Repository

public interface BrandRepository extends JpaRepository<BrandModel, Integer> {

	@Transactional
	BrandModel findFirstByName(String brandName);
	
	

}
