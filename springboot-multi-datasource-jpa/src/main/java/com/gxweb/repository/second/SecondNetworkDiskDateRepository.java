package com.gxweb.repository.second;

import com.gxweb.entity.second.SecondNetworkDiskDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * 次数据 持久层
 */
@Repository
public interface SecondNetworkDiskDateRepository extends JpaRepository<SecondNetworkDiskDate, Long> {

}