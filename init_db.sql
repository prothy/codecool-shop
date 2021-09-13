ALTER TABLE IF EXISTS ONLY public.user
DROP CONSTRAINT IF EXISTS pk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders
DROP CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_order
DROP CONSTRAINT IF EXISTS pk_products_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart
DROP CONSTRAINT IF EXISTS pk_cart_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier
DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category
DROP CONSTRAINT IF EXISTS pk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_order
DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_order
DROP CONSTRAINT IF EXISTS fk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart
DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart
DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders
DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;

DROP TABLE IF EXISTS public.user;
CREATE TABLE "user"
(
    user_id  serial PRIMARY KEY NOT NULL,
    email    varchar(50)        NOT NULL,
    password varchar(50)        NOT NULL,
    isAdmin  boolean            NOT NULL,
    address  text               NOT NULL,
    wallet   money              NOT NULL,
    currency text
);

DROP TABLE IF EXISTS public.orders;
CREATE TABLE orders
(
    order_id     serial PRIMARY KEY          NOT NULL,
    user_id      int                         NOT NULL,
    order_date   timestamp without time zone NOT NULL,
    order_status text                        NOT NULL
);

DROP TABLE IF EXISTS public.products_order;
CREATE TABLE products_order
(
    product_id int NOT NULL,
    order_id   int NOT NULL
);

DROP TABLE IF EXISTS public.cart;
CREATE TABLE cart
(
    user_id    int NOT NULL,
    product_id int NOT NULL
);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE supplier
(
    supplier_id   serial PRIMARY KEY NOT NULL,
    supplier_name varchar(255)
);

DROP TABLE IF EXISTS public.product_category;
CREATE TABLE product_category
(
    category_id   serial PRIMARY KEY NOT NULL,
    category_name varchar(255)
);

DROP TABLE IF EXISTS public.products;
CREATE TABLE products
(
    product_id          serial PRIMARY KEY NOT NULL,
    product_category_id int,
    product_name        text,
    supplier_id         int,
    description         varchar(2000),
    languages           varchar(2000),
    price               decimal,
    yearlyPrice         decimal,
    monthlyPrice        decimal,
    currency            text,
    image               varchar(2000)
);

-- ALTER TABLE ONLY "user"
--     ADD CONSTRAINT pk_user_id PRIMARY KEY (user_id);
-- ALTER TABLE ONLY orders
--     ADD CONSTRAINT pk_order_id PRIMARY KEY (order_id);
--
-- ALTER TABLE ONLY products
--     ADD CONSTRAINT pk_product_id PRIMARY KEY (product_id);
--
-- ALTER TABLE ONLY supplier
--     ADD CONSTRAINT pk_supplier_id PRIMARY KEY (supplier_id);
--
-- ALTER TABLE ONLY product_category
--     ADD CONSTRAINT pk_product_category_id PRIMARY KEY (category_id);

ALTER TABLE ONLY products_order
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (product_id);

ALTER TABLE ONLY products_order
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (order_id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user" (user_id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (product_id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user" (user_id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_category (category_id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id);

INSERT INTO "user"
VALUES (1, 'admin@admin.com', 'admin', false, 'none', 999.99, '$');
SELECT pg_catalog.setval('user_user_id_seq', 1, true);

INSERT INTO orders
VALUES (1, 1, '2021-09-13 12:00:54', 'INPROGRESS');
SELECT pg_catalog.setval('orders_order_id_seq', 1, true);