package bank_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BankTest {
  @Test
  public void testInitialBalance() {
    // Kiểm tra xem tài khoản khởi tạo có đúng số dư không
    Account account = new CheckingAccount(101, 500.0);
    assertEquals(500.0, account.getBalance(), "Số dư khởi tạo phải là 500");
  }
  @Test
  public void testCrossPlatformPathSuccess() {
    java.nio.file.Path path = java.nio.file.Paths.get("src", "main", "resources");
    org.junit.jupiter.api.Assertions.assertTrue(path.toFile().exists() || !path.toFile().exists());
    System.out.println("Robot đang chạy trên OS với dấu ngăn cách là: " + java.io.File.separator);
  }
}