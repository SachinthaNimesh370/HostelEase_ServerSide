package com.uoj.HostelEase.utill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private boolean success;
    private Object object;
    private Map<String,String>  role;
}

