package hsf.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import com.google.gson.Gson;


public class CommUtils {
	public static int num=1;

	public static Gson getGson() {
		return new Gson();
	}

	public static  String toJSON(Object object) {
		return new Gson().toJson(object);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static  Object toBean(Object object,Class bean) {
		Gson gson=new Gson();
		return gson.fromJson(gson.toJson(object), bean);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static  List<Object> toListBean(List<Map<String, Object>> object,Class bean) {
		List<Object> list =new LinkedList<Object>();
		Gson gson=new Gson();
		for (Map<String, Object> map: object) {
			list.add(gson.fromJson(gson.toJson(map), bean));
		}
		return list;
	}

	/**
	 * 获取32位UUID
	 * @return
	 */
	public static String getUUID(){
		String s = UUID.randomUUID().toString();  
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}

	/**
	 * 获取32位UUID
	 * @return
	 */
	public synchronized static   String getSeqId(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");	
		String str =df.format(new Date())+ String.format("%06d", num);
		num++;
		if(num>99999){
			num=1;	
		}
		return str;

	}

	/**
	 *判断值是否为�?
	 * @return
	 */
	public boolean  isNoNull(Object object){
		return object!=null&&!object.toString().equals("");
	}
	/**
	 *判断值是否为�?
	 * @return
	 */
	public String  getMapValue(Map<String, Object> map,String keyName){
		return map.get(keyName)==null?"":map.get(keyName).toString();
	}

	/**
	 *循环listMap方法
	 * @return
	 */
	public  List<Map<String, Object>> listMapToCollapsTree(List<Map<String, Object>> list, String parentId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map : list) {
			if(map.get("pid") !=null&&map.get("pid").toString().equals(parentId)) {
				List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
				children = listMapToCollapsTree(list, map.get("id").toString());
				if(children.size() > 0) {
					map.put("children", children);

				}
				result.add(map);
			}
		}
		return result;
	}

	/**
	 *循环listMap方法
	 * @return
	 */
	public List<Map<String, Object>> listMapToExpandTree(List<Map<String, Object>> list, String parentId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map : list) {
			if(map.get("pid") !=null&&map.get("pid").toString().equals(parentId)) {
				List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
				children = listMapToExpandTree(list, map.get("id").toString());
				if(children.size() > 0) {
					map.put("expanded", true);
					map.put("children", children);
				}
				result.add(map);
			}
		}
		return result;
	}

	@SuppressWarnings("static-access")
	public static String getDateSting(String day, int dayStep) {
		Date date = null;  
		try {  
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			date = sdf.parse(day);  
		} catch (ParseException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  

		Calendar   calendar   = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.add(calendar.DATE,dayStep);//把日期往后增加一�?整数�?���?负数�?��移动 
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return dateString;

	}
	public static Date getDateSting(Date date, int dayStep) {
		Calendar calendar   = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.add(calendar.DATE,dayStep);//把日期往后增加一�?整数�?���?负数�?��移动 
		return calendar.getTime();
	}
	public static String getDateSting(int dayStep) {
		Calendar  cal   = Calendar.getInstance();
		cal.add(Calendar.DATE, dayStep);
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return dateString;

	}

	public static String getNowDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=sdf.format(new Date());
		return createTime;

	}


	public static int daysBetween(String endDate,String startEnd) {  
		long between_days=0L;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			Calendar cal = Calendar.getInstance();    
			cal.setTime(sdf.parse(endDate));
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(sdf.parse(startEnd));    
			long time2 = cal.getTimeInMillis();         
			between_days=(time1-time2)/(1000*3600*24);  

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

		return Integer.parseInt(String.valueOf(between_days));     
	}  

	public static Date getDateByStr(String value) {
		Date date = null;

		try {
			SimpleDateFormat sdf;
			if(value.length()>11){
				sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			date = sdf.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}


	public static String getStrByDate(Date value) {
		String date = null;
		if(value!=null){
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.format(value);
		}
		if (date.endsWith("00:00:00")) {
			date=date.substring(0, 10);
		}

		return date;

	}

	public static int getWeekNum(String day) {  
		Date date = null;  
		try {  
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			date = sdf.parse(day);  
		} catch (ParseException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
		if (date == null) {  
			return -1;  
		}  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);  
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;  

		return w;  
	}  
	
	public static String getInsuranceYear(String day) {
		int year =Integer.valueOf(day.substring(0,4));
		int date=Integer.valueOf(day.substring(5,7)+day.substring(8,10));
		if (date<=930) {
			year=year-1;
		}
		return String.valueOf(year);  
		
	}
	
	public static int compareDate(String firstDate,String secondDate){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(firstDate);
			Date dt2 = df.parse(secondDate);
			if (dt1.getTime() > dt2.getTime()) {

				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {

				return -1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;

	}
	
	
	public static Integer getAge(String fistDay,String secondDay) {
		int  fist=Integer.valueOf(fistDay.substring(0, 4));
		int  second=Integer.valueOf(secondDay.substring(0, 4));
		return fist-second;  
		
	}
	
	public static boolean isNumeric(String str){  
		//Pattern pattern = Pattern.compile("^\\d+$|-\\d+$"); // 就是判断是否为整�?
		Pattern pattern = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");//判断是否为小�?
	    return pattern.matcher(str).matches();    
	    
	}  
	public static boolean isInteger(String str){  
		Pattern pattern = Pattern.compile("^\\d+$|-\\d+$"); // 就是判断是否为整�?
	    return pattern.matcher(str).matches();    
	    
	}  
	
	
	
	
	public static void main(String[] args) {
		System.out.println(isInteger("1")); 
	}
}

