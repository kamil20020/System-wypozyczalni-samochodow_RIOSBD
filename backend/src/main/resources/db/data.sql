INSERT INTO Cars (ID, Manufacturer, Model, Type, Color, Licence_number, Cost_15min, Photo)
VALUES
    (1, 'Ford', 'Focus', 'Kombi', 'Niebieski', 'DW21345', 7, NULL),
    (2, 'Opel', 'Astra', 'Sedan', 'Czarny', 'DWZA213', 5.12, NULL);

-- Insert records into Clients table
INSERT INTO Clients (ID, Name, Last_name, Phone_number, Email, Birth_date, Client_code)
VALUES
    (1, 'Jan', 'Kowalski', 731232734, 'jan.kowalski@o2.pl', '1995-11-20', 181733435),
    (2, 'Tomasz', 'Nowak', 605123852, 'tomasz.nowak@wp.pl', '1935-11-12', 136354433);

-- Insert records into Cars_Clients table
INSERT INTO Cars_Clients (ID, KlientID, CarsID, Rental_date, Return_date, Total_cost)
VALUES
    (1, 1, 1, '2023-11-25', '2023-11-29', 743.22),
    (2, 2, 2, '2023-11-21', '2023-12-27', 1264.66);