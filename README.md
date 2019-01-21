[TOC]

# 目录说明
```text
drivers/ 数据驱动，包括db和缓存驱动
models/ 数据模型，以数据表为单位
services/ 业务逻辑层
servlets/ 控制器层
utils/ 常用工具类
App 应用类，负责应用的初始化和调度
Db 数据层的门脸类
View 视图层的门脸类
```

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

#获取一条记录（对象方式，防sql注入，推荐）
Statement statement = user.getStatement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
HashMap<String, Object> userinfo = user.fetchOne("id,name", statement);
System.out.println(userinfo);
System.out.println(userinfo.isEmpty());

#获取一条记录（对象方式，防sql注入，推荐）
#设置字段
Field field = user.getField();
String[] fields = {"id","name","age"};
field.setField(fields);
field.setField("sex");
#设置查询条件
Statement statement = user.getStatement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
HashMap<String, Object> userinfo = user.fetchOne(field, statement);
System.out.println(userinfo);
System.out.println(userinfo.isEmpty());


#获取全部记录
ArrayList<HashMap<String, Object>> list = user.fetchAll("id,name", "id>13705 and id<13710");
System.out.println(list);
System.out.println(list.isEmpty());

#获取全部记录（对象方式，防sql注入，推荐）
Statement statement = user.getStatement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
ArrayList<HashMap<String, Object>> list = user.fetchAll("id,name", statement);
System.out.println(list);
System.out.println(list.isEmpty());

#获取全部记录（对象方式，防sql注入，推荐）
#设置字段
Field field = user.getField();
String[] fields = {"id","name","age"};
field.setField(fields);
field.setField("sex");
#设置查询条件
Statement statement = user.getStatement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
ArrayList<HashMap<String, Object>> list = user.fetchAll(field, statement);
System.out.println(list);
System.out.println(list.isEmpty());


#插入数据
RowSet insData = user.getRowSet();
insData.addString("name", "lxy");
insData.addString("sex", "M");
insData.addNumber("age", 35);
user.insert(insData);


#更新数据(根据where条件更新数据)
String sets = "uname='test', pwd='123'";
String where = "id>13705 and id<13710";
Integer num = user.update(sets, where);

#更新数据(根据主键更新数据)
String sets = "uname='test', pwd='123'";
Integer id = 13705;
Integer num = user.update(sets, id);

#更新数据
RowSet upData = user.getRowSet();
upData.addSet("uname", "123");
upData.addSet("pwd", "234");
//statement参数使用对象方式，防sql注入，推荐
Statement statement = user.getStatement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
Integer num = user.update(upData, statement);


#删除数据(根据where条件删除数据)
String where = "id>13705 and id<13710";
Integer num = user.delete(where);

#删除数据(根据主键删除数据)
int id = 13705;
Integer num = user.delete(id);

#删除数据（Statement statement）
Statement statement = user.getStatement();
statement.setWhere("id > ? and id < ?");
statement.setParam(1, 13705);
statement.setParam(2, "13709 or 1=1");
Integer num = user.delete(statement);
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
    db.beginTransaction(); //开始事务
    ......
    db.commit(); //提交事务
} catch (Exception e) {
    db.rollback(); //事务回滚
}
```
#使用视图
```java
//视图展示1
view.assign("title", "Hello");
view.render("user/login");

//视图展示2
HashMap<String, Object> viewData = new HashMap<String, Object>();
viewData.put("title", "Hello");
view.render("user/login", viewData);
```