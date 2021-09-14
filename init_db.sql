ALTER TABLE IF EXISTS ONLY public.users
DROP
CONSTRAINT IF EXISTS pk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders
DROP
CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders
DROP
CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_orders
DROP
CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.carts
DROP
CONSTRAINT IF EXISTS pk_cart_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.suppliers
DROP
CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_categories
DROP
CONSTRAINT IF EXISTS pk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP
CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP
CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_orders
DROP
CONSTRAINT IF EXISTS fk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.carts
DROP
CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.carts
DROP
CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders
DROP
CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP
CONSTRAINT IF EXISTS fk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
DROP
CONSTRAINT IF EXISTS fk_supplier_id CASCADE;

DROP TABLE IF EXISTS public.users;
CREATE TABLE users
(
    user_id  serial PRIMARY KEY NOT NULL,
    user_name text              NOT NULL,
    email    varchar(50)        NOT NULL,
    password varchar(50)        NOT NULL,
    isAdmin  boolean            NOT NULL,
    address  text               NOT NULL,
    wallet   decimal            NOT NULL,
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

DROP TABLE IF EXISTS public.products_orders;
CREATE TABLE products_orders
(
    product_id int NOT NULL,
    order_id   int NOT NULL
);

DROP TABLE IF EXISTS public.carts;
CREATE TABLE carts
(
    user_id    int NOT NULL,
    product_id int NOT NULL
);

DROP TABLE IF EXISTS public.suppliers;
CREATE TABLE suppliers
(
    supplier_id   serial PRIMARY KEY NOT NULL,
    supplier_name varchar(255)
);

DROP TABLE IF EXISTS public.product_categories;
CREATE TABLE product_categories
(
    category_id   serial PRIMARY KEY NOT NULL,
    category_name varchar(255)
);

DROP TABLE IF EXISTS public.products;
CREATE TABLE products
(
    product_id          serial PRIMARY KEY NOT NULL,
    product_name        text,
    product_category_id int,
    supplier_id         int,
    description         varchar(2000),
    languages           varchar(2000),
    price               decimal,
    yearlyPrice         decimal,
    monthlyPrice        decimal,
    bitversion          int,
    currency            text,
    image               varchar(2000)
);

ALTER TABLE ONLY products_orders
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (product_id);

ALTER TABLE ONLY products_orders
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (order_id);

ALTER TABLE ONLY carts
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE ONLY carts
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (product_id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_categories (category_id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id);

INSERT INTO users
VALUES (1, 'guest' ,'guest@guest.com', '12345', false, 'none', 999.99, 'USD');

INSERT INTO orders
VALUES (1, 1, '2021-09-13 12:00:54', 'IN_PROGRESS');

INSERT INTO product_categories
VALUES (0, 'OS');
INSERT INTO product_categories
VALUES (1, 'IDE');
INSERT INTO product_categories
VALUES (2, 'Cloud');
INSERT INTO product_categories
VALUES (3, 'WorkTool');

INSERT INTO suppliers
VALUES (0, 'Microsoft');
INSERT INTO suppliers
VALUES (1, 'Jetbrains');
INSERT INTO suppliers
VALUES (2, 'Google');
INSERT INTO suppliers
VALUES (3, 'Amazon');
INSERT INTO suppliers
VALUES (4, 'Slack');
INSERT INTO suppliers
VALUES (5, 'Atlassian');

INSERT INTO products
VALUES (0, 'Visual Studio', 1, 0, '', 'C, C++, C#, Javascript, Visual Basic', null, 149.50, 14.50, 0, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fdevblogs.microsoft.com%2Fvisualstudio%2Fwp-content%2Fuploads%2Fsites%2F4%2F2019%2F01%2Fvisualstudio-1.png&f=1&nofb=1');

INSERT INTO products
VALUES (1, 'Pycharm', 1, 1, '', 'Python, Javascript', null, 89.00, 8.90, null, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.theindianwire.com%2Fwp-content%2Fuploads%2F2018%2F06%2FPyCharm_Logo.png&f=1&nofb=1');

INSERT INTO products
VALUES (2, 'IntelliJ IDEA', 1, 1, '', 'Java, Kotlin, Scala, Groovy', null, 149.90, 14.90, null, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fhdlicense.com%2Fwp-content%2Fuploads%2F2019%2F11%2FIntelliJ-IDEA-crack.png&f=1&nofb=1');

INSERT INTO products
VALUES (3, 'Google Cloud Platform', 2, 2, '', '', null, 200.00, 21.00, null, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fpluspng.com%2Fimg-png%2Fgoogle-cloud-logo-png-library-of-google-cloud-logo-picture-royalty-free-stock-png-files-960x960.png&f=1&nofb=1');

INSERT INTO products
VALUES (4, 'Amazon Web Services', 2, 3, '', '', null, 200.00, 20.00, null, '$',
        'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Flogos-download.com%2Fwp-content%2Fuploads%2F2016%2F12%2FAmazon_Web_Services_logo_AWS.png&f=1&nofb=1');

INSERT INTO products
VALUES (5, 'Windows 10 Pro', 0, 0, '', '', 122.99, null, null, 64, '$',
        'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fcdn-reichelt.de%2Fbilder%2Fweb%2Fxxl_ws%2FE500%2FWINDOWS10_PRO_02.png&f=1&nofb=1');

INSERT INTO products
VALUES (6, 'Windows 10 Home', 0, 0, '', '', 90.00, null, null, 64, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn-reichelt.de%2Fbilder%2Fweb%2Fxxl_ws%2FE500%2FWINDOWS10_HOME_02.png&f=1&nofb=1');

INSERT INTO products
VALUES (7, 'Slack Pro', 3, 4, '', '', null, 72.67, 6.67, null, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn2.iconfinder.com%2Fdata%2Ficons%2Fsocial-media-2285%2F512%2F1_Slack_colored_svg-512.png&f=1&nofb=1');

INSERT INTO products
VALUES (8, 'Jira Standard', 3, 5, '', '', null, 84.00, 7.00, null, '$',
        'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fdoversus.com%2Fwp-content%2Fuploads%2F2020%2F01%2Fjira-standard.png&f=1&nofb=1');
