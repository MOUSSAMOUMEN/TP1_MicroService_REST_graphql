package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAcoountRequestDTO;
import org.sid.bank_account_service.dto.BankAcoountResponseDTO;
import org.sid.bank_account_service.entities.BankAcoount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {


    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;


    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping( "/bankAccounts")
    public List<BankAcoount> bankAcoounts(){
        return bankAccountRepository.findAll();
    }

    //pour consulter un compte

    @GetMapping("/bankAccounts/{id}")
    public BankAcoount bankAcoount(@PathVariable String id){
        return bankAccountRepository.findById(id).
                orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    @PostMapping("/bankAccount")
    public BankAcoountResponseDTO save(@RequestBody BankAcoountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    public BankAcoount update(@PathVariable String id ,@RequestBody BankAcoount bankAcoount){

        BankAcoount account=bankAccountRepository.findById(id).orElseThrow();
        if (bankAcoount.getBalance()!=null) account.setBalance(bankAcoount.getBalance());
        if (bankAcoount.getCreateDAt()!=null) account.setCreateDAt(new Date());
        if (bankAcoount.getType()!=null)account.setType(bankAcoount.getType());
        if (bankAcoount.getCurrency()!=null) account.setCurrency(bankAcoount.getCurrency());
        return bankAccountRepository.save(account);

    }

     @DeleteMapping("/bankAccount/{id}")
     public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
     }
}
