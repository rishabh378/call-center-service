package com.kapture.analytics.controller;

import com.kapture.analytics.entity.Call;
import com.kapture.analytics.repository.CallRepository;
import com.kapture.analytics.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
