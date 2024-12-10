package com.example.PhoneStore.dao;

import com.example.PhoneStore.domain.request.*;
import com.example.PhoneStore.domain.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ResponseProduct> getAllProduct();

    ResponseProduct getProductByID(String productID);

    ResponseProductOrder getProductByOrderID(String orderID);

    List<ResponseProduct> getProductByCategoryID(int categoryID);

    List<ResponseProduct> getProductByName(String productName);
    List<ResponsePromotion> getPromotionOfProduct(String productID);
    List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(String productID, String variantID);

    List<ResponseProductColor> getColorOfProduct(String productID);
    List<ResponseProductSpecification> getSpecificationOfProduct(String productID);

    List<ResponseBanner> getBanner();

    List<ResponseCustomerVisitImage> getCustomerVisitImage();

    List<ResponseNews> getNews();

    List<ResponseProductContent> getProductContent(String productID);

    List<ResponseStorage> getProductStorage(String productID);

    ResponseProductVariant getProductVariant(String productID, String productColorID, int productStorageID);

    void insertCustomer(String customerID, String fullName, String phone);

    void insertOrder(String orderID, String customerID, String orderDate, double totalAmount, String orderType);

    void insertOrderDetail(String orderDetailID, String orderID, String productID, String variantID, String content, Double priceAtOrder, Integer installmentPlanID);

    void createProduct(RequestProduct requestProduct);

    void createInstallmentPlan(ResponseInstallmentPlan installmentPlan);

    void insertProductContent(RequestProductContent requestProductContent);

    void editProductContent(RequestEditProductContent requestEditProductContent);

    void addProductColor(String productColorID, String productID, String colorName, String imagePath);

    void addProductVariant(String variantID, String productID, String productColorID, int productStorageID, double price);

    void addSpecificationForProduct(RequestProductSpecification requestProductSpecification);

    void deleteSpecificationForProduct(int specificationID);
    void addBanner(RequestBanner requestBanner);
    void deleteBanner(int bannerID);
    void addCustomerImage(RequestImageCustomer requestImageCustomer);
    void deleteCustomerImage(int customerImageID);

    List<ResponseOrderDetail> getListOrder();

    List<ResponseVariantByProductID> getListVariantByProductID(String productID);

    void deleteVariant(String variant);

    List<ResponseProduct> getTopProduct();

    List<ResponseQuote> getListQuote();

    void insertQuote(RequestQuote requestQuote);

    void deleteQuote(int quoteID);

    void updateStatusOfProduct(String productID, int status);

}
