package com.gxweb.dao;

import com.gxweb.entity.Person;
import com.gxweb.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 16:54
 * @description ：
 * @version: 1.0
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //根据姓名查询
    Optional<Person> findByName(String name);

    //按年龄找到大于
    List<Person> findByAgeGreaterThan(int age);

    //按当前用户名查询所有字段
    @Query("select p from Person p where p.name = :name")
    Optional<Person> findByNameCustomeQuery(@Param("name") String name);

    //按当前用户名查询指定字段
    @Query("select p.name from Person p where p.id = :id")
    String findPersonNameById(@Param("id") Long id);

    /**
     * 自定义 sql 语句更新操作
     */
    @Modifying
    @Transactional
    @Query("update Person p set p.name = ?1 where p.id = ?2")
    void updatePersonNameById(String name, Long id);

    /**
     * 连表查询  获取用户信息
     */
    @Query(value = "select new com.gxweb.entity.dto.UserDTO(p.name,p.age,c.companyName,s.name) " +
            "from Person p left join Company c on  p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.id=:personId")
    Optional<UserDTO> getPersonInformation(@Param("personId") Long personId);

    /**
     * 按年龄过滤用户信息   使用 wher
     * @param small 最小e price between smail and big  |  WHERE age >=smail AND age <=big  取区间内的值
     *      *
     * @param big   最大
     * @return SELECT * from person p LEFT JOIN company c on c.id= p.company_id LEFT JOIN school s on p.school_id = s.id WHERE p.age BETWEEN 19 and 20
     */
    @Query(value = "select new com.gxweb.entity.dto.UserDTO(p.name,p.age,c.companyName,s.name) " +
            "from Person p left join Company c on  p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.age between :small and :big")
    List<UserDTO> filterPersonInfoByAge(int small, int big);

    //过滤用户信息 多条件 使用 [in]   SELECT * from person p LEFT JOIN company c on c.id= p.company_id LEFT JOIN school s on p.school_id = s.id where p.`name` IN (SELECT name from person WHERE name= 'person1' or `name` = 'person2')
    @Query(value = "select new com.gxweb.entity.dto.UserDTO(p.name,p.age,c.companyName,s.name) " +
            "from Person p left join Company c on  p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.name IN :peopleList")
    List<UserDTO> filterPersonInfo(List peopleList);

    //获取用户信息列表 返回分页信息 所以这里需要查询总条数返回
    @Query(value = "select new com.gxweb.entity.dto.UserDTO(p.name,p.age,c.companyName,s.name) " +
            "from Person p left join Company c on  p.companyId=c.id " +
            "left join School s on p.schoolId=s.id ",
            countQuery = "select count(p.id) from Person p left join Company c on  p.companyId=c.id " +
                    "left join School s on p.schoolId=s.id ")
    Page<UserDTO> getPersonInformationList(Pageable pageable);

}
