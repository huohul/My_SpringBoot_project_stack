package com.gxweb.dto;

import com.gxweb.pojo.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author majie
 * @date 2018/7/2.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDTO extends Student {

    private String highlighted;  //突出显示 高亮
}
