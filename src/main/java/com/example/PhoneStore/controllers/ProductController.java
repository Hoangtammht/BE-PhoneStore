package com.example.PhoneStore.controllers;

import com.example.PhoneStore.domain.request.RequestOrder;
import com.example.PhoneStore.domain.response.*;
import com.example.PhoneStore.exception.ApiRequestException;
import com.example.PhoneStore.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/allProduct")
    public ResponseEntity<List<ResponseProduct>> getAllProduct() {
        try {
            List<ResponseProduct> products = productService.getAllProduct();
            return ResponseEntity.ok(products);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ResponseProduct> getProductByID(@PathVariable int productID) {
        try {
            ResponseProduct product = productService.getProductByID(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getProductByCategoryID")
    public ResponseEntity<List<ResponseProduct>> getProductByCategoryID(@RequestParam int categoryID) {
        try {
            List<ResponseProduct> product = productService.getProductByCategoryID(categoryID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getPromotionOfProduct")
    public ResponseEntity<List<ResponsePromotion>> getPromotionOfProduct(@RequestParam int productID) {
        try {
            List<ResponsePromotion> product = productService.getPromotionOfProduct(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getInstallmentPlanOfProduct")
    public ResponseEntity<List<ResponseInstallmentPlan>> getInstallmentPlanOfProduct(@RequestParam int productID) {
        try {
            List<ResponseInstallmentPlan> product = productService.getInstallmentPlanOfProduct(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getColorOfProduct")
    public ResponseEntity<List<ResponseProductColor>> getColorOfProduct(@RequestParam int productID) {
        try {
            List<ResponseProductColor> product = productService.getColorOfProduct(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getSpecificationOfProduct")
    public ResponseEntity<List<ResponseProductSpecification>> getSpecificationOfProduct(@RequestParam int productID) {
        try {
            List<ResponseProductSpecification> product = productService.getSpecificationOfProduct(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getBanner")
    public ResponseEntity<List<ResponseBanner>> getBanner() {
        try {
            List<ResponseBanner> banner = productService.getBanner();
            return ResponseEntity.ok(banner);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getCustomerVisitImage")
    public ResponseEntity<List<ResponseCustomerVisitImage>> getCustomerVisitImage() {
        try {
            List<ResponseCustomerVisitImage> customerVisitImages = productService.getCustomerVisitImage();
            return ResponseEntity.ok(customerVisitImages);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getProductStorage")
    public ResponseEntity<List<ResponseStorage>> getProductStorage(@RequestParam int productID) {
        try {
            List<ResponseStorage> storages = productService.getProductStorage(productID);
            return ResponseEntity.ok(storages);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getProductVariant")
    public ResponseEntity<ResponseProductVariant> getProductVariant(@RequestParam int productID,@RequestParam int productColorID,@RequestParam int productStorageID) {
        try {
            ResponseProductVariant variant = productService.getProductVariant(productID, productColorID, productStorageID);
            return ResponseEntity.ok(variant);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody RequestOrder requestOrder) {
        try {
            productService.createOrder(requestOrder);
            return ResponseEntity.ok("Create order successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

}
