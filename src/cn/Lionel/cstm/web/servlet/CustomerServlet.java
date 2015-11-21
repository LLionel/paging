package cn.Lionel.cstm.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.Lionel.cstm.domain.Customer;
import cn.Lionel.cstm.domain.PageBean;
import cn.Lionel.cstm.service.CustomerService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class CustomerServlet extends BaseServlet {

	private CustomerService cs = new CustomerService() ;


	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class) ;
		c.setCid(CommonUtils.uuid()) ;
		cs.add(c);
		request.setAttribute("msg", "用户添加成功！") ;
		return "/msg.jsp" ;
	}
	
	/*
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("list", cs.findAll());
		
		return "/list.jsp";
	}
	*/
	
	
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pc = getPc(request);
		int ps = 10 ;
		
		PageBean<Customer> pb = cs.findAll(pc,ps);
		String url = getURL(request);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "/list.jsp"; 
	}
	
	
	private int getPc(HttpServletRequest request) {
		
		String spc = request.getParameter("pc");
		
		if(spc == null || spc.trim().isEmpty())
		{
			return 1 ;
		}
		
		return Integer.parseInt(spc);
	}

	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer cstm = cs.load(request.getParameter("cid"));
		request.setAttribute("cstm", cstm) ;
		return "/edit.jsp" ;
	}
		
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		Customer cstm = CommonUtils.toBean(request.getParameterMap(), Customer.class) ;
		cs.edit(cstm);
		request.setAttribute("msg", "恭喜你，修改成功！") ;
		return "/msg.jsp" ;
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		cs.delete(request.getParameter("cid")) ;
		request.setAttribute("msg", "恭喜你，刪除成功！");
		return "/msg.jsp";
	}
	/*
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
	
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		List<Customer> list = cs.query(c) ;
		request.setAttribute("list", list);
		return "/list.jsp";
	}
	*/
	
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取查询条件
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		int pc = getPc(request);
		int ps = 10 ;
		// 设置编码
		c = encoding(c) ;
		PageBean<Customer> pb = cs.query(c,pc,ps);
		
		//获取url
		String url = getURL(request);
//		System.out.println(url);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/list.jsp" ;
	}

	private Customer encoding(Customer c) throws UnsupportedEncodingException {

		String cname = c.getCname() ;
		String gender = c.getGender() ;
		String email = c.getEmail();
		String cellphone = c.getCellphone();
		
		if(cname != null && !cname.trim().isEmpty())
		{
			cname = new String(cname.getBytes("ISO-8859-1"),"utf-8");
			c.setCname(cname);
		}
		if(gender != null && !gender.trim().isEmpty())
		{
			gender = new String(gender.getBytes("ISO-8859-1"),"utf-8");
			c.setGender(gender);
		}
		if(email != null && !email.trim().isEmpty())
		{
			email = new String(email.getBytes("ISO-8859-1"),"utf-8");
			c.setEmail(email);
		}
		if(cellphone != null && !cellphone.trim().isEmpty())
		{
			cellphone = new String(cellphone.getBytes("ISO-8859-1"),"utf-8");
			c.setCellphone(cellphone);
		}
		
		return c;
	}

	private String getURL(HttpServletRequest request) {

		String contextPath = request.getContextPath() ;
		String servletPath = request.getServletPath();
		String queryPath = request.getQueryString();
		
		if(queryPath.contains("&pc="))
		{
			int index = queryPath.lastIndexOf("&pc=") ;
			queryPath = queryPath.substring(0, index);
		}
		return contextPath+servletPath+"?"+queryPath ;
	}
}
