package com.common.desensitization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public interface DesensitizeService {
    default List<String> truncateRender(String[] attachs) {
        List<String> regular = new ArrayList<>();
        if (null != attachs && attachs.length > 1) {
            String rule = attachs[0];
            String size = attachs[1];
            String template, result;
            if ("0".equals(rule)) {
                template = "^(\\S{%s})(\\S+)$";
                result = "$1";
            } else if ("1".equals(rule)) {
                template = "^(\\S+)(\\S{%s})$";
                result = "$2";
            } else {
                return regular;
            }
            if (Integer.parseInt(size) > 0) {
                regular.add(0, String.format(template, size));
                regular.add(1, result);
            }
        }
        return regular;
    }

    default List<String> desensitize(DesensitionType desensitionType, Desensitization desensitization) {
        List<String> rule;
        switch (desensitionType) {
            case CUSTOM:
                rule = Arrays.asList(desensitization.attach());
                break;
            case TRUNCATE:
                rule = this.truncateRender(desensitization.attach());
                break;
            default:
                rule = Arrays.asList(desensitionType.getRule());
        }
        return rule;
    }
}