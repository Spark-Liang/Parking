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

import com.park.ssm.annotation.Permission;
import com.park.ssm.annotation.Permission.Type;
import com.park.ssm.dao.ParkingLotDao.CONDITION;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.service.ParkingLotService;

@RequestMapping("parkinglot")
@Controller
@Permission(value={Permission.Type.ADMIN})
public class ParkingLotController {
	@Autowired
	private ParkingLotService parkingLotService;
	
	/**
	 * 返回parkingLot 的Admin的主页
	 * @return
	 */
	
	@RequestMapping("admin")
	@Permission(value= {},haveControl=false)
	public String parkingLotIndex() {
		return "admin";
	}
	
	/**
	 * 返回parkingLot 的Operator的主页
	 * @return
	 */
	@RequestMapping("operator")
	@Permission(value= {},haveControl=false)
	public String operatorIndex() {
		return "operator";
	}
	
	/**
	 * 返回parkingLot 的Operator的主页
	 * @return
	 */
	@RequestMapping("manager")
	@Permission(value= {},haveControl=false)
	public String managerIndex() {
		return "manager";
	}
	
	/**
	 * 
	 * @param parkingLot
	 * @return JSON {”res”: “true”(成功) “false”(失败) “parkingLot”：添加成功之后的parkingLot }
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="add")
	public @ResponseBody Map addParkingLot(ParkingLot parkingLot) {
		boolean res=false;
		Map result=new HashMap();
		try {
			parkingLotService.saveParkingLot(parkingLot);
			res=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result.put("error", e.toString());
			e.printStackTrace();
		}
		result.put("res", res);
		result.put("parkingLot", parkingLot);
		return result;
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
	 * @return JSON ｛"res":结果 "totalRowNum":符合条件的行数｝
	 * 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="list")
	@Permission(value= {Type.ADMIN,Type.MANAGER,Type.OPERATOR})
	public @ResponseBody Map listParkingLot(HttpServletRequest request) {
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
		result.put("totalRowNum", parkingLotService.countParkingLot(conditions));
		return result;
	}
	
	
	/**
	 * 
	 * @param parkingLot
	 * @return JSON {”res”: “true”(成功) “false”(失败) “parkingLot”：更新之后的parkingLot }
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="update")
	@Permission(value= {Permission.Type.ADMIN,Permission.Type.MANAGER})
	public @ResponseBody Map updateParkingLot(ParkingLot parkingLot) {
		Map result=new HashMap();
		boolean res=false;
		
		try {
			parkingLotService.updateParkingLot(parkingLot);
			res=true;
			result.put("parkingLot", parkingLot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result.put("error", e.toString());
			e.printStackTrace();
		}
		result.put("res", res);
		System.err.println("\nresult:"+result);
		return result;
	}
	
	/**删除停车场
	 * 提交一个id时 删除成功返回true，失败返回false
	 * 提交多个id是，所有删除成功返回null，否则返回失败的id
	 * 
	 * @param ids
	 * @return String JSON ｛"res":结果 ,"fail":多个删除的时候失败的id列表｝
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="delete")
	public @ResponseBody Map deleteParkingLot(@RequestParam("id") Integer[] ids) {
		Map result=new HashMap();
		boolean res=false;
		if(ids.length==1) {
			//单个删除
			try {
				parkingLotService.deleteParkingLot(ids[0]);
				res=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result.put("error", e.toString());
				e.printStackTrace();
			}
		}else {
			//批量删除
			List<Integer> list=new ArrayList<>(ids.length);
			for(int i=0,ni=ids.length;i<ni;i++) {
				list.add(ids[i]);
			}
			List<Integer> resultList=parkingLotService.listDeleteParkingLot(list);
			if(resultList!=null && !resultList.isEmpty()) {
				result.put("fail", resultList);
			}else {
				res=true;
			}
		}
		result.put("res", res);
		return result;
		
	}
	
	/**
	 *  检查是否存在停车场名字重复
	 * @param name
	 * @return JSON ｛"res":结果｝ 结果：true存在重复名字，false不存在重复名字
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="existname")
	public @ResponseBody Map isExistName(@RequestParam("name")String name) {
		Map<String, Object> result=new HashMap<>();
		result.put("res", parkingLotService.isExistingName(name));
		return result;
	}
	
}
