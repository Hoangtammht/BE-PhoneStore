package com.example.PhoneStore.service.impl;

import com.example.PhoneStore.dao.ProductMapper;
import com.example.PhoneStore.domain.request.RequestOrder;
import com.example.PhoneStore.domain.response.*;
import com.example.PhoneStore.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImpl implements ProductService {

    private final ProductMapper productMapper;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public List<ResponseProduct> getAllProduct() {
        return productMapper.getAllProduct();
    }

    @Override
    public ResponseProduct getProductByID(int productID) {
        return productMapper.getProductByID(productID);
    }

    @Override
    public List<ResponseProduct> getProductByCategoryID(int categoryID) {
        return productMapper.getProductByCategoryID(categoryID);
    }

    @Override
    public List<ResponsePromotion> getPromotionOfProduct(int productID) {
        return productMapper.getPromotionOfProduct(productID);
    }

    @Override
    public List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(int productID) {
        return productMapper.getInstallmentPlanOfProduct(productID);
    }

    @Override
    public List<ResponseProductColor> getColorOfProduct(int productID) {
        return productMapper.getColorOfProduct(productID);
    }

    @Override
    public List<ResponseProductSpecification> getSpecificationOfProduct(int productID) {
        return productMapper.getSpecificationOfProduct(productID);
    }

    @Override
    public List<ResponseBanner> getBanner() {
        return productMapper.getBanner();
    }

    @Override
    public List<ResponseCustomerVisitImage> getCustomerVisitImage() {
        return productMapper.getCustomerVisitImage();
    }

    @Override
    public List<ResponseStorage> getProductStorage(int productID) {
        return productMapper.getProductStorage(productID);
    }

    @Override
    public ResponseProductVariant getProductVariant(int productID, int productColorID, int productStorageID) {
        return productMapper.getProductVariant(productID, productColorID, productStorageID);
    }

    @Override
    @Transactional
    public void createOrder(RequestOrder requestOrder) {
        // Insert customer and retrieve the customer ID
        productMapper.insertCustomer(requestOrder.getFullName(), requestOrder.getPhone());
        Integer customerID = productMapper.getLastInsertedCustomerID();


        if (requestOrder.getOrderDate() == null || requestOrder.getOrderDate().isEmpty()) {
            requestOrder.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        productMapper.insertOrder(customerID, requestOrder.getOrderDate(), requestOrder.getTotalAmount());
        Integer orderID = productMapper.getLastInsertedOrderID();

        productMapper.insertOrderDetail(orderID, requestOrder.getProductID(), requestOrder.getPriceAtOrder());

        sendOrderNotificationEmail(requestOrder);
    }

    private void sendOrderNotificationEmail(RequestOrder requestOrder) {
        SimpleMailMessage email = new SimpleMailMessage();
        ResponseProduct product = productMapper.getProductByID(requestOrder.getProductID());
        email.setTo("hoangtammht@gmail.com");
        email.setSubject("New Order Received");
        email.setText("You have received a new order.\n\n" +
                "Order Details:\n" +
                "Full Name: " + requestOrder.getFullName() + "\n" +
                "Phone: " + requestOrder.getPhone() + "\n" +
                "Product Name: " + product.getProductName() + "\n" +
                "Order Date: " + requestOrder.getOrderDate() + "\n" +
                "Total Amount: " + requestOrder.getTotalAmount());
        mailSender.send(email);
    }

}
