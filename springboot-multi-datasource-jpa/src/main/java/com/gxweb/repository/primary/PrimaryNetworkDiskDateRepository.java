package com.gxweb.repository.primary;

import com.gxweb.entity.primary.PrimaryNetworkDiskDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * 主数据 持久层
 */
@Repository
public interface PrimaryNetworkDiskDateRepository extends JpaRepository<PrimaryNetworkDiskDate, Integer> {

}