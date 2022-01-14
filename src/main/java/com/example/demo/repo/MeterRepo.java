package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Meters;

@Repository
public interface MeterRepo extends JpaRepository<Meters, Integer> {

	@Transactional
	@Modifying
	@Query(value="UPDATE meters SET end_reading=:end_reading, start_reading=:start_reading,last_updated=:last_updated WHERE meter_Id=:meterId ",nativeQuery = true)
	int updateById(@Param(value = "meterId") int meterId,@Param(value="start_reading") int start_reading,@Param(value = "end_reading") int end_reading,@Param(value="last_updated") String last_updated);
}
