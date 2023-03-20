package com.ontop.infraestructure.services;

import com.ontop.application.dtos.AccountDTO;
import com.ontop.application.mappers.AccountMapper;
import com.ontop.application.services.AccountService;
import com.ontop.domain.entities.Account;
import com.ontop.domain.repositories.AccountRepository;
import com.ontop.domain.repositories.UserRepository;
import com.ontop.application.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository  userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Page<AccountDTO> getAllAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> accounts = accountRepository.findAll(pageable);
        return accounts.map(accountMapper::toDTO);
    }

    @Override
    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
        return accountMapper.toDTO(account);
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account;
        account = accountMapper.toEntity(accountDTO);
        account.setUserId(accountDTO.getUserId());
        account = accountRepository.save(account);
        return accountMapper.toDTO(account);
    }

    @Override
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setRoutingNumber(accountDTO.getRoutingNumber());
        account.setFullName(accountDTO.getFullName());
        account.setBankName(accountDTO.getBankName());
        account.setCurrency(accountDTO.getCurrency());
        account.setDni(accountDTO.getDni());
        account = accountRepository.save(account);
        return accountMapper.toDTO(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        accountRepository.delete(account);
    }

}
