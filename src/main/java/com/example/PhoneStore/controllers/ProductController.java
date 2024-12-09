package com.example.PhoneStore.controllers;

import com.example.PhoneStore.domain.request.*;
import com.example.PhoneStore.domain.response.*;
import com.example.PhoneStore.exception.ApiRequestException;
import com.example.PhoneStore.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        try {
            if (image.isEmpty()) {
                return ResponseEntity.badRequest().body("Không có tệp nào được chọn.");
            }

            String uploadDir = System.getProperty("user.dir") + "/uploads/products";

            Path path = Paths.get(uploadDir, image.getOriginalFilename());

            File destDir = new File(uploadDir);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            File dest = new File(path.toString());
            image.transferTo(dest);

            String imageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + "/uploads/products/" + image.getOriginalFilename();
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            log.error("Lỗi khi tải lên hình ảnh: ", e);
            return ResponseEntity.status(500).body("Có lỗi xảy ra khi tải lên hình ảnh.");
        }
    }

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
    public ResponseEntity<ResponseProduct> getProductByID(@PathVariable String productID) {
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

    @GetMapping("/getProductByName")
    public ResponseEntity<List<ResponseProduct>> getProductByName(@RequestParam String productName) {
        try {
            List<ResponseProduct> product = productService.getProductByName(productName);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getPromotionOfProduct")
    public ResponseEntity<List<ResponsePromotion>> getPromotionOfProduct(@RequestParam String productID) {
        try {
            List<ResponsePromotion> product = productService.getPromotionOfProduct(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getInstallmentPlanOfProduct")
    public ResponseEntity<List<ResponseInstallmentPlan>> getInstallmentPlanOfProduct(@RequestParam String productID, @RequestParam String variantID) {
        try {
            List<ResponseInstallmentPlan> product = productService.getInstallmentPlanOfProduct(productID, variantID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getColorOfProduct")
    public ResponseEntity<List<ResponseProductColor>> getColorOfProduct(@RequestParam String productID) {
        try {
            List<ResponseProductColor> product = productService.getColorOfProduct(productID);
            return ResponseEntity.ok(product);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getSpecificationOfProduct")
    public ResponseEntity<List<ResponseProductSpecification>> getSpecificationOfProduct(@RequestParam String productID) {
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

    @GetMapping("/getNews")
    public ResponseEntity<List<ResponseNews>> getNews() {
        try {
            List<ResponseNews> news = productService.getNews();
            return ResponseEntity.ok(news);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getProductStorage")
    public ResponseEntity<List<ResponseStorage>> getProductStorage(@RequestParam String productID) {
        try {
            List<ResponseStorage> storages = productService.getProductStorage(productID);
            return ResponseEntity.ok(storages);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getProductVariant")
    public ResponseEntity<ResponseProductVariant> getProductVariant(@RequestParam String productID,@RequestParam String productColorID,@RequestParam int productStorageID) {
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

    @GetMapping("/getProductContent")
    public ResponseEntity<List<ResponseProductContent>> getProductContent(@RequestParam String productID) {
        try {
            List<ResponseProductContent> productContents = productService.getProductContent(productID);
            return ResponseEntity.ok(productContents);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody RequestProduct requestProduct) {
        try {
            productService.createProduct(requestProduct);
            return ResponseEntity.ok("Create product successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/insertProductContent")
    public ResponseEntity<?> insertProductContent(@RequestBody RequestProductContent requestProductContent) {
        try {
            productService.insertProductContent(requestProductContent);
            return ResponseEntity.ok("Create product content successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PutMapping("/editProductContent")
    public ResponseEntity<?> editProductContent(@RequestBody RequestEditProductContent requestEditProductContent) {
        try {
            productService.editProductContent(requestEditProductContent);
            return ResponseEntity.ok("Create edit product content successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/addPriceForProduct")
    public ResponseEntity<?> addPriceForProduct(@RequestBody RequestPriceForProduct requestPriceForProduct) {
        try {
            productService.addPriceForProduct(requestPriceForProduct);
            return ResponseEntity.ok("Create edit product content successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/addSpecificationForProduct")
    public ResponseEntity<?> addSpecificationForProduct(@RequestBody RequestProductSpecification requestProductSpecification) {
        try {
            productService.addSpecificationForProduct(requestProductSpecification);
            return ResponseEntity.ok("Create product specification successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @DeleteMapping("/deleteSpecificationForProduct")
    public ResponseEntity<?> deleteSpecificationForProduct(@RequestParam int specificationID) {
        try {
            productService.deleteSpecificationForProduct(specificationID);
            return ResponseEntity.ok("Delete product specification successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/addBanner")
    public ResponseEntity<?> addBanner(@RequestBody RequestBanner requestBanner) {
        try {
            productService.addBanner(requestBanner);
            return ResponseEntity.ok("Create banner successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @DeleteMapping("/deleteBanner")
    public ResponseEntity<?> deleteBanner(@RequestParam int bannerID) {
        try {
            productService.deleteBanner(bannerID);
            return ResponseEntity.ok("Delete banner successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/addCustomerImage")
    public ResponseEntity<?> addCustomerImage(@RequestBody RequestImageCustomer requestImageCustomer) {
        try {
            productService.addCustomerImage(requestImageCustomer);
            return ResponseEntity.ok("Create image's customer successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @DeleteMapping("/deleteCustomerImage")
    public ResponseEntity<?> deleteCustomerImage(@RequestParam int customerImageID) {
        try {
            productService.deleteCustomerImage(customerImageID);
            return ResponseEntity.ok("Delete image's customer successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getListOrder")
    public ResponseEntity<List<ResponseOrderDetail>> getListOrder() {
        try {
            List<ResponseOrderDetail> responseOrderDetails = productService.getListOrder();
            return ResponseEntity.ok(responseOrderDetails);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getListVariantByProductID")
    public ResponseEntity<List<ResponseVariantByProductID>> getListVariantByProductID(@RequestParam String productID) {
        try {
            List<ResponseVariantByProductID> responseVariantByProductIDList = productService.getListVariantByProductID(productID);
            return ResponseEntity.ok(responseVariantByProductIDList);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @DeleteMapping("/deleteVariant")
    public ResponseEntity<?> deleteVariant(@RequestParam String variantID) {
        try {
            productService.deleteVariant(variantID);
            return ResponseEntity.ok("Delete variant successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getTopProduct")
    public ResponseEntity<List<ResponseProduct>> getTopProduct() {
        try {
            List<ResponseProduct> responseProducts = productService.getTopProduct();
            return ResponseEntity.ok(responseProducts);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getListQuote")
    public ResponseEntity<List<ResponseQuote>> getListQuote() {
        try {
            List<ResponseQuote> responseQuotes = productService.getListQuote();
            return ResponseEntity.ok(responseQuotes);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/insertQuote")
    public ResponseEntity<?> insertQuote(@RequestBody RequestQuote requestQuote) {
        try {
            productService.insertQuote(requestQuote);
            return ResponseEntity.ok("Create quote successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @DeleteMapping("/deleteQuote")
    public ResponseEntity<?> deleteQuote(@RequestParam int quoteID) {
        try {
            productService.deleteQuote(quoteID);
            return ResponseEntity.ok("Delete quote successfully");
        } catch (ApiRequestException e) {
            throw e;
        }
    }


}
