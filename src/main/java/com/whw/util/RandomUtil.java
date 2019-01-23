package com.whw.util;

import java.util.Random;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2018/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RandomUtil {
    public static String getRandomChar(int length) {            //生成随机字符串
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(36)]);
        }
        return buffer.toString();



    }

    public static int[] getRandomNums (int left,int right,int num){
        int[] result = new int[num];
        Random random = new Random();
        int seed=random.nextInt(right-left);
        for(int i=0;i<num;i++) {
            result[i] = random.nextInt(seed);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] res = getRandomNums(28, 40, 20);

    }
}
