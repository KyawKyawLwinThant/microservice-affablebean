package com.example.payment.controller;

import com.example.payment.service.AccountService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    record RequestAccount(String email,double amount){}
    record ResponseAccount(String email,double amount){}

    record RequestTransferAccount(@JsonProperty("from_email") String fromEmail,
                                  @JsonProperty("to_email")String toEmail,
                                  double amount){}
    record ResponseTransferAccount(String msg){}
    @PostMapping("/transfer")
    public ResponseTransferAccount
        transfer(@RequestBody RequestTransferAccount
                                            account){
        accountService.transfer(account.fromEmail,
                account.toEmail,account.amount);
        return new ResponseTransferAccount(String.format(
                "%s transfer $%s amount to %s successfully",
                account.fromEmail,account.amount,account.toEmail
        ));
    }
    @PostMapping("/withdraw")
    public ResponseAccount widthDraw(
            @RequestBody RequestAccount requestAccount
    ){
        double amount=accountService.withdraw(requestAccount.email
                , requestAccount.amount);
        return new ResponseAccount(requestAccount.email, amount);
    }
    @PostMapping("/deposit")
    public ResponseAccount deposit(@RequestBody RequestAccount requestAccount){
        double amount=accountService.deposit(requestAccount.email,
                requestAccount.amount);
        return new ResponseAccount(requestAccount.email,amount);
    }







}
