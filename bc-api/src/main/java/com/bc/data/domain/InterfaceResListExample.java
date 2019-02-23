package com.bc.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterfaceResListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InterfaceResListExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andResListIdIsNull() {
            addCriterion("res_list_id is null");
            return (Criteria) this;
        }

        public Criteria andResListIdIsNotNull() {
            addCriterion("res_list_id is not null");
            return (Criteria) this;
        }

        public Criteria andResListIdEqualTo(String value) {
            addCriterion("res_list_id =", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdNotEqualTo(String value) {
            addCriterion("res_list_id <>", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdGreaterThan(String value) {
            addCriterion("res_list_id >", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdGreaterThanOrEqualTo(String value) {
            addCriterion("res_list_id >=", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdLessThan(String value) {
            addCriterion("res_list_id <", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdLessThanOrEqualTo(String value) {
            addCriterion("res_list_id <=", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdLike(String value) {
            addCriterion("res_list_id like", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdNotLike(String value) {
            addCriterion("res_list_id not like", value, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdIn(List<String> values) {
            addCriterion("res_list_id in", values, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdNotIn(List<String> values) {
            addCriterion("res_list_id not in", values, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdBetween(String value1, String value2) {
            addCriterion("res_list_id between", value1, value2, "resListId");
            return (Criteria) this;
        }

        public Criteria andResListIdNotBetween(String value1, String value2) {
            addCriterion("res_list_id not between", value1, value2, "resListId");
            return (Criteria) this;
        }

        public Criteria andActinaIdIsNull() {
            addCriterion("actina_id is null");
            return (Criteria) this;
        }

        public Criteria andActinaIdIsNotNull() {
            addCriterion("actina_id is not null");
            return (Criteria) this;
        }

        public Criteria andActinaIdEqualTo(String value) {
            addCriterion("actina_id =", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdNotEqualTo(String value) {
            addCriterion("actina_id <>", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdGreaterThan(String value) {
            addCriterion("actina_id >", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdGreaterThanOrEqualTo(String value) {
            addCriterion("actina_id >=", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdLessThan(String value) {
            addCriterion("actina_id <", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdLessThanOrEqualTo(String value) {
            addCriterion("actina_id <=", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdLike(String value) {
            addCriterion("actina_id like", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdNotLike(String value) {
            addCriterion("actina_id not like", value, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdIn(List<String> values) {
            addCriterion("actina_id in", values, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdNotIn(List<String> values) {
            addCriterion("actina_id not in", values, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdBetween(String value1, String value2) {
            addCriterion("actina_id between", value1, value2, "actinaId");
            return (Criteria) this;
        }

        public Criteria andActinaIdNotBetween(String value1, String value2) {
            addCriterion("actina_id not between", value1, value2, "actinaId");
            return (Criteria) this;
        }

        public Criteria andListNameIsNull() {
            addCriterion("list_name is null");
            return (Criteria) this;
        }

        public Criteria andListNameIsNotNull() {
            addCriterion("list_name is not null");
            return (Criteria) this;
        }

        public Criteria andListNameEqualTo(String value) {
            addCriterion("list_name =", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotEqualTo(String value) {
            addCriterion("list_name <>", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameGreaterThan(String value) {
            addCriterion("list_name >", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameGreaterThanOrEqualTo(String value) {
            addCriterion("list_name >=", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLessThan(String value) {
            addCriterion("list_name <", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLessThanOrEqualTo(String value) {
            addCriterion("list_name <=", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLike(String value) {
            addCriterion("list_name like", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotLike(String value) {
            addCriterion("list_name not like", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameIn(List<String> values) {
            addCriterion("list_name in", values, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotIn(List<String> values) {
            addCriterion("list_name not in", values, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameBetween(String value1, String value2) {
            addCriterion("list_name between", value1, value2, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotBetween(String value1, String value2) {
            addCriterion("list_name not between", value1, value2, "listName");
            return (Criteria) this;
        }

        public Criteria andSqlStrIsNull() {
            addCriterion("sql_str is null");
            return (Criteria) this;
        }

        public Criteria andSqlStrIsNotNull() {
            addCriterion("sql_str is not null");
            return (Criteria) this;
        }

        public Criteria andSqlStrEqualTo(String value) {
            addCriterion("sql_str =", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrNotEqualTo(String value) {
            addCriterion("sql_str <>", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrGreaterThan(String value) {
            addCriterion("sql_str >", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrGreaterThanOrEqualTo(String value) {
            addCriterion("sql_str >=", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrLessThan(String value) {
            addCriterion("sql_str <", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrLessThanOrEqualTo(String value) {
            addCriterion("sql_str <=", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrLike(String value) {
            addCriterion("sql_str like", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrNotLike(String value) {
            addCriterion("sql_str not like", value, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrIn(List<String> values) {
            addCriterion("sql_str in", values, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrNotIn(List<String> values) {
            addCriterion("sql_str not in", values, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrBetween(String value1, String value2) {
            addCriterion("sql_str between", value1, value2, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andSqlStrNotBetween(String value1, String value2) {
            addCriterion("sql_str not between", value1, value2, "sqlStr");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}