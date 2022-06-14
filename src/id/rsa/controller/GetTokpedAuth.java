package id.rsa.controller;

import com.google.gson.Gson;
import id.rsa.model.AuthJsonModel;
import id.rsa.model.HeaderModel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class GetTokpedAuth {

    List<HeaderModel> headerapi = new ArrayList<HeaderModel>();

    private String access_token,token_type;
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

    public List<HeaderModel> getHeaderapi() {
        return headerapi;
    }

    public void setHeaderapi(List<HeaderModel> headerapi) {
        this.headerapi = headerapi;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    

    public void GoGetTokpedAuth() throws UnsupportedEncodingException {
        String responseBody = "";

        List<HeaderModel> listHeader = new ArrayList<>();
        String clientId = "rsa";
        String clientSecret = "rsa1234567898765432";
        String authorization = clientId + ":" + clientSecret;
        byte[] encodeByte = authorization.getBytes();
        String token = "Basic " + new String(Base64.getEncoder().encode(encodeByte), "UTF-8");
        HitAPI hit = new HitAPI();
        hit.setStrUrl("https://accounts.tokopedia.com/token?grant_type=client_credentials");
//        hit.setToken(token);
        setHeader("Authorization", token);
        setHeader("Content-Length", "0");
        hit.setHeader(headerapi);
        hit.setReqBody("");
        hit.hitAPI();

        if (hit.getErrorCode() == 0) {
            responseBody = hit.getResponseBody();
            Gson gson = new Gson();
            AuthJsonModel obj = (AuthJsonModel) gson.fromJson(responseBody, AuthJsonModel.class);

            access_token = obj.getAccess_token();
            token_type = obj.getToken_type();
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
