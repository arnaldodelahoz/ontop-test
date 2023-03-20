package com.ontop.application.mappers;

import com.ontop.application.dtos.*;
import com.ontop.domain.entities.Account;
import com.ontop.domain.entities.User;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {

    public AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setFullName(account.getFullName());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setRoutingNumber(account.getRoutingNumber());
        dto.setBankName(account.getBankName());
        dto.setDni(account.getDni());
        dto.setUserId(account.getUser().getId());
        dto.setCurrency(account.getCurrency());
        return dto;
    }

    public Account toEntity(AccountDTO dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setFullName(dto.getFullName());
        account.setAccountNumber(dto.getAccountNumber());
        account.setRoutingNumber(dto.getRoutingNumber());
        account.setBankName(dto.getBankName());
        account.setCurrency(dto.getCurrency());
        account.setDni(dto.getDni());
        User user = new User();
        user.setId(dto.getUserId());
        account.setUser(user);
        return account;
    }
    public SourceDTO toSourceDTO(Account entity){
        SourceDTO dto = new SourceDTO();
        dto.setAccount(toAccountRequestDTO(entity));
        dto.setType("COMPANY");
        dto.setSourceInformation(new SourceInformationDTO(entity.getFullName()));
        return dto;
    }
    public DestinationDTO toDestinationDTO(Account entity){
        DestinationDTO dto = new DestinationDTO();
        dto.setAccount(toAccountRequestDTO(entity));
        dto.setName(entity.getFullName());
        return dto;
    }

    public AccountRequestDTO toAccountRequestDTO(Account entity){
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setAccountNumber(entity.getAccountNumber());
        accountRequestDTO.setCurrency(entity.getCurrency());
        accountRequestDTO.setRoutingNumber(entity.getRoutingNumber());
        return accountRequestDTO;
    }
}
