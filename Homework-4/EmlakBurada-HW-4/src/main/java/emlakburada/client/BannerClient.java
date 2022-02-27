package main.java.emlakburada.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import emlakburada.client.request.AddressRequest;
import emlakburada.client.request.BannerRequest;
import emlakburada.client.response.BannerResponse;

@Service
@FeignClient(value = "feign",url ="http://localhost:8081" )
public interface BannerClient {

	@PostMapping(value = "/banners")
	void saveBanner(BannerRequest bannerRequest);



}
