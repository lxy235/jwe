package com.backend.services;

import com.frame.drivers.mysql.Statement;
import com.backend.models.UserModel;
import com.frame.utils.Hash;

import java.util.HashMap;

public class UserService extends Service {

    protected UserModel userModel = null;

    public UserService() {
        userModel = new UserModel();
    }

    public boolean login(String username, String password, HashMap<String, Object> userinfo) {
        Statement statement = userModel.getStatement();
        statement.setWhere("uname = ? and pwd = ?");
        statement.setParam(1, username);
        statement.setParam(2, Hash.md5(password));
        HashMap<String, Object> _userinfo = userModel.fetchOne("*", statement);
        if(_userinfo.isEmpty()) {
            return false;
        }
        _userinfo.remove("pwd");
        userinfo.put("userinfo", _userinfo);
        return true;
    }
}
