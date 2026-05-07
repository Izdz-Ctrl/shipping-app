package bank_system;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp trừu tượng đại diện cho một tài khoản ngân hàng cơ bản.
 */
public abstract class Account {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    public static final String CHECKING_TYPE = "CHECKING";
    public static final String SAVINGS_TYPE = "SAVINGS";

    private long accountNumber;
    private double balance;
    protected List<Transaction> transactionList;

    /**
     * Khởi tạo tài khoản với số tài khoản và số dư ban đầu.
     *
     * @param accountNumber Số tài khoản của khách hàng.
     * @param balance       Số dư ban đầu.
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionList = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * Cập nhật danh sách giao dịch.
     *
     * @param transactionList Danh sách giao dịch mới.
     */
    public void setTransactionList(List<Transaction> transactionList) {
        if (transactionList == null) {
            this.transactionList = new ArrayList<>();
        } else {
            this.transactionList = transactionList;
        }
    }

    /**
     * Nạp tiền vào tài khoản.
     *
     * @param amount Số tiền cần nạp.
     */
    public abstract void deposit(double amount);

    /**
     * Rút tiền từ tài khoản.
     *
     * @param amount Số tiền cần rút.
     */
    public abstract void withdraw(double amount);

    protected void doDepositing(double amount) throws InvalidFundingAmountException {
        if (amount <= 0) {
            logger.error("Lỗi nạp tiền: Số tiền không hợp lệ ({})", amount);
            throw new InvalidFundingAmountException(amount);
        }
        this.balance += amount;
    }

    protected void doWithdrawing(double amount) throws BankException {
        if (amount <= 0) {
            logger.error("Lỗi rút tiền: Số tiền không hợp lệ ({})", amount);
            throw new InvalidFundingAmountException(amount);
        }
        if (amount > this.balance) {
            logger.error("Lỗi rút tiền: Số dư không đủ (Cần: {}, Có: {})", amount, this.balance);
            throw new InsufficientFundsException(amount);
        }
        this.balance -= amount;
    }

    /**
     * Thêm một giao dịch vào lịch sử.
     *
     * @param transaction Giao dịch cần thêm.
     */
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionList.add(transaction);
        }
    }

    /**
     * Lấy chuỗi mô tả toàn bộ lịch sử giao dịch.
     *
     * @return Chuỗi chứa thông tin lịch sử giao dịch.
     */
    public String getTransactionHistory() {
        logger.debug("Đang truy xuất lịch sử giao dịch cho tài khoản: {}", accountNumber);
        StringBuilder historyBuilder = new StringBuilder();
        historyBuilder.append("Lịch sử giao dịch của tài khoản ")
                .append(accountNumber)
                .append(":\n");

        for (int i = 0; i < transactionList.size(); i++) {
            historyBuilder.append(transactionList.get(i).getTransactionSummary());
            if (i < transactionList.size() - 1) {
                historyBuilder.append("\n");
            }
        }
        return historyBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        return this.accountNumber == other.accountNumber;
    }

    @Override
    public int hashCode() {
        return (int) (accountNumber ^ (accountNumber >>> 32));
    }
}