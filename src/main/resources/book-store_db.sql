CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    price DECIMAL(10,2),
    available BOOLEAN DEFAULT TRUE
);

INSERT INTO books (title, author, price, available) VALUES
('Clean Code', 'Robert C. Martin', 15.99, TRUE),
('Effective Java', 'Joshua Bloch', 20.50, TRUE),
('Design Patterns', 'Erich Gamma', 18.75, FALSE);

CREATE TABLE categories (
	category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name	NVARCHAR(250) UNIQUE NOT NULL
);

INSERT INTO categories (name) VALUE
('Tiểu thuyết'),
('Công nghệ'),
('Truyện tranh'),
('Tâm lý')