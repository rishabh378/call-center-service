package com.kapture.analytics.repository;

import com.kapture.analytics.entity.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {
  @Query(
          value = "SELECT HOUR(startTime) AS hoursOfDay, Count(*) As callCount FROM Call GROUP BY hoursOfDay order by callCount DESC"
  )
  List<Object[]> findHighestCallVolumeByHour();

  @Query(
          value = "SELECT HOUR(startTime) AS hoursOfDay, AVG(duration) AS avgDuration FROM Call GROUP BY hoursOfDay order by avgDuration DESC"
  )
  List<Object[]> findLongestCallByHour();

  @Query(
          value = "SELECT DAYNAME(startTime) AS dayOfWeek, COUNT(*) AS callCount FROM Call GROUP BY dayOfWeek ORDER BY callCount DESC"
  )
  List<Object[]> findHighestCallVolumeByDayOfWeek();

  @Query(
          value = "SELECT DAYNAME(startTime) AS dayOfWeek, AVG(duration) AS avgDuration FROM Call GROUP BY dayOfWeek ORDER BY avgDuration DESC"
  )
  List<Object[]> findLongestCallByDayOfWeek();

}
