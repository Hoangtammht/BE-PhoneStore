package com.example.PhoneStore.service.impl;

import com.example.PhoneStore.dao.UserMapper;
import com.example.PhoneStore.domain.request.UserRole;
import com.example.PhoneStore.domain.response.ResponseUser;
import com.example.PhoneStore.service.interf.RoleService;
import com.example.PhoneStore.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseUser user = userMapper.findResponseUserByUserName(username);
        if (user == null) {
            log.error("Không tìm thấy người dùng trong cơ sở dữ liệu: {}", username);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("Đã tìm thấy người dùng trong cơ sở dữ liệu: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        UserRole userRole = roleService.findRoleByUserName(user.getUserName());
        authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    @Override
    public ResponseUser findResponseUserByUserName(String userName) {
        return userMapper.findResponseUserByUserName(userName);
    }
}
