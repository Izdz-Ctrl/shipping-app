package bank_system;

/**
 * Lớp ngoại lệ chung cho toàn bộ hệ thống ngân hàng.
 */
public class BankException extends Exception {
    /**
     * Khởi tạo ngoại lệ với thông điệp.
     *
     * @param message Thông điệp báo lỗi.
     */
    public BankException(String message) {
        super(message);
    }
}