package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.BankAcoountRequestDTO;
import org.sid.bank_account_service.dto.BankAcoountResponseDTO;

public interface AccountService {
     BankAcoountResponseDTO addAccount(BankAcoountRequestDTO bankAcoountDTO);

    BankAcoountResponseDTO updateAccount(String id, BankAcoountRequestDTO bankAcoountDTO);
}
