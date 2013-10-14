package com.cj.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cj.domain.PrizeItem;

public interface PrizeItemRepository extends JpaRepository<PrizeItem, Long>,
JpaSpecificationExecutor<PrizeItem> {

}
