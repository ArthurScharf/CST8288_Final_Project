USE transport;

DROP TABLE IF EXISTS Vehicle;

CREATE TABLE Vehicle (
    VehicleNumber VARCHAR(10) PRIMARY KEY,
    TypeInfo VARCHAR(200) NULL NULL,
    MaximumPassengers INT NOT NULL,
    RouteID INT, -- Assuming RouteID is an INT and can be NULL initially
    TotalDistanceTraveled DECIMAL(10, 2) -- DECIMAL for precise distance, adjust precision/scale as needed
);

INSERT INTO Vehicle (VehicleNumber, TypeInfo, MaximumPassengers, RouteID, TotalDistanceTraveled) VALUES
('V001', 'DieselElectric|150.0|200.0|500.0|750.0', 60, 101, 25000.75),
('V002', 'LightRail|180.0|250.0', 120, 201, 45000.90),
('V003', 'Bus|Diesel|80.0|100.0', 50, 301, 18000.50),
('V004', 'DieselElectric|140.0|190.0|480.0|720.0', 55, 102, 23500.67),
('V005', 'LightRail|170.0|240.0', 110, 202, 42500.10),
('V006', 'Bus|CNG|75.0|95.0', 48, 302, 16500.25),
('V007', 'DieselElectric|160.0|210.0|520.0|780.0', 65, 103, 26500.43),
('V008', 'LightRail|190.0|260.0', 130, 203, 48000.54),
('V009', 'Bus|Diesel|85.0|105.0', 52, 303, 19500.56),
('V010', 'DieselElectric|130.0|180.0|460.0|700.0', 50, 104, 22000.54),
('V011', 'LightRail|160.0|230.0', 100, 204, 39000.45),
('V012', 'Bus|CNG|70.0|90.0', 45, 304, 15000.75),
('V013', 'DieselElectric|155.0|205.0|510.0|760.0', 62, 105, 25500.12),
('V014', 'LightRail|185.0|255.0', 125, 205, 46500.78),
('V015', 'Bus|Diesel|78.0|98.0', 49, 305, 17000.01),
('V016', 'DieselElectric|145.0|195.0|490.0|730.0', 58, 106, 24000.00),
('V017', 'LightRail|175.0|245.0', 115, 206, 43500.00),
('V018', 'Bus|CNG|82.0|102.0', 51, 306, 18500.00),
('V019', 'DieselElectric|135.0|185.0|470.0|710.0', 53, 107, 22800.00),
('V020', 'LightRail|168.0|238.0', 108, 207, 40500.00);


SELECT * FROM Vehicle;

DELETE FROM Vehicle;



/* ----- Operators ----- */


DROP TABLE IF EXISTS Operators;

CREATE TABLE Operators (
    OperatorID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Role VARCHAR(50) NOT NULL, -- e.g., 'Manager', 'Operator'
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(50) NOT NULL -- In a real application, store hashed passwords!
);


INSERT INTO OPERATORS (OperatorID, FirstName, LastName, Role, Username, Password) VALUES
(1, 'John', 'Doe', 'Operator', 'jdoe', 'password123'),
(2, 'Jane', 'Smith', 'Manager', 'jsmith', 'adminpass'),
(3, 'Peter', 'Jones', 'Operator', 'pjones', 'userpass456'),
(4, 'Mary', 'Brown', 'Operator', 'mbrown', 'secureuser'),
(5, 'David', 'Lee', 'Manager', 'dlee', 'managerpass'),
(6, 'Sarah', 'Miller', 'Operator', 'smiller', 'operator1'),
(7, 'Chris', 'Wilson', 'Operator', 'cwilson', 'operator2'),
(8, 'Anna', 'Garcia', 'Operator', 'agarcia', 'operator3'),
(9, 'Mark', 'Davis', 'Operator', 'mdavis', 'operator4'),
(10, 'Arthur', 'Scharf', 'Manager', 'cst8288', 'cst8288');

SELECT * FROM OPERATORS;


CREATE TABLE Notification (
	ID INT auto_increment PRIMARY KEY,
    Type ENUM('MAINTENANCE', 'FUEL', 'BREAK', 'UNKNOWN') NOT NULL,
    Data VARCHAR(1000) NOT NULL
);

SELECT * FROM Notification;

DELETE FROM Notification;



