package com.example.PhoneStore.service.interf;

import com.example.PhoneStore.domain.request.RequestOrder;
import com.example.PhoneStore.domain.response.*;

import java.util.List;

public interface ProductService {

    List<ResponseProduct> getAllProduct();

    ResponseProduct getProductByID(int productID);

    List<ResponseProduct> getProductByCategoryID(int categoryID);

    List<ResponseProduct> getProductByName(String productName);

    List<ResponsePromotion> getPromotionOfProduct(int productID);
    List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(int productID);

    List<ResponseProductColor> getColorOfProduct(int productID);
    List<ResponseProductSpecification> getSpecificationOfProduct(int productID);
    List<ResponseBanner> getBanner();
    List<ResponseCustomerVisitImage> getCustomerVisitImage();

    List<ResponseStorage> getProductStorage(int productID);

    ResponseProductVariant getProductVariant(int productID, int productColorID, int productStorageID);

    void createOrder(RequestOrder requestOrder);
}
