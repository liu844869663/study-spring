package com.fullmoon.study.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Persion implements Serializable {
    private String name;
    private int age;
    private User user;
}
