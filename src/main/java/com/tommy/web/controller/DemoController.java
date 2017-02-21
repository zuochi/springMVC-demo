package com.tommy.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tommy.web.model.common.bo.TestBo;
import com.tommy.web.model.common.vo.Result;
import com.tommy.web.utils.JsonUtils;

/**
 * DEMO,参考用
 * 
 * @author Tommy Lau
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@ResponseBody
	@RequestMapping("/json")
	public String json() throws Exception {
		return JsonUtils.toString(new Result("呵呵哒"));//标准json返回
	}
	
	@RequestMapping("/components")
	public ModelAndView demo() throws Exception {
		return new ModelAndView("demo");
	}
	
	@ResponseBody
	@RequestMapping("/page")
	public String page(TestBo bo) throws Exception {
		//表格分页参考
		List<DemoVo> data = new ArrayList<DemoVo>();
		for(int i=0 ; i<12 ; i++){
			data.add(new DemoVo(i + ((bo.getPageNumber() - 1)*12), UUID.randomUUID().toString(), new BigDecimal(i)));
		}
		return JsonUtils.toString(new Result(36,data));//36是总条数
	}
	
	class DemoVo{
		private Integer id;
		private String name;
		private BigDecimal price;
		
		public DemoVo(Integer id, String name, BigDecimal price) {
			this.id = id;
			this.name = name;
			this.price = price;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
	}
}
