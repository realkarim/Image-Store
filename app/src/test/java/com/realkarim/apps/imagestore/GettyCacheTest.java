package com.realkarim.apps.imagestore;

import com.realkarim.apps.imagestore.getty.GettyCache;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Karim Mostafa on 2/3/17.
 */

public class GettyCacheTest {

    @Test
    public void testCacheSuccess() {
        String key = "key";
        String value = "value";
        Assert.assertFalse("Pair cached before inserted", GettyCache.isCached(value));
        GettyCache.cache(key, value);
        Assert.assertTrue(GettyCache.isCached(key));
    }

    @Test
    public void testCacheExceedLimit() {
        String key = "key";
        String value = "value";
        Integer id = 0;

        for (int i = 0; i < GettyCache.getMaxCacheSize(); i++) {
            key += id++;
            value += id++;
            GettyCache.cache(key, value);
        }

        GettyCache.cache(key, value);

        Assert.assertFalse("First pair not removed", GettyCache.isCached("key0"));
        Assert.assertTrue("Last pair not inserted", GettyCache.isCached(key));

    }


    @Test
    public void testRemoveFromCache() {
        String key = "key";
        String value = "value";

        Assert.assertFalse("Pair cached before inserted", GettyCache.isCached(value));
        GettyCache.cache(key, value);
        Assert.assertTrue(GettyCache.isCached(key));
        GettyCache.removeFromCache(key.hashCode());
        Assert.assertFalse("Cached pari not removed", GettyCache.isCached(key));
    }

}
