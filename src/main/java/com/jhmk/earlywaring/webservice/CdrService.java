package com.jhmk.earlywaring.webservice;

import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.webservice.service.HdrQueryDataService;
import com.jhmk.earlywaring.webservice.service.HdrQueryDataWsImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/19 11:16
 */

@Service
public class CdrService {
    public static String getXml(Map<String, String> params, List<Map<String, String>> conditions) {
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                "<MSG>" +
//                "<HOSPITAL_OID>" + BaseConstants.OID + "</HOSPITAL_OID>" +
//                "<PATIENT_ID>" + params.get("patient_id") + "</PATIENT_ID>" +
//                "<VISIT_ID>" + params.get("visit_id") + "</VISIT_ID>" +
//                "<WS_CODE>" + params.get("ws_code") + "</WS_CODE>" +
//                "<CONDITION>" +
//                "<ELEM NAME=\"" + params.get("elemName") + "\" VALUE=\"" + params.get("value") + "\"  OPERATOR=\"" + params.get("operator") + "\"></ELEM>" +
//                "</CONDITION>" +
//                "</MSG>";

        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<MSG>")
                .append("<HOSPITAL_OID>")
                .append(BaseConstants.OID)
                .append("</HOSPITAL_OID>")
                .append("<PATIENT_ID>")
                .append(params.get("patient_id"))
                .append("</PATIENT_ID>")
                .append("<VISIT_ID>")
                .append(params.get("visit_id"))
                .append("</VISIT_ID>")
                .append("<WS_CODE>")
                .append(params.get("ws_code"))
                .append("</WS_CODE>");
        if (Objects.nonNull(conditions)) {
            sb.append("<CONDITION>");
            for (Map<String, String> map : conditions) {
                sb.append("<ELEM NAME=\"" + map.get("elemName") + "\" VALUE=\"" + map.get("value") + "\"  OPERATOR=\"" + map.get("operator") + "\"></ELEM>");
            }
            sb.append("</CONDITION>");
        }

        sb.append("</MSG>");
        return sb.toString();
    }

    /**
     * 调用数据中心接口 并返回数据
     *
     * @return
     */
    public String getDataByCDR(Map<String, String> params, List<Map<String, String>> coditions) {
        String xml = getXml(params, coditions);
        HdrQueryDataService hdrQueryDataService = new HdrQueryDataService();
        HdrQueryDataWsImpl hdrQueryDataWsImplPort = hdrQueryDataService.getHdrQueryDataWsImplPort();
        String data = hdrQueryDataWsImplPort.queryData(xml);
        return data;
    }


    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("oid", BaseConstants.OID);
        params.put("patient_id", "000571764100");
//        params.put("visit_id", "1");
        //检验数据
//        params.put("ws_code", "JHHDRWS006B");
        params.put("ws_code", "JHHDRWS004A");
//        params.put("elemName", "REPORT_TIME");
//        params.put("value", "2017-08-15 06:40:26");
//        params.put("operator", "=");
        String xml = getXml(params, null);

        HdrQueryDataService hdrQueryDataService = new HdrQueryDataService();
        HdrQueryDataWsImpl hdrQueryDataWsImplPort = hdrQueryDataService.getHdrQueryDataWsImplPort();
        String data = hdrQueryDataWsImplPort.queryData(xml);
        System.out.println(data);
        AnalysisXmlService analysisXmlService = new AnalysisXmlService();
//        analysisXmlService.analysisXml2Binganshouye(data);
    }
}
