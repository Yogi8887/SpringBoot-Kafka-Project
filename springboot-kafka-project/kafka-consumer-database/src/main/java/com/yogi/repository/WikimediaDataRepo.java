package com.yogi.repository;

import com.yogi.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepo extends JpaRepository<WikimediaData,Long> {
}
