package br.com.ifood.adminservice.infrastructure.rest.controller.v1.login.dto;

import lombok.Data;

@Data
public class NetworkDTO {

    private String ipAddress;

    private String macAddress;
}
