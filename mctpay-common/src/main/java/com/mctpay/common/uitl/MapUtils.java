package com.mctpay.common.uitl;

import java.util.Map;

/*
关于地图的工具类
 */
public class MapUtils {
    /*
    计算一定半径内的方形经纬度
     */
    public static void loadGeoSquare(Map<String, Object> parm, double lat, double lon, long raidus){
        Double latitude = lat;
        Double longitude = lon;

        // 赤道周长24901英里 1609是转换成米的系数
        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        //return new double[] { minLat, minLng, maxLat, maxLng };
        parm.put("minLat", minLat);
        parm.put("minLng", minLng);
        parm.put("maxLat", maxLat);
        parm.put("maxLng", maxLng);
    }

}
