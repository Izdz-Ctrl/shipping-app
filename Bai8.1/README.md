# Quản lý Dependency với Maven

## 1. Vấn đề

Tệp `pom.xml` nguyên bản của dự án được cấu hình để chạy trên nền tảng **Java 1.8**. Tuy nhiên, Hibernate Core 6.2.x và Logback 1.4.x yêu cầu môi trường thực thi tối thiểu là **Java 11/17**.
## 2. Giải pháp
*   Chỉnh sửa thẻ `<maven.compiler.source>` và `<maven.compiler.target>` từ `1.8` lên `17`. Đồng thời cập nhật phiên bản tương ứng trong `<configuration>` của `maven-compiler-plugin`.
*   Bổ sung thư viện `logback-classic` (phiên bản 1.4.11).
*   Bổ sung thư viện `hibernate-core` (phiên bản 6.2.0.Final).
*   Xóa bỏ `<dependency>` của `junit 4.10` cũ và thay thế bằng `junit-jupiter` (phiên bản 5.9.2).