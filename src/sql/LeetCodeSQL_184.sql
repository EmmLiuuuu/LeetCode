select Department.Name as Department,   # 三个需要查询的字段
       Employee.Name as Employee,
       Salary
from Employee join Department on DepartmentId = Department.id # 临时表
where (DepartmentId, Salary) in # 再从临时表中进行条件筛选
      (select DepartmentId, MAX(distinct Employee.salary) as Salary from Employee group by Employee.DepartmentId)