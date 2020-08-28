package com.github.appundefined.data.dictionary.service;

import com.github.appundefined.annotation.AnnotationUtils;
import com.github.appundefined.annotation.ClassFiledsUtils;
import com.github.appundefined.commons.ASMBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * 通用方法
 */
@SuppressWarnings("Duplicates")
@Transactional
@Service
public class BaseServiceImpl {
    //新增1条

    public Object save(JpaRepository jpaRepository, Object object, HttpServletRequest request) throws Exception {
       return jpaRepository.save(object);
    }
    //新增n条

    public void saveAll(JpaRepository jpaRepository, List<?> objects, HttpServletRequest request) throws Exception {
        jpaRepository.saveAll(objects);
    }
    //删除1条

    public void delete(JpaRepository jpaRepository, Long id){
            jpaRepository.deleteById(id);
    }
    //删除n条

    public void deleteAll(JpaRepository jpaRepository, List<Long> ids){
        for (Long id : ids) {
            delete(jpaRepository,id);
        }

    }
    //修改1条

    public void update(JpaRepository jpaRepository, Object object) throws  Exception{
        Optional byId = jpaRepository.findById(AnnotationUtils.getValue(object,"id"));
        if(byId.isPresent()) {
            ASMBeanUtils.copyProperties(byId.get(),object);
            jpaRepository.save(byId.get());
        }else{
            jpaRepository.save(object);
//            throw new Exception("该数据不存在");
        }
    }
    //修改n条
    public void updateAll(JpaRepository jpaRepository, List<Object> objects)throws  Exception{
        for(int i =0 ;i< objects.size();i++){
            update(jpaRepository,objects.get(i));
        }
    }
    //查询1条
    public Optional find(JpaRepository jpaRepository, Long id){
       return jpaRepository.findById(id);
    }
    //查询n条

    /**
     *
     * @param jpaSpecificationExecutor dao对象
     * @param objects   查询对象
     * @param pageNumber 第几页
     * @param pageSize  每页大小
     * @param dimField 需要模糊查询的字段
     * @return
     */
    public Page findAll(JpaSpecificationExecutor jpaSpecificationExecutor, Object objects,Integer pageNumber,Integer pageSize,String[] dimField) throws IllegalAccessException {
        HashMap<String, Field> filedsMap = ClassFiledsUtils.getFiledsMap(objects);
        Field order1 = filedsMap.get("order");
        String orderName = (String)order1.get(objects);
        ArrayList<Sort> order = new ArrayList<>();
        if(StringUtils.isNotEmpty(orderName)){
                order.add(Sort.by(orderName));
        }
        filedsMap.remove("order");
        Specification<Object> querySpecifi = new Specification<Object>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesOr = new ArrayList<>();
                List<Predicate> theirDepartmentNames = new ArrayList<>();
                        filedsMap.forEach((x,y)->{
                    try {
                        String key = (String)y.get(objects);
                        if(StringUtils.isNotEmpty(key)) {
                            if(x.equalsIgnoreCase("theirCompany")){
                                String[] keys = key.split(",");
                                if(keys.length>0) {
                                    for (String currentDepartmentName : keys) {
                                        theirDepartmentNames.add(criteriaBuilder.equal(root.get("theirCompany"),  currentDepartmentName));
                                    }
                                }
                            }else {
                                predicatesOr.add(criteriaBuilder.like(root.get(x), "%" + key + "%"));
                            }

                        }
                    } catch (IllegalAccessException e) {
                    }
                });

                Predicate predicateOr = criteriaBuilder.and(predicatesOr.toArray(new Predicate[predicatesOr.size()]));
                Predicate departmentName = criteriaBuilder.or(theirDepartmentNames.toArray(new Predicate[theirDepartmentNames.size()]));
                if(predicatesOr.size()>0&&theirDepartmentNames.size()>0) {
                   return criteriaBuilder.and(predicateOr,departmentName);
                }
                if(predicatesOr.size()>0){
                    return criteriaBuilder.and(predicateOr);
                }
                if(theirDepartmentNames.size()>0){
                    return criteriaBuilder.and(departmentName);
                }
                return  criteriaBuilder.and();

            }
        };
        if(order.size()==1){
            return jpaSpecificationExecutor.findAll(querySpecifi, PageRequest.of(pageNumber, pageSize,order.get(0)));
        }else {
          return jpaSpecificationExecutor.findAll(querySpecifi, PageRequest.of(pageNumber, pageSize));
        }
    }
    /**
     *
     * @param jpaRepository dao对象
     * @param objects   查询对象
     * @param dimField 需要模糊查询的字段
     * @return
     */
    public List findAll(JpaRepository jpaRepository, Object objects,String[] dimField){
        //创建匹配器，即如何使用查询条件   designManagerNumbetr模糊匹配
        ExampleMatcher matcher = ExampleMatcher.matching();
        if(dimField!=null&&dimField.length>0){
            for (int i =0;i<dimField.length;i++) {
                matcher = matcher.withMatcher(dimField[i], ExampleMatcher.GenericPropertyMatchers.contains());
                //构建对象
            }
        }
        Example<Object> example = Example.of(objects, matcher);
        return jpaRepository.findAll(example);
    }

    public Page findAllByKey(JpaSpecificationExecutor jpaSpecificationExecutor, String key, Integer pageNumber, Integer pageSize, String[] pollingrecordDimField) {


        Specification<Object> querySpecifi = new Specification<Object>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesOr = new ArrayList<>();
                if(StringUtils.isNotEmpty(key)) {
                    for (String dim : pollingrecordDimField) {
                        predicatesOr.add(criteriaBuilder.like(root.get(dim), "%" + key + "%"));
                    }
                }
                Predicate predicateOr = criteriaBuilder.or(predicatesOr.toArray(new Predicate[predicatesOr.size()]));


                return  criteriaBuilder.and(predicateOr);
            }
        };
        Page resultAll = jpaSpecificationExecutor.findAll(querySpecifi, new PageRequest(pageNumber, pageSize));
        return resultAll;
    }

    /**
     * 多条件查询加所属公司筛选
     * @param jpaSpecificationExecutor
     * @param objects
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page findAll(JpaSpecificationExecutor jpaSpecificationExecutor, Object objects, Integer pageNumber, Integer pageSize) throws IllegalAccessException {
        HashMap<String, Field> filedsMap = ClassFiledsUtils.getFiledsMap(objects);
        Field order1 = filedsMap.get("order");
        String orderName = (String)order1.get(objects);
        ArrayList<Sort> order = new ArrayList<>();
        if(StringUtils.isNotEmpty(orderName)){
            order.add(Sort.by(orderName));
        }
        filedsMap.remove("order");
        Specification<Object> querySpecifi = new Specification<Object>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesOr = new ArrayList<>();
                List<Predicate> theirDepartmentNames = new ArrayList<>();
                filedsMap.forEach((x,y)->{
                    try {
                        String key = (String)y.get(objects);
                        if(StringUtils.isNotEmpty(key)) {
                            if(x.equalsIgnoreCase("theirCompany")){
                                String[] keys = key.split(",");
                                if(keys.length>0) {
                                    for (String currentDepartmentName : keys) {
                                        theirDepartmentNames.add(criteriaBuilder.equal(root.get("theirCompany"),  currentDepartmentName));
                                    }
                                }
                            }else {
                                predicatesOr.add(criteriaBuilder.like(root.get(x), "%" + key + "%"));
                            }

                        }
                    } catch (IllegalAccessException e) {
                    }
                });

                Predicate predicateOr = criteriaBuilder.and(predicatesOr.toArray(new Predicate[predicatesOr.size()]));
                Predicate departmentName = criteriaBuilder.or(theirDepartmentNames.toArray(new Predicate[theirDepartmentNames.size()]));
                if(predicatesOr.size()>0&&theirDepartmentNames.size()>0) {
                    return criteriaBuilder.and(predicateOr,departmentName);
                }
                if(predicatesOr.size()>0){
                    return criteriaBuilder.and(predicateOr);
                }
                if(theirDepartmentNames.size()>0){
                    return criteriaBuilder.and(departmentName);
                }
                return  criteriaBuilder.and();

            }
        };
        if(order.size()==1){
            return jpaSpecificationExecutor.findAll(querySpecifi, PageRequest.of(pageNumber, pageSize,order.get(0)));
        }else {
            return jpaSpecificationExecutor.findAll(querySpecifi, PageRequest.of(pageNumber, pageSize));
        }
    }


    public List findAllByKey(JpaSpecificationExecutor jpaSpecificationExecutor, String key,  String[] pollingrecordDimField) {
        Specification<Object> querySpecifi = new Specification<Object>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesOr = new ArrayList<>();
                if(StringUtils.isNotEmpty(key)) {
                    for (String dim : pollingrecordDimField) {
                        predicatesOr.add(criteriaBuilder.like(root.get(dim), "%" + key + "%"));
                    }
                }
                Predicate predicateOr = criteriaBuilder.or(predicatesOr.toArray(new Predicate[predicatesOr.size()]));
                return  criteriaBuilder.and(predicateOr);
            }
        };
        List resultAll = jpaSpecificationExecutor.findAll(querySpecifi);
        return resultAll;
    }
}
