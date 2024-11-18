package com.example.PhoneStore.exception;

import com.example.PhoneStore.service.impl.EmailService;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

//    @Autowired
//    private EmailService emailService;

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException();
        apiException.setTitle("Internal Server Error");
        apiException.setMessage(e.getMessage());
        apiException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ZoneId vietnamZoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime vietnamTime = ZonedDateTime.now(vietnamZoneId);
        apiException.setTimestamp(vietnamTime);

        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity<ApiException> handleInvalidDefinitionException(InvalidDefinitionException e) {
        ApiException apiException = new ApiException();
        apiException.setTitle("Internal Server Error");
        apiException.setMessage("Invalid JSON Definition");
        apiException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ZoneId vietnamZoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime vietnamTime = ZonedDateTime.now(vietnamZoneId);
        apiException.setTimestamp(vietnamTime);

        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
//        String errorDetails = "Hệ thống gặp lỗi nghiêm trọng: " + ex.getMessage() + "\n";
//        errorDetails += "Nguyên nhân: " + (ex.getCause() != null ? ex.getCause() : "Không rõ nguyên nhân");
//        emailService.sendErrorEmail("Lỗi hệ thống nghiêm trọng", errorDetails);
//        return new ResponseEntity<>("Lỗi hệ thống đã xảy ra, chúng tôi đang xử lý.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<?> handleDatabaseError(SQLException ex, WebRequest request) {
//        String errorDetails = "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage();
//        emailService.sendErrorEmail("Lỗi cơ sở dữ liệu", errorDetails);
//        return new ResponseEntity<>("Lỗi cơ sở dữ liệu đã xảy ra.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<?> handleNullPointerError(NullPointerException ex, WebRequest request) {
//        String errorDetails = "Null pointer exception xảy ra: " + ex.getMessage();
//        emailService.sendErrorEmail("Null Pointer Exception", errorDetails);
//        return new ResponseEntity<>("Một lỗi hệ thống đã xảy ra.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
