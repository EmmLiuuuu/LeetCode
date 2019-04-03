CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
    # Write your MySQL query statement below.
    select * from
      (select distinct Salary from Employee
       where N <= (select count(distinct Salary) from Employee) # 先计算唯一Salary数量，如果N大于此数，则返回NULL
       order by Salary desc limit N) as tmp order by Salary limit 1 # 先降序再升序limit1

  );
END