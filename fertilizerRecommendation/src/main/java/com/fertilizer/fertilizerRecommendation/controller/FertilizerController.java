package com.fertilizer.fertilizerRecommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fertilizer.fertilizerRecommendation.entity.Fertilizer;
import com.fertilizer.fertilizerRecommendation.service.FertilizerService;

@RestController
@RequestMapping("/api/fertilizers")
public class FertilizerController {
	
	@Autowired
	private FertilizerService fertilizerService;
	
	// Endpoint to get recommendations by disease
    @GetMapping("/recommendation")
    public List<Fertilizer> getRecommendation(@RequestParam String diseaseName) {
        return fertilizerService.getRecommendations(diseaseName);
    }

    // Optional: Add new fertilizer recommendation
    @PostMapping("/add")
    public Fertilizer addFertilizer(@RequestBody Fertilizer fertilizer) {
        return fertilizerService.addFertilizer(fertilizer);
    }


}
