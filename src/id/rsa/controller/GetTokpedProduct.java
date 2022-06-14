/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.rsa.controller;

import com.google.gson.Gson;
import id.rsa.model.AuthJsonModel;
import id.rsa.model.GetProductModel;
import id.rsa.model.GetTokpedProductModel;
import id.rsa.model.HeaderModel;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author User
 */
public class GetTokpedProduct {

    List<HeaderModel> headerapi = new ArrayList<HeaderModel>();
    List<GetProductModel> listProduct = new ArrayList<GetProductModel>();

    private String access_token, token_type;
    private int code = 0;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public List<HeaderModel> getHeaderapi() {
        return headerapi;
    }

    public void setHeaderapi(List<HeaderModel> headerapi) {
        this.headerapi = headerapi;
    }

    public List<GetProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<GetProductModel> listProduct) {
        this.listProduct = listProduct;
    }

    public void GoGetTokpedProduct() {
        String responseBody = "";

        String token = token_type + " " + access_token;
        HitAPI hit = new HitAPI();
        hit.setStrUrl("https://fs.tokopedia.net/inventory/v1/fs/13004/product/list?shop_id=479573&rows=100&start=1&order_by=23");
//        hit.setToken(token);
        setHeader("Authorization", token);
        hit.setHeader(headerapi);
        hit.setReqBody("");
        hit.hitAPI();

        if (hit.getErrorCode() == 0) {
            responseBody = hit.getResponseBody();
            Gson gson = new Gson();
            GetTokpedProductModel obj = (GetTokpedProductModel) gson.fromJson(responseBody, GetTokpedProductModel.class);

            for (int i = 0; i < obj.getData().getProducts().size(); i++) {
                GetProductModel product = new GetProductModel();
                product.setProductName(obj.getData().getProducts().get(i).getName());
                product.setDescription(obj.getData().getProducts().get(i).getDesc());
                product.setImagerLink(obj.getData().getProducts().get(i).getImage_url());
                product.setPrice(obj.getData().getProducts().get(i).getPrice());
                product.setRating(obj.getData().getProducts().get(i).getRating());
                product.setStoreName(obj.getData().getShop().getName());
                listProduct.add(product);
            }

            code = 0;
        } else {
            code = 1;
        }
    }

    public void setHeader(String type, String value) {
        HeaderModel header = new HeaderModel();
        header.setType(type);
        header.setValue(value);
        headerapi.add(header);
    }

}
