package com.xxl.job.admin.core.route.strategy;

import com.xxl.job.admin.core.route.ExecutorRouter;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/7/23 15:38
 */
public class ExecutorRouteTest extends ExecutorRouter {
    private static ConcurrentHashMap<String,Integer> lfuItemMap = new ConcurrentHashMap<>();
    public void testAddress(){
        String addressIP = "";
        List<String> addressList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            addressList.add("192.168.1." + i);
        }

        for (int i = 0; i < 100; i++) {
            for(String address:addressList){
                if(!lfuItemMap.containsKey(address)){
                    lfuItemMap.put(address,0);
                }
            }
            //LFU Least Frequency Used
            List<Map.Entry<String,Integer>> lfuItemList = new ArrayList<>(lfuItemMap.entrySet());
            Collections.sort(lfuItemList, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            Map.Entry<String,Integer> itemEntry = lfuItemList.get(0);
            itemEntry.setValue(itemEntry.getValue()+1);
            System.out.println(itemEntry.getKey());
        }

    }


    @Override
    public ReturnT<String> routeRun(TriggerParam triggerParam, ArrayList<String> addressList) {
        return null;
    }
}