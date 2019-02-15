
package com.qbb.${package}.domain;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import com.qbb.util.db.BasePage;

/**
 * ${tableName}
 * 
 * 
 */
public class ${fixName}DO extends BasePage implements Serializable {
	private static final long serialVersionUID = "${fixName}".hashCode();
	
	<#list meta as x>
	private ${x.columnType} ${x.property};//
	</#list>
	
	<#list meta as x>
	public ${x.columnType} get${x.property?cap_first}() {
		return this.${x.property};
	}
	public void set${x.property?cap_first}(${x.columnType} ${x.property}) {
		this.${x.property} = ${x.property};
	}
	</#list>
	public int hashCode() {
		int result = 17;
		<#if meta_pk?size != 0>
		<#list meta_pk as x>
		result = 37 * result + (this.${x.property} == null ? 0 : this.${x.property}.hashCode());
		</#list>
		</#if>
		<#if meta_pk?size == 0>
		<#list meta as x>
		result = 37 * result + (this.${x.property} == null ? 0 : this.${x.property}.hashCode());
		</#list>
		</#if>

		return result;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ${fixName}DO))
			return false;

		${fixName}DO castOther = (${fixName}DO) other;

		return
			<#if meta_pk?size != 0>
			<#list meta_pk as x>
			<#if x_index != 0>&& </#if>((this.${x.property} == castOther.get${x.property?cap_first}()) || (this.${x.property} != null && castOther.get${x.property?cap_first}() != null && this.${x.property}.equals(castOther.get${x.property?cap_first}())))
			</#list>
			</#if>
			<#if meta_pk?size == 0>
			<#list meta as x>
			<#if x_index != 0>&& </#if>((this.${x.property} == castOther.get${x.property?cap_first}()) || (this.${x.property} != null && castOther.get${x.property?cap_first}() != null && this.${x.property}.equals(castOther.get${x.property?cap_first}())))
			</#list>
			</#if>
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		<#list meta as x>
		builder.append("${x.property}:").append(${x.property}.toString())<#if x_has_next>.append(",")</#if>;
		</#list>
		return builder.toString();
	}
}
