package com.ftune.basic.android.core.network;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * Model exclusion strategy. Use to ignore model or model fields in serialize.
 */
public class ModelExclusionStrategy implements ExclusionStrategy {

    private final Class<?> excludedThisClass;
    private final Class<?> excludedThisClassFields;

    /***
     * Constructor
     *
     * @param excludedThisClass This class and its subclass will be ignore when doing serialize.
     * @param excluedThisClassFields These fields will be ignore when doing serialize.
     */
    public ModelExclusionStrategy(Class<?> excludedThisClass, Class<?> excluedThisClassFields) {
        this.excludedThisClass = excludedThisClass;
        this.excludedThisClassFields = excluedThisClassFields;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz == null) return false;
        if (clazz.equals(excludedThisClass)) return true;
        return shouldSkipClass(clazz.getSuperclass());
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(excludedThisClassFields);
    }

}
