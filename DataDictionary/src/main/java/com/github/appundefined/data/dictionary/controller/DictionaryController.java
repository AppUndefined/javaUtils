package com.github.appundefined.data.dictionary.controller;

import com.github.appundefined.commons.ResultMap;
import com.github.appundefined.data.dictionary.dao.DictionaryDao;
import com.github.appundefined.data.dictionary.entity.Dictionary;
import com.github.appundefined.data.dictionary.service.BaseServiceImpl;
import com.github.appundefined.tree.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/dataDictionary")
@Api(tags="数据字典")
public class DictionaryController {
    @Resource
    private  DictionaryDao dictionaryDao;
    @Resource
    private BaseServiceImpl baseService;
    @RequestMapping(value = "/findAllTree",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询所有[树结构]（可根据条件查询）",notes = "查询所有[树结构]（可根据条件查询")
    public ResultMap getTreeByTypeTree(@RequestBody Dictionary dictionary){
        List<Dictionary> dictionaries = null;
        try {
            List all = baseService.findAll(dictionaryDao, dictionary, new String[]{});
            dictionaries = TreeUtils.ListToTree(all);
        } catch (Exception e) {
            return ResultMap.error(e.toString().substring( e.toString().indexOf(":")+2));
        }
        return ResultMap.successData(dictionaries);
    }
    @RequestMapping(value = "/findAllList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询所有[列表结构]（可根据条件查询）",notes = "查询所有[列表结构]（可根据条件查询")
    public ResultMap findAllList(@RequestBody Dictionary dictionary){
        List<Dictionary> dictionaries = null;
        try {
            dictionaries = baseService.findAll(dictionaryDao, dictionary, new String[]{});
        } catch (Exception e) {
            return ResultMap.error(e.toString().substring( e.toString().indexOf(":")+2));
        }
        return ResultMap.successData(dictionaries);
    }
    @RequestMapping(value = "/findNameById",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据id查名称（Get请求返回String为空说明查询不到）",notes = "根据id查名称（Get请求返回String为空说明查询不到）")
    public String findNameById(Long id){
        try {
            Optional<Dictionary> byId = dictionaryDao.findById(id);
            if(byId.isPresent()){
                return byId.get().getName();
            }
        } catch (Exception e) {
            return e.toString().substring( e.toString().indexOf(":")+2);
        }
        return "";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增",notes = "新增")
    public ResultMap add(@RequestBody Dictionary dictionary){
        try {
            dictionaryDao.save(dictionary);
        } catch (Exception e) {
            return ResultMap.error(e.toString().substring( e.toString().indexOf(":")+2));
        }
        return ResultMap.success();
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除",notes = "删除")
    public ResultMap delete(@RequestBody ArrayList<Long> ids){
        try {
            baseService.deleteAll(dictionaryDao,ids);
        } catch (Exception e) {
            return ResultMap.error(e.toString().substring( e.toString().indexOf(":")+2));
        }
        return ResultMap.success();
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新",notes = "更新")
    public ResultMap update(@RequestBody Dictionary dictionary){
        try {
            baseService.update(dictionaryDao,dictionary);
        } catch (Exception e) {
            return ResultMap.error(e.toString().substring( e.toString().indexOf(":")+2));
        }
        return ResultMap.success();
    }
}
