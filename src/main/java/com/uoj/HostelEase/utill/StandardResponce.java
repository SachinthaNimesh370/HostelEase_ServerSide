package com.uoj.HostelEase.utill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponce {
    private  int code;
    private String message;
    private Object data;
    private Map<String,String> role;
}
