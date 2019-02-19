<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<#macro mapperEl value>${r"#{"}${value}}</#macro>
<#macro jspEl value>${r"${"}${value}}</#macro> 
<mapper namespace="com.bc.${package}.dao.${fixName}Mapper">

 <resultMap id="BaseResultMap" type="com.bc.${package}.domain.${fixName}" >
 	<#if meta_pk?size != 0>
 	<#list meta_pk as x>
		<id column="${x.columnName}" property="${x.property}" jdbcType="${x.columnJdbcType}" />
	</#list>
 	</#if>
 	<#if meta_npk?size != 0>
 	<#list meta_npk as x>
		<result column="${x.columnName}" property="${x.property}" jdbcType="${x.columnJdbcType}" />
	</#list>
 	</#if>
 </resultMap>
	
	<insert id="insert" parameterType="com.bc.${package}.domain.${fixName}">
		insert into ${tableName} (<#list meta as x>${x.columnName}<#if x_has_next>, </#if></#list>)
		values (<#list meta as x><@mapperEl x.property/><#if x_has_next>, </#if></#list>)
	</insert>

  <select id="selectByPk" resultMap="BaseResultMap" parameterType="java.util.Map" >
    select  * from ${tableName} where ${primaryKey} = ${r"#{pk}"}
  </select>
  <select id="selectByExample" resultMap="BaseResultMap" parameterType="com.bc.${package}.domain.${fixName}" >
      select * from ${tableName} 
    	<trim prefix="WHERE" prefixOverrides="AND|OR">
			<#if meta?size != 0>
 				<#list meta as x>
					<if test="${x.property}!=null and ${x.property}!=''"> 
						and ${x.columnName} = <@mapperEl x.property/>
					</if>
				</#list>
 			</#if>
		</trim>
		<if test="orderBy != null and orderBy !=''" >
      			order by ${r"${orderBy}"}
    	</if>
		<if test="startRecord != -1" >
      			limit  ${r"#{startRecord}"},  ${r"#{pageSize}"}
    	</if>
  </select>
  <select id="countByExample" parameterType="com.bc.${package}.domain.${fixName}" resultType="java.lang.Integer" >
    select count(*) from ${tableName} 
    <trim prefix="WHERE" prefixOverrides="AND|OR">
			<#if meta?size != 0>
 				<#list meta as x>
					<if test="${x.property}!=null and ${x.property}!=''"> 
						and ${x.columnName} = <@mapperEl x.property/>
					</if>
				</#list>
 			</#if>
		</trim>
  	</select>
	
	<update id="updateByExample" parameterType="map" >
    update ${tableName}
    <set >
    	<#if meta?size != 0>
 				<#list meta as x>
 				 <if test="record.${x.property}!=null and record.${x.property}!=''"> 
						${x.columnName} = ${r"#{record."}${x.property}},
					</if>
				</#list>
 			</#if>
    </set>
    <trim prefix="WHERE" prefixOverrides="AND|OR"> 
	<#if meta_pk?size != 0>
		<#list meta as x>
			 <#if x.columnType != "Date" >
 			<if test="example.${x.property}!=null and example.${x.property}!=''">
			   and ${x.columnName} = ${r"#{example."}${x.property}}
			</if>
 			</#if>
			
		</#list>
	</#if>
	</trim>
  </update>

<update id="updateByPk" parameterType="com.bc.${package}.domain.${fixName}" >
    update ${tableName}
    <set >
    	<#if meta?size != 0>
 				<#list meta as x>
					<if test="${x.property}!=null and ${x.property}!=''"> 
						${x.columnName} = ${x.property},
					</if>
				</#list>
 			</#if>
    </set>
    where ${primaryKey} = <@mapperEl primaryKeyPro/>;
  </update>
  <delete id="deleteByPk" parameterType="map" >
    delete from ${tableName}  where ${primaryKey} =  ${r"#{pk}"}
  </delete>
</mapper>
