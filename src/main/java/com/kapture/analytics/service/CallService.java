package com.kapture.analytics.service;



import java.util.List;

public interface CallService{

     List<Object[]> getHighestCallVolumeByHour();
     List<Object[]> getLongestCallByHour();
     List<Object[]> getHighestCallVolumeByDayOfWeek();
     List<Object[]> getLongestCallByDayOfWeek();

}
