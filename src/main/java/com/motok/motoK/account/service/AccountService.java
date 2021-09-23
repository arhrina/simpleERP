package com.motok.motoK.account.service;

import com.motok.motoK.account.domain.entity.Account;
import com.motok.motoK.account.domain.enums.AccountCode;
import com.motok.motoK.account.dto.AccountRequest;
import com.motok.motoK.account.dto.AccountResponse;
import com.motok.motoK.domain.vo.DailyAccountVO;
import com.motok.motoK.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public AccountResponse saveAccount(AccountRequest accountRequest) {
        Account savedAccount = accountRepository.save(accountRequest.toAccount());
        return AccountResponse.of(savedAccount);
    }

    public AccountResponse updateAccount(Long id, AccountRequest accountRequest) {
        Account updateAccount = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        updateAccount.updateTo(accountRequest.getContent(),
                accountRequest.getCode(),
                accountRequest.getAmount(),
                accountRequest.getRemarks());
        return AccountResponse.of(updateAccount);
    }

    public List<AccountResponse> getAccounts(LocalDate date) {
        List<Account> accounts = accountRepository.findAllByDateOrderByIdDesc(date);
        return accounts.stream()
                .map(AccountResponse::of)
                .collect(Collectors.toList());
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public BigInteger getTotalAmount(LocalDate date) {
        return accountRepository.findAllByDate(date).stream()
                .map(account -> account.getAmount().multiply(BigInteger.valueOf(account.getCode().isPlus() ? 1 : -1)))
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
