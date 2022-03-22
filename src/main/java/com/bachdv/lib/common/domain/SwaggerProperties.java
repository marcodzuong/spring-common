package com.bachdv.lib.common.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BachDV
 * Date : 22/03/2022
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
}
