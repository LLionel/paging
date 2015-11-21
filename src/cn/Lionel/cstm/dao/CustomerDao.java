package cn.Lionel.cstm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.Lionel.cstm.domain.Customer;
import cn.Lionel.cstm.domain.PageBean;
import cn.itcast.jdbc.TxQueryRunner;

public class CustomerDao {

	QueryRunner qr = new TxQueryRunner();

	public String add(Customer c) {
		String sql = "insert into t_customer values(?,?,?,?,?,?,?)";
		Object[] param = { c.getCid(), c.getCname(), c.getGender(), c.getBirthday(),
				c.getCellphone(), c.getEmail(), c.getDescription() };

		try {
			qr.update(sql, param) ;
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
		return "f:/msg.jsp" ;
	}
	/*
	public List<Customer> findAll()
	{
		BeanListHandler<Customer> handler = new BeanListHandler(Customer.class) ;
		String sql = "select * from t_customer" ;
		try {
			List<Customer>  list = qr.query(sql, handler);
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
	}
	 */
	
	public PageBean<Customer> findAll(int pc , int ps){
		
		
		try {

			PageBean<Customer> pb = new PageBean<Customer>() ;
			pb.setPc(pc);
			pb.setPs(ps);
			
			String sql = "select count(*) from t_customer" ;
			Number num = (Number) qr.query(sql, new ScalarHandler());
			int tr = num.intValue() ;
			pb.setTr(tr);
			
			sql = "select * from t_customer limit ?,?";
			Object[] params = {(pc-1)*ps , ps} ;
			List<Customer> list = qr.query(sql,
					new BeanListHandler<Customer>(Customer.class) ,
					params) ;
			pb.setBeanList(list) ;
			
			return pb ;
			
		} catch (SQLException e) {
			throw new RuntimeException() ;
		}
		
	}
	
	
	public Customer load(String cid) {
		String sql = "select * from t_customer where cid = ?" ;
		try {
			return qr.query(sql , new BeanHandler<Customer>(Customer.class) , cid);
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
	}

	public void edit(Customer c) {
		
		String sql = "update t_customer set cname = ?, gender = ?, birthday = ?, cellphone = ?, email = ?, description = ? where cid = ?" ; 
		Object[] params = {c.getCname(), c.getGender(), c.getBirthday(),
				c.getCellphone(), c.getEmail(), c.getDescription() ,c.getCid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
	}

	public void delete(String cid) {
		
		String sql = "delete from t_customer where cid = ?" ;
		try {
			qr.update(sql, cid);
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
		
	}

	public List<Customer> query(Customer c) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer("select * from t_customer where 1=1 ") ;
		List<Object> params = new ArrayList<Object>() ;
		if(c.getCname() != null && !c.getCname().trim().isEmpty())
		{
			sql.append("and cname like ?") ;
			params.add("%"+c.getCname()+"%");
		}
		if(c.getGender() != null && !c.getGender().trim().isEmpty())
		{
			sql.append("and gender = ?") ;
			params.add(c.getGender());
		}
		if(c.getCellphone() != null && !c.getCellphone().trim().isEmpty())
		{
			sql.append("and cellphone like ?") ;
			params.add("%"+c.getCellphone()+"%");
		}
		if(c.getEmail() != null && !c.getEmail().trim().isEmpty())
		{
			sql.append("and email like ?") ;
			params.add("%"+c.getEmail()+"%");
		}
		try {
			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray()) ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
