package com.example.PhoneStore.service.interf;

import com.example.PhoneStore.domain.request.*;
import com.example.PhoneStore.domain.response.*;

import java.util.List;

public interface ProductService {

    List<ResponseProduct> getAllProduct();
    ResponseProduct getProductByID(String productID);
    List<ResponseProduct> getProductByCategoryID(int categoryID);
    List<ResponseProduct> getProductByName(String productName);
    List<ResponsePromotion> getPromotionOfProduct(String productID);
    List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(String productID, String variantID);
    List<ResponseProductColor> getColorOfProduct(String productID);
    List<ResponseProductSpecification> getSpecificationOfProduct(String productID);
    List<ResponseBanner> getBanner();
    List<ResponseCustomerVisitImage> getCustomerVisitImage();
    List<ResponseNews> getNews();
    List<ResponseStorage> getProductStorage(String productID);
    ResponseProductVariant getProductVariant(String productID, String productColorID, int productStorageID);
    void createOrder(RequestOrder requestOrder);
    List<ResponseProductContent> getProductContent(String productID);
    void createProduct(RequestProduct requestProduct);
    void insertProductContent(RequestProductContent requestProductContent);
    void editProductContent(RequestEditProductContent requestEditProductContent);
    void addPriceForProduct(RequestPriceForProduct requestPriceForProduct);
    void addSpecificationForProduct(RequestProductSpecification requestProductSpecification);
    void deleteSpecificationForProduct(int specificationID);

    void addBanner(RequestBanner requestBanner);
    void deleteBanner(int bannerID);

    void addCustomerImage(RequestImageCustomer requestImageCustomer);

    void deleteCustomerImage(int customerImageID);

    List<ResponseOrderDetail> getListOrder();

    List<ResponseVariantByProductID> getListVariantByProductID(String productID);

    void deleteVariant(String variantID);

    List<ResponseProduct> getTopProduct();

    List<ResponseQuote> getListQuote();

    void insertQuote(RequestQuote requestQuote);

    void deleteQuote(int quoteID);

    void updateStatusOfProduct(String productID, int status);

}
