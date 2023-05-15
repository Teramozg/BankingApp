package com.example.demo.DTO;

import lombok.Data;

@Data
public class TransferCashFromAccount {
    private int idFrom;
    private int idTo;
    private double cash;
}
