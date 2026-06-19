package org.example.lab2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService {
    private final AccountRepository repository = new AccountRepository();
    private static final Logger logger =
            LoggerFactory.getLogger(BankService.class);
    public void transfer(Account from, Account to, double amount)
            throws TransactionException {

        try {

            if (from.getBalance() < amount) {
                throw new InsufficientFundsException(
                        "Insufficient funds for this transaction.",
                        "TRX001"
                );
            }

            if (amount > 10000) {
                throw new FraudDetectedException(
                        "Transaction flagged as suspicious.",
                        "TRX002"
                );
            }

            from.withdraw(amount);
            to.deposit(amount);

            logger.info("Transfer successful: {} -> {} amount {}",
                    from.getId(), to.getId(), amount);

        } catch (TransactionException e) {
            logger.error("Transaction failed", e);
            throw e;
        }
    }
    public double getAccountBalance(String accountId) throws DataAccessException {
        return repository.getBalance(accountId);
    }
}
