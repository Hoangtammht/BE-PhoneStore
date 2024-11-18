package com.example.PhoneStore.dao;

import com.example.PhoneStore.domain.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ResponseProduct> getAllProduct();

    ResponseProduct getProductByID(int productID);

    List<ResponseProduct> getProductByCategoryID(int productID);
    List<ResponsePromotion> getPromotionOfProduct(int productID);
    List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(int productID);

    List<ResponseProductColor> getColorOfProduct(int productID);
    List<ResponseProductSpecification> getSpecificationOfProduct(int productID);

    List<ResponseBanner> getBanner();

    List<ResponseCustomerVisitImage> getCustomerVisitImage();

    List<ResponseStorage> getProductStorage(int productID);

    ResponseProductVariant getProductVariant(int productID, int productColorID, int productStorageID);

    void insertCustomer(String fullName, String phone);

    int getLastInsertedCustomerID();

    void insertOrder(int customerID, String orderDate, double totalAmount);

    int getLastInsertedOrderID();

    void insertOrderDetail(int orderID, int productID, double priceAtOrder);

}
