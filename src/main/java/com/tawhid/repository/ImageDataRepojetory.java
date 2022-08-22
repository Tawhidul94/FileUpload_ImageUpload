package com.tawhid.repository;

import com.tawhid.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepojetory extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String  filrName);
}
