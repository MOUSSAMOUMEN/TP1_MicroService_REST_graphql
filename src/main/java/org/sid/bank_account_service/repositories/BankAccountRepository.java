package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.BankAcoount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.List;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAcoount,String> {

    @RestResource(path = "/byType")
    List<BankAcoount> findByType( @Param("t") AccountType type);
}
