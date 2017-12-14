package com.smyw.basemodule.common.model.dictionary;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * @Author:songwenke
 * @Description:数据字典数据
 * @Date:create in 9:36 2017/11/17
 * @Modified By:
 **/
@Entity
@Table(name="SYS_DICTIONARY_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_req")
    @SequenceGenerator(name = "user_id_req", sequenceName = "sys_req",allocationSize = 1,initialValue = 0)
    private Long id;
    /**
     * ManyToOne：多对一的配置
     * cascade(级联)：all(所有)，merge(更新)，refresh(查询)，persistence(保存)，remove(删除)
     * fetch: eager:立即加载  one的一方默认是立即加载
     *            lazy:懒加载    many的一方默认是懒加载
     * optional:是否可选,外键是否允许为空
     * JoinColumn:指定外键名
     *
     *//*
    @ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,optional=false)
    @NotEmpty(message = "字典类型不能为空")
    @ApiModelProperty(value = "字典类型")
    private DicType type;*/
    @Column
    @NotEmpty(message = "关联类型ID不能为空")
    @ApiModelProperty(value = "关联类型ID")
    private Long typeId;
    @Column(nullable = false,length = 50)
    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "编码")
    private String code;
    @Column(nullable = false,length = 50)
    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;
    @Column(columnDefinition="TEXT")
    @ApiModelProperty(value = "备注")
    private String memo;
}
