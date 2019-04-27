package cn.leekoko.controller.realm;

import cn.leekoko.controller.vo.User;
import cn.leekoko.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm{

    @Autowired
    private UserDao userDao;

    /**
     * 自定义角色权限
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRoleByUserName(userName);
        Set<String> permission = getPressionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permission);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPressionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    private Set<String> getRoleByUserName(String userName) {
/*        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;*/
        List<String> list = userDao.queryRolesByUserName(userName);
        Set<String> sets = new HashSet<String>(list);
        return sets;
    }

    /**
     * 自定义认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //获取凭证
        String password = getPasswordByUsername(userName);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,ByteSource.Util.bytes("leekoko"),getName());
        return authenticationInfo;
    }

    /**
     * 模拟数据库
     * @param userName
     * @return
     */
    private String getPasswordByUsername(String userName) {
        User user = userDao.getUserByUserName(userName);
        if(user != null){
            return user.getPassword();
        }
        return null;
//        return userMap.get(userName);
    }

/*    Map<String,String> userMap = new HashMap<String, String>(16);
    {
        userMap.put("leekoko","f4e926585ac943b8f1ec789a639527e0");  //md5   123456
        super.setName("customRealm");
    }*/

}
