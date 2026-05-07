package bank_system;

import java.util.Locale;

/**
 * Ngoại lệ tung ra khi số tiền yêu cầu giao dịch không hợp lệ (âm hoặc bằng 0).
 */
public class InvalidFundingAmountException extends BankException {
    /**
     * Khởi tạo ngoại lệ số tiền sai.
     *
     * @param amount Số tiền không hợp lệ.
     */
    public InvalidFundingAmountException(double amount) {
        super(String.format(Locale.US, "Số tiền không hợp lệ: $%.2f", amount));
    }
}