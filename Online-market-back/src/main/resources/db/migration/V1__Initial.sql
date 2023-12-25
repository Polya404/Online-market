CREATE TABLE IF NOT EXISTS t_user (
                                      id SERIAL PRIMARY KEY,
                                      username VARCHAR(255),
                                      password VARCHAR(255),
                                      full_name VARCHAR(255),
                                      street VARCHAR(255),
                                      city VARCHAR(255),
                                      phone_number VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS t_order (
                       id SERIAL PRIMARY KEY,
                       user_id BIGINT,
                       FOREIGN KEY (user_id) REFERENCES t_user (id)
);


CREATE TABLE IF NOT EXISTS t_order_product (
                               order_id BIGINT,
                               product_mongo_id VARCHAR(50) UNIQUE ,
                               quantity BIGINT,
                               PRIMARY KEY (order_id, product_mongo_id),
                               FOREIGN KEY (order_id) REFERENCES t_order (id)
);

CREATE TABLE IF NOT EXISTS t_product_stock (
    product_mongo_id VARCHAR(50) PRIMARY KEY,
    quantity BIGINT,
    FOREIGN KEY (product_mongo_id) REFERENCES t_order_product(product_mongo_id)
);

