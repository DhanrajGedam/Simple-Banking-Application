package net.javaguides.banking.controller;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
    private AccountService accountService ;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //add account REST APi
    @PostMapping("")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto ){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id){
        AccountDto accountDto  = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto  = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id,@RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto  = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //get All Accounts REST API

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> account= accountService.getAllAccounts();
        return ResponseEntity.ok(account);
    }

    //deleteAccount REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id){
        accountService.DeleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully");
    }
}
