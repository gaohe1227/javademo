package com.demo.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import com.demo.dao.BaseDao;
import com.util.Paper;
 
 

public abstract class BaseServiceImpl<T> implements BaseService<T>{
	protected	Class<T> clazz;// 通过反射得到T的真实类型
	  
    public BaseServiceImpl() {
		super();
		//获取当前类的直接超类(或接口)的参数类型
		ParameterizedType parameterizedType=  (ParameterizedType) this.getClass().getGenericSuperclass();//ParameterizedType:获取java泛型参数类型 
		java.lang.reflect.Type type=	parameterizedType.getActualTypeArguments()[0];//获取此类型实际类型参数的 Type 对象的数组的第一个类型
		this.clazz=(Class<T>)type;
		System.out.println("clazz = " + clazz.getName());
	}

	protected abstract BaseDao<T> getBaseDao();
    
    /**
     * 查询总记录数
     * 
     * @return long 记录总数
     * @date 2015年3月3日下午5:35:36
     */
    public Long selectCount(){
        return this.getBaseDao().selectCount();
    }
    
    /**
     * 查询所有记录列表
     * 
     * @return List 结果列表
     * @date 2015年3月3日下午5:35:01
     */
    public <V extends T> List<V> selectAll(){
        return this.getBaseDao().selectList();
    }

    /**
     * 查询记录数
     * 
     * @param query
     *            查询对象，如果为null，则查询对象总数
     * @return long 记录总数
     * @date 2015年3月3日下午5:45:40
     */
    public Long selectCount(Map<String, Object> query){
        return this.getBaseDao().selectCount(query);
    }

    /**
     * 查询对象列表
     * 
     * @param query
     *            查询参数，如果未null则查询所有
     * @return 结果对象列表
     * @date 2015年3月3日下午5:43:33
     */
    public <V extends T> List<V> selectList(Map<String, Object> query){
        return this.getBaseDao().selectList(query);
    }
    
   
    
    /**
     * 
    * @Title: selectListPage  分页使用，分页信息以及分页数据存放在Paper对象中
    * @Description: 针对json串类型的搜索条件，该条件中带有搜索字段以及搜索方式（相似、相等、大于、小于、等于）
    * @param @param searchjson 页面搜索的json串
    * @param @param page 分页信息
    * @param @param query 特定搜索条件
    * @param @return    设定文件 
    * @return Paper<T>    返回类型 
    * @throws
     */
    public Paper<T> selectListPage(Paper<T> page,Map<String, Object> queryother){ 
        //查询总数量
        Long count = this.getBaseDao().selectCount(queryother);
        if(count > 0){
            page.setTotal(count);
            queryother.put("page", page);
            List<T> list = this.getBaseDao().selectList(queryother);
            page.setDatas(list);
        } 
        

        return page;
    }
    
    
    /**
     * 
    * @Title: selectListAll  查询所有数据存放在Paper对象中
    * @Description: 针对json串类型的搜索条件，该条件中带有搜索字段以及搜索方式（相似、相等、大于、小于、等于）
    * @param @param searchjson 页面搜索的json串
    * @param @param query 特定搜索条件
    * @param @return    设定文件 
    * @return Paper<T>    返回类型 
    * @throws
     */
    public List<T> selectListAll(String searchjson,Map<String, Object> query){
   
       /* List<T> list = this.getBaseDao().selectListJson(query);*/
    	 List<T> list = this.getBaseDao().selectList(query);
        return list;
    }

    /**
     * 通过Id查询一个对象，如果id为null这会抛出IllegalArgumentException异常
     * 
     * @param id  主键，不能为null
     * @return 结果对象，如果未找到返回null
     * @date 2015年3月3日下午5:58:07
     */
    public <V extends T> V selectById(String id){
        return this.getBaseDao().selectById(id);
    }

    /**
     * 根据Id删除对象
     * 
     * @param id
     *            要删除的ID，不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:59:59
     */
    public int deleteById(String id){
        return this.getBaseDao().deleteById(id);
    }

    /**
     * 删除对象
     * 
     * @param entity
     *            要删除的实体对象，不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:47:47
     */
    public int delete(T query){
        return this.getBaseDao().delete(query);
    }
    
    /**
     * 根据id字符串组对象，例如1,2,3
     * 
     * @param id 要删除的ID，不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:59:59
     */
    public int deleteByIdMore(String id){
        String[] ids = id.split(",");
        int count = 0;
        for(String tempid:ids){
            count++;
            this.deleteById(tempid);
        }
        return count;
    }

    /**
     * 添加对象,如果要添加的对象没有设置Id或者Id为空字符串或者是空格，则添加数据之前会调用 generateId()方法设置Id
     * 
     * @param entity
     *            要实例化的实体，不能为null
     * @return 受影响的结果数
     * @date 2015年3月3日下午5:47:15
     */
    public int insert(T entity){
        return this.getBaseDao().insert(entity);
    }

    /**
     * 更新对象，对象必须设置ID
     * 
     * @param entity
     *            实体的Id不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:48:05
     */
    public int updateById(T entity){
        return this.getBaseDao().updateById(entity);
    }
    
    /**
     * 更新对象中已设置的字段，未设置的字段不更新
     * @param entity 要更新的实体对象，不能为null，切ID必须不为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:48:05
     */
    public int updateByIdSelective(T entity){
        return this.getBaseDao().updateByIdSelective(entity);
    }

}
