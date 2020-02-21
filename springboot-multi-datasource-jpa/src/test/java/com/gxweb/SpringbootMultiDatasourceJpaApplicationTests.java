package com.gxweb;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.gxweb.entity.primary.PrimaryNetworkDiskDate;
import com.gxweb.entity.second.SecondNetworkDiskDate;
import com.gxweb.repository.primary.PrimaryNetworkDiskDateRepository;
import com.gxweb.repository.second.SecondNetworkDiskDateRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMultiDatasourceJpaApplicationTests {

    @Autowired
    private PrimaryNetworkDiskDateRepository primaryRepo;
    @Autowired
    private SecondNetworkDiskDateRepository secondRepo;
    //学花算法分布式id生成
    @Autowired
    private Snowflake snowflake;

    //添加数据 两个表
    @Test
    public void testInsert() {
        primaryRepo.findAll().forEach(primary->{
            System.out.println(primary);
        });
        PrimaryNetworkDiskDate primary = new PrimaryNetworkDiskDate(snowflake.nextId(), 18,1L,"personPrimary", 11L);
        primaryRepo.save(primary);

        SecondNetworkDiskDate second = new SecondNetworkDiskDate();
        BeanUtil.copyProperties(primary, second);
        secondRepo.save(second);
    }

    //新增即更新
    @Test
    public void testUpdate() {
        primaryRepo.findAll().forEach(primary -> {
            primary.setName("修改后的"+primary.getName());
            primaryRepo.save(primary);

            SecondNetworkDiskDate second = new SecondNetworkDiskDate();
            BeanUtil.copyProperties(primary, second);
            secondRepo.save(second);
        });
    }


}
