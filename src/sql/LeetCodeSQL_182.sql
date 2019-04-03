select Email
from Person
group by Email
having count(Email) > 1; # having新的关键词认识之旅。。

# low逼解法
# select distinct Email from Person a where (select count(*) from Person where a.Email = Email) > 1