package quanlythuvien.service;

import quanlythuvien.exception.AccountException;
import quanlythuvien.model.Account;

import java.util.List;

public interface AccountService {
    Account login(String username) throws AccountException;
    List<Account> getALlAccounts();
    Account getById(int id);
    Account createAccount(Account account);

    Account findAccountByUsername(String username);

    void deletUser(int id);
}
