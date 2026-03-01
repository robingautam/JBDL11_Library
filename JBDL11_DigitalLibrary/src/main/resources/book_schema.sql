-- Create Author table
CREATE TABLE IF NOT EXISTS authors (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL,
    author_email VARCHAR(100) UNIQUE NOT NULL,
    author_phone VARCHAR(20),
    author_bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_author_email (author_email),
    INDEX idx_author_name (author_name)
);

-- Create BookCategory table (Enum support)
CREATE TABLE IF NOT EXISTS book_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Book table with foreign key to Author
CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    book_isbn VARCHAR(20) UNIQUE NOT NULL,
    book_category_id INT NOT NULL,
    book_price DOUBLE NOT NULL,
    book_quantity INT NOT NULL DEFAULT 0,
    book_publisher VARCHAR(100),
    book_description TEXT,
    publication_year INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE,
    INDEX idx_book_isbn (book_isbn),
    INDEX idx_book_title (book_title),
    INDEX idx_author_id (author_id),
    INDEX idx_book_category (book_category_id)
);

-- Insert default book categories
INSERT IGNORE INTO book_categories (category_name) VALUES
('Fiction'),
('Science'),
('History'),
('Technology'),
('Biography'),
('Mystery'),
('Romance'),
('Self-Help'),
('Adventure'),
('Education');

