package com.ontop.application.services;

import com.ontop.application.dtos.AccountDTO;
import org.springframework.data.domain.Page;


public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountById(Long id);
    Page<AccountDTO> getAllAccounts(int page, int size);
    void deleteAccount(Long id);
    AccountDTO updateAccount(Long id, AccountDTO accountDTO);
}

