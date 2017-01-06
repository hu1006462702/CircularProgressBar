package com.hubaoqi.circularprogressbar.utils;

import com.hubaoqi.circularprogressbar.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 大小适配工具处理类
 * 
 * @author 胡宝齐
 * 
 */
public class TextUtil
{
    
    /**
     * 字体大小适配
     * 
     * @param orgSize 字体大小
     * @param formate 适配格式
     * @return
     */
    public static float formateTextSize(float orgSize, String formate)
    {
        
        int wWidth = BaseActivity.screenWidth;
        
        int wHeight = BaseActivity.screenHeight;
        float newSize = orgSize;
        double scale = 1.0;
        if (formate == null)
        {
            formate = "WIDTH";
        }
        if ("WIDTH".equals(formate))
        {
            
            scale = wWidth / 1080.0;
        }
        else if ("HEIGHT".equals(formate))
        {
            
            scale = wHeight / 1920.0;
        }
        newSize = (float)(orgSize * scale);
        return newSize;
    }
    
    /**
     * 字体大小适配
     * 
     * @param orgSize 字体大小
     * @param formate 适配格式
     * @return
     */
    public static int formateTextSize(int orgSize, String formate)
    {
        
        int wWidth = BaseActivity.screenWidth;
        
        int wHeight = BaseActivity.screenHeight;
        
        int newSize = orgSize;
        double scale = 1.0;
        if (formate == null)
        {
            formate = "WIDTH";
        }
        if ("WIDTH".equals(formate))
        {
            
            scale = wWidth / 1080.0;
        }
        else if ("HEIGHT".equals(formate))
        {
            
            scale = wHeight / 1920.0;
        }
        newSize = (int)(orgSize * scale);
        return newSize;
    }
    
    public static float formateTextSize(int orgSize)
    {
        if("MI 3W".equals(android.os.Build.MODEL)){
            return (formateTextSize(Float.parseFloat(String.valueOf(orgSize)), "HEIGHT")
                / BaseActivity.screenDensity)*1.0f;
        } else if(BaseActivity.screenWidth>=1080){
            return (formateTextSize(Float.parseFloat(String.valueOf(orgSize)), "HEIGHT")
                / BaseActivity.screenDensity)*1.2f;
        }else{
            return (formateTextSize(Float.parseFloat(String.valueOf(orgSize)), "HEIGHT")
                / BaseActivity.screenDensity);
        }
    }
    
    private static float getWindowScale()
    {
        float normal = 1080;
        float fact = BaseActivity.screenWidth;
        float scale = fact / normal;
        return scale;
    }
    
    /**
     * 获得view宽度
     * 
     * @param size 宽度尺寸
     * @return
     */
    public static int getWidthSize(int size)
    {
        return (int)(size * getWindowScale());
    }
    
    /**
     * 获得view高度
     * 
     * @param size 高度尺寸
     * @return
     */
    public static int getHightSize(int size)
    {
        return (int)(BaseActivity.screenHeight / 1920.0 * size);
    }
    
    /**
     * 时间戳转换为时间
     * 
     * @param timeStr
     */
    public static String getDate(String timeStr)
    {
        
        if (timeStr != null && !"".equals(timeStr.trim()))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sd = sdf.format(new Date(Long.parseLong(timeStr)));
            return sd;
        }
        else
        {
            return "";
        }
    }
}
