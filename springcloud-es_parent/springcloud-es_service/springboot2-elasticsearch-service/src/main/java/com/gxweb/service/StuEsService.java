package com.gxweb.service;

import com.gxweb.pojo.StudentEsDB;

import java.util.List;


/**
 * 
* Title: UserService
* Description:用户接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
public interface StuEsService {
	
	/**
     * 新增用户信息
     *
     * @param studentEsDB
     * @return
     */
    boolean insert(StudentEsDB studentEsDB);

    /**
     * 根据关键字进行全文搜索
     * @param searchContent
     * @return
     */
    List<StudentEsDB> search(String searchContent);



    /**
     * 根据名字进行分页查询
     * @param searchContent
     * @return
     */
    List<StudentEsDB> searchByName(Integer pageNumber, Integer pageSize,String searchContent);
    
    /**
     * 根据关键字进行搜索并分页
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<StudentEsDB> searchUser(Integer pageNumber, Integer pageSize, String searchContent);
	
    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<StudentEsDB> searchUserByWeight(String searchContent);
	
    
}
