# Write your MySQL query statement below

# select Name as Employee from Employee e where Salary > (select Salary from Employee where e.ManagerId = Id and e.Salary > Salary) # 子查询，慢

# select a.Name as Employee from Employee a, Employee b where a.ManagerId = b.Id and a.Salary > b.Salary  # 一般

# 还是join要快一点
SELECT
  a.NAME AS Employee
FROM Employee AS a JOIN Employee AS b
                        ON a.ManagerId = b.Id
                          AND a.Salary > b.Salary;