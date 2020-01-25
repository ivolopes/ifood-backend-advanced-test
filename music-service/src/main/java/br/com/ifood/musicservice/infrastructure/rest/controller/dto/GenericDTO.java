package br.com.ifood.musicservice.infrastructure.rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class GenericDTO {
    @JsonIgnore
    private String id;
    private String name;
}
