package com.fertilizer.fertilizerRecommendation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fertilizer.fertilizerRecommendation.entity.Fertilizer;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long>{
	List<Fertilizer> findByDiseaseName(String diseaseName);

}
