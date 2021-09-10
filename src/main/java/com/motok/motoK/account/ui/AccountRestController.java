package com.motok.motoK.account.ui;

import com.motok.motoK.account.dto.AccountRequest;
import com.motok.motoK.account.dto.AccountResponse;
import com.motok.motoK.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<AccountResponse>> getAccount(@RequestParam String date) {
        List<AccountResponse> accounts = accountService.getAccounts(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse account = accountService.saveAccount(accountRequest);
        return ResponseEntity.created(URI.create("/view/account?date=" + accountRequest.getDate().toString())).body(account);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        AccountResponse account = accountService.updateAccount(id, accountRequest);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountResponse> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/amount")
    public ResponseEntity<Map<String, BigInteger>> getTotalAmount(@RequestParam String date) {
        BigInteger totalAmount = accountService.getTotalAmount(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
        Map<String, BigInteger> map = new HashMap<>();
        map.put("totalAmount", totalAmount);
        return ResponseEntity.ok(map);
    }
}
