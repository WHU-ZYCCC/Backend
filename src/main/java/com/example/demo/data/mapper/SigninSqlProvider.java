package com.example.demo.data.mapper;

import com.example.demo.data.domain.Signin;
import com.example.demo.data.domain.SigninExample.Criteria;
import com.example.demo.data.domain.SigninExample.Criterion;
import com.example.demo.data.domain.SigninExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SigninSqlProvider {
    public String countByExample(SigninExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("signin");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SigninExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("signin");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Signin record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("signin");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.VALUES("number", "#{number,jdbcType=VARCHAR}");
        }
        
        if (record.getLongitude() != null) {
            sql.VALUES("longitude", "#{longitude,jdbcType=DECIMAL}");
        }
        
        if (record.getLatitude() != null) {
            sql.VALUES("latitude", "#{latitude,jdbcType=DECIMAL}");
        }
        
        if (record.getDate() != null) {
            sql.VALUES("date", "#{date,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SigninExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("name");
        } else {
            sql.SELECT("name");
        }
        sql.SELECT("number");
        sql.SELECT("longitude");
        sql.SELECT("latitude");
        sql.SELECT("date");
        sql.FROM("signin");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Signin record = (Signin) parameter.get("record");
        SigninExample example = (SigninExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("signin");
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{record.number,jdbcType=VARCHAR}");
        }
        
        if (record.getLongitude() != null) {
            sql.SET("longitude = #{record.longitude,jdbcType=DECIMAL}");
        }
        
        if (record.getLatitude() != null) {
            sql.SET("latitude = #{record.latitude,jdbcType=DECIMAL}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{record.date,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("signin");
        
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("number = #{record.number,jdbcType=VARCHAR}");
        sql.SET("longitude = #{record.longitude,jdbcType=DECIMAL}");
        sql.SET("latitude = #{record.latitude,jdbcType=DECIMAL}");
        sql.SET("date = #{record.date,jdbcType=TIMESTAMP}");
        
        SigninExample example = (SigninExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SigninExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}