package com.github.appundefined.data.dictionary.dao;

import com.github.appundefined.data.dictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface DictionaryDao extends JpaRepository<Dictionary, Long>, JpaSpecificationExecutor<Dictionary> {
}
