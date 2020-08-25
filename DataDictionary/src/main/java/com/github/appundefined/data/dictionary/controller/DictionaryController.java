package com.github.appundefined.data.dictionary.controller;

import com.github.appundefined.tree.TreeUtils;
import com.github.appundefined.data.dictionary.dao.DictionaryDao;
import com.github.appundefined.data.dictionary.entity.Dictionary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/dataDictionary")
public class DictionaryController {
    @Resource
    private  DictionaryDao dictionaryDao;
    @RequestMapping("/getByType")
    @ResponseBody
    public List<Dictionary> getTreeByType(){
        List<Dictionary> dictionaries = null;
        try {
            dictionaries = TreeUtils.ListToTree(dictionaryDao.findAll());
        } catch (IllegalAccessException e) {
           System.err.println(e.toString());
           return null;
        }
        return dictionaries;
}
}
