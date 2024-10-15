CREATE TABLE IF NOT EXISTS Price (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id INTEGER NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    priority INTEGER NOT NULL,
    price DOUBLE NOT NULL,
    currency VARCHAR(3) NOT NULL
);
