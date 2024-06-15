package com.app.course.vnPay.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VnPayResponse implements Serializable {
    private String status;
    private String message;
    private Object data;
}
