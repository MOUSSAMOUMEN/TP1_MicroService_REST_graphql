package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAcoountResponseDTO;
import org.sid.bank_account_service.entities.BankAcoount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BankAcoountResponseDTO fromBankAccount(BankAcoount bankAcoount){
        BankAcoountResponseDTO bankAcoountResponseDTO=new BankAcoountResponseDTO();
        BeanUtils.copyProperties(bankAcoount,bankAcoountResponseDTO);

        return bankAcoountResponseDTO;
    }
}
