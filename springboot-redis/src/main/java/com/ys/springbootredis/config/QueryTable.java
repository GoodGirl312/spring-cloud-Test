package com.ys.springbootredis.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class QueryTable {

    SessionFactory sessionFactory;

    {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * 单表单条记录查询
     *
     * @param clazz
     * @param varables
     * @return
     */
    public <T> T querySingleResult(Class<T> clazz, Map<String, Object> varables) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = selectStatement(clazz, varables, session);
        return (T) query.uniqueResult();
    }

    /**
     * 单表多条记录查询
     *
     * @param className 要查询的对象
     * @param varables  封装查询条件的map
     * @return 返回查询结果的List集合
     */
    public <T> List<T> queryResultList(Class<T> className, Map<String, Object> varables) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<T> valueList = selectStatement(className, varables, session).list();
        transaction.commit();
        return valueList;
    }

    /**
     * 拼接SQL查询字符串,得到Query并赋值查询条件
     *
     * @param className
     * @param varables
     * @param session
     * @return Query
     */
    private <T> Query selectStatement(Class<T> className, Map<String, Object> varables, Session session) {
        StringBuilder stringBuilder = new StringBuilder();
        /*
         * 通过className得到该实体类的字符串形式,
         */
        stringBuilder.append("from " + sessionFactory.getClassMetadata(className).getEntityName());
        stringBuilder.append(" where 1=1 ");
        /*
         * 动态的拼接sql语句,如果一个属性的值为"", 则不往条件中添加.
         */
        for (Map.Entry<String, Object> entry : varables.entrySet()) {
            if (!entry.getValue().equals("")) {
                stringBuilder.append(" and " + entry.getKey() + "=:" + entry.getKey());
            }
        }

        Query query = session.createQuery(stringBuilder.toString());
        /*
         * 动态的给条件赋值
         */
        for (Map.Entry<String, Object> entry : varables.entrySet()) {
            if (!entry.getValue().equals("")) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query;
    }

    /**
     * 传入一个sql，传入map
     */
    public <T> List<T> getfindAndTurnToDto(String sql, Map<String, String> map) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(sql);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            query.setParameter(entry.getKey(),entry.getValue());
        }
        List<T> valueList=query.list();
        transaction.commit();
        return valueList;
    }


}
