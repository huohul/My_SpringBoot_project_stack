package com.gxweb.dao;

import com.gxweb.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 16:54
 * @description ：
 * @version: 1.0
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
