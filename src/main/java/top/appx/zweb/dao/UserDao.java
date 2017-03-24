package top.appx.zweb.dao;

import top.appx.zutil.DateTimeUtil;
import top.appx.zutil.StringUtil;
import top.appx.zutil.easysql.BaseDatabase;
import top.appx.zutil.easysql.Restrain;
import top.appx.zutil.eweb.MsgException;
import top.appx.zutil.eweb.PageInfo;
import top.appx.zutil.eweb.PageResultInfo;
import top.appx.zutil.info.ResultMap;
import top.appx.zweb.entity.RoleEntity;
import top.appx.zweb.entity.UserEntity;
import top.appx.zweb.entity.UserRoleEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mrz on 2017/3/23.
 */
public class UserDao extends BaseDao {


    public Object queryPage(PageInfo pageInfo,Restrain... restrains) throws SQLException {
        try(BaseDatabase db = dh.createDatabase()){
            PageResultInfo pageResultInfo = db.queryPage(pageInfo,restrains);
            List<Object> rows = new ArrayList<Object>();
            List<UserEntity> userEntityList = pageResultInfo.getRows();
            String userIds = "";
            for (UserEntity userEntity : userEntityList) {
                if(userIds.length()!=0){
                    userIds+=",";
                }
                userIds += userEntity.getId();
            }

//            List<RoleEntity> roleEntityList = db.queryBySql("select * from role wh);

            List<UserRoleEntity> userRoleEntityList = userIds.length()==0?new ArrayList<>(): db.queryBySql("select * from userRole where userId in ("+userIds+")",UserRoleEntity.class);
            String roleIds_tmp = "";
            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                if(roleIds_tmp.length()!=0){
                    roleIds_tmp += ",";
                }
                roleIds_tmp+=userRoleEntity.getRoleId();
            }
            List<RoleEntity> roleEntityList = roleIds_tmp.length()==0?new ArrayList<>(): db.queryBySql("select * from role where id in ("+roleIds_tmp+")",RoleEntity.class);

            for (UserEntity userEntity : userEntityList) {
                String roleNames = "";
                String roleIds = "";
                for(UserRoleEntity userRoleEntity : userRoleEntityList){
                    if(userEntity.getId().equals(userRoleEntity.getUserId())){
                        for(RoleEntity roleEntity : roleEntityList){
                            if(roleEntity.getId().equals(userRoleEntity.getRoleId())){
                                if(roleNames.length()!=0){
                                    roleNames+=",";
                                    roleIds += ",";
                                }
                                roleNames += roleEntity.getName();
                                roleIds += roleEntity.getId();
                            }
                        }
                    }
                }
                rows.add(ResultMap.data("roleNames",roleNames).p("roleIds",roleIds).p(userEntity));
            }

            pageResultInfo.setRows(rows);
            return pageResultInfo;
        }
    }

    public void save(UserEntity entity,String roleIds) throws SQLException {
        try(BaseDatabase db = dh.createDatabase()){
            db.beginTransaction();
            db.saveAutoSetId(entity);
            int c = db.total("select count(*) from user where username=?",entity.getUsername());
            if(c>1){
                throw new MsgException("已存在相同的用户名");
            }

            if(!StringUtil.isNullOrEmpty(roleIds)) {
                for (String s : roleIds.split(",")) {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setUserId(entity.getId());
                    userRoleEntity.setRoleId(Integer.parseInt(s));
                    db.save(userRoleEntity);
                }
            }


            db.commitTranscation();
        }
    }

    public void update(UserEntity entity,String roleIds)throws SQLException{
        entity.setPassword(null);//不修改密码
        entity.setUsername(null);//不修改用户名
        try(BaseDatabase db = dh.createDatabase()){
            db.update(entity);
            db.execute("delete from userrole where userId=?",entity.getId());
            if(!StringUtil.isNullOrEmpty(roleIds)) {
                for (String s : roleIds.split(",")) {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setUserId(entity.getId());
                    userRoleEntity.setRoleId(Integer.parseInt(s));
                    db.save(userRoleEntity);
                }
            }
        }

    }
    public UserEntity login(UserEntity entity) throws SQLException {
        try(BaseDatabase db = dh.createDatabase()){
            List<UserEntity> list = db.queryBySql("select * from user where username=?",UserEntity.class,entity.getUsername());
            if(list.size()==0){
                throw new MsgException("用户名不存在");
            }
            UserEntity userEntity = list.get(0);
            if(userEntity.getStatus()!=1){
                throw new MsgException("用户当前状态不能登录");
            }
            if (userEntity.getPassword() != null && entity.getPassword().equals(entity.getPassword())) {
                //成功
                db.execute("update user set lastLogin=? where id=?", DateTimeUtil.now(), userEntity.getId());
                userEntity.setPassword(null);
                return  userEntity;
            } else {
                throw new MsgException("用户名或密码错误");
            }

        }
    }


}
