package com.kapture.analytics.controller;

import com.kapture.analytics.entity.Call;
import com.kapture.analytics.repository.CallRepository;
import com.kapture.analytics.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/analytics")
public class CallController {

    @Autowired
    private CallRepository callRepository;
    @Autowired
    private CallService callService;


    @PostMapping("/import")
    public ResponseEntity<Call> importData(@RequestBody Call call) {
        return ResponseEntity.ok(callRepository.save(call));
    }

    @PostMapping("/importList")
    public ResponseEntity<List<Call>> importData(@RequestBody List<Call> call) {
        return ResponseEntity.ok(callRepository.saveAll(call));
    }

    // Using JPA Native Query Methods
    @GetMapping("/callWithHighestVolume")
    public ResponseEntity<List<Object[]>> getCallWithHighestVolume() {
        List<Object[]> callVolume = callService.getHighestCallVolumeByHour();
        return ResponseEntity.ok(callVolume);

    }

    @GetMapping("/longestCallByHour")
    public ResponseEntity<List<Object[]>> findLongestCallByHour() {
        List<Object[]> callVolumeByHourOfDay = callService.getLongestCallByHour();
        return ResponseEntity.ok(callVolumeByHourOfDay);
    }

    @GetMapping("/highestCallByDayOfWeek")
    public ResponseEntity<List<Object[]>> findHighestCallVolumeByDayOfWeek() {
        List<Object[]> dayOfWeek = callService.getHighestCallVolumeByDayOfWeek();
        return ResponseEntity.ok(dayOfWeek);
    }

    @GetMapping("/longestCallByDayOfWeek")
    public ResponseEntity<List<Object[]>> findLongestCallByDayOfWeek() {
        List<Object[]> dayOfWeek = callService.getLongestCallByDayOfWeek();
        return ResponseEntity.ok(dayOfWeek);
    }

    // Using Java 8 Stream API
    @GetMapping("/hourly-call-volume")
    public Map<Integer, Long> getHourlyCallVolume() {
        Map<Integer, Long> hourlyCallVolume = callRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(call -> call.getStartTime().getHour(), Collectors.counting()));
        return hourlyCallVolume;
    }

    @GetMapping("/hourly-call-duration")
    public Map<Integer, Double> getHourlyCallDuration() {
        Map<Integer, Double> hourlyCallDuration = callRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(call -> call.getStartTime().getHour(), Collectors.averagingInt(Call::getDuration)));
        return hourlyCallDuration;
    }

    @GetMapping("/daily-call-volume")
    public Map<DayOfWeek, Long> getDailyCallVolume() {
        Map<DayOfWeek, Long> dailyCallVolume = callRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(call -> call.getStartTime().getDayOfWeek(), Collectors.counting()));
        return dailyCallVolume;
    }

    @GetMapping("/daily-call-duration")
    public Map<DayOfWeek, Double> getDailyCallDuration() {
        Map<DayOfWeek, Double> dailyCallDuration = callRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(call -> call.getStartTime().getDayOfWeek(), Collectors.averagingInt(Call::getDuration)));
        return dailyCallDuration;
    }
}
