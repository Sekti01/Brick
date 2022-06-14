/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.rsa.model;

import java.util.List;

/**
 *
 * @author User
 */
public class GetTokpedProductData {

    private List<GetTokpedProductDataProduct> products;
    private GetTokpedProductDataShop shop;

    public List<GetTokpedProductDataProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GetTokpedProductDataProduct> products) {
        this.products = products;
    }

    public GetTokpedProductDataShop getShop() {
        return shop;
    }

    public void setShop(GetTokpedProductDataShop shop) {
        this.shop = shop;
    }
    

}
