package com.bc.data.util;

import com.bc.gateway.query.CommonQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * @author lzk
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Repository(value = "util")
public class DBUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonQuery.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplateSave;

    /**
     * 添加或者更新数据
     *
     * @param sql
     * @param obj
     * @return
     */
    public Integer addOrUpdate(String sql, Object obj) {
        Integer id = 0;
        try {
            id = jdbcTemplateSave.update(sql, new BeanPropertySqlParameterSource(obj));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
        return id;
    }

    /**
     * 获取List集合
     *
     * @param sql
     * @param className
     * @param obj
     * @return
     */
    public List<Map<String, Object>> getObjList(String sql, Object[] obj) {
        List<Map<String, Object>> array = null;
        try {
            array = jdbcTemplate.query(sql, new ColumnMapRowMapper(), obj);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
        return array;
    }

    /**
     * 获取Map集合值
     *
     * @param sql
     * @param obj
     * @return
     */
    public Map<String, ?> getMap(String sql, Object[] obj) {

        Map<String, ?> map = null;
        try {
            map = jdbcTemplate.queryForMap(sql, obj);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }

        return map;
    }

    /**
     * 获取相应的Object
     *
     * @param sql
     * @param className
     * @param obj
     * @return
     */
    public Object getObject(String sql, Class<? extends Serializable> className, Object[] obj) {
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(className), obj);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage());
            //throw new DaoException("数据库操作失败！",e);
            return "";
        }
        return object;
    }

    /**
     * 批量操作
     *
     * @param sql
     * @param obj
     * @return
     */
    public int[] batchOperate(String sql, final List<String> columns, final List<?> obj) {
        int[] a = null;
        try {
            a = jdbcTemplateSave.batchUpdate(sql, new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return obj.size();
                }

                @Override
                public void setValues(java.sql.PreparedStatement ps, int i)
                        throws SQLException {
                    Object ob = obj.get(i);
                    int j = 0;
                    for (; j < columns.size() - 2; j++) {
                        String columnName = columns.get(j);
                        String method = "get" + columnName;
                        Method md = null;
                        try {
                            md = ob.getClass().getMethod(method);
                        } catch (NoSuchMethodException | SecurityException e) {
                            log.error(e.getLocalizedMessage());
                        }
                        try {
                            ps.setString(j + 1, (String) md.invoke(ob));
                        } catch (IllegalAccessException
                                | IllegalArgumentException
                                | InvocationTargetException e) {
                            log.error(e.getLocalizedMessage());
                        }
                    }

                    // ps.setDate(j+1, new java.sql.Date(((BaseObject)ob).getCreate_time().getTime()));
                    // ps.setDate(j+2, new java.sql.Date(((BaseObject)ob).getCreate_time().getTime()));
                }
            });
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
        return a;
    }

    /**
     * 通过map列表，批量更新与插入操作
     *
     * @param sql update  table set a=?,b=?
     * @param obj list 对象
     * @return
     */
    public int[] batchUpdate(String sql, final List<String> columns, final List<Map<String, String>> para) {
        int[] a = null;
        try {
            a = jdbcTemplateSave.batchUpdate(sql, new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return para.size();
                }

                @Override
                public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {
                    Map<String, String> map = para.get(i);
                    int j = 0;
                    for (String key : columns) {
                        j = j + 1;
                        String value = map.get(key);
                        ps.setString(j, value);
                    }
                }
            });
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
        return a;
    }

    public int update(String sql, Object[] obj) {
        try {
            return jdbcTemplateSave.update(sql, obj);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
    }

    /**
     * 检查是否有值
     *
     * @param sql
     * @param obj
     * @return
     */
    public int isExist(String sql, Object[] obj) {
        int index = 0;
        try {
            index = jdbcTemplate.queryForObject(sql, obj, Integer.class);
        } catch (Exception e) {
            log.info(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
        return index;
    }

    /**
     * 编辑操作(增删改查都可以)
     *
     * @param sql
     * @param obj
     * @return
     */
    public int editObject(String sql, Object[] obj) {
        int index = 0;
        try {
            index = jdbcTemplateSave.update(sql, obj);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage());
            throw new DaoException("数据库操作失败！", e);
        }
        return index;
    }

    public int getCount(String sql, Object[] obj) {
        return jdbcTemplate.queryForObject(sql, obj, Integer.class);
    }

    public static String getUUID() {
        String id = UUID.randomUUID().toString();
        id = id.replaceAll("-", "");
        return id;
    }

    public static List<Object> transferParam4Query(Map param, Map wh, String alias, List<Object> list) {
        String where = "";
        if (alias == null || alias.equals("")) alias = "t";
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object val = entry.getValue();
            if (val == null) continue;
            if (val instanceof Class) continue;
            if (key.equalsIgnoreCase("tablename")) continue;
            if (val instanceof String && ((String) val).equals("")) {
                continue;
            }

            if (key.endsWith("orderBy")) {
                continue;
            }
            if (key.endsWith("pageNumber")) {
                continue;
            }

            if (key.endsWith("pageSize")) {
                continue;
            }

            if (val instanceof String && (((String) val).indexOf("_") > 0 || ((String) val).indexOf("%") >= 0)) {
                where += alias + "." + key + " like ? and ";
            } else {
                where += alias + "." + key + " =? and ";
            }

            list.add(val);
        }
        if (where.length() > 4 && where.substring(where.length() - 4, where.length()).equals("and ")) {
            wh.put("where", where.substring(0, where.length() - 4));
        } else {
            wh.put("where", where.toString());
        }

        return list;
    }

    public static List<Object> transferParam4Insert(Map<String, Object> param, Map<String, String> wh, String userName) {
        String uid = DBUtil.getUUID();
        String keylist = "";
        List valist = new ArrayList();
        valist.add(uid);
        String holders = "";
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object val = entry.getValue();
            //if (val instanceof String && ((String)val).equals("")){
            //	 continue;
            //}
            if (key.equals("id") && (val == null || ((String) val).equals(""))) continue;
            if (val == null) continue;
            if (val instanceof Class) continue;
            if (key.equalsIgnoreCase("tablename")) continue;
            if (key.endsWith("pageNumber")) {
                continue;
            }
            keylist += ",`" + key + "`";
            holders += ",?";
            valist.add(val);
        }
        keylist += ",`create_user`, `update_user`, `create_time`, `update_time`";
        holders += ",?,?,?,?";
        valist.add(userName);
        valist.add(userName);
        valist.add(new Date());
        valist.add(new Date());
        wh.put("columns", keylist);
        wh.put("holders", holders);
        return valist;
    }

    public static Object[] transferParam4Update(Map<String, Object> param, Map<String, String> wh, String userName) {
        List valist = new ArrayList();
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        String set = "";
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object val = entry.getValue();
            if (key.equals("id")) continue;
            if (val == null) continue;
            if (val instanceof Class) continue;
            if (key.equalsIgnoreCase("tablename")) continue;
            if (key.endsWith("pageNumber")) {
                continue;
            }
            set += "`" + key + "`=?,";
            valist.add(val);
        }
        set += "`update_user`=?, `update_time`=?";
        wh.put("set", set);
        valist.add(userName);
        valist.add(new Date());
        return valist.toArray();
    }

}
