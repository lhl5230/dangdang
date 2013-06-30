package com.tarena.interceptor;

import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.util.HibernateUtil;

public class TransactionInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation in) throws Exception {
		//开启事务
		Transaction transaction = HibernateUtil.getSession().beginTransaction();
		System.out.println("开启事务");
		try {
			in.invoke();//执行后续Interceptor,Action,Result
			//提交事务
			if(!transaction.wasCommitted()){
				transaction.commit();
			}
			System.out.println("提交事物");
			return null;
		} catch (Exception e) {
			//回滚事务
			if(!transaction.wasRolledBack()){
				transaction.rollback();
			}
			System.out.println("回滚事务");
			throw e;
		} finally {
			//结束事物
			HibernateUtil.closeSession();
		}
	
	}

}
