package id.rsa.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import id.rsa.model.GetProductModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class MainTest {

    String access_token = "";
    String token_type = "";
    List<GetProductModel> listProduct = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException {
        MainTest go = new MainTest();
        go.GoRun();
    }

    public static void GoRun() throws UnsupportedEncodingException {
        MainTest go = new MainTest();
        if (go.getAuth() == 0) {
            if (go.getProduct() == 0) {
                go.createCSV();
            } else {
                System.out.println("Get Product gagal");
            }
        } else {
            System.out.println("Get Auth key gagal");
        }
    }

    int getAuth() throws UnsupportedEncodingException {

        GetTokpedAuth go = new GetTokpedAuth();
        go.GoGetTokpedAuth();
        if (go.getCode() == 0) {
            access_token = go.getAccess_token();
            token_type = go.getToken_type();
            return 0;
        } else {
            return 1;
        }
    }

    int getProduct() {
        GetTokpedProduct getTokpedProduct = new GetTokpedProduct();
        getTokpedProduct.setAccess_token(access_token);
        getTokpedProduct.setToken_type(token_type);
        getTokpedProduct.GoGetTokpedProduct();
        if (getTokpedProduct.getCode() == 0) {
            listProduct = getTokpedProduct.getListProduct();
            return 0;
        } else {
            return 1;
        }
    }

    public void createCSV() {
        try {
            Writer writer = new FileWriter("E:\\log\\test.csv");
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(listProduct);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
