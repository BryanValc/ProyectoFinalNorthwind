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