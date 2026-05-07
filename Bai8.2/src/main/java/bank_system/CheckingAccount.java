package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho Tài khoản vãng lai.
 */
public class CheckingAccount extends Account {
    private static final Logger logger = LoggerFactory.getLogger(CheckingAccount.class);

    /**
     * Khởi tạo tài khoản vãng lai.
     *
     * @param accountNumber Số tài khoản.
     * @param balance       Số dư ban đầu.
     */
    public CheckingAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            Transaction t = new Transaction(
                    Transaction.TYPE_DEPOSIT_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(t);
            logger.info("Nạp tiền vãng lai thành công. Số tiền: {}", amount);
        } catch (BankException e) {
            logger.error("Lỗi giao dịch nạp tiền vãng lai: {}", e.getMessage(), e);
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            doWithdrawing(amount);
            double finalBalance = getBalance();
            Transaction t = new Transaction(
                    Transaction.TYPE_WITHDRAW_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(t);
            logger.info("Rút tiền vãng lai thành công. Số tiền: {}", amount);
        } catch (BankException e) {
            logger.error("Lỗi giao dịch rút tiền vãng lai: {}", e.getMessage(), e);
        }
    }
}