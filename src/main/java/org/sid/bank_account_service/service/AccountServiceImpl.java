package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.BankAcoountRequestDTO;
import org.sid.bank_account_service.dto.BankAcoountResponseDTO;
import org.sid.bank_account_service.entities.BankAcoount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    public AccountServiceImpl() {
    }

    @Override
    public BankAcoountResponseDTO addAccount(BankAcoountRequestDTO bankAcoountDTO) {
        BankAcoount bankAcoount=BankAcoount.builder()
                .id(UUID.randomUUID().toString())
                .CreateDAt(new Date())
                .balance(bankAcoountDTO.getBalance())
                .type(bankAcoountDTO.getType())
                .currency(bankAcoountDTO.getCurrency())
                .build();
       BankAcoount savedBankAccount=bankAccountRepository.save(bankAcoount);
       BankAcoountResponseDTO bankAcoountResponseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return bankAcoountResponseDTO;
    }

    @Override
    public BankAcoountResponseDTO updateAccount(String id,BankAcoountRequestDTO bankAcoountDTO) {
        BankAcoount bankAcoount=BankAcoount.builder()
                .id(id)
                .CreateDAt(new Date())
                .balance(bankAcoountDTO.getBalance())
                .type(bankAcoountDTO.getType())
                .currency(bankAcoountDTO.getCurrency())
                .build();
        BankAcoount savedBankAccount=bankAccountRepository.save(bankAcoount);
        BankAcoountResponseDTO bankAcoountResponseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return bankAcoountResponseDTO;
    }
}
