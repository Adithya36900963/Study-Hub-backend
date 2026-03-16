package com.example.StudyHub.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class ResponseModel<T> {
    
    private int status;
    private String message;
    private T data;

    public ResponseModel(int status,String message,T data)
    {
        this.status=status;
        this.message=message;
        this.data=data;
    }
    public ResponseEntity<Map<String,Object>> res()
    {
        Map<String,Object> body=new HashMap<>();
        body.put("message",message);
        body.put("data",data);

        return ResponseEntity.status(status)
                              .body(body);
    }
}