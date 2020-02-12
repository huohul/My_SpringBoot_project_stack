package com.gxweb.system.service.impl;

import com.gxweb.system.dao.StudentMapper;
import com.gxweb.system.service.StudentService;
import com.gxweb.pojo.Student;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Student findById(Integer id){
        return  studentMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param student
     */
    @Override
    public void add(Student student){
        studentMapper.insert(student);
    }


    /**
     * 修改
     * @param student
     */
    @Override
    public void update(Student student){
        studentMapper.updateByPrimaryKey(student);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        studentMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Student> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return studentMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Student> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Student>)studentMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Student> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Student>)studentMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 班级
            if(searchMap.get("class_and_grade")!=null && !"".equals(searchMap.get("class_and_grade"))){
                criteria.andLike("class_and_grade","%"+searchMap.get("class_and_grade")+"%");
           	}
            // 考生号
            if(searchMap.get("candidate_number")!=null && !"".equals(searchMap.get("candidate_number"))){
                criteria.andLike("candidate_number","%"+searchMap.get("candidate_number")+"%");
           	}
            // 姓名
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 性别
            if(searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))){
                criteria.andLike("sex","%"+searchMap.get("sex")+"%");
           	}
            // 专业名称
            if(searchMap.get("name_of_major")!=null && !"".equals(searchMap.get("name_of_major"))){
                criteria.andLike("name_of_major","%"+searchMap.get("name_of_major")+"%");
           	}
            // 名族
            if(searchMap.get("ethnic")!=null && !"".equals(searchMap.get("ethnic"))){
                criteria.andLike("ethnic","%"+searchMap.get("ethnic")+"%");
           	}
            // 政治面貌
            if(searchMap.get("politics_status")!=null && !"".equals(searchMap.get("politics_status"))){
                criteria.andLike("politics_status","%"+searchMap.get("politics_status")+"%");
           	}
            // 录取省份
            if(searchMap.get("Admission_to_the_province")!=null && !"".equals(searchMap.get("Admission_to_the_province"))){
                criteria.andLike("Admission_to_the_province","%"+searchMap.get("Admission_to_the_province")+"%");
           	}
            // 批次
            if(searchMap.get("patch")!=null && !"".equals(searchMap.get("patch"))){
                criteria.andLike("patch","%"+searchMap.get("patch")+"%");
           	}
            // 来源省份
            if(searchMap.get("Source_provinces")!=null && !"".equals(searchMap.get("Source_provinces"))){
                criteria.andLike("Source_provinces","%"+searchMap.get("Source_provinces")+"%");
           	}
            // 来源市
            if(searchMap.get("The_source")!=null && !"".equals(searchMap.get("The_source"))){
                criteria.andLike("The_source","%"+searchMap.get("The_source")+"%");
           	}
            // 区县市州
            if(searchMap.get("Area_county_city_states")!=null && !"".equals(searchMap.get("Area_county_city_states"))){
                criteria.andLike("Area_county_city_states","%"+searchMap.get("Area_county_city_states")+"%");
           	}

            // user_id
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id", "%"+searchMap.get("id")+"%");
            }

        }
        return example;
    }

}
