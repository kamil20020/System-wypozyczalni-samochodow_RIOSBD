

-- Create Cars table
CREATE TABLE IF NOT EXISTS Cars (
                      ID INT PRIMARY KEY,
                      Manufacturer VARCHAR(50),
                      Model VARCHAR(50),
                      Type VARCHAR(50),
                      Color VARCHAR(20),
                      Licence_number VARCHAR(20),
                      Cost_15min DECIMAL(10, 2),
                      Photo BLOB -- Assuming Photo is a binary field (BLOB)
);

-- Create Clients table
CREATE TABLE IF NOT EXISTS Clients (
                         ID INT PRIMARY KEY,
                         Name VARCHAR(50),
                         Last_name VARCHAR(50),
                         Phone_number INT,
                         Email VARCHAR(100),
                         Birth_date DATE,
                         Client_code INT
);

-- Create Cars_Clients table
CREATE TABLE IF NOT EXISTS Cars_Clients (
    ID INT PRIMARY KEY,
    KlientID INT,
    CarsID INT,
    Rental_date DATE,
    Return_date DATE,
    Total_cost DECIMAL(10, 2),
    FOREIGN KEY (KlientID) REFERENCES Clients(ID),
    FOREIGN KEY (CarsID) REFERENCES Cars(ID)
);