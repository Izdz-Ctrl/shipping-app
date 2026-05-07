package bank_system;

// Import thư viện SLF4J
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  // 1. Khai báo đối tượng Logger cho lớp Main
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    // 2. Sử dụng logger.info thay cho System.out.println
    logger.info("--- Bank System is starting up ---");

    try {
      // Khởi tạo đối tượng Bank
      Bank bank = new Bank();
      logger.info("Bank initialized successfully!");

      // Ví dụ ghi log kèm tham số (Parameterized logging)
      logger.info("Hệ thống đang chạy trên phiên bản Java: {}", System.getProperty("java.version"));

      // Giả lập một vài thông báo kiểm tra hệ thống
      for (int i = 1; i <= 3; i++) {
        logger.info("Đang kiểm tra kết nối cơ sở dữ liệu lần {}...", i);
      }

      logger.info("--- Bank System is ready for operations ---");

    } catch (Exception e) {
      // 3. Sử dụng logger.error để ghi lại lỗi nếu có sự cố
      logger.error("Lỗi nghiêm trọng khi khởi động hệ thống: {}", e.getMessage());
    }
  }
}