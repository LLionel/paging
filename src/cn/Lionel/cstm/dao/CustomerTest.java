package cn.Lionel.cstm.dao;

import org.junit.Test;

import cn.Lionel.cstm.domain.Customer;
import cn.itcast.commons.CommonUtils;

public class CustomerTest {
	@Test
	public void fun1() {
		CustomerDao dao = new CustomerDao();
		for(int i = 1; i <= 300; i++) {
			Customer c = new Customer();
			
			c.setCid(CommonUtils.uuid());
			c.setCname("cstm_" + i);
			c.setBirthday("2014-07-13");
			c.setGender(i%2==0?"男":"女");
			c.setCellphone("139" + i);
			c.setEmail("cstm_" + i + "@163.com");
			c.setDescription("我是客户");
			
			dao.add(c);
		}
	}
}
