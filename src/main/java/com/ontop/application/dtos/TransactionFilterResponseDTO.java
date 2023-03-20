package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionFilterResponseDTO {

    List<TransactionResponseDTO> data;
    private Integer totalPages;
    private Integer page;
    private Integer size;
}
