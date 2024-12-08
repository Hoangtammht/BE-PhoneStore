package com.example.PhoneStore.service.impl;

import com.example.PhoneStore.dao.RoleMapper;
import com.example.PhoneStore.domain.request.UserRole;
import com.example.PhoneStore.service.interf.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public UserRole findRoleByUserName(String userName) {
        log.info("Đang tìm role theo số tài khoản: {}", userName);
        return roleMapper.findRoleByUserName(userName);
    }

}
