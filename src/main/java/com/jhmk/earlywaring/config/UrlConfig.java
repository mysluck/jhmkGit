package com.jhmk.earlywaring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(locations = {"classpath:config/myProps.yml"},prefix = "myProps")
@ConfigurationProperties(prefix = "url")
public class UrlConfig {
    private String ywdurl;
    private String cdssurl;

    public String getYwdurl() {
        return ywdurl;
    }

    public void setYwdurl(String ywdurl) {
        this.ywdurl = ywdurl;
    }

    public String getCdssurl() {
        return cdssurl;
    }

    public void setCdssurl(String cdssurl) {
        this.cdssurl = cdssurl;
    }
}
