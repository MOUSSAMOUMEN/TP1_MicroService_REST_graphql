package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAcoountRequestDTO;
import org.sid.bank_account_service.dto.BankAcoountResponseDTO;
import org.sid.bank_account_service.entities.BankAcoount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAcoount> accountsList(){
        return bankAccountRepository.findAll();

    }

    @QueryMapping
    public BankAcoount bankAcoountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found ",id)));

    }

    @MutationMapping
    public BankAcoountResponseDTO addAccount(@Argument BankAcoountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAcoountResponseDTO updateAccount(@Argument String id,@Argument BankAcoountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
    }

    @QueryMapping
    public List<Customer> customers(){
      return customerRepository.findAll();
    }
}



/*
record BankAccountDTO(Double balance,String type,String currency){

}*/
