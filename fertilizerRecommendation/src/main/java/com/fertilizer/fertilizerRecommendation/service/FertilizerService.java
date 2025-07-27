package com.fertilizer.fertilizerRecommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fertilizer.fertilizerRecommendation.entity.Fertilizer;
import com.fertilizer.fertilizerRecommendation.repository.FertilizerRepository;

@Service
public class FertilizerService {
	@Autowired
    private FertilizerRepository fertilizerRepository;

    public List<Fertilizer> getRecommendations(String diseaseName) {
        return fertilizerRepository.findByDiseaseName(diseaseName);
    }

    // Optional: Add method to insert data
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        return fertilizerRepository.save(fertilizer);
    }

}
