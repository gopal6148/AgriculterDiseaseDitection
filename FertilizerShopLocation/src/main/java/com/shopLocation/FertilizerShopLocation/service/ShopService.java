package com.shopLocation.FertilizerShopLocation.service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shopLocation.FertilizerShopLocation.dto.ShopDTO;
import com.shopLocation.FertilizerShopLocation.entity.Shop;
import com.shopLocation.FertilizerShopLocation.repository.ShopRepository;
import com.shopLocation.FertilizerShopLocation.util.GeoUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	
	private final ShopRepository shopR;
	
	public ShopService(ShopRepository shopR2) {
		this.shopR = shopR2;
		
	}
	
	public List<ShopDTO> getNearestShops(double userLat, double userLon, int maxResults) {
        return shopR.findAll().stream()
                .map(shop -> new AbstractMap.SimpleEntry<>(shop,
                        GeoUtil.haversine(userLat, userLon, shop.getLatitude(), shop.getLongitude())))
                .sorted(Map.Entry.comparingByValue())
                .limit(maxResults)
                .map(entry -> {
                    Shop s = entry.getKey();
                    return new ShopDTO(s.getName(), s.getAddress(), s.getLatitude(), s.getLongitude());
                })
                .toList();
    }
	
	 public List<ShopDTO> getShopsByPincode(String pincode) {
	        return shopR.findByPinCode(pincode).stream()
	                .map(s -> new ShopDTO(s.getName(), s.getAddress(), s.getLatitude(), s.getLongitude()))
	                .toList();
	    }
	 public void loadSampleShops() {
	        shopR.saveAll(List.of(
	                new Shop(null, "Krishi Kendra A", "Main Bazaar, Pune", "411001", 18.5204, 73.8567),
	                new Shop(null, "Fertilizer Hub B", "Station Road, Nashik", "422001", 19.9975, 73.7898),
	                new Shop(null, "AgriMart C", "Market Yard, Kolhapur", "416001", 16.7050, 74.2433)
	        ));
	    }
	

}
