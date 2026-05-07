package bank_system;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho Ngân hàng, quản lý danh sách khách hàng.
 */
public class Bank {
    private static final Logger logger = LoggerFactory.getLogger(Bank.class);
    private List<Customer> customerList;

    public Bank() {
        this.customerList = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Thiết lập danh sách khách hàng mới cho ngân hàng.
     *
     * @param customerList Danh sách khách hàng.
     */
    public void setCustomerList(List<Customer> customerList) {
        if (customerList == null) {
            this.customerList = new ArrayList<>();
        } else {
            this.customerList = customerList;
        }
    }

    /**
     * Đọc dữ liệu khách hàng từ InputStream.
     *
     * @param inputStream Luồng dữ liệu đầu vào.
     */
    public void readCustomerList(InputStream inputStream) {
        logger.info("Bắt đầu đọc dữ liệu khách hàng từ InputStream...");
        if (inputStream == null) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Customer currentCustomer = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                int lastSpaceIndex = line.lastIndexOf(' ');
                if (lastSpaceIndex > 0) {
                    String token = line.substring(lastSpaceIndex + 1).trim();
                    if (token.matches("\\d{9}")) {
                        String name = line.substring(0, lastSpaceIndex).trim();
                        currentCustomer = new Customer(Long.parseLong(token), name);
                        customerList.add(currentCustomer);
                        logger.debug("Đã thêm khách hàng: {}", name);
                    } else if (currentCustomer != null) {
                        String[] parts = line.split("\\s+");
                        if (parts.length >= 3) {
                            long accNum = Long.parseLong(parts[0]);
                            double balance = Double.parseDouble(parts[2]);
                            if (Account.CHECKING_TYPE.equals(parts[1])) {
                                currentCustomer.addAccount(new CheckingAccount(accNum, balance));
                            } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
                                currentCustomer.addAccount(new SavingsAccount(accNum, balance));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Đã xảy ra lỗi trong quá trình đọc dữ liệu: {}", e.getMessage(), e);
        }
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo ID.
     *
     * @return Chuỗi thông tin khách hàng.
     */
    public String getCustomersInfoByIdOrder() {
        Collections.sort(customerList, (c1, c2) ->
                Long.compare(c1.getIdNumber(), c2.getIdNumber()));

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < customerList.size(); i++) {
            res.append(customerList.get(i).getCustomerInfo());
            if (i < customerList.size() - 1) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo Tên.
     *
     * @return Chuỗi thông tin khách hàng.
     */
    public String getCustomersInfoByNameOrder() {
        List<Customer> sortedList = new ArrayList<>(customerList);
        Collections.sort(sortedList, (c1, c2) -> {
            int nameCompare = c1.getFullName().compareTo(c2.getFullName());
            if (nameCompare != 0) {
                return nameCompare;
            }
            return Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });

        StringBuilder sb = new StringBuilder();
        for (Customer c : sortedList) {
            sb.append(c.getCustomerInfo()).append("\n");
        }
        return sb.toString().trim();
    }
}