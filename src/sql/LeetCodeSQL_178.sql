select Score,
       (select count(distinct score) from scores where Score >= a.Score) Rank # 计算表中Score大于a表中Score的唯一Salary的数量，也就是a表中的score比N个数小，排第N名
from Scores a order by Score desc