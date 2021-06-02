DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
  id INT AUTO_INCREMENT ,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  email_address VARCHAR(40) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
  status VARCHAR(9) NOT NULL,
    PRIMARY KEY(first_name,last_name)
);

INSERT INTO contacts (first_name, last_name, email_address,phone_number,status) VALUES
  ('Amit', 'Walia', 'Amit.Walia@xyz.com','2323232323','Active');
