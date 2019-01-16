# 使用数据模型

```java
UserModel user = new UserModel();

#按照条件获取一条记录
HashMap<String, Object> userinfo = user.fetchOne("id,name", "name = 'lxy2' and pass = '123456'");
System.out.println(userinfo);
System.out.println(userinfo.isEmpty());

#按照主键获取一条记录
HashMap<String, Object> userinfo = user.fetchOne("id,name", 2);
System.out.println(userinfo);
System.out.println(userinfo.isEmpty());

#获取全部记录
ArrayList<HashMap<String, Object>> list = user.fetchAll("id,name", "id>13705 and id<13710");
System.out.println(list);
System.out.println(list.isEmpty());

#获取全部记录（对象方式，防sql注入，推荐）
Statement statement = new Statement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
ArrayList<HashMap<String, Object>> list = user.fetchAll("id,name", statement);
System.out.println(list);
System.out.println(list.isEmpty());
        
#插入数据
RowSet insData = new RowSet();
insData.addString("name", "lxy");
insData.addString("sex", "M");
insData.addNumber("age", 35);
user.insert(insData);
```

# 使用原生SQL
```java
DbDriver db = App.getDb().getDbDriver();
db.query("select * from `t_user`");
db.execute("insert into `t_user` (`name`, `pass`) values ('admin','123456')");
```

# 使用事务
```java
try {
    db.startTransaction(); //开始事务
    ......
    db.endTransaction(); //提交事务
} catch (Exception e) {
    db.rollback(); //事务回滚
}
```