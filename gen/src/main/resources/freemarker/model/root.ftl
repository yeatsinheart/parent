package ${root.packageName};
import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import ${entity.fullName};
<#if swagger >
    import io.swagger.annotations.*;
</#if>
<#if lombok >
    import lombok.Data;
    import lombok.EqualsAndHashCode;
</#if>
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
<#if lombok >
    @Data
    @EqualsAndHashCode(callSuper=false)
</#if>
<#if swagger >
    @ApiModel("${table.comment!}")
</#if>
public class ${root.className} implements Serializable {

private static final long serialVersionUID = 1L;
<#if swagger >
    @ApiModelProperty("数据列表")
</#if>
private List<${entity.className}> dataList;
<#if swagger >
    @ApiModelProperty("数据量")
</#if>
private Long rowCount;
<#if swagger >
    @ApiModelProperty("总金额")
</#if>
private BigDecimal dataTotal;
<#if swagger >
    @ApiModelProperty("总金额")
</#if>
private BigDecimal amountTotal;

public void setDataList(List<${entity.className}> dataList){this.dataList = dataList ;}
public void setRowCount(Long rowCount){this.rowCount = rowCount;}
public void setDataTotal(BigDecimal dataTotal){this.dataTotal = dataTotal;}
public void setAmountTotal(BigDecimal amountTotal){this.amountTotal =  amountTotal;}

public List<${entity.className}> getDataList( ){ return this.dataList; }
public Long getRowCount( ){ return this.rowCount ;}
public BigDecimal getDataTotal( ){ return this.dataTotal ;}
public BigDecimal getAmountTotal( ){ return this.amountTotal ;}
}
