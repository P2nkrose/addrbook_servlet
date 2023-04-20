package com.example.addrbook_servlet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddrbookDTO {
    private int ab_id;
    private String ab_name;
    private String ab_email;
    private String ab_tel;
    private String ab_birth;
    private String ab_comdept;
    private String ab_memo;
}
