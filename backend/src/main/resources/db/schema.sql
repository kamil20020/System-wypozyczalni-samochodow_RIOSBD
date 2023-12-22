

-- Create Cars table
CREATE TABLE IF NOT EXISTS Cars (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Manufacturer VARCHAR(50) NOT NULL,
    Model VARCHAR(50) NOT NULL,
    Type VARCHAR(50) NOT NULL,
    Color VARCHAR(20) NOT NULL,
    Licence_number VARCHAR(20) NOT NULL UNIQUE,
    Cost_15min DECIMAL(10, 2) NOT NULL,
    Photo BLOB -- Assuming Photo is a binary field (BLOB)
);

-- Create Clients table
CREATE TABLE IF NOT EXISTS Clients (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Last_name VARCHAR(50) NOT NULL,
    Phone_number INT NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Birth_date DATE NOT NULL,
    Client_code INT NOT NULL
);

-- Create Cars_Clients table
CREATE TABLE IF NOT EXISTS Cars_Clients (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    KlientID INT NOT NULL,
    CarsID INT NOT NULL,
    Rental_date DATE NOT NULL,
    Return_date DATE NOT NULL,
    Total_cost DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (KlientID) REFERENCES Clients(ID),
    FOREIGN KEY (CarsID) REFERENCES Cars(ID)
);