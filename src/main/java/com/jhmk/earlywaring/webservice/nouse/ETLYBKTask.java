//package com.jhmk.earlywaring.webservice;
//
//import com.alibaba.fastjson.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//
///**
// * Created by wangfei on 2018/3/8.
// */
//public class ETLYBKTask {
//    private static final Logger logger = LoggerFactory.getLogger(ETLYBKTask.class);
//    public static void main(String[] args) {
//        String type = args[0];
//        String patient_id = "";
//        String visit_id = "";
//        String start = "";
//        String end = "";
//        if(args.length==5){
//            patient_id = args[1];
//            visit_id = args[2];
//            start = args[3];
//            end = args[4];
//        }
//        logger.info(type+"|"+patient_id+"|"+visit_id+"|"+start+"|"+end);
//        OutputStream os = null;
//        try{
//            //第一步：创建服务地址，不是WSDL地址
//            URL url = new URL(BaseUtils.getInstance().get("yangbenku.URL"));
//            //第二步：打开一个通向服务地址的连接
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            //第三步：设置参数
//            //3.1发送方式设置：POST必须大写
//            connection.setRequestMethod("POST");
//            //3.2设置数据格式：content-type
//            connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
//            if(type.equals("out")){
//                connection.setRequestProperty("SOAPAction", "\"http://tempuri.org/GetSampleOutstock\"");
//            }else{
//                connection.setRequestProperty("SOAPAction", "\"http://tempuri.org/GetSampleInstock\"");
//            }
//            //3.3设置输入输出，因为默认新创建的connection没有读写权限，
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            //第四步：组织SOAP数据，发送请求
//            String soapXML = WebServeiceUtils.getXML(type,patient_id,visit_id,start,end);
//            os = connection.getOutputStream();
//            os.write(soapXML.getBytes());
//            //第五步：接收服务端响应，打印
//            int responseCode = connection.getResponseCode();
//            if(200 == responseCode){//表示服务端响应成功
//                InputStream is = connection.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is);
//                BufferedReader br = new BufferedReader(isr);
//                StringBuilder sb = new StringBuilder();
//                String temp = null;
//                while(null != (temp = br.readLine())){
//                    sb.append(temp);
//                }
//                logger.info(sb.toString());
//                if(type.equals("out")){
//                    XmlUtils.parseXML("SampleOuttock",sb.toString(),type);
//                }else{
//                    XmlUtils.parseXML("SampleInstock",sb.toString(),type);
//                }
//                is.close();
//                isr.close();
//                br.close();
//            }else{
//                logger.error(responseCode);
//            }
//            os.close();
//            //开始跑html
//            logger.info("开始入样本库html...");
//            List<String> lines = MongoUtils.getAllID("yangbenku");
//            for(String line:lines){
//                JSONObject ybk = MongoUtils.findJsonResult("yangbenku",line);
//                XmlUtils.yangbenku(ybk,"htmls");
//            }
//            logger.info("样本库html结束.");
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            try{
//                if(os!=null){
//                    os.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
