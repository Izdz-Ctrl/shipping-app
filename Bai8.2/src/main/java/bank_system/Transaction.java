package bank_system;

import java.util.Locale;

/**
 * Lớp đại diện cho một giao dịch ngân hàng.
 */
public class Transaction {
    public static final int TYPE_DEPOSIT_CHECKING = 1;
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    /**
     * Khởi tạo một đối tượng giao dịch.
     *
     * @param type           Loại giao dịch.
     * @param amount         Số tiền giao dịch.
     * @param initialBalance Số dư ban đầu.
     * @param finalBalance   Số dư sau giao dịch.
     */
    public Transaction(int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    /**
     * Trả về tên kiểu giao dịch dưới dạng chuỗi.
     *
     * @param transactionType Mã số kiểu giao dịch.
     * @return Tên loại giao dịch.
     */
    public static String getTypeString(int transactionType) {
        switch (transactionType) {
            case TYPE_DEPOSIT_CHECKING:
                return "Nạp tiền vãng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vãng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    /**
     * Sinh ra chuỗi tóm tắt thông tin của giao dịch.
     *
     * @return Chuỗi chứa chi tiết giao dịch.
     */
    public String getTransactionSummary() {
        String typeStr = getTypeString(this.type);
        String initBalStr = String.format(Locale.US, "%.2f", initialBalance);
        String amountStr = String.format(Locale.US, "%.2f", amount);
        String finalBalStr = String.format(Locale.US, "%.2f", finalBalance);

        return String.format("- Kiểu giao dịch: %s. Số dư ban đầu: $%s. Số tiền: $%s. Số dư cuối: $%s.",
                typeStr, initBalStr, amountStr, finalBalStr);
    }
}