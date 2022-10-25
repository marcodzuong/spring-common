package com.marco.common.swagger;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MarcoDuong
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {
    /**
     * API base path
     */
    private String apiBasePackage;
    /**
     * enable login authentication
     */
    private boolean enableSecurity;
    /**
     * document title
     */
    private String title;
    /**
     * document description
     */
    private String description;
    /**
     * document version
     */
    private String version;
    /**
     * Document contact name
     */
    private String contactName;
    /**
     * Documentation Contact URL
     */
    private String contactUrl;
    /**
     * Document Contact Email
     */
    private String contactEmail;
    /**
     * Document terms Url
     */
    private String termsOfServiceUrl;
}

