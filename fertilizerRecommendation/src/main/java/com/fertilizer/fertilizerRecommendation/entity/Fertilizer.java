package com.fertilizer.fertilizerRecommendation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fertilizer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String diseaseName;
	    private String fertilizerName;
	    private String usageInstructions;
		public String getDiseaseName() {
			return diseaseName;
		}
		public void setDiseaseName(String diseaseName) {
			this.diseaseName = diseaseName;
		}
		public String getFertilizerName() {
			return fertilizerName;
		}
		public void setFertilizerName(String fertilizerName) {
			this.fertilizerName = fertilizerName;
		}
		public String getUsageInstructions() {
			return usageInstructions;
		}
		public void setUsageInstructions(String usageInstructions) {
			this.usageInstructions = usageInstructions;
		}
		public Fertilizer() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
