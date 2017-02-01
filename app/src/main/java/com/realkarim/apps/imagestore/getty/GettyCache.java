package com.realkarim.apps.imagestore.getty;

import java.util.LinkedHashMap;

/**
 * Created by Karim Mostafa on 2/1/17.
 */

class GettyCache {
    private static LinkedHashMap<Integer, String> cacheContainer = new LinkedHashMap<>();
    private static int MAX_CACHE_SIZE = 50;

    static void cache(String url, String result){
        if(cacheContainer.size() == MAX_CACHE_SIZE){    // check if cache size reached the maximum
            Integer key = cacheContainer.keySet().iterator().next(); // get first key inserted
            removeFromCache(key); // remove it
        }
        cacheContainer.put(url.hashCode(), result);
    }

    static void removeFromCache(Integer key){
        cacheContainer.remove(key);
    }

    static boolean isCached(String key){
        if(cacheContainer.containsKey(key.hashCode()))
            return true;

        return false;
    }

    static String getFromCache(String key){
        return cacheContainer.get(key.hashCode());
    }
}
