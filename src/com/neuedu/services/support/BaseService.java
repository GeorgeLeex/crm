package com.neuedu.services.support;

import java.util.List;
import java.util.Map;

public interface BaseService {

    /**
     * 在CoreController调用BaseController中被ControllerSupport实现的setDTO方法时
     * 在setDTO方法里调用BaseService中被ServiceSupport实现的setMatDto方法将其dto作为副本保存在ServiceSupport的dto中
     * @param dto
     */
    void setMapDTO(Map<String, Object> dto);

    /**
     * 添加数据至JdbcSupport中的dto中,在JdbcSupport中被实现
     * @param key
     * @param value
     */
    void addValueToDTO(String key, Object value);
    
    /**
     * 单例查询的方法,在 ServiceImpl中被重写
     * @return
     * @throws Exception
     */
    Map<String, Object> queryForMap() throws Exception;
    
    /**
     * 多例查询的方法,在ServiceImpl中被重写
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> queryForList() throws Exception;
    
    /**
     * 更新操作的统一方法,在ServiceImpl中被重写
     * @param type: 根据该值判断调用对应的方法
     * @return
     * @throws Exception
     */
    boolean update(String type) throws Exception;
    /*default Map<String, Object> findOne() throws Exception {
        return  null;
    }

    default List<Map<String, Object>> findAll() throws Exception {
        return null;
    }

    default boolean update(String type) throws Exception {
        return false;
    }*/

}
