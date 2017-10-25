package com.ecochain.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AclRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AclRoleExample() {
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

        public Criteria andRole_nameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRole_nameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRole_nameEqualTo(String value) {
            addCriterion("role_name =", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameGreaterThan(String value) {
            addCriterion("role_name >", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameLessThan(String value) {
            addCriterion("role_name <", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameLike(String value) {
            addCriterion("role_name like", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotLike(String value) {
            addCriterion("role_name not like", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameIn(List<String> values) {
            addCriterion("role_name in", values, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "role_name");
            return (Criteria) this;
        }

        public Criteria andPronounIsNull() {
            addCriterion("pronoun is null");
            return (Criteria) this;
        }

        public Criteria andPronounIsNotNull() {
            addCriterion("pronoun is not null");
            return (Criteria) this;
        }

        public Criteria andPronounEqualTo(String value) {
            addCriterion("pronoun =", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounNotEqualTo(String value) {
            addCriterion("pronoun <>", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounGreaterThan(String value) {
            addCriterion("pronoun >", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounGreaterThanOrEqualTo(String value) {
            addCriterion("pronoun >=", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounLessThan(String value) {
            addCriterion("pronoun <", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounLessThanOrEqualTo(String value) {
            addCriterion("pronoun <=", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounLike(String value) {
            addCriterion("pronoun like", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounNotLike(String value) {
            addCriterion("pronoun not like", value, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounIn(List<String> values) {
            addCriterion("pronoun in", values, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounNotIn(List<String> values) {
            addCriterion("pronoun not in", values, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounBetween(String value1, String value2) {
            addCriterion("pronoun between", value1, value2, "pronoun");
            return (Criteria) this;
        }

        public Criteria andPronounNotBetween(String value1, String value2) {
            addCriterion("pronoun not between", value1, value2, "pronoun");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Date value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Date value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Date value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Date> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeIsNull() {
            addCriterion("last_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeIsNotNull() {
            addCriterion("last_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeEqualTo(Date value) {
            addCriterion("last_modify_time =", value, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeNotEqualTo(Date value) {
            addCriterion("last_modify_time <>", value, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeGreaterThan(Date value) {
            addCriterion("last_modify_time >", value, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_modify_time >=", value, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeLessThan(Date value) {
            addCriterion("last_modify_time <", value, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeLessThanOrEqualTo(Date value) {
            addCriterion("last_modify_time <=", value, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeIn(List<Date> values) {
            addCriterion("last_modify_time in", values, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeNotIn(List<Date> values) {
            addCriterion("last_modify_time not in", values, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeBetween(Date value1, Date value2) {
            addCriterion("last_modify_time between", value1, value2, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andLast_modify_timeNotBetween(Date value1, Date value2) {
            addCriterion("last_modify_time not between", value1, value2, "last_modify_time");
            return (Criteria) this;
        }

        public Criteria andUser_numberIsNull() {
            addCriterion("user_number is null");
            return (Criteria) this;
        }

        public Criteria andUser_numberIsNotNull() {
            addCriterion("user_number is not null");
            return (Criteria) this;
        }

        public Criteria andUser_numberEqualTo(Integer value) {
            addCriterion("user_number =", value, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberNotEqualTo(Integer value) {
            addCriterion("user_number <>", value, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberGreaterThan(Integer value) {
            addCriterion("user_number >", value, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_number >=", value, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberLessThan(Integer value) {
            addCriterion("user_number <", value, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberLessThanOrEqualTo(Integer value) {
            addCriterion("user_number <=", value, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberIn(List<Integer> values) {
            addCriterion("user_number in", values, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberNotIn(List<Integer> values) {
            addCriterion("user_number not in", values, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberBetween(Integer value1, Integer value2) {
            addCriterion("user_number between", value1, value2, "user_number");
            return (Criteria) this;
        }

        public Criteria andUser_numberNotBetween(Integer value1, Integer value2) {
            addCriterion("user_number not between", value1, value2, "user_number");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagIsNull() {
            addCriterion("department_flag is null");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagIsNotNull() {
            addCriterion("department_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagEqualTo(String value) {
            addCriterion("department_flag =", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagNotEqualTo(String value) {
            addCriterion("department_flag <>", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagGreaterThan(String value) {
            addCriterion("department_flag >", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagGreaterThanOrEqualTo(String value) {
            addCriterion("department_flag >=", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagLessThan(String value) {
            addCriterion("department_flag <", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagLessThanOrEqualTo(String value) {
            addCriterion("department_flag <=", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagLike(String value) {
            addCriterion("department_flag like", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagNotLike(String value) {
            addCriterion("department_flag not like", value, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagIn(List<String> values) {
            addCriterion("department_flag in", values, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagNotIn(List<String> values) {
            addCriterion("department_flag not in", values, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagBetween(String value1, String value2) {
            addCriterion("department_flag between", value1, value2, "department_flag");
            return (Criteria) this;
        }

        public Criteria andDepartment_flagNotBetween(String value1, String value2) {
            addCriterion("department_flag not between", value1, value2, "department_flag");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdIsNull() {
            addCriterion("response_user_Id is null");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdIsNotNull() {
            addCriterion("response_user_Id is not null");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdEqualTo(Integer value) {
            addCriterion("response_user_Id =", value, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdNotEqualTo(Integer value) {
            addCriterion("response_user_Id <>", value, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdGreaterThan(Integer value) {
            addCriterion("response_user_Id >", value, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("response_user_Id >=", value, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdLessThan(Integer value) {
            addCriterion("response_user_Id <", value, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdLessThanOrEqualTo(Integer value) {
            addCriterion("response_user_Id <=", value, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdIn(List<Integer> values) {
            addCriterion("response_user_Id in", values, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdNotIn(List<Integer> values) {
            addCriterion("response_user_Id not in", values, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdBetween(Integer value1, Integer value2) {
            addCriterion("response_user_Id between", value1, value2, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andResponse_user_IdNotBetween(Integer value1, Integer value2) {
            addCriterion("response_user_Id not between", value1, value2, "response_user_Id");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOperateidIsNull() {
            addCriterion("operateid is null");
            return (Criteria) this;
        }

        public Criteria andOperateidIsNotNull() {
            addCriterion("operateid is not null");
            return (Criteria) this;
        }

        public Criteria andOperateidEqualTo(Integer value) {
            addCriterion("operateid =", value, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidNotEqualTo(Integer value) {
            addCriterion("operateid <>", value, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidGreaterThan(Integer value) {
            addCriterion("operateid >", value, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidGreaterThanOrEqualTo(Integer value) {
            addCriterion("operateid >=", value, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidLessThan(Integer value) {
            addCriterion("operateid <", value, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidLessThanOrEqualTo(Integer value) {
            addCriterion("operateid <=", value, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidIn(List<Integer> values) {
            addCriterion("operateid in", values, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidNotIn(List<Integer> values) {
            addCriterion("operateid not in", values, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidBetween(Integer value1, Integer value2) {
            addCriterion("operateid between", value1, value2, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperateidNotBetween(Integer value1, Integer value2) {
            addCriterion("operateid not between", value1, value2, "operateid");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNull() {
            addCriterion("operatetime is null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNotNull() {
            addCriterion("operatetime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeEqualTo(Date value) {
            addCriterion("operatetime =", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotEqualTo(Date value) {
            addCriterion("operatetime <>", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThan(Date value) {
            addCriterion("operatetime >", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operatetime >=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThan(Date value) {
            addCriterion("operatetime <", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThanOrEqualTo(Date value) {
            addCriterion("operatetime <=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIn(List<Date> values) {
            addCriterion("operatetime in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotIn(List<Date> values) {
            addCriterion("operatetime not in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeBetween(Date value1, Date value2) {
            addCriterion("operatetime between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotBetween(Date value1, Date value2) {
            addCriterion("operatetime not between", value1, value2, "operatetime");
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