package com.bc.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterfaceReqParaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InterfaceReqParaExample() {
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

        public Criteria andParaNameIsNull() {
            addCriterion("para_name is null");
            return (Criteria) this;
        }

        public Criteria andParaNameIsNotNull() {
            addCriterion("para_name is not null");
            return (Criteria) this;
        }

        public Criteria andParaNameEqualTo(String value) {
            addCriterion("para_name =", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotEqualTo(String value) {
            addCriterion("para_name <>", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameGreaterThan(String value) {
            addCriterion("para_name >", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameGreaterThanOrEqualTo(String value) {
            addCriterion("para_name >=", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameLessThan(String value) {
            addCriterion("para_name <", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameLessThanOrEqualTo(String value) {
            addCriterion("para_name <=", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameLike(String value) {
            addCriterion("para_name like", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotLike(String value) {
            addCriterion("para_name not like", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameIn(List<String> values) {
            addCriterion("para_name in", values, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotIn(List<String> values) {
            addCriterion("para_name not in", values, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameBetween(String value1, String value2) {
            addCriterion("para_name between", value1, value2, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotBetween(String value1, String value2) {
            addCriterion("para_name not between", value1, value2, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaDesIsNull() {
            addCriterion("para_des is null");
            return (Criteria) this;
        }

        public Criteria andParaDesIsNotNull() {
            addCriterion("para_des is not null");
            return (Criteria) this;
        }

        public Criteria andParaDesEqualTo(String value) {
            addCriterion("para_des =", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesNotEqualTo(String value) {
            addCriterion("para_des <>", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesGreaterThan(String value) {
            addCriterion("para_des >", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesGreaterThanOrEqualTo(String value) {
            addCriterion("para_des >=", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesLessThan(String value) {
            addCriterion("para_des <", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesLessThanOrEqualTo(String value) {
            addCriterion("para_des <=", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesLike(String value) {
            addCriterion("para_des like", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesNotLike(String value) {
            addCriterion("para_des not like", value, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesIn(List<String> values) {
            addCriterion("para_des in", values, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesNotIn(List<String> values) {
            addCriterion("para_des not in", values, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesBetween(String value1, String value2) {
            addCriterion("para_des between", value1, value2, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaDesNotBetween(String value1, String value2) {
            addCriterion("para_des not between", value1, value2, "paraDes");
            return (Criteria) this;
        }

        public Criteria andParaTypeIsNull() {
            addCriterion("para_type is null");
            return (Criteria) this;
        }

        public Criteria andParaTypeIsNotNull() {
            addCriterion("para_type is not null");
            return (Criteria) this;
        }

        public Criteria andParaTypeEqualTo(String value) {
            addCriterion("para_type =", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeNotEqualTo(String value) {
            addCriterion("para_type <>", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeGreaterThan(String value) {
            addCriterion("para_type >", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeGreaterThanOrEqualTo(String value) {
            addCriterion("para_type >=", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeLessThan(String value) {
            addCriterion("para_type <", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeLessThanOrEqualTo(String value) {
            addCriterion("para_type <=", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeLike(String value) {
            addCriterion("para_type like", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeNotLike(String value) {
            addCriterion("para_type not like", value, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeIn(List<String> values) {
            addCriterion("para_type in", values, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeNotIn(List<String> values) {
            addCriterion("para_type not in", values, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeBetween(String value1, String value2) {
            addCriterion("para_type between", value1, value2, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaTypeNotBetween(String value1, String value2) {
            addCriterion("para_type not between", value1, value2, "paraType");
            return (Criteria) this;
        }

        public Criteria andParaLengthIsNull() {
            addCriterion("para_length is null");
            return (Criteria) this;
        }

        public Criteria andParaLengthIsNotNull() {
            addCriterion("para_length is not null");
            return (Criteria) this;
        }

        public Criteria andParaLengthEqualTo(Byte value) {
            addCriterion("para_length =", value, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthNotEqualTo(Byte value) {
            addCriterion("para_length <>", value, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthGreaterThan(Byte value) {
            addCriterion("para_length >", value, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthGreaterThanOrEqualTo(Byte value) {
            addCriterion("para_length >=", value, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthLessThan(Byte value) {
            addCriterion("para_length <", value, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthLessThanOrEqualTo(Byte value) {
            addCriterion("para_length <=", value, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthIn(List<Byte> values) {
            addCriterion("para_length in", values, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthNotIn(List<Byte> values) {
            addCriterion("para_length not in", values, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthBetween(Byte value1, Byte value2) {
            addCriterion("para_length between", value1, value2, "paraLength");
            return (Criteria) this;
        }

        public Criteria andParaLengthNotBetween(Byte value1, Byte value2) {
            addCriterion("para_length not between", value1, value2, "paraLength");
            return (Criteria) this;
        }

        public Criteria andIsNullIsNull() {
            addCriterion("is_null is null");
            return (Criteria) this;
        }

        public Criteria andIsNullIsNotNull() {
            addCriterion("is_null is not null");
            return (Criteria) this;
        }

        public Criteria andIsNullEqualTo(Byte value) {
            addCriterion("is_null =", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotEqualTo(Byte value) {
            addCriterion("is_null <>", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullGreaterThan(Byte value) {
            addCriterion("is_null >", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_null >=", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullLessThan(Byte value) {
            addCriterion("is_null <", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullLessThanOrEqualTo(Byte value) {
            addCriterion("is_null <=", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullIn(List<Byte> values) {
            addCriterion("is_null in", values, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotIn(List<Byte> values) {
            addCriterion("is_null not in", values, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullBetween(Byte value1, Byte value2) {
            addCriterion("is_null between", value1, value2, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotBetween(Byte value1, Byte value2) {
            addCriterion("is_null not between", value1, value2, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsEncIsNull() {
            addCriterion("is_enc is null");
            return (Criteria) this;
        }

        public Criteria andIsEncIsNotNull() {
            addCriterion("is_enc is not null");
            return (Criteria) this;
        }

        public Criteria andIsEncEqualTo(Byte value) {
            addCriterion("is_enc =", value, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncNotEqualTo(Byte value) {
            addCriterion("is_enc <>", value, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncGreaterThan(Byte value) {
            addCriterion("is_enc >", value, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_enc >=", value, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncLessThan(Byte value) {
            addCriterion("is_enc <", value, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncLessThanOrEqualTo(Byte value) {
            addCriterion("is_enc <=", value, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncIn(List<Byte> values) {
            addCriterion("is_enc in", values, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncNotIn(List<Byte> values) {
            addCriterion("is_enc not in", values, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncBetween(Byte value1, Byte value2) {
            addCriterion("is_enc between", value1, value2, "isEnc");
            return (Criteria) this;
        }

        public Criteria andIsEncNotBetween(Byte value1, Byte value2) {
            addCriterion("is_enc not between", value1, value2, "isEnc");
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