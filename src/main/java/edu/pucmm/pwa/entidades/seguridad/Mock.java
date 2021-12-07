package edu.pucmm.pwa.entidades.seguridad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMock;

    private String ownerName;
    private String mockName;
    private String accessMethod;
    private String header;
    private String headerKey;
    private String contentType;
    private String responseBody;
    private String description;
    private String responseCode;
    private int respondeDelay;
    private int expireTime;
    private String token;

    public Mock() {

    }

    public Mock(int idMock, String ownerName, String mockName, String accessMethod,
                String header, String headerKey, String contentType, String responseBody,
                String description, String responseCode, int respondeDelay, int expireTime, String token
    ) {
        this.idMock = idMock;
        this.ownerName = ownerName;
        this.mockName = mockName;
        this.accessMethod = accessMethod;
        this.header = header;
        this.headerKey = headerKey;
        this.contentType = contentType;
        this.responseBody = responseBody;
        this.description = description;
        this.responseCode = responseCode;
        this.respondeDelay = respondeDelay;
        this.expireTime = expireTime;
        this.token = token;
    }

    public int getIdMock() {
        return idMock;
    }

    public void setIdMock(int idMock) {
        this.idMock = idMock;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMockName() {
        return mockName;
    }

    public void setMockName(String mockName) {
        this.mockName = mockName;
    }

    public String getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public int getRespondeDelay() {
        return respondeDelay;
    }

    public void setRespondeDelay(int respondeDelay) {
        this.respondeDelay = respondeDelay;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

