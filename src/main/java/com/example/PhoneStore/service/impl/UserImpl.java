package com.example.PhoneStore.service.impl;

import com.example.PhoneStore.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class UserImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userMapper.findUserByUserName(username);
//        if (user == null) {
//            log.error("Không tìm thấy người dùng trong cơ sở dữ liệu: {}", username);
//            throw new UsernameNotFoundException("User not found in the database");
//        } else {
//            log.info("Đã tìm thấy người dùng trong cơ sở dữ liệu: {}", username);
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        UserRole userRole = roleService.findRoleByUserName(user.getUserName());
//        authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        return null;
    }


}
