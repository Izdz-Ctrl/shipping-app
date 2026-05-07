# Code Quality - Checkstyle
### 1. Cấp độ `INFO`
* **Mục đích:** Ghi nhận các luồng nghiệp vụ cốt lõi hoạt động bình thường.
* **Dữ liệu ghi lại:** Loại hành động (Nạp/Rút), Số tài khoản (Account ID), Số tiền giao dịch.

### 2. Cấp độ `ERROR`
* **Mục đích:** Bắt các sự cố khiến giao dịch thất bại.
* **Dữ liệu ghi lại:** Dữ liệu đầu vào gây lỗi, Thông báo lỗi (Message).

### 3. Cấp độ `WARN`
* **Mục đích:** Đánh dấu các hành vi vi phạm nhưng hệ thống đã chủ động ngăn chặn an toàn.
* **Dữ liệu ghi lại:** Thông tin người dùng, Quy tắc bị vi phạm (Vượt hạn mức, Số dư tối thiểu).

### 4. Cấp độ `DEBUG`
* **Mục đích:** Dùng cho Dev theo dõi chi tiết luồng thực thi code.
* **Dữ liệu ghi lại:** Thời điểm bắt đầu/kết thúc.