package com.jhmk.earlywaring.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * HttpHeaders工具类
 */
public class HttpHeadersUtils {

    public static HttpHeaders getHttpHeadersByCharsAndMed(List<Charset> acceptableCharsets,List<MediaType> acceptableMediaTypes){
        HttpHeaders httpHeaders = new HttpHeaders() ;
        httpHeaders.setAcceptCharset(acceptableCharsets);
        httpHeaders.setAccept(acceptableMediaTypes);
        return httpHeaders;
    }

    public static HttpHeaders getHttpHeadersByContentType(MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return headers;
    }


    public static HttpHeaders getHeader() {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        return headers;
    }
}
