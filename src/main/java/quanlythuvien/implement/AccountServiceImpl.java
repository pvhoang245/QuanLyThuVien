package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.exception.AccountException;
import quanlythuvien.model.Account;
import quanlythuvien.repository.AccountRepository;
import quanlythuvien.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account login(String username) throws AccountException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account != null) {
            return account;
        } else {
            throw new AccountException("Not found");
        }
    }

    @Override
    public List<Account> getALlAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {
        return accountRepository.findById(id).get();
    }
}
