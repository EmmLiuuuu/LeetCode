# delete p1 from Person p1, Person p2 where p1.Email = p2.Email and p1.Id > p2.Id;
# select * from Person p1, Person p2 where p1.Email = p2.Email and p1.Id > p2.Id;
# 我是弱智，这都没转过来

delete from Person where Id not in (select * from (select min(Id) from Person group by Email) as p);
select * from Person;