package com.jew.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken {

    private int SEQ;
    private String USER_ID;
    private String TOKEN;
    private String EXPIRE_DT;
    private String REG_DT;
}
