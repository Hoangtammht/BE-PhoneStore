package com.example.PhoneStore.dao;

import com.example.PhoneStore.domain.request.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    UserRole findRoleByUserName(String userName);


}
