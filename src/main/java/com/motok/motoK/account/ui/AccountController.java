package com.motok.motoK.account.ui;

import com.motok.motoK.account.dto.AccountRequest;
import com.motok.motoK.account.dto.AccountResponse;
import com.motok.motoK.account.service.AccountService;
import com.motok.motoK.domain.vo.DailyAccountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<AccountResponse>> getAccount(@RequestParam String date) {
        List<AccountResponse> accounts = accountService.getAccounts(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse account = accountService.saveAccount(accountRequest);
        return ResponseEntity.created(URI.create("/account")).body(account);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        AccountResponse account = accountService.updateAccount(id, accountRequest);
        return ResponseEntity.ok(account);
    }
}
