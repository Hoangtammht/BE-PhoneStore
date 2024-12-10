package com.example.PhoneStore.service.impl;

import com.example.PhoneStore.dao.ProductMapper;
import com.example.PhoneStore.domain.request.*;
import com.example.PhoneStore.domain.response.*;
import com.example.PhoneStore.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    public ResponseProduct getProductByID(String productID) {
        return productMapper.getProductByID(productID);
    }

    @Override
    public List<ResponseProduct> getProductByCategoryID(int categoryID) {
        return productMapper.getProductByCategoryID(categoryID);
    }

    @Override
    public List<ResponsePromotion> getPromotionOfProduct(String productID) {
        return productMapper.getPromotionOfProduct(productID);
    }

    @Override
    public List<ResponseInstallmentPlan> getInstallmentPlanOfProduct(String productID, String variantID) {
        return productMapper.getInstallmentPlanOfProduct(productID, variantID);
    }

    @Override
    public List<ResponseProductColor> getColorOfProduct(String productID) {
        return productMapper.getColorOfProduct(productID);
    }

    @Override
    public List<ResponseProductSpecification> getSpecificationOfProduct(String productID) {
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
    public List<ResponseStorage> getProductStorage(String productID) {
        return productMapper.getProductStorage(productID);
    }

    @Override
    public ResponseProductVariant getProductVariant(String productID, String productColorID, int productStorageID) {
        return productMapper.getProductVariant(productID, productColorID, productStorageID);
    }

    @Override
    public void createOrder(RequestOrder requestOrder) {
        productMapper.insertCustomer(requestOrder.getCustomerID(), requestOrder.getFullName(), requestOrder.getPhone());

        if (requestOrder.getVariantID() == null || requestOrder.getVariantID().trim().isEmpty() || requestOrder.getVariantID().equals("0")) {
            requestOrder.setVariantID(null);
        }

        if (requestOrder.getInstallmentPlanID() != null && requestOrder.getInstallmentPlanID() == 0) {
            requestOrder.setInstallmentPlanID(null);
        }


        if (requestOrder.getOrderDate() == null || requestOrder.getOrderDate().isEmpty()) {
            requestOrder.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        productMapper.insertOrder(requestOrder.getOrderID(), requestOrder.getCustomerID(), requestOrder.getOrderDate(), requestOrder.getTotalAmount(), requestOrder.getOrderType());

        productMapper.insertOrderDetail(requestOrder.getOrderDetailID(), requestOrder.getOrderID(), requestOrder.getProductID(), requestOrder.getVariantID(), requestOrder.getContent(),requestOrder.getPriceAtOrder(), requestOrder.getInstallmentPlanID());

        sendOrderNotificationEmail(requestOrder);
    }

    public void sendOrderNotificationEmail(RequestOrder requestOrder) {
        SimpleMailMessage email = new SimpleMailMessage();
        ResponseProductOrder product = null;

        if (requestOrder.getVariantID() != null) {
            product = productMapper.getProductByOrderID(requestOrder.getOrderID());
        }

        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
        String formattedPrice = formatter.format(requestOrder.getPriceAtOrder());

        if (product != null) {
            email.setTo("hoangtammht@gmail.com");
            email.setSubject("Đơn hàng mới");
            email.setText("Bạn có 1 đơn hàng mới.\n\n" +
                    "Chi tiết đơn hàng:\n" +
                    "Họ và tên: " + requestOrder.getFullName() + "\n" +
                    "Số điện thoại: " + requestOrder.getPhone() + "\n" +
                    "Tên sản phẩm: " + product.getProductName() + "\n" +
                    "Màu sắc: " + product.getColorName() + "\n" +
                    "Dung lượng: " + product.getStorageCapacity() + "\n" +
                    "Ngày đặt hàng: " + requestOrder.getOrderDate() + "\n" +
                    "Nội dung: " + requestOrder.getContent() + "\n" +
                    "Tổng số tiền: " + formattedPrice);
        } else {
            email.setTo("hoangtammht@gmail.com");
            email.setSubject("Đơn hàng mới nhận được");
            email.setText("Bạn đã nhận được một đơn hàng mới mà không có thông tin sản phẩm.\n\n" +
                    "Chi tiết đơn hàng:\n" +
                    "Họ và tên: " + requestOrder.getFullName() + "\n" +
                    "Số điện thoại: " + requestOrder.getPhone() + "\n" +
                    "Ngày đặt hàng: " + requestOrder.getOrderDate() + "\n" +
                    "Nội dung: " + requestOrder.getContent() + "\n" +
                    "Tổng số tiền: " + formattedPrice);
        }

        mailSender.send(email);
    }


    @Override
    public List<ResponseProduct> getProductByName(String productName) {
        return productMapper.getProductByName(productName);
    }

    @Override
    public List<ResponseNews> getNews() {
        return productMapper.getNews();
    }

    @Override
    public List<ResponseProductContent> getProductContent(String productID) {
        return productMapper.getProductContent(productID);
    }

    public void createProduct(RequestProduct requestProduct) {
        productMapper.createProduct(requestProduct);

        double price = requestProduct.getPrice();
        double interestRate = 2.3;
        double downPayment = price * 0.1;

        int[] durations = {3, 6, 9};
        for (int durationMonths : durations) {
            double remainingAmount = price - downPayment;
            double monthlyInterestRate = interestRate / 100 / 12;

            double monthlyPayment = (remainingAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -durationMonths));
            monthlyPayment = Math.ceil(monthlyPayment);
            double totalAmount = downPayment + (monthlyPayment * durationMonths);

            ResponseInstallmentPlan installmentPlan = new ResponseInstallmentPlan();
            installmentPlan.setProductID(requestProduct.getProductID());
            installmentPlan.setVariantID(null);
            installmentPlan.setPlanName("Trả góp " + durationMonths + " tháng");
            installmentPlan.setPrice(price);
            installmentPlan.setDurationMonths(durationMonths);
            installmentPlan.setInterestRate(interestRate);
            installmentPlan.setDownPayment(downPayment);
            installmentPlan.setMonthlyPayment(monthlyPayment);
            installmentPlan.setTotalAmount(totalAmount);
            installmentPlan.setActive(true);

            productMapper.createInstallmentPlan(installmentPlan);
        }
    }

    @Override
    public void insertProductContent(RequestProductContent requestProductContent) {
        productMapper.insertProductContent(requestProductContent);
    }

    @Override
    public void editProductContent(RequestEditProductContent requestEditProductContent) {
        productMapper.editProductContent(requestEditProductContent);
    }

    @Override
    @Transactional
    public void addPriceForProduct(RequestPriceForProduct request) {
        productMapper.addProductColor(request.getProductColorID(), request.getProductID(), request.getColorName(), request.getImagePath());
        productMapper.addProductVariant(request.getVariantID(), request.getProductID(), request.getProductColorID(), request.getProductStorageID(), request.getPrice());
        double price = request.getPrice();
        double interestRate = 2.3;
        double downPayment = price * 0.1;

        int[] durations = {3, 6, 9};
        for (int durationMonths : durations) {
            double remainingAmount = price - downPayment;
            double monthlyInterestRate = interestRate / 100 / 12;

            double monthlyPayment = (remainingAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -durationMonths));
            monthlyPayment = Math.ceil(monthlyPayment);
            double totalAmount = downPayment + (monthlyPayment * durationMonths);

            ResponseInstallmentPlan installmentPlan = new ResponseInstallmentPlan();
            installmentPlan.setProductID(request.getProductID());
            installmentPlan.setVariantID(request.getVariantID());
            installmentPlan.setPlanName("Trả góp " + durationMonths + " tháng");
            installmentPlan.setPrice(price);
            installmentPlan.setDurationMonths(durationMonths);
            installmentPlan.setInterestRate(interestRate);
            installmentPlan.setDownPayment(downPayment);
            installmentPlan.setMonthlyPayment(monthlyPayment);
            installmentPlan.setTotalAmount(totalAmount);
            installmentPlan.setActive(true);

            productMapper.createInstallmentPlan(installmentPlan);
        }
    }

    @Override
    public void addSpecificationForProduct(RequestProductSpecification requestProductSpecification) {
        productMapper.addSpecificationForProduct(requestProductSpecification);
    }

    @Override
    public void deleteSpecificationForProduct(int specificationID) {
        productMapper.deleteSpecificationForProduct(specificationID);
    }

    @Override
    public void addBanner(RequestBanner requestBanner) {
        productMapper.addBanner(requestBanner);
    }

    @Override
    public void deleteBanner(int bannerID) {
        productMapper.deleteBanner(bannerID);
    }

    @Override
    public void addCustomerImage(RequestImageCustomer requestImageCustomer) {
        productMapper.addCustomerImage(requestImageCustomer);
    }

    @Override
    public void deleteCustomerImage(int customerImageID) {
        productMapper.deleteCustomerImage(customerImageID);
    }

    @Override
    public List<ResponseOrderDetail> getListOrder() {
        return productMapper.getListOrder();
    }

    @Override
    public List<ResponseVariantByProductID> getListVariantByProductID(String productID) {
        return productMapper.getListVariantByProductID(productID);
    }

    @Override
    public void deleteVariant(String variantID) {
        productMapper.deleteVariant(variantID);
    }

    @Override
    public List<ResponseProduct> getTopProduct() {
        return productMapper.getTopProduct();
    }

    @Override
    public List<ResponseQuote> getListQuote() {
        return productMapper.getListQuote();
    }

    @Override
    public void insertQuote(RequestQuote requestQuote) {
        productMapper.insertQuote(requestQuote);
    }

    @Override
    public void deleteQuote(int quoteID) {
        productMapper.deleteQuote(quoteID);
    }

    @Override
    public void updateStatusOfProduct(String productID, int status) {
        productMapper.updateStatusOfProduct(productID, status);
    }

}
