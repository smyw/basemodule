package com.smyw.basemodule.common.model.dictionary;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:songwenke
 * @Description:数据字典类型
 * @Date:create in 9:26 2017/11/17
 * @Modified By:
 **/
@Entity
@Table(name="SYS_DICTIONARY_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_req")
    @SequenceGenerator(name = "user_id_req", sequenceName = "sys_req",allocationSize = 1,initialValue = 0)
    private Long id;
    @Column(nullable = false,length = 50,unique=true)
    @NotEmpty(message = "标签不能为空")
    @ApiModelProperty(value = "标签不能为空且唯一")
    private String tag;
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
    /**
     * OneToMany:一对多的配置  ---------一般情况下不使用一对多
     * mappedBy="order":指定由多的一方的order属性维护关联关系
    @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="type")*/
    @Transient
    private Set<DicData> datas=new HashSet<DicData>();
}
