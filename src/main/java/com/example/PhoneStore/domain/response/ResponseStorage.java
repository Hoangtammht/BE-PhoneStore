package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStorage {

    private int productStorageID;
    private String storageCapacity;

}
