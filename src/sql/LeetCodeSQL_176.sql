select IFNULL((select distinct Salary SecondHighestSalary from Employee order by Salary desc limit 1 offset 1), NULL) as SecondHighestSalary