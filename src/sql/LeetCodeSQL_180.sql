# select distinct l1.Num As ConsecutiveNums 如果不加distinct就会返回3个l1
# from Logs l1, Logs l2, Logs l3  # 为了后面对比三个数
# where l1.id = l2.id - 1 and # 第二位数
# l2.id = l3.id - 1 and # 第三位数
# l1.Num = l2.Num and # 如果三个都相等的话，返回l1
# l2.Num = l3.Num;

select distinct l1.Num as ConsecutiveNums
from Logs l1
where l1.Num = (select Num from Logs where id = l1.id + 1) # 与后面的对比
  and l1.Num = (select Num from Logs where id = l1.id + 2)