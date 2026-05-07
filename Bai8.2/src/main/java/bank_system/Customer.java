package bank_system;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho một khách hàng của ngân hàng.
 */
public class Customer {
    private long idNumber;
    private String fullName;
    private List<Account> accountList;

    /**
     * Khởi tạo khách hàng không tham số.
     */
    public Customer() {
        this(0L, "");
    }

    /**
     * Khởi tạo khách hàng với CMND và họ tên.
     *
     * @param idNumber Số CMND.
     * @param fullName Họ và tên.
     */
    public Customer(long idNumber, String fullName) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.accountList = new ArrayList<>();
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Thiết lập danh sách tài khoản.
     *
     * @param accountList Danh sách tài khoản mới.
     */
    public void setAccountList(List<Account> accountList) {
        if (accountList == null) {
            this.accountList = new ArrayList<>();
        } else {
            this.accountList = accountList;
        }
    }

    /**
     * Thêm tài khoản mới cho khách hàng.
     *
     * @param account Tài khoản cần thêm.
     */
    public void addAccount(Account account) {
        if (account == null) {
            return;
        }
        if (!accountList.contains(account)) {
            accountList.add(account);
        }
    }

    /**
     * Xóa tài khoản khỏi danh sách của khách hàng.
     *
     * @param account Tài khoản cần xóa.
     */
    public void removeAccount(Account account) {
        if (account == null) {
            return;
        }
        accountList.remove(account);
    }

    /**
     * Lấy chuỗi thông tin cơ bản của khách hàng.
     *
     * @return Chuỗi chứa CMND và Họ tên.
     */
    public String getCustomerInfo() {
        return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
    }
}