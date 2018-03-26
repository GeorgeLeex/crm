package com.neuedu.web.support;

import java.util.Map;

public interface BaseController {
    /**
     * 其子类实现业务操作所必须调用的方法, 被所有业务处理Servlet实现,动态调度方法之一
     * 在CoreController核心控制器中被调用
     * @return
     * @throws Exception
     */
    String execute() throws Exception;

    /**
     * 保存传入的dto,被抽象类ControllerSupport所实现,
     * 将数据保存在ControllerSupport中的dto里,动态调度方法之一
     * 在CoreController核心控制器中被调用
     * @param dto
     */
    void setDTO(Map<String, Object> dto);

    /**
     * 获取保存在数据信息集合里的数据,例如查询结果, 其它信息
     * 被抽象类ControllerSupport所实现
     * 在CoreController核心控制器中被调用
     * @return
     */
    Map<String, Object> getAttributes();

    /**
     * 获取将要存在session域中的数据
     * 在CoreController中被调用
     * @return
     */
    Map<String, Object> getSessionAttributes();
    
    /**
     * 获取存储在响应信息集合中的响应信息
     * 在CoreController中被调用
     * @param key
     * @return
     */
    Map<String, Object> getResponseMessages();
}
