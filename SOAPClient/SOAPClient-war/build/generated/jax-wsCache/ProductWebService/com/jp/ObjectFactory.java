
package com.jp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jp package. 
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

    private final static QName _FindShirts_QNAME = new QName("http://webservice/", "findShirts");
    private final static QName _FindShirtsResponse_QNAME = new QName("http://webservice/", "findShirtsResponse");
    private final static QName _FindShoes_QNAME = new QName("http://webservice/", "findShoes");
    private final static QName _FindShoesResponse_QNAME = new QName("http://webservice/", "findShoesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindShirts }
     * 
     */
    public FindShirts createFindShirts() {
        return new FindShirts();
    }

    /**
     * Create an instance of {@link FindShirtsResponse }
     * 
     */
    public FindShirtsResponse createFindShirtsResponse() {
        return new FindShirtsResponse();
    }

    /**
     * Create an instance of {@link FindShoes }
     * 
     */
    public FindShoes createFindShoes() {
        return new FindShoes();
    }

    /**
     * Create an instance of {@link FindShoesResponse }
     * 
     */
    public FindShoesResponse createFindShoesResponse() {
        return new FindShoesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindShirts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "findShirts")
    public JAXBElement<FindShirts> createFindShirts(FindShirts value) {
        return new JAXBElement<FindShirts>(_FindShirts_QNAME, FindShirts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindShirtsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "findShirtsResponse")
    public JAXBElement<FindShirtsResponse> createFindShirtsResponse(FindShirtsResponse value) {
        return new JAXBElement<FindShirtsResponse>(_FindShirtsResponse_QNAME, FindShirtsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindShoes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "findShoes")
    public JAXBElement<FindShoes> createFindShoes(FindShoes value) {
        return new JAXBElement<FindShoes>(_FindShoes_QNAME, FindShoes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindShoesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "findShoesResponse")
    public JAXBElement<FindShoesResponse> createFindShoesResponse(FindShoesResponse value) {
        return new JAXBElement<FindShoesResponse>(_FindShoesResponse_QNAME, FindShoesResponse.class, null, value);
    }

}
