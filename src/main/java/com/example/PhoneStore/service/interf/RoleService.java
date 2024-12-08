package com.example.PhoneStore.service.interf;

import com.example.PhoneStore.domain.request.UserRole;

public interface RoleService {

    UserRole findRoleByUserName(String userName);

}
