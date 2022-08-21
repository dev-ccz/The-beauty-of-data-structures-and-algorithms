# mysql-index

```
CREATE TABLE `t_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `id_no` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份编号',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `union_idx` (`id_no`,`username`,`age`),
  KEY `create_time_idx` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
```
准备上面这张表，同时插入一些数据。
```
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000001','name-20220000001',18);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000002','name-20220000002',19);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000003','name-20220000003',20);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000004','name-20220000004',21);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000005','name-20220000005',22);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000006','name-20220000006',23);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000007','name-20220000007',24);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000008','name-20220000008',25);
INSERT INTO t_user (`id_no`,`username`,`age`) VALUES ('20220000009','name-20220000009',26);
```
数据库版本 8.0.26
### 1. 联合索引不满足最左匹配原则 

- 执行sql： 
```
EXPLAIN SELECT * FROM t_user WHERE id_no='20220000001'
```
很明显，这个命中了union_idx索引。结果如下：

    id  select_type  table   partitions  type    possible_keys  key        key_len  ref       rows  filtered  Extra   
------  -----------  ------  ----------  ------  -------------  ---------  -------  ------  ------  --------  --------
     1  SIMPLE       t_user  (NULL)      ref     union_idx      union_idx  75       const        1    100.00  (NULL)  

这里补充一下key_len的计算方法：
varchar(18)，字符集为utf8mb4，是使用4个字节来表示一个完整的UTF-8，key_length=18*4;
因为是varchar边长类型，需要再加上两个字节： 18*4+2=74. 再加上该字段默认为null的，length=75.

- 执行sql
```
EXPLAIN SELECT * FROM t_user WHERE id_no='20220000002' AND  username='name-20220000002' 
```
    id  select_type  table   partitions  type    possible_keys  key        key_len  ref            rows  filtered  Extra   
------  -----------  ------  ----------  ------  -------------  ---------  -------  -----------  ------  --------  --------
     1  SIMPLE       t_user  (NULL)      ref     union_idx      union_idx  206      const,const       1    100.00  (NULL)  

大胆猜测，命中了union_idx索引，并且使用了 id_no和username.

- 执行sql
```
EXPLAIN SELECT * FROM t_user WHERE id_no='20220000002' AND  age=19
```
命中了union_idx索引，并且只使用了id_no

下面两条是否会命中呢？
```
EXPLAIN SELECT * FROM t_user WHERE username='name-20220000002' age=19;
EXPLAIN SELECT * FROM t_user WHERE age=19 AND username='name-20220000002' ;
```
### 2.使用了select *
不适用select * ,可能会走到覆盖索引。
```
EXPLAIN SELECT id_no,username,age FROM t_user WHERE  username='name-20220000002' AND age=19;
EXPLAIN SELECT id_no,username,age FROM t_user WHERE age=16;
```
    id  select_type  table   partitions  type    possible_keys  key        key_len  ref                  rows  filtered  Extra        
------  -----------  ------  ----------  ------  -------------  ---------  -------  -----------------  ------  --------  -------------
     1  SIMPLE       t_user  (NULL)      ref     union_idx      union_idx  211      const,const,const       1    100.00  Using index  

通过key_length,我们可以分析出走到了哪个索引。

### 3 索引列参与运算
### 4 索引列参使用了函数
### 5 错误的Like使用
### 6 类型隐式转换
### 7.使用OR操作  查询条件使用or关键字，其中一个字段没有创建索引，则会导致整个查询语句索引失效；or两边为“>”和“<”范围查询时，索引失效。
### 8.两列做比较
```
EXPLAIN SELECT * FROM t_user WHERE id < age;
```

   id  select_type  table   partitions  type    possible_keys  key     key_len  ref       rows  filtered  Extra        
------  -----------  ------  ----------  ------  -------------  ------  -------  ------  ------  --------  -------------
     1  SIMPLE       t_user  (NULL)      ALL     (NULL)         (NULL)  (NULL)   (NULL)       9     33.33  Using where  

### 9 不等于比较
```
EXPLAIN SELECT * FROM t_user WHERE id_no <> '1002';
```
    id  select_type  table   partitions  type    possible_keys  key     key_len  ref       rows  filtered  Extra        
------  -----------  ------  ----------  ------  -------------  ------  -------  ------  ------  --------  -------------
     1  SIMPLE       t_user  (NULL)      ALL     union_idx      (NULL)  (NULL)   (NULL)       9    100.00  Using where  

### 10 is not null
```
EXPLAIN SELECT * FROM t_user WHERE id_no IS NOT NULL;
```

    id  select_type  table   partitions  type    possible_keys  key     key_len  ref       rows  filtered  Extra        
------  -----------  ------  ----------  ------  -------------  ------  -------  ------  ------  --------  -------------
     1  SIMPLE       t_user  (NULL)      ALL     union_idx      (NULL)  (NULL)   (NULL)       9    100.00  Using where  

### 11 not in和not exists

```
EXPLAIN SELECT * FROM t_user WHERE id_no NOT IN('1002' , '1003');
```
  id  select_type  table   partitions  type    possible_keys  key     key_len  ref       rows  filtered  Extra        
------  -----------  ------  ----------  ------  -------------  ------  -------  ------  ------  --------  -------------
     1  SIMPLE       t_user  (NULL)      ALL     union_idx      (NULL)  (NULL)   (NULL)       9    100.00  Using where  

可以看到，全表扫描了。

小技巧，当使用not in时，不走索引？把条件列换成主键试试：
```
EXPLAIN SELECT * FROM t_user WHERE id NOT IN (2,3);
```
   id  select_type  table   partitions  type    possible_keys  key      key_len  ref       rows  filtered  Extra        
------  -----------  ------  ----------  ------  -------------  -------  -------  ------  ------  --------  -------------
     1  SIMPLE       t_user  (NULL)      range   PRIMARY        PRIMARY  4        (NULL)      11    100.00  Using where  


查询条件使用not in时，如果是主键则走索引，如果是普通索引，则索引失效。

```
EXPLAIN SELECT * FROM t_user u1 WHERE NOT EXISTS (SELECT 1 FROM t_user u2 WHERE u2.id  = 2 AND u2.id = u1.id);
```

    id  select_type  table   partitions  type    possible_keys  key      key_len  ref       rows  filtered  Extra                                 
------  -----------  ------  ----------  ------  -------------  -------  -------  ------  ------  --------  --------------------------------------
     1  SIMPLE       u1      (NULL)      ALL     (NULL)         (NULL)   (NULL)   (NULL)       9    100.00  (NULL)                                
     1  SIMPLE       u2      (NULL)      const   PRIMARY        PRIMARY  4        const        1    100.00  Using where; Not exists; Using index 

查询条件使用not exists时，索引失效。



另外的一些情况：


当Mysql发现通过索引扫描的行记录数超过全表的10%-30%时，优化器可能会放弃走索引，自动变成全表扫描。某些场景下即便强制SQL语句走索引，也同样会失效。

类似的问题，在进行范围查询（比如>、< 、>=、<=、in等条件）时往往会出现上述情况，而上面提到的临界值根据场景不同也会有所不同。

第十四种索引失效情况：当查询条件为大于等于、in等范围查询时，根据查询结果占全表数据比例的不同，优化器有可能会放弃索引，进行全表扫描。