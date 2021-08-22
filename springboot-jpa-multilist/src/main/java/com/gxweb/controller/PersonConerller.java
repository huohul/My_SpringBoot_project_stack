package com.gxweb.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.gxweb.entity.Person;
import com.gxweb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 17:18
 * @description ：
 * @version: 1.0
 */
@Controller
public class PersonConerller {

    @Autowired
    private PersonService personService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("查询所有");
        List<Person> personList = personService.findAll();
        model.addAttribute("persons", personList);
        return "person/list";
    }

    /**
     * 转换UTF-8
     *
     * @param string
     * @return
     */
    public static String toUtf8String(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= 0 && c <= 255) {
                stringBuffer.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    stringBuffer.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return stringBuffer.toString();
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "person/personAdd";
    }

    @RequestMapping("/add")
    public String add(Person person) {
        personService.addPerson(person);
        System.out.println("新增成功");
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, long id) {
        Person person = personService.findPersonById(id);
        model.addAttribute("person", person);
        return "person/personEdit";
    }

    @RequestMapping("/edit")
    public String edit(Person person) {
        personService.updatePerson(person);
        return "redirect:/list";
    }

    @RequestMapping("/toDelete")
    public String delete(long id) {
        personService.deletePerson(id);
        return "redirect:/list";
    }

    /**
     * 使用hutool导出execl      https://mp.weixin.qq.com/s/7-UyqCcy7U48K-uq-xGxuA
     * 会将说有数属性导出 未自定义标题名的也会导出 所以 要自定义导出数据 得重新定义实体
     *
     * @param response
     */
    @RequestMapping("/Eclist")
    public void Eclist(HttpServletResponse response) {
        List<Person> list = personService.findAll();
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "性别");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "申请人员信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String name = toUtf8String("导出文件名");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

}

