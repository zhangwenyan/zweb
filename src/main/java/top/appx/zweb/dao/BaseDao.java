package top.appx.zweb.dao;

import top.appx.zutil.easysql.BaseDBHelper;
import top.appx.zutil.easysql.DBHelperFactory;

/**
 * Created by mrz on 2017/3/23.
 */
public abstract class BaseDao {
    private String url ="jdbc:mysql://localhost:3306/zweb?useUnicode=true&characterEncoding=utf-8";
    private String user = "root";
    private String password = "root";
    protected BaseDBHelper dh = DBHelperFactory.createMySqlDBHelper(url,user,password);



}
