package com.example.PdfGeneration.model;

import lombok.Data;

import java.util.List;


@Data
public class PdfInfo {


    private String seller;

    private String sellerGstin;

    private String sellerAddress;

    private String buyer;

    private String buyerGstin;

    private String buyerAddress;

    private List<ItemInfo> items;

}
