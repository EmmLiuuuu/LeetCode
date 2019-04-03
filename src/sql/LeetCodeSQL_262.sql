select request_at as day,
       round(sum(case when status <> 'completed' then 1 else 0 end)/count(*),2)
                  as 'Cancellation Rate'
from Trips T
       inner join
     (select Users_Id Client_Id from Users where Banned='No' and role = 'client') C
     on
         T.Client_id = C.Client_id
where request_at between '2013-10-01' and '2013-10-03'
group by
  request_at

# 等死吧，没救了

select Request_at as Day,
       round(sum(case when `status` <> 'completed' then 1 else 0 end) / count(*), 2)
                  as `Cancellation Rate` from Trips t where Client_Id in (select Users_Id from Users where
    Banned = 'no' and Role = 'client') and Request_at between '2013-10-01' and '2013-10-03' group by Request_at