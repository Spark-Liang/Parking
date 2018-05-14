package com.park.ssm.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.park.ssm.dao.ParkingLotDao.CONDITION;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.service.ParkingLotService;

@RequestMapping("ParkingLot")
@Controller
public class ParkingLotController {
	@Autowired
	private ParkingLotService parkingLotService;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("add")
	@ResponseBody
	public String addParkingLot(ParkingLot parkingLot) {
		boolean res=false;
		try {
			parkingLotService.saveParkingLot(parkingLot);
			res=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map result=new HashMap();
		result.put("res", res);
		return JSON.toJSONString(result);
	}
	
	/**返回的parkingLot中的所有对象的所有的bean属性都不进行加载，只是生成代理类
	 * 默认返回未被删除的停车场信息
	 * @param conditions 
	 * 	输入数字类型的条件Map
	 * “name”,“location”
	 * "totalPositionNum_max","totalPositionNum_min" 表示totalPositionNum的边界
	 * “price_max”“price_min”表示currentPrice的边界
	 * “cost_max”“cost_min”表示cost的边界
	 * @param pageNum 页数 default 1
	 * @param pageSize 每页行数 default 20
	 * 
	 * @return String JSON ｛"res":结果｝
	 * 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("list")
	@ResponseBody
	public String listParkingLot(HttpServletRequest request) {
		Map<String, String[]> params=request.getParameterMap();
		Map<String, Object> conditions=new HashMap<>();
		//提取参数
		for(CONDITION condition:CONDITION.values()) {
			String[] values=params.get(condition.getName());
			if(values!=null) {
				conditions.put(condition.getName(), values[0]);
			}
		}
		Integer pageNum=null,pageSize=null;
		if(params.get("pageNum")!=null && params.get("pageSize")!=null) {
			pageNum=new Integer(params.get("pageNum")[0]);
			pageSize=new Integer(params.get("pageSize")[0]);
		}
		
		List<ParkingLot> res=parkingLotService.listParkingLot(conditions, pageNum, pageSize);
		Map result=new HashMap();
		result.put("res", res);
		return JSON.toJSONString(result);
	}
	
	/**删除停车场
	 * 提交一个id时 删除成功返回true，失败返回false
	 * 提交多个id是，所有删除成功返回null，否则返回失败的id
	 * 
	 * @param ids
	 * @return String JSON ｛"res":结果｝
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("delete")
	@ResponseBody
	public String deleteParkingLot(@RequestParam("id") Integer[] ids) {
		Map result=new HashMap();
		if(ids.length==1) {
			//单个删除
			boolean res=false;
			try {
				parkingLotService.deleteParkingLot(ids[0]);
				res=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result.put("res", res);
		}else {
			//批量删除
			List<Integer> list=new ArrayList<>(ids.length);
			for(int i=0,ni=ids.length;i<ni;i++) {
				list.add(ids[i]);
			}
			List<Integer> resultList=parkingLotService.listDeleteParkingLot(list);
			result.put("res", resultList);
		}
		return JSON.toJSONString(result);
		
	}
}
