package com.khan.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.khan.security.dto.Coupon;
import com.khan.security.model.Product;
import com.khan.security.repository.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	@Autowired
	private ProductRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponservice.url}")
	private String couponServiceURL;

	@PostMapping("/products")
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
		// Now we want discount to be deducted from the product price
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));

		return repo.save(product);

	}
}
