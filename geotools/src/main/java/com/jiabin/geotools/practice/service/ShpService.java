package com.jiabin.geotools.practice.service;


import com.jiabin.geotools.practice.geotools.ShpTools;
import com.jiabin.geotools.practice.pojos.ShpDatas;
import com.jiabin.geotools.practice.pojos.ShpInfo;
import com.jiabin.geotools.practice.result.ResponseMessage;
import com.jiabin.geotools.practice.result.ResponseResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;


@Service
public class ShpService {

    public ResponseResult getShpDatas(String shpPath, Integer limit) throws  Exception{
        ShpDatas shpDatas = ShpTools.readShpByPath(shpPath, limit);
        return new ResponseResult(ResponseMessage.OK,shpDatas);
    }

    public void showShp(String shpPath,String imagePath,String color, HttpServletResponse response) throws  Exception{
        ShpTools.shp2Image(shpPath, imagePath ,color,response);
    }

    public ResponseResult writeShp(ShpInfo shpInfo) throws  Exception{
        return  ShpTools.writeShpByGeom(shpInfo);
    }
}
