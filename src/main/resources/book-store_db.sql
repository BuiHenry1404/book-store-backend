-- ======================
-- Tạo bảng CATEGORIES
-- ======================
CREATE TABLE categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(250) UNIQUE NOT NULL
);

-- Dữ liệu mẫu cho categories
INSERT INTO categories (name) VALUES
('Tiểu thuyết'),
('Công nghệ'),
('Truyện tranh'),
('Tâm lý');

-- ======================
-- Tạo bảng BOOKS
-- ======================
CREATE TABLE books (
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    price DECIMAL(10,2),
    available BOOLEAN DEFAULT TRUE,
    image_url VARCHAR(500),
    description TEXT
);

-- Dữ liệu mẫu cho books
INSERT INTO books (title, author, price, available, image_url, description) VALUES
('Clean Code', 'Robert C. Martin', 15.99, TRUE, 
 'src/main/resources/static/uploads/books/clean_code.png', 
 'Cuốn sách nổi tiếng về cách viết mã sạch, dễ đọc và dễ bảo trì.'),
('Effective Java', 'Joshua Bloch', 20.50, TRUE, 
 'src/main/resources/static/uploads/books/effective_java.png', 
 'Sách nâng cao về Java, hướng dẫn các best practice và kỹ thuật lập trình.'),
('Design Patterns', 'Erich Gamma', 18.75, FALSE, 
 'src/main/resources/static/uploads/books/design_patterns.png', 
 'Cuốn kinh điển về các mẫu thiết kế phần mềm.'),
('OOP trong Java', 'Nguyễn Văn A', 12.99, TRUE, 
 'src/main/resources/static/uploads/books/oop_java.png', 
 'Sách lập trình về OOP trong Java, lý thuyết và thực hành.');

-- ======================
-- Tạo bảng BOOK_CATEGORY (nhiều-nhiều)
-- ======================
CREATE TABLE book_category (
    book_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, category_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);

-- Gán category cho sách
INSERT INTO book_category (book_id, category_id) VALUES
(1, 2), -- Clean Code -> Công nghệ
(2, 2), -- Effective Java -> Công nghệ
(3, 2), -- Design Patterns -> Công nghệ
(4, 2); -- OOP trong Java -> Công nghệ
