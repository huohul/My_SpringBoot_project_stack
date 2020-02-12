package com.gxweb.system.controller;
import com.gxweb.entity.PageResult;
import com.gxweb.entity.Result;
import com.gxweb.entity.StatusCode;
import com.gxweb.system.service.StudentService;
import com.gxweb.pojo.Student;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<Student> studentList = studentService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",studentList) ;
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Student student = studentService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",student);
    }


    /***
     * 新增数据
     * @param student
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        studentService.add(student);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /***
     * 修改数据
     * @param student
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody Student student,@PathVariable Integer id){
        student.setId(id);
        studentService.update(student);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        studentService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search" )
    public Result findList(@RequestParam Map searchMap){
        List<Student> list = studentService.findList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }


    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@RequestParam Map searchMap, @PathVariable  int page, @PathVariable  int size){
        Page<Student> pageList = studentService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }


}
