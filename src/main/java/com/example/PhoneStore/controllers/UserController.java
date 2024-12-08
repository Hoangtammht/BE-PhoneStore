package com.example.PhoneStore.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.PhoneStore.domain.request.LoginUser;
import com.example.PhoneStore.domain.request.RequestOrder;
import com.example.PhoneStore.domain.request.UserRole;
import com.example.PhoneStore.domain.response.*;
import com.example.PhoneStore.exception.ApiRequestException;
import com.example.PhoneStore.service.interf.ProductService;
import com.example.PhoneStore.service.interf.RoleService;
import com.example.PhoneStore.service.interf.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    @Value("${SECRET_KEY}")
    private String secret;

    @PostMapping("/loginUser")
    public void loginUser(@RequestBody LoginUser loginUser,
                          HttpServletResponse response,
                          HttpServletRequest request) throws IOException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String name = authentication.getName();
            UserRole userRole = roleService.findRoleByUserName(name);
            String access_token = JWT.create()
                    .withSubject(name)
//                    .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", userRole.getRoleName())
                    .sign(algorithm);

            ResponseUser responseUser = userService.findResponseUserByUserName(name);
            Map<String, Object> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("User", responseUser);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            log.info("Người dùng {} đăng nhập thành công", name);
        } catch (ApiRequestException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        } catch (BadCredentialsException e) {
            log.error("Lỗi khi đăng nhập: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", "Tài khoản hoặc mật khẩu không chính xác");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        } catch (Exception e) {
            log.error("Lỗi không mong đợi xảy ra: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }

}
