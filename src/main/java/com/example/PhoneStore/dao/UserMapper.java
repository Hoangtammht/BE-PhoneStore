package com.example.PhoneStore.dao;

import com.example.PhoneStore.domain.response.ResponseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    ResponseUser findResponseUserByUserName(String userName);


}
