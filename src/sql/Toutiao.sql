
select t.s_id from sub t where (select count(s.subject) from sub s where t.s_id = s.s_id and s.score < 60) >= 2