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
