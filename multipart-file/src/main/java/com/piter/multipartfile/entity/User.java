package com.piter.multipartfile.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String name;

    private Date birthday;

    private String address;

    private MultipartFile file;

}