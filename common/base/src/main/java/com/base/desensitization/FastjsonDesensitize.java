//package com.base.desensitization;
//
//import com.alibaba.fastjson.serializer.ValueFilter;
//import lombok.extern.log4j.Log4j2;
//import java.lang.reflect.Field;
//import java.util.List;
//
///**
// *
// */
//@Log4j2
//public class FastjsonDesensitizeFilter implements ValueFilter,DesensitizeService {
//    @Override
//    public Object process(Object object, String name, Object value) {
//        if (null == value || !(value instanceof String) || ((String) value).length() == 0) {
//            return value;
//        }
//        try {
//            Field field = object.getClass().getDeclaredField(name);
//            Desensitization desensitization;
//            if (String.class != field.getType() || (desensitization = field.getAnnotation(Desensitization.class)) == null) {
//                return value;
//            }
//            DesensitionType type = desensitization.type();
//            List<String> regular=this.desensitize(type,desensitization);
//            if (regular.size() > 1) {
//                String match = regular.get(0);
//                String result = regular.get(1);
//                if (null != match && result != null && match.length() > 0) {
//                    return ((String) value).replaceAll(match, result);
//                }
//            }
//        } catch (Exception e) {
//            log.warn("FastJsonDesensitizeFilter the class {} has no field {}", object.getClass(), name);
//        }
//        return value;
//    }
//}
