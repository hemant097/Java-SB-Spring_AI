package com.example.project.springai.entity;

import lombok.Data;

@Data
public class ApiResponse {

    private String name;
    private Weather[] weather;
    private Main main;

}
