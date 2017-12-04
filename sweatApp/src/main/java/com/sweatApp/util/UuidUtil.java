package com.sweatApp.util;

import java.util.Random;
import java.util.UUID;

/**
 * 
 * @author lha
 * 生成随机数代码
 */
public class UuidUtil {
	
	/**
	 * 获取登陆界面验证码
	 * @return
	 */
	public static String getCheckCode(){
		
		String code = UUID.randomUUID().toString();
		code = code.replace("-", "");//生成的UUID默认含有“-”字符
		code=code.substring(0, 4);
		return code;
	}
	
	
	
	
	
	/**
	 * 获取uuid 10位数随机码
	 * @return
	 */
	public static String getCode(){
		
		String code = UUID.randomUUID().toString();
		code = code.replace("-", "");//生成的UUID默认含有“-”字符
		code=code.substring(0, 10);
		return code;
	}
	
	
	
	
	
	/**
	 * 获取随机数验证码
	 * length 长度
	 * @return
	 */
	public static String getStringRandom(int length) {  
        
        String val = "";  
        Random random = new Random();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
	
	
	
	/**
	 * 获取随机数验证码
	 * length 长度
	 * @return
	 */
	public static int getNumRandom(int length) {  
		String val = "";  
        Random random = new Random();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            val += String.valueOf(random.nextInt(10));  
        }  
        return Integer.parseInt(val);
	} 
	
	
}
