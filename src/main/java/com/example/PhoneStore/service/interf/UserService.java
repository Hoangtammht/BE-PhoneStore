package com.example.PhoneStore.service.interf;

import com.example.PhoneStore.domain.response.ResponseUser;

public interface UserService {

    ResponseUser findResponseUserByUserName(String userName);


}
