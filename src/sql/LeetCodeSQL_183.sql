select Name as Customers from Customers a where a.Id not in (select CustomerId from Orders) # not in 用法初步学习
# not exists也可学习

select Name as Customers from Customers a where not exists (select Id from Orders where a.Id = CustomerId)