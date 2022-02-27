package main.java.emlakburada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.service.BannerService;

@RestController
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping(value = "/banners")
    public ResponseEntity<List<BannerResponse>> getAllBanners() {
        return new ResponseEntity<>(bannerService.getAllBanners(), HttpStatus.OK);
    }

    @PostMapping(value = "/banners")
    public ResponseEntity<BannerResponse> saveBanner(@RequestBody BannerRequest request) {
        return new ResponseEntity<>(bannerService.saveBanner(request), HttpStatus.OK);
    }

    @PutMapping(value = "/messages")
    public ResponseEntity<BannerResponse> updateBannerById(@RequestBody BannerRequest request, @RequestParam int bannerId) {
        return new ResponseEntity<>(bannerService.updateMessageById(request, bannerId), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/messages")
    public ResponseEntity<String> deleteBannerById(@RequestParam int bannerId) {
        return new ResponseEntity<>(bannerService.deleteBannerById(bannerId), HttpStatus.CREATED);
    }
}
