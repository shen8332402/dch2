package com.digitalchina.frame.util;
/**
 * 
 * @author shentt
 * @date 2018年1月19日
 * @className DistanceUtil
 * @param 
 * @Description 计算亮点之间距离
 */
public final class DistanceUtil {
	 public static final double R = 6371.004;  
     
	    /** 
	     * 根据给定的两个经纬度计算两地之间的距离，单位km 
	     * @param lon1  经度1 
	     * @param lat1  纬度1 
	     * @param lon2  经度2 
	     * @param lat2  纬度2 
	     * @return  两地距离 
	     */  
	    public static double getDistance(double lon1, double lat1, double lon2, double lat2)  
	    {  
	        double x = changeToRad(lon1);  
	        double y = changeToRad(lat1);  
	        double a = changeToRad(lon2);  
	        double b = changeToRad(lat2);  
	        double rad = Math.acos(Math.cos(y) * Math.cos(b) * Math.cos(x - a) + Math.sin(y) * Math.sin(b));  
	        if (rad > Math.PI)  
	            rad = Math.PI * 2 - rad;  
	        return R * rad;  
	    }  
	      
	    /** 
	     * 将角度转化为弧度 
	     * @param angle 角度 
	     * @return  弧度 
	     */  
	    public static double changeToRad(double angle)  
	    {  
	        return angle / 180 * Math.PI;  
	    }  
	/* private static final double EARTH_RADIUS = 6378.137;

	    //
	    private static double rad(double d) {
	        return d * Math.PI / 180.0;
	    }

	    // 返回单位是:千米
	    public static double getDistanceKM(double longitude1, double latitude1,
	                                     double longitude2, double latitude2) {
	        double Lat1 = rad(latitude1);
	        double Lat2 = rad(latitude2);
	        double a = Lat1 - Lat2;
	        double b = rad(longitude1) - rad(longitude2);
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
	                + Math.cos(Lat1) * Math.cos(Lat2)
	                * Math.pow(Math.sin(b / 2), 2)));
	        s = s * EARTH_RADIUS;
	        //有小数的情况;注意这里的10000d中的“d”
	        s = Math.round(s * 10000d) / 10000d;
	        s = s * 1000;//单位：米
	        s = Math.round(s/10d) /100d   ;//单位：千米 保留两位小数
	        //s = Math.round(s / 100d) / 10d;//单位：千米 保留一位小数
	        return s;
	    }
	    
	 // 返回单位是:米
	    public static double getDistanceM(double longitude1, double latitude1,
	                                     double longitude2, double latitude2) {
	        double Lat1 = rad(latitude1);
	        double Lat2 = rad(latitude2);
	        double a = Lat1 - Lat2;
	        double b = rad(longitude1) - rad(longitude2);
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
	                + Math.cos(Lat1) * Math.cos(Lat2)
	                * Math.pow(Math.sin(b / 2), 2)));
	        s = s * EARTH_RADIUS;
	        //有小数的情况;注意这里的10000d中的“d”
	        s = Math.round(s * 10000d) / 10000d;
	        s = s * 1000;//单位：米
	        return s;
	    }*/
}
