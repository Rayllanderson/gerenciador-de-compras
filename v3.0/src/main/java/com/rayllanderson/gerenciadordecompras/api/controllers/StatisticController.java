package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.core.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.core.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController { //nao vai ser esse nome

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<StatisticResponseBody> getStatistics() {
        Long userId = 1L;
        return ResponseEntity.ok(statisticService.getStatistics(userId));
    }
}
