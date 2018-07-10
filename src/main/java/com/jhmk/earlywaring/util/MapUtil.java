package com.jhmk.earlywaring.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MapUtil {
    public static void main(String[] args) {
        Map<String,Integer> map=new HashMap<>();          map.put("a",1);
        map.put("c",3);
        map.put("b",5);
        map.put("f",7);
        map.put("e",6);
        map.put("d",8);
        Map<String, Integer> test = test(map);
        for (Map.Entry<String, Integer> entry : test.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 根据map的value值大小排序，降续
     * @param map
     * @return
     */
    public static Map<String,Integer> test(Map<String,Integer>map){
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

//        2、map 转换成 bean
        /**
         *
         *
         * Map转换层Bean，使用泛型免去了类型转换的麻烦。
         * @param <T>
         * @param map
         * @param class1
         * @return
         */
        public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
            T bean = null;
            try {
                bean = class1.newInstance();
                BeanUtils.populate(bean, map);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return bean;
        }




    }
