Create database PhoneShopDB;
use PhoneShopDB;

-- Bảng Admin
CREATE TABLE Admin (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    roleID INT,
    email VARCHAR(100),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Role
CREATE TABLE Role (
    roleID INT AUTO_INCREMENT PRIMARY KEY,
    roleName VARCHAR(50) NOT NULL
);

-- Bảng Category
CREATE TABLE Category (
    categoryID INT AUTO_INCREMENT PRIMARY KEY,
    categoryName NVARCHAR(50) NOT NULL
);

-- Bảng Banner
CREATE TABLE Banner (
    bannerID INT AUTO_INCREMENT PRIMARY KEY,
    imageURL VARCHAR(255),
    description NVARCHAR(255),
    startDate DATE,
    endDate DATE,
    isActive BOOLEAN DEFAULT TRUE
);

-- Bảng Product
CREATE TABLE Product (
    productID VARCHAR(255) PRIMARY KEY,
    categoryID INT,
    productName NVARCHAR(100) NOT NULL,
    description NVARCHAR(255),
    image VARCHAR(255),
    price DECIMAL(10, 2),
    createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status INT DEFAULT 1
);

CREATE TABLE ProductContent (
    contentID INT AUTO_INCREMENT PRIMARY KEY,
    productID VARCHAR(255),
    title TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    contentText TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    contentImage VARCHAR(255),
    displayOrder INT,
    createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE ProductColor (
    productColorID VARCHAR(255) PRIMARY KEY,
    productID VARCHAR(255),
    colorName NVARCHAR(50),
    imagePath VARCHAR(255)
);

CREATE TABLE ProductStorage (
    productStorageID INT AUTO_INCREMENT PRIMARY KEY,
    storageCapacity NVARCHAR(50) UNIQUE
);

CREATE TABLE ProductVariant (
    variantID VARCHAR(255) PRIMARY KEY,
    productID VARCHAR(255),
    productColorID VARCHAR(255),
    productStorageID INT,
    price DECIMAL(10, 2)
);

-- Bảng ProductSpecification
CREATE TABLE ProductSpecification (
    specificationID INT AUTO_INCREMENT PRIMARY KEY,
    productID VARCHAR(255),
    specName NVARCHAR(100),
    specValue VARCHAR(255)
);

-- Bảng Customer
CREATE TABLE Customer (
    customerID VARCHAR(255) PRIMARY KEY,
    fullName NVARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    address VARCHAR(255)
);

-- Bảng Order
CREATE TABLE `Order` (
    orderID VARCHAR(255) PRIMARY KEY,
    customerID VARCHAR(255),
    orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    totalAmount DECIMAL(10, 2),
    status VARCHAR(50) DEFAULT 'Processing' ,
    orderType ENUM('normal', 'installment') NOT NULL DEFAULT 'normal'
);

-- Bảng OrderDetail
CREATE TABLE OrderDetail (
    orderDetailID VARCHAR(255) PRIMARY KEY,
    orderID VARCHAR(255),
    variantID VARCHAR(255),
    productID VARCHAR(255),
    content NVARCHAR(255),
    priceAtOrder DECIMAL(10, 2),
	installmentPlanID INT NULL
);

-- Bảng Promotion
CREATE TABLE Promotion (
    promotionID INT AUTO_INCREMENT PRIMARY KEY,
    productID VARCHAR(255),
    promotionDescription NVARCHAR(255),
    discountValue DECIMAL(10, 2),
    startDate DATE,
    endDate DATE,
    isActive BOOLEAN DEFAULT TRUE
);

-- Bảng InstallmentPlan
CREATE TABLE InstallmentPlan (
    installmentPlanID INT AUTO_INCREMENT PRIMARY KEY,
    productID VARCHAR(255),
    variantID VARCHAR(255),
    planName NVARCHAR(100),
    price DECIMAL(10, 2),
    durationMonths INT,
    interestRate DECIMAL(5, 2),
    downPayment DECIMAL(10, 2),
    monthlyPayment DECIMAL(10, 2),
    totalAmount DECIMAL(10, 2),
    differenceAmount DECIMAL(10, 2),
    startDate DATE,
    endDate DATE,
    isActive BOOLEAN DEFAULT TRUE
);

CREATE TABLE CustomerVisitImage (
    customerImageID INT AUTO_INCREMENT PRIMARY KEY,
    imageURL VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE News (
    newID INT AUTO_INCREMENT PRIMARY KEY,
    imageURL VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Quote (
    quoteID INT AUTO_INCREMENT PRIMARY KEY,
    quoteCategory NVARCHAR(255) NOT NULL,
    imageUrl VARCHAR(255)
);

ALTER TABLE Admin ADD CONSTRAINT fk_roleID FOREIGN KEY (roleID) REFERENCES Role(roleID);
ALTER TABLE Product ADD CONSTRAINT fk_categoryID FOREIGN KEY (categoryID) REFERENCES Category(categoryID);
ALTER TABLE ProductContent ADD CONSTRAINT fk_productContent FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE ProductVariant ADD CONSTRAINT fk_productVariant FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE ProductVariant ADD CONSTRAINT fk_colorProductVariant FOREIGN KEY (productColorID) REFERENCES ProductColor(productColorID);
ALTER TABLE ProductVariant ADD CONSTRAINT fk_storageProductVariant FOREIGN KEY (productStorageID) REFERENCES ProductStorage(productStorageID);
ALTER TABLE ProductColor ADD CONSTRAINT fk_productID FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE ProductSpecification ADD CONSTRAINT fk_productSpecificationID FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE `Order` ADD CONSTRAINT fk_customerID FOREIGN KEY (customerID) REFERENCES Customer(customerID);
ALTER TABLE OrderDetail ADD CONSTRAINT fk_orderID FOREIGN KEY (orderID) REFERENCES `Order`(orderID);
ALTER TABLE OrderDetail ADD CONSTRAINT fk_variantID FOREIGN KEY (variantID) REFERENCES ProductVariant(variantID);
ALTER TABLE OrderDetail ADD CONSTRAINT fk_orderproductID FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE Promotion ADD CONSTRAINT fk_productPromotionID FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE InstallmentPlan ADD CONSTRAINT fk_productInstallmentID FOREIGN KEY (productID) REFERENCES Product(productID);
ALTER TABLE InstallmentPlan ADD CONSTRAINT fk_variantInstallmentID FOREIGN KEY (variantID) REFERENCES ProductVariant(variantID);
ALTER TABLE OrderDetail ADD CONSTRAINT FK_installmentPlanID FOREIGN KEY (installmentPlanID) REFERENCES InstallmentPlan(installmentPlanID) ON DELETE SET NULL;
ALTER TABLE ProductVariant ADD isStatus BOOLEAN DEFAULT TRUE;
ALTER TABLE ProductColor ADD isStatus BOOLEAN DEFAULT TRUE;

-- Dữ liệu cho bảng Category
INSERT INTO Category (categoryName) VALUES
('Điện thoại'), 
('Tablet'), 
('MacBook'), 
('Apple Watch'), 
('Phụ kiện');

-- Dữ liệu cho bảng Product
INSERT INTO Product (productId, categoryID, productName, image, description, price, createAt, updateAt) VALUES
(1, 1, 'iPhone 14 Pro Max', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 14 Pro Max với màn hình 6.7 inch và chip A16 Bionic.', 29990000, NOW(), NOW()),
(2, 1, 'iPhone 13', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 13 với màn hình Super Retina XDR 6.1 inch.', 19990000, NOW(), NOW()),
(3, 1, 'iPhone 13 pro max', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 13 với màn hình Super Retina XDR 6.1 inch.', 19990000, NOW(), NOW()),
(4, 1, 'iPhone 13 pro 64GB', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 13 với màn hình Super Retina XDR 6.1 inch.', 19990000, NOW(), NOW()),
(5, 1, 'iPhone 13 pro 128GB', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 13 với màn hình Super Retina XDR 6.1 inch.', 19990000, NOW(), NOW()),
(6, 1, 'iPhone 13 pro 256GB', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 13 với màn hình Super Retina XDR 6.1 inch.', 19990000, NOW(), NOW()),
(7, 1, 'iPhone 13 pro max 64GB', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone 13 với màn hình Super Retina XDR 6.1 inch.', 19990000, NOW(), NOW()),
(8, 1, 'iPhone SE 2022', 'https://hoangphucstore.com/assets/uploads/images/HoK0Bd2RTb6J_iphone-11-anh-dai-dien.jpg', 'iPhone SE 2022 với chip A15 Bionic.', 10990000, NOW(), NOW()),
(9, 2, 'iPad Pro 11 inch', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPDHZCBYfA3A_rUWM6pxRTPKk-s8Qum7b4hA&s', 'iPad Pro 11 inch với chip M2 và màn hình Liquid Retina.', 22990000, NOW(), NOW()),
(10, 2, 'iPad Air 5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7sXKT9mO0NAq1XHZCwEFqJTNBcFaUWRPDQw&s', 'iPad Air thế hệ 5 với chip M1 và màn hình 10.9 inch.', 16990000, NOW(), NOW()),
(11, 2, 'iPad Mini 6', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7KnkVtJmDp_jgssUuQazkgER6ZrjTCWBSRQ&s', 'iPad Mini 6 với màn hình Liquid Retina 8.3 inch.', 12990000, NOW(), NOW()),
(12, 3, 'MacBook Air M2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCnubosAJ1q1LjdF0fr1lIV-kRLhyT4CZwIg&s', 'MacBook Air với chip M2 và màn hình Retina 13.6 inch.', 28990000, NOW(), NOW()),
(13, 3, 'MacBook Pro 14 inch', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSemUMOvALuuqPDaFqoGtZ57bb-cZ0BRR2img&s', 'MacBook Pro 14 inch với chip M2 Pro.', 54990000, NOW(), NOW()),
(14, 3, 'MacBook Pro 16 inch', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_md2MsQ2dJUXLPn4uIkmZo3-B6Qc7scUAKg&s', 'MacBook Pro 16 inch với chip M2 Max.', 64990000, NOW(), NOW()),
(15, 4, 'Apple Watch Ultra', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSejJxeRSfIJVuWMPkbe5HFZxopBa3kKxFknw&s', 'Apple Watch Ultra với thiết kế bền bỉ và GPS chính xác.', 19990000, NOW(), NOW()),
(16, 4, 'Apple Watch SE 2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUyC8qBEcVK2HI0CXvqFTKtC1sHpQt3Y9Enw&s', 'Apple Watch SE 2 với tính năng phát hiện va chạm.', 7990000, NOW(), NOW()),
(17, 5, 'AirPods Pro 2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWNCnc1wq7ztLVxRtonMI1W1D_i9mK2tp4RQ&s', 'Tai nghe AirPods Pro thế hệ 2 với tính năng chống ồn.', 5990000, NOW(), NOW()),
(18, 5, 'AirTag', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1VAqt8gOIu213dsXcmLycchSHAQpmvMXMZA&s', 'Thiết bị theo dõi AirTag của Apple.', 790000, NOW(), NOW()),
(19, 5, 'Sạc nhanh 20W', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiTbho4I2Fm5omc_4I-1KuHxKJGQQAx6y98Q&s', 'Củ sạc nhanh 20W chính hãng Apple.', 450000, NOW(), NOW()),
(20, 1, 'iPhone 14', 'https://hoangphucstore.com/assets/uploads/images/h4IxoUkdqC6M_file-ip14-avatar.png', 'iPhone 14 với màn hình Super Retina XDR 6.1 inch.', 23990000, NOW(), NOW()),
(21, 2, 'iPad 10.9 inch', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvdt5NL_CsoKSS7a_HlbCGST3uIJMtXTmEKw&s', 'iPad thế hệ mới với màn hình 10.9 inch.', 11990000, NOW(), NOW()),
(22, 3, 'MacBook Air M1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3J6VXBh5LWLmG270w-0DACxxs25k2RIy92A&s', 'MacBook Air với chip M1, hiệu năng tốt.', 19990000, NOW(), NOW()),
(23, 4, 'Apple Watch Series 8', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe7d70ktLNVvL4VT7pRL6YQ6H76hALdfrYag&s', 'Apple Watch Series 8 với màn hình Always-On Retina.', 11990000, NOW(), NOW()),
(24, 5, 'Cáp sạc USB-C', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaQHjEI-BnmW9_GOP6ui7YObVtT74Gj2PnSg&s', 'Cáp sạc USB-C chính hãng Apple.', 350000, NOW(), NOW());

INSERT INTO ProductColor (productColorID,productID, colorName, imagePath) VALUES
(1,'1', 'Đen', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTz7lArQKPYQfvCiLoV1Blgm75HEnSzAKAL2A&s'),
(2,'1', 'Trắng', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3FO1KBxCAlWmpUROzA4UWJAWvV2tEmR-WQQ&s'),
(3,'2', 'Xanh Dương', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxvkqaO3T5EIEl99V9UYtBj-XEsmM78y5X5w&s'),
(4, '2', 'Đỏ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCnubosAJ1q1LjdF0fr1lIV-kRLhyT4CZwIg&s'),
(5, '2', 'Xám', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7sXKT9mO0NAq1XHZCwEFqJTNBcFaUWRPDQw&s'),
(6, '2', 'Vàng', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCnubosAJ1q1LjdF0fr1lIV-kRLhyT4CZwIg&s'),
(7, '3', 'Đỏ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCnubosAJ1q1LjdF0fr1lIV-kRLhyT4CZwIg&s'),
(8, '4', 'Xám', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7sXKT9mO0NAq1XHZCwEFqJTNBcFaUWRPDQw&s'),
(9, '5', 'Vàng', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCnubosAJ1q1LjdF0fr1lIV-kRLhyT4CZwIg&s'),
(10, '6', 'Tím', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCnubosAJ1q1LjdF0fr1lIV-kRLhyT4CZwIg&s'),
(11, '7', 'Xanh Lá', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSemUMOvALuuqPDaFqoGtZ57bb-cZ0BRR2img&s'),
(12, '8', 'Bạc', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSejJxeRSfIJVuWMPkbe5HFZxopBa3kKxFknw&s'),
(13, '9', 'Hồng', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1VAqt8gOIu213dsXcmLycchSHAQpmvMXMZA&s'),
(14,'10', 'Xanh Rêu', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvdt5NL_CsoKSS7a_HlbCGST3uIJMtXTmEKw&s');

INSERT INTO ProductSpecification (productID, specName, specValue) VALUES
(1, 'Màn hình', '6.7 inch Super Retina XDR'),
(1, 'Chip', 'A16 Bionic'),
(1, 'Camera', '48 MP chính, 12 MP siêu rộng'),
(2, 'Màn hình', '6.1 inch Super Retina XDR'),
(2, 'Chip', 'A15 Bionic'),
(3, 'Màn hình', '4.7 inch Retina HD'),
(3, 'Chip', 'A15 Bionic'),
(4, 'Màn hình', '11 inch Liquid Retina'),
(4, 'Chip', 'M2'),
(5, 'Màn hình', '10.9 inch Liquid Retina'),
(5, 'Chip', 'M1'),
(6, 'Màn hình', '8.3 inch Liquid Retina'),
(6, 'Chip', 'A15 Bionic');

INSERT INTO Promotion (productID, promotionDescription, discountValue, startDate, endDate, isActive) VALUES
(2, 'Giảm giá cuối tuần', 2, '2024-11-15', '2024-11-18', TRUE),
(2, 'Khuyến mãi mùa lễ hội', 2, '2024-12-01', '2024-12-31', TRUE),
(2, 'Mua iPhone 14 Pro Max tặng AirPods', 2, '2024-11-20', '2024-12-05', TRUE),
(2, 'Giảm giá cho sinh viên', 2, '2024-11-01', '2025-01-01', TRUE),
(2, 'Black Friday Sale', 2, '2024-11-29', '2024-11-29', TRUE);

INSERT INTO InstallmentPlan (productID, durationMonths, interestRate, downPayment, monthlyPayment, totalAmount, isActive, startDate, endDate) VALUES
(1, 3, 0, 5000000, 8330000, 29990000, TRUE, '2024-11-01', '2025-01-01'),
(1, 6, 0, 5000000, 4500000, 32000000, TRUE, '2024-11-01', '2025-01-01'),
(2, 3, 0, 4000000, 6300000, 22990000, TRUE, '2024-11-01', '2025-02-01'),
(2, 6, 0, 4000000, 3200000, 25000000, TRUE, '2024-11-01', '2025-02-01'),
(3, 3, 0, 6000000, 10000000, 31990000, TRUE, '2024-11-01', '2025-01-01'),
(4, 6, 0, 2000000, 1800000, 12990000, TRUE, '2024-11-01', '2025-03-01'),
(5, 3, 0, 1000000, 2000000, 5990000, TRUE, '2024-11-01', '2025-04-01');

INSERT INTO Banner (`imageURL`, `description`, `startDate`, `endDate`, `isActive`) VALUES 
('https://hoangphucstore.com/assets/uploads/images/qOW9hFXt50aA_thu-cu%CC%83-do%CC%82%CC%89i-mo%CC%9B%CC%81i.jpg', 'Quảng cáo', '2024-11-16', '2024-12-19', TRUE),
('https://hoangphucstore.com/assets/uploads/images/2jPNQ8rv92mC_temp-001.png', 'Quảng cáo', '2024-11-16', '2024-12-19', TRUE),
('https://hoangphucstore.com/assets/uploads/images/F1NRG392ni19_banner-trang-chu%CC%89.jpg', 'Quảng cáo', '2024-11-16', '2024-12-19', TRUE);

INSERT INTO CustomerVisitImage (`imageURL`, `createdAt`) VALUES 
('https://hoangphucstore.com/assets/uploads/images/fF9D5b9rGqN6_4.jpg','2024-11-16'),
('https://hoangphucstore.com/assets/uploads/images/nG8M9kyQN8cK_10.jpg','2024-11-16'),
('https://hoangphucstore.com/assets/uploads/images/m3csxg2P4G95_1.jpg','2024-11-16'),
('https://hoangphucstore.com/assets/uploads/images/R7935Qo586nA_11.jpg','2024-11-16'),
('https://hoangphucstore.com/assets/uploads/images/93MJG8LD1H9p_4-hoang-phuc-store.png','2024-11-16');

INSERT INTO News (`imageURL`, `title`,`createdAt`) VALUES 
('https://hoangphucstore.com/image_resized/temp/2jPNQ8rv92mC_temp-001_100_56_cf_q100.png', 'Mua Trả Góp iPhone - iPad - Watch Lấy Liền','2024-11-16'),
('https://hoangphucstore.com/image_resized/temp/2jPNQ8rv92mC_temp-001_100_56_cf_q100.png', 'Lộ diện iPhone 13 (iPhone 12s) màu cam đồng quá lạ mắt','2024-11-16'),
('https://hoangphucstore.com/image_resized/temp/2jPNQ8rv92mC_temp-001_100_56_cf_q100.png', 'ESIM là gì ? Những smartphone nào trên thị trường Việt đang dùng ESIM ?','2024-11-16');

INSERT INTO ProductStorage (storageCapacity) 
VALUES ('64GB'), ('128GB'), ('256GB'), ('512GB');

-- Dữ liệu mẫu cho bảng ProductVariant
INSERT INTO ProductVariant (variantID, productID, productColorID, productStorageID, price) 
VALUES 
(1,1, '1', 1, 1000000),   -- iPhone màu 1, dung lượng 64GB
(2,1, '1', 2, 1500000),  -- iPhone màu 1, dung lượng 128GB
(3,1, '2', 1, 1200000),  -- iPhone màu 2, dung lượng 64GB
(4,1, '2', 2, 1500000),  -- iPhone màu 2, dung lượng 128GB
(5,2, '3', 3, 1500000),  -- iPhone màu 2, dung lượng 64GB
(6,2, '4', 3, 1500000),  -- iPhone màu 2, dung lượng 128GB
(7,2, '5', 3, 1500000),   -- iPad màu 3, dung lượng 256GB
(8,2, '6', 3, 1300000),   -- iPad màu 4, dung lượng 512GB
(9,2, '3', 4, 2500000),  -- iPhone màu 2, dung lượng 64GB
(10,2, '4', 4, 1800000),  -- iPhone màu 2, dung lượng 128GB
(11,2, '5', 4, 2300000),   -- iPad màu 3, dung lượng 256GB
(12,2, '6', 4, 3200000);   -- iPad màu 4, dung lượng 512GB

-- Nội dung 1
INSERT INTO ProductContent (productID, title, contentText, contentImage, displayOrder)
VALUES
(2, 
'Tận hưởng tốc độ khó tin trên chiếc smartphone nhanh nhất thế giới, iPhone 13 Pro với màn hình ProMotion 120Hz đột phá, bộ vi xử lý A15 Bionic cho hiệu năng không đối thủ, thời lượng pin vượt trội và bộ 3 camera được nâng cấp đáng kể, xứng đáng là chiếc điện thoại đầu bảng trên thị trường.',
NULL,
'https://hoangphucstore.com/assets/uploads/images/698kBc0HPf73_iphone-13-pro-1.jpeg',
1);

-- Nội dung 2
INSERT INTO ProductContent (productID, title, contentText, contentImage, displayOrder)
VALUES
(2, 
'Đẳng cấp trong từng đường nét', 
'Được chế tác từ khung thép không gỉ cứng cáp, bảo vệ màn hình là mặt gốm Ceramic Shield siêu cứng cùng ngôn ngữ thiết kế phẳng hiện đại',
'https://hoangphucstore.com/assets/uploads/images/hc47BL7UiMP4_iphone-13-pro-2.jpeg',
2);

-- Nội dung 3
INSERT INTO ProductContent (productID, title, contentText, contentImage, displayOrder)
VALUES
(2, 
'Camera nâng cấp nhiều nhất từ trước đến nay', 
'Hệ thống camera Pro với 3 camera được nâng cấp mạnh mẽ, với phần cứng ống kính chất lượng hơn 
iPhone 13 Pro sở hữu camera chính khẩu độ f/1.5, điểm ảnh 1.9 um, lớn nhất trong thế giới smartphone cho khả năng thu sáng vượt trội; camera góc siêu rộng khẩu độ f/1.8, cảm biến nhanh hơn, lấy nét từng điểm ảnh và camera tele có khả năng zoom quang học 3x.',
'https://hoangphucstore.com/assets/uploads/images/X79Ir50Rp6jw_iphone-13-pro-3.jpeg',
3);

-- Nội dung 4
INSERT INTO ProductContent (productID, title, contentText, contentImage, displayOrder)
VALUES
(2, 
'Chụp ảnh và quay video macro', 
'Khả năng lấy nét ở khoảng cách siêu gần chỉ 2cm giúp các vật thể nhỏ như chiếc lá, côn trùng hay thậm chí giọt sương đều được tái hiện sắc nét.
Chiếc điện thoại đầu tiên có thể quay video macro, tích hợp cả tính năng chuyển động chậm và tua nhanh khi quay, mang đến những thước phim macro mê hoặc.',
'https://hoangphucstore.com/assets/uploads/images/e83G3v6KPCJ4_iphone-13-pro-camera-4.jpeg',
4);

-- Nội dung 5
INSERT INTO ProductContent (productID, title, contentText, contentImage, displayOrder)
VALUES
(2, 
'Thách thức màn đêm', 
'iPhone 13 Pro là chiếc điện thoại chuyên dụng cho điều kiện ánh sáng yếu. Với ống kính camera khẩu độ rộng hơn, kích thước cảm biến lớn hơn và sự hỗ trợ đắc lực của máy quét LiDAR. 
So với thế hệ trước, camera chính cho ánh sáng nhiều hơn tới 2,2 lần, camera siêu rộng sáng hơn 92%. Chế độ chụp đêm giờ đây cũng có mặt ở camera siêu rộng và camera tele để ảnh chụp đêm sắc nét, giàu chi tiết, màu sắc chính xác trong mọi hoàn cảnh.',
'https://hoangphucstore.com/assets/uploads/images/7iZ965L7t4X7_iphone-13-pro-camera-5.jpeg',
5);

-- Nội dung 6
INSERT INTO ProductContent (productID, title, contentText, contentImage, displayOrder)
VALUES
(2, 
'Zoom quang 3x và hơn thế nữa với camera Tele', 
'Camera Tele mới trên điện thoại iPhone 13 Pro có tiêu cự 77mm và khả năng zoom quang 3x, lý tưởng cho ảnh chụp chân dung hoặc phóng to ảnh, video mà không hề giảm chất lượng.',
NULL,
6);

INSERT INTO Role (roleID, roleName)
VALUES(1, "Admin");

INSERT INTO Admin (userName, password, roleID)
VALUES("Admin", "$2a$12$n7dFxSueJwDLmzVzTY8Eked3wOgQQ5cNm8QGn.4DbgCYndsqXU/Zy", 1);








