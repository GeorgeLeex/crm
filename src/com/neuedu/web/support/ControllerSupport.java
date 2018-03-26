package com.neuedu.web.support;

import com.neuedu.services.support.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ControllerSupport implements BaseController {

    /**
     * 保存页面传输数据转换后的数据
     */
    private Map<String, Object> dto;

    /**
     * 维护的BaseService接口,为了方便ControllerSupport的子类调用该接口的实现类中业务处理方法
     */
    protected BaseService baseService;

    /**
     * 将数据保存在dto里,动态调度方法之一
     * 在CoreController核心控制器中被调用
     * @param dto
     */
    @Override
    public void setDTO(final Map<String, Object> dto) {
        this.dto = dto;
        //将dto数据保存在BaseService的实现类ServiceSupport中的dto里,方便其子类 ServiceImpl调用
        this.baseService.setMapDTO(dto);
    }

    /**
     * 为子类提供获取dto的方法
     * @return
     */
    protected Map<String, Object> getDTO() {
        return this.dto;
    }

    /**
     * 添加数据至dto
     * @param key
     * @param value
     */
    protected void addValueToDto(final String key, final Object value) {
        this.dto.put(key, value);
    }

    /**
     * 为子类提供向JdbcSupport中的dto添加数据的方法,用于在子类中进行多步操作时额外添加数据供以ServiceImpl调用
     * @param key
     * @param value
     */
    protected void addValueToJdbcDTO(final String key, final Object value) {
    	this.baseService.addValueToDTO(key, value);
    }
    
    /**
     * 为子类提供从dto中获取数据的方法
     * @param key
     * @return
     */
    protected Object getValueFromDto(final String key) {
    	return this.dto.get(key);
    }
    
    
    /**
     * 数据信息集合, 包含查询结果及其它信息
     */
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * 方便业务处理的Servlet调用, 添加查询结果及其他信息
     * @param key
     * @param value
     */
    protected void addAttribute(final String key,final Object value) {
        attributes.put(key, value);
    }

    /**
     * 获取保存在数据信息集合里的数据,例如查询结果, 其它信息
     * 在CoreController核心控制器中被调用
     * @return
     */
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    /**
     * 用于存放将要存入session域中的数据的集合
     */
    private Map<String, Object> sessionAttributes = new HashMap<>();

    /**
     * 获取将要存入session域的数据
     */
    @Override
    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    /**
     * 添加将要存入session域中的数据
     * @param key
     * @param value
     */
    protected void putSessionAttributes(final String key, final Object value) {
        this.sessionAttributes.put(key, value);
    }

    /**
     * 响应信息集合
     */
    private Map<String,Object> responseMessage = new HashMap<>();
    
    /**
     * 将要响应的数据添加至响应信息集合
     * @param key
     * @param value
     */
    protected void addResponseMessage(String key, Object value) {
    	this.responseMessage.put(key, value);
    }
    
    /**
     * 获取响应信息集合中的数据
     */
    @Override
    public Map<String, Object> getResponseMessages() {
    	return this.responseMessage;
    }
    
    /**
     * 通用更新的简化操作
     * @param type 操作类型
     * @param msg  操作成功或失败的相关信息
     * @return
     * @throws Exception
     */
    protected final void simpleUpdate(String type, String msg) throws Exception {
        String message = this.baseService.update(type) ? "成功!" : "失败!";
        this.addAttribute("msg", msg + message);
    }

    /**
     * 通用单例查询的简化操作
     * @return
     * @throws Exception
     */ 
    protected final void simpleFindOne() throws Exception {
        Map<String, Object> one = this.baseService.queryForMap();
        if (one != null && one.size() > 0) {
            this.addAttribute("row", one);
        } else {
            this.addAttribute("msg", "未找到匹配数据!");
        }
    }

    
    /**
     * 通用多例查询的简化操作
     * @throws Exception
     */
    protected final void simpleFindAll() throws Exception {
        List<Map<String, Object>> all = this.baseService.queryForList();
        if (all != null && all.size() > 0) {
            this.addAttribute("rows", all);
        } else {
            this.addAttribute("msg", "未找到匹配数据!");
        }                               
    }
    /**
     * 多例分页查询的简化操作,会把分页的查询结果存入request域中
     * @throws Exception
     */
    protected final void simpleFindAllByPage() throws Exception {
    	List<Map<String, Object>> all = this.baseService.queryForList();
    	Map<String, Object> map = all.get(0);
		if (map != null && map.size() > 0) {
			this.addAttribute("rows", map.get("rows"));
			this.addAttribute("nowPage", map.get("nowPage"));
			this.addAttribute("pageCount", map.get("pageCount"));
			this.addAttribute("rowCount", map.get("rowCount"));
			this.addAttribute("numbers", map.get("numbers"));
		}
    }
    
    /**
     * 更新操作后数据重查
     * @throws Exception
     */
    protected final void queryAfterUpdate() throws Exception {
        List<Map<String, Object>> all = this.baseService.queryForList();
        if (all != null && all.size() > 0) {
            this.addAttribute("rows", all);
        }
    }
}
