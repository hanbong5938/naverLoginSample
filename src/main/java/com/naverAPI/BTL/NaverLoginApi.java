package com.naverAPI.BTL;

import com.github.scribejava.core.builder.api.DefaultApi20;

//scribe java-core 라이브러리에 있는 DefaultApi20에 토큰에 대한 내용
//DefaultApi20 abstract 클래스
public class NaverLoginApi extends DefaultApi20{

    private NaverLoginApi(){
    }

    private static class InstanceHolder{
        private static final NaverLoginApi INSTANCE = new NaverLoginApi();
    }


    public static NaverLoginApi instance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
    }                   

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://nid.naver.com/oauth2.0/authorize";
    }   
}
