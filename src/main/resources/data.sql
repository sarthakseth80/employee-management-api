-- Sample departments
INSERT INTO departments (name, location) VALUES ('Engineering', 'Nagpur');
INSERT INTO departments (name, location) VALUES ('HR', 'Mumbai');
INSERT INTO departments (name, location) VALUES ('Sales', 'Pune');

-- Sample addresses
INSERT INTO addresses (street, city, state, pincode) VALUES ('123 MG Road', 'Nagpur', 'Maharashtra', '440001');
INSERT INTO addresses (street, city, state, pincode) VALUES ('45 Park Street', 'Mumbai', 'Maharashtra', '400001');

-- Sample employees (address_id and department_id reference the rows above, 1-indexed)
INSERT INTO employees (name, email, salary, department_id, address_id) VALUES ('Sarthak Seth', 'sarthak@example.com', 55000.0, 1, 1);
INSERT INTO employees (name, email, salary, department_id, address_id) VALUES ('Tania Seth', 'tania@example.com', 48000.0, 2, 2);
