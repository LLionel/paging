package cn.Lionel.cstm.service;

import java.util.List;

import cn.Lionel.cstm.dao.CustomerDao;
import cn.Lionel.cstm.domain.Customer;
import cn.Lionel.cstm.domain.PageBean;

public class CustomerService {

	CustomerDao dao = new CustomerDao() ;
	
	public String add(Customer c)
	{
		return dao.add(c);
	}
	/*
	public List<Customer> findAll()
	{
		return dao.findAll();
	}
	*/
	
	public PageBean<Customer> findAll(int pc ,int ps){
		
		return dao.findAll(pc,ps);
		
	}
	
	/**
	 * 加载客户
	 * @param cid
	 * @return
	 */
	public Customer load(String cid) {

		return dao.load(cid);
	}
	public void edit(Customer cstm) {
		dao.edit(cstm);
	}
	public void delete(String cid) {
		dao.delete(cid);
	}
	
	/*public List<Customer> query(Customer c) {

		return dao.query(c);
		
	}*/
	
	public PageBean<Customer> query(Customer c , int pc , int ps ) {

		return dao.query(c,pc,ps);
		
	}
	
}
