package com.shopLocation.FertilizerShopLocation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopLocation.FertilizerShopLocation.dto.LocationRequest;
import com.shopLocation.FertilizerShopLocation.dto.ShopDTO;
import com.shopLocation.FertilizerShopLocation.service.ShopService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/shops")
@AllArgsConstructor
public class ShopController {
	
	private final ShopService shopS;
	
	public ShopController(ShopService shopS) {
		this.shopS = shopS;
	}
	
	// Get shops using lat/lon (nearest)
    @PostMapping("/nearby")
    public ResponseEntity<List<ShopDTO>> getNearby(@RequestBody LocationRequest req) {
        return ResponseEntity.ok(shopS.getNearestShops(req.getLatitude(), req.getLongitude(), 3));
    }
    
 // Get shops by pin code
    @GetMapping("/pincode/{pinCode}")
    public ResponseEntity<List<ShopDTO>> getByPin(@PathVariable String pinCode) {
        return ResponseEntity.ok(shopS.getShopsByPincode(pinCode));
    }
    
    @PostMapping("/load")
    public ResponseEntity<String> load() {
        shopS.loadSampleShops();
        return ResponseEntity.ok("Sample shops added.");
    }

}
