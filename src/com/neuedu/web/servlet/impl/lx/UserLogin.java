package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.UserLoginServiceSupport;

/**
 * 
 * @author 李宣
 * @time 2018年3月7日 下午2:49:49
 * 用于处理用户登录
 */
public class UserLogin extends UserLoginServiceSupport{

	
	@Override
	public String execute() throws Exception {
		//标准form表单提交实现
		/*//要跳转的页面名,默认为登录失败要跳转的页面,此处登录失败重新跳转至登录页面
		String page = "login2";
		//根据用户名与密码获取到用户信息集合
		Map<String, Object> map = this.baseService.queryForMap();
		//存在该用户
		if (map != null && map.size() > 0) {
			//将用户信息集合添加至session中
			this.putSessionAttributes("userInfoMap", map);
			//修改要跳转的页面.跳转至首页main
			page = "main";
		}
		//返回结果,该返回值为接下来要跳转的页面
		return page;*/
		
		//ajax处理用户登录请求的实现
		//根据用户名与密码获取到用户信息集合
		Map<String, Object> map = this.baseService.queryForMap();
		String loginInfo = "NO";
		//如果存在记录则修改loginInfo为 "OK"以响应前端ajax请求
		if (map != null && map.size() > 0) {
			//用户名密码正确,在进行跳转之前将下拉菜单数据和菜单列表数据初始化至session域中
			loginInfo = "OK";
			//获取用户编号
			Object usNo = map.get("us_no");
			//将用户编号添加至dto让UserService根据其身份查询菜单
			this.addValueToJdbcDTO("usNo", usNo);
			//查询获取下拉菜单数据
			List<Map<String, Object>> all = this.baseService.queryForList();
			//获取存放下拉菜单数据的Map集合
			Map<String, Object> selectMap = all.get(0);
			//将下拉菜单数据存入session域中
			this.putSessionAttributes("managers", (List<Map<String, Object>>)selectMap.get("managers"));
			this.putSessionAttributes("xys", (List<Map<String, Object>>)selectMap.get("xys"));
			this.putSessionAttributes("pxs", (List<Map<String, Object>>)selectMap.get("pxs"));
			this.putSessionAttributes("djs", (List<Map<String, Object>>)selectMap.get("djs"));
			this.putSessionAttributes("lbs", (List<Map<String, Object>>)selectMap.get("lbs"));
			//将用户信息集合添加至session中
			this.putSessionAttributes("userInfoMap", map);
			//取出用户的菜单添加至session域
			Map<String, List<Map<String, Object>>> map1 = (Map<String, List<Map<String, Object>>>) selectMap.get("menus");
			this.putSessionAttributes("menus", map1);
		}
		//添加响应信息
		this.addResponseMessage("loginInfo", loginInfo);
		// "response:"表示只通过response的PrintWriter对象响应响应信息,不进行页面跳转,页面跳转流程控制交给前端js
		return "response:";
	}

}
