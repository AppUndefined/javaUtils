package com.github.appundefined.data.dictionary.entity;

import com.github.appundefined.tree.TreeElement;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Dictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @TreeElement(name = "id")
    private Long id;//序号
    @Column(name = "d_name" , columnDefinition = "varchar(100)")
    private String name;//名称
    @Column(name = "d_type" , columnDefinition = "varchar(100)")
    private String type;//类型
    @Column(name = "d_pid")
    @TreeElement(name = "pid")
    private Long pid;//描述
    @TreeElement(name = "children")
    @Transient
    private List<Object> children = new ArrayList<>();

}
