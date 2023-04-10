package com.kapture.analytics.service.impl;

import com.kapture.analytics.repository.CallRepository;
import com.kapture.analytics.service.CallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallServiceImpl implements CallService {
  @Autowired private CallRepository callRepository;

  @Override
  public List<Object[]> getHighestCallVolumeByHour() {
    return callRepository.findHighestCallVolumeByHour();
  }

  @Override
  public List<Object[]> getLongestCallByHour() {
    return callRepository.findLongestCallByHour();
  }

  @Override
  public List<Object[]> getHighestCallVolumeByDayOfWeek() {
    return callRepository.findHighestCallVolumeByDayOfWeek();
  }

  @Override
  public List<Object[]> getLongestCallByDayOfWeek() {
    return callRepository.findLongestCallByDayOfWeek();
  }
}
