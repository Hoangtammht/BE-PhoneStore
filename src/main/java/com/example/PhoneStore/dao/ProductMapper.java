package com.example.PhoneStore.dao;

import com.example.PhoneStore.domain.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ResponseProduct> getAllProduct();

    ResponseProduct getProductByID(int productID);

    ResponseProductOrder getProductByOrderID(String orderID);

    List<ResponseProduct> getProductByCategoryID(int productID);

    List<ResponseProduct> getProductByName(String productName);
    List<ResponsePromotion> getPromotionOfProduct(int productID);
    List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(int productID);

    List<ResponseProductColor> getColorOfProduct(int productID);
    List<ResponseProductSpecification> getSpecificationOfProduct(int productID);

    List<ResponseBanner> getBanner();

    List<ResponseCustomerVisitImage> getCustomerVisitImage();

    List<ResponseStorage> getProductStorage(int productID);

    ResponseProductVariant getProductVariant(int productID, int productColorID, int productStorageID);

    void insertCustomer(String customerID, String fullName, String phone);

    int getCustomerByID(String customerID);

    void insertOrder(String orderID, String customerID, String orderDate, double totalAmount);

    int getLastInsertedOrderID();

    void insertOrderDetail(String orderDetailID, String orderID, int productID, int variantID, double priceAtOrder);

}
