package com.shopLocation.FertilizerShopLocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopLocation.FertilizerShopLocation.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>{
	
	List<Shop> findByPinCode(String pinCode);

}
