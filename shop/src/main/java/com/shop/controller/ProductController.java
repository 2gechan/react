package com.shop.controller;

import com.shop.models.ProductDto;
import com.shop.service.FileService;
import com.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
public class ProductController {

    private final ProductService productService;
    private final FileService fileService;

    public ProductController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    @PostMapping("addItem")
    public ProductDto addItem(@RequestParam("p_category") String p_category,
                              @RequestParam("p_name") String p_name,
                              @RequestParam("p_qty") int p_qty,
                              @RequestParam("p_price") int p_price,
                              @RequestParam("p_main_image")MultipartFile p_main_image) {
        ProductDto returnDto = new ProductDto();
        returnDto.setP_category(p_category);
        returnDto.setP_name(p_name);
        returnDto.setP_qty(p_qty);
        returnDto.setP_price(p_price);

        System.out.println(p_main_image.getOriginalFilename());
        try {
            if (!p_main_image.getOriginalFilename().isEmpty()) {
                String fileName = fileService.fileUp(p_main_image);
                returnDto.setP_main_image(fileName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        productService.insert(returnDto);
        System.out.println("returnDto = " + returnDto.toString());
        return returnDto;
    }

    @GetMapping("product")
    public List<ProductDto> allProduct() {
        log.debug("리스트들 {}", productService.selectAll());
        return productService.selectAll();
    }

//    @Value("${image.path}")
//    private String imagePath;

//    @GetMapping("/images/{imageName}")
//    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
//        File file = new File(imagePath, imageName);
//        if (!file.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        byte[] imageBytes = Files.readAllBytes(file.toPath());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 타입에 따라 설정
//
//        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//    }
}