package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho Tài khoản tiết kiệm.
 * Thực thi các quy định về mức rút tối đa và số dư tối thiểu.
 */
public class SavingsAccount extends Account {
    private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);
    private static final double MAX_WITHDRAWAL = 1000.0;
    private static final double MIN_BALANCE = 5000.0;

    /**
     * Khởi tạo tài khoản tiết kiệm.
     *
     * @param accountNumber Số tài khoản.
     * @param balance       Số dư ban đầu.
     */
    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        logger.debug("Bắt đầu xử lý nạp tiền tiết kiệm...");
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            Transaction t = new Transaction(
                    Transaction.TYPE_DEPOSIT_SAVINGS, amount, initialBalance, finalBalance);
            addTransaction(t);
            logger.info("Nạp tiền tiết kiệm thành công: +{}", amount);
        } catch (BankException e) {
            logger.error("Lỗi nạp tiền tiết kiệm: {}", e.getMessage(), e);
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            if (amount > MAX_WITHDRAWAL) {
                logger.warn("Từ chối rút tiền: Số tiền {} vượt quá hạn mức {}", amount, MAX_WITHDRAWAL);
                throw new InvalidFundingAmountException(amount);
            }
            if (initialBalance - amount < MIN_BALANCE) {
                logger.warn("Từ chối rút tiền: Vi phạm số dư tối thiểu {}", MIN_BALANCE);
                throw new InsufficientFundsException(amount);
            }

            doWithdrawing(amount);
            double finalBalance = getBalance();

            Transaction t = new Transaction(
                    Transaction.TYPE_WITHDRAW_SAVINGS, amount, initialBalance, finalBalance);
            addTransaction(t);

            logger.info("Rút tiền tiết kiệm thành công. Số dư còn lại: {}", finalBalance);
        } catch (BankException e) {
            logger.error("Giao dịch rút tiền tiết kiệm thất bại: {}", e.getMessage(), e);
        }
    }
}