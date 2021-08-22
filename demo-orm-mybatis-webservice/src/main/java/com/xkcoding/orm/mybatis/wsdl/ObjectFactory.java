
package com.xkcoding.orm.mybatis.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.xkcoding.orm.mybatis.wsdl package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryWeatherByCityName_QNAME = new QName("http://service.mybatis.orm.xkcoding.com/", "queryWeatherByCityName");
    private final static QName _FindAllWebServiceInfaceResponse_QNAME = new QName("http://service.mybatis.orm.xkcoding.com/", "findAllWebServiceInfaceResponse");
    private final static QName _FindAllWebServiceInface_QNAME = new QName("http://service.mybatis.orm.xkcoding.com/", "findAllWebServiceInface");
    private final static QName _QueryWeatherByCityNameResponse_QNAME = new QName("http://service.mybatis.orm.xkcoding.com/", "queryWeatherByCityNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xkcoding.orm.mybatis.wsdl
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryWeatherByCityNameResponse }
     */
    public QueryWeatherByCityNameResponse createQueryWeatherByCityNameResponse() {
        return new QueryWeatherByCityNameResponse();
    }

    /**
     * Create an instance of {@link FindAllWebServiceInface }
     */
    public FindAllWebServiceInface createFindAllWebServiceInface() {
        return new FindAllWebServiceInface();
    }

    /**
     * Create an instance of {@link FindAllWebServiceInfaceResponse }
     */
    public FindAllWebServiceInfaceResponse createFindAllWebServiceInfaceResponse() {
        return new FindAllWebServiceInfaceResponse();
    }

    /**
     * Create an instance of {@link QueryWeatherByCityName }
     */
    public QueryWeatherByCityName createQueryWeatherByCityName() {
        return new QueryWeatherByCityName();
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWeatherByCityName }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.mybatis.orm.xkcoding.com/", name = "queryWeatherByCityName")
    public JAXBElement<QueryWeatherByCityName> createQueryWeatherByCityName(QueryWeatherByCityName value) {
        return new JAXBElement<QueryWeatherByCityName>(_QueryWeatherByCityName_QNAME, QueryWeatherByCityName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllWebServiceInfaceResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.mybatis.orm.xkcoding.com/", name = "findAllWebServiceInfaceResponse")
    public JAXBElement<FindAllWebServiceInfaceResponse> createFindAllWebServiceInfaceResponse(FindAllWebServiceInfaceResponse value) {
        return new JAXBElement<FindAllWebServiceInfaceResponse>(_FindAllWebServiceInfaceResponse_QNAME, FindAllWebServiceInfaceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllWebServiceInface }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.mybatis.orm.xkcoding.com/", name = "findAllWebServiceInface")
    public JAXBElement<FindAllWebServiceInface> createFindAllWebServiceInface(FindAllWebServiceInface value) {
        return new JAXBElement<FindAllWebServiceInface>(_FindAllWebServiceInface_QNAME, FindAllWebServiceInface.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWeatherByCityNameResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.mybatis.orm.xkcoding.com/", name = "queryWeatherByCityNameResponse")
    public JAXBElement<QueryWeatherByCityNameResponse> createQueryWeatherByCityNameResponse(QueryWeatherByCityNameResponse value) {
        return new JAXBElement<QueryWeatherByCityNameResponse>(_QueryWeatherByCityNameResponse_QNAME, QueryWeatherByCityNameResponse.class, null, value);
    }

}
