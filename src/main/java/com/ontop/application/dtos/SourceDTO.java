package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceDTO {
    private String type;
    private SourceInformationDTO sourceInformation;
    private AccountRequestDTO account;
}
