CREATE TABLE "Usuarios" (
    "username" varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL);

GO

INSERT "Usuarios" VALUES ('admin', 'admin');

create view "Products by Category" AS
SELECT Categories.CategoryName, Products.ProductName, Products.QuantityPerUnit, Products.UnitsInStock, Products.Discontinued
FROM Categories INNER JOIN Products ON Categories.CategoryID = Products.CategoryID
WHERE Products.Discontinued <> 1
--ORDER BY Categories.CategoryName, Products.ProductName
GO

-- CREATE TABLE "bkp_Categories" (
--     "DeletionTime" datetime,
--     "CategoryID" int NOT NULL,
--     "CategoryName" varchar(255) NOT NULL,
--     "Description" varchar(255) NULL,
--     "Picture" "image" NULL ,
--     CONSTRAINT "PK_bkp_Categories" PRIMARY KEY NONCLUSTERED
--     (
--         "DeletionTime","CategoryID"
--     )    
-- )

-- CREATE TRIGGER TR_Categories_delete
-- ON [dbo].[Categories] FOR DELETE
-- AS
-- BEGIN

-- SET NOCOUNT ON;
-- INSERT INTO bkp_Categories (DeletionTime,CategoryID,CategoryName,Description,Picture)
-- SELECT CURRENT_TIMESTAMP, CategoryID,CategoryName,Description,Picture 
-- FROM deleted

-- END

-- GO

CREATE TABLE "bkp_Products" (
    "DeletionTime" datetime,
    "ProductID" int NOT NULL,
    "ProductName" varchar(40) NOT NULL,
    "SupplierID" int NULL,
    "CategoryID" int NULL,
    "QuantityPerUnit" varchar(20) NULL,
    "UnitPrice" money NULL,
    "UnitsInStock" smallint NULL,
    "UnitsOnOrder" smallint NULL,
    "ReorderLevel" smallint NULL,
    "Discontinued" bit NOT NULL,
    CONSTRAINT "PK_bkp_Products" PRIMARY KEY NONCLUSTERED
    (
        "DeletionTime","ProductID"
    )    
)

    CREATE TRIGGER TR_Products_delete
    ON [dbo].[Products] FOR DELETE

    AS
    BEGIN
    SET NOCOUNT ON;
    INSERT INTO [dbo].[bkp_Products] (DeletionTime,ProductID,ProductName,SupplierID,CategoryID,QuantityPerUnit,UnitPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued)
    SELECT CURRENT_TIMESTAMP, ProductID,ProductName,SupplierID,CategoryID,QuantityPerUnit,UnitPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued
    FROM deleted
    END

    GO


CREATE  INDEX "CompanyName" ON "dbo"."Suppliers"("CompanyName")
GO
CREATE  INDEX "PostalCode" ON "dbo"."Suppliers"("PostalCode")
GO

--Particionamiento

USE Northwind
GO

ALTER DATABASE Northwind
ADD FILEGROUP Northwind_1
GO

ALTER DATABASE Northwind
ADD FILEGROUP Northwind_2
GO

ALTER DATABASE Northwind
	ADD FILE 
	(
	NAME = Northwind_1,
	FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Northwind_1.ndf',
		SIZE = 10 MB,
		MAXSIZE = UNLIMITED,
		FILEGROWTH = 64 MB
		) TO FILEGROUP Northwind_1

ALTER DATABASE Northwind
	ADD FILE 
	(
	NAME = Northwind_2,
	FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Northwind_2.ndf',
		SIZE = 10 MB,
		MAXSIZE = UNLIMITED,
		FILEGROWTH = 64 MB
		) TO FILEGROUP Northwind_2


-- CREATE PARTITION FUNCTION [PF_Northwind](datetime)
-- AS RANGE LEFT FOR VALUES (N'2021-12-31T00:00:00',N'2022-12-31T00:00:00')

-- CREATE PARTITION SCHEME Particionamiento
-- AS PARTITION [PF_Northwind]
-- TO ([PRIMARY],Northwind_1,Northwind_1)
-- GO

CREATE PARTITION FUNCTION [PF_Northwind_Units](smallint)
AS RANGE LEFT FOR VALUES (0,75)

CREATE PARTITION SCHEME Particionamiento2
AS PARTITION [PF_Northwind_Units]
TO ([PRIMARY],Northwind_1,Northwind_1)
GO

USE Northwind
GO

CREATE TABLE "Products_Particionado" (
	"ProductID" "int" IDENTITY (1, 1) NOT NULL ,
	"ProductName" nvarchar (40) NOT NULL ,
	"SupplierID" "int" NULL ,
	"CategoryID" "int" NULL ,
	"QuantityPerUnit" nvarchar (20) NULL ,
	"UnitPrice" "money" NULL CONSTRAINT "DF_Products_Particionado_UnitPrice" DEFAULT (0),
	"UnitsInStock" "smallint" NULL CONSTRAINT "DF_Products_Particionado_UnitsInStock" DEFAULT (0),
	"UnitsOnOrder" "smallint" NULL CONSTRAINT "DF_Products_Particionado_UnitsOnOrder" DEFAULT (0),
	"ReorderLevel" "smallint" NULL CONSTRAINT "DF_Products_Particionado_ReorderLevel" DEFAULT (0),
	"Discontinued" "bit" NOT NULL CONSTRAINT "DF_Products_Particionado_Discontinued" DEFAULT (0),
	CONSTRAINT "FK_Products_Particionado_Categories" FOREIGN KEY 
	(
		"CategoryID"
	) REFERENCES "dbo"."Categories" (
		"CategoryID"
	),
	CONSTRAINT "FK_Products_Particionado_Suppliers" FOREIGN KEY 
	(
		"SupplierID"
	) REFERENCES "dbo"."Suppliers" (
		"SupplierID"
	),
	CONSTRAINT "CK_Products_Particionado_UnitPrice" CHECK (UnitPrice >= 0),
	CONSTRAINT "CK_Particionado_ReorderLevel" CHECK (ReorderLevel >= 0),
	CONSTRAINT "CK_Particionado_UnitsInStock" CHECK (UnitsInStock >= 0),
	CONSTRAINT "CK_Particionado_UnitsOnOrder" CHECK (UnitsOnOrder >= 0)
) ON Particionamiento2("UnitsInStock")
GO


ALTER TABLE Products_Particionado ADD CONSTRAINT "PK_Products_Particionado" PRIMARY KEY ("ProductID");


-- The next stored procedure will show us the amount of stock in dollars for each supplier.

CREATE PROCEDURE [dbo].[sp_Suppliers_Stock]
AS
SELECT 
	s.CompanyName AS Proveedor,
	SUM(p.UnitPrice * (p.UnitsInStock+p.UnitsOnOrder)) AS 'Valor en stock'
FROM Products p
JOIN Suppliers s ON p.SupplierID = s.SupplierID
GROUP BY s.CompanyName
GO

EXECUTE	[dbo].[sp_Suppliers_Stock]

