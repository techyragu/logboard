package com.cisco.logboard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
	public Optional<Log> findById(Integer integer);
	
	public List<Log> findAllByTimestampBetween(LocalDateTime TimestampBefore, LocalDateTime TimestampAfter);
}
