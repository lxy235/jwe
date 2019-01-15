# 数据模型使用

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

#获取全部数据
ArrayList<HashMap<String, Object>> list = user.fetchAll("id,name", "id>13705 and id<13710");
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

```
