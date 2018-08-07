
package com.jhmk.earlywaring.webservice.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.goodwill.hdr.edi.ws package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestWS_QNAME = new QName("http://ws.edi.hdr.goodwill.com/", "testWS1");
    private final static QName _TestWSResponse_QNAME = new QName("http://ws.edi.hdr.goodwill.com/", "testWSResponse");
    private final static QName _QueryDataResponse_QNAME = new QName("http://ws.edi.hdr.goodwill.com/", "queryDataResponse");
    private final static QName _QueryData_QNAME = new QName("http://ws.edi.hdr.goodwill.com/", "queryData1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.goodwill.hdr.edi.ws
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryDataResponse }
     *
     */
    public QueryDataResponse createQueryDataResponse() {
        return new QueryDataResponse();
    }

    /**
     * Create an instance of {@link QueryData }
     *
     */
    public QueryData createQueryData() {
        return new QueryData();
    }

    /**
     * Create an instance of {@link TestWS }
     *
     */
    public TestWS createTestWS() {
        return new TestWS();
    }

    /**
     * Create an instance of {@link TestWSResponse }
     *
     */
    public TestWSResponse createTestWSResponse() {
        return new TestWSResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestWS }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.edi.hdr.goodwill.com/", name = "testWS1")
    public JAXBElement<TestWS> createTestWS(TestWS value) {
        return new JAXBElement<TestWS>(_TestWS_QNAME, TestWS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestWSResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.edi.hdr.goodwill.com/", name = "testWSResponse")
    public JAXBElement<TestWSResponse> createTestWSResponse(TestWSResponse value) {
        return new JAXBElement<TestWSResponse>(_TestWSResponse_QNAME, TestWSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDataResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.edi.hdr.goodwill.com/", name = "queryDataResponse")
    public JAXBElement<QueryDataResponse> createQueryDataResponse(QueryDataResponse value) {
        return new JAXBElement<QueryDataResponse>(_QueryDataResponse_QNAME, QueryDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryData }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.edi.hdr.goodwill.com/", name = "queryData1")
    public JAXBElement<QueryData> createQueryData(QueryData value) {
        return new JAXBElement<QueryData>(_QueryData_QNAME, QueryData.class, null, value);
    }

}
