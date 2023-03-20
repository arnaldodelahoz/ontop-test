package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceInformationDTO {
    private String name;
    public SourceInformationDTO(){}
    public SourceInformationDTO(String name){
        this.name = name;
    }
}
