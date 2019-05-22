package at.fh.swenga.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.SmartphoneModel;

@Repository
@Transactional
public interface SmartphoneRepository extends JpaRepository<SmartphoneModel, Integer> {

	List<SmartphoneModel> findByName(String name);

	@Query ("SELECT s FROM SmartphoneModel AS s JOIN s.brand AS b "
			+ "WHERE LOWER(b.name) LIKE CONCAT('%', LOWER(:brand), '%')")
	List<SmartphoneModel> findByBrand(@Param("brand") String brand);

	List<SmartphoneModel> findByNameOrBrand(@Param("searchString") String brandOrName);
	
	@Query ("SELECT s FROM SmartphoneModel AS s JOIN s.brand AS b "
			+ "WHERE LOWER(b.name) LIKE CONCAT('%',LOWER(:brand), '%') ORDER BY s.price ASC")
	List<SmartphoneModel> findByBrandOrderByPriceAsc(@Param("brand") String brand);

	int countByBrand(@Param("searchString") String brand);
	
	List<SmartphoneModel> findTop1ByNameOrderByPriceAsc(String name);
	
	@Query ("SELECT s FROM SmartphoneModel AS s WHERE s.price < :price")
	List<SmartphoneModel> findByPrice(@Param("price")double price);

	List<SmartphoneModel> deleteFirstByNameOrderByPriceDesc(String name);

	List<SmartphoneModel> findTop10ByReleaseDateAfterOrderByPriceAsc(Calendar date);
	
	

	
	
	
	
	
	
	
	
}