//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.context.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class AnnotationBeanNameGenerator implements BeanNameGenerator {
    public static final AnnotationBeanNameGenerator INSTANCE = new AnnotationBeanNameGenerator();
    private static final String COMPONENT_ANNOTATION_CLASSNAME = "org.springframework.stereotype.Component";
    private final Map<String, Set<String>> metaAnnotationTypesCache = new ConcurrentHashMap();

    public AnnotationBeanNameGenerator() {
    }

    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

        if (definition instanceof AnnotatedBeanDefinition) {
            String beanName = this.determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
            if (StringUtils.hasText(beanName)) {
                return beanName;
            }
        }

        return this.buildDefaultBeanName(definition, registry);
    }

    @Nullable
    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
        AnnotationMetadata amd = annotatedDef.getMetadata();
        Set<String> types = amd.getAnnotationTypes();
        String beanName = null;
        Iterator var5 = types.iterator();

        while (var5.hasNext()) {
            String type = (String) var5.next();
            AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(amd, type);
            if (attributes != null) {
                Set<String> metaTypes = (Set) this.metaAnnotationTypesCache.computeIfAbsent(type, (key) -> {
                    Set<String> result = amd.getMetaAnnotationTypes(key);
                    return result.isEmpty() ? Collections.emptySet() : result;
                });
                if (this.isStereotypeWithNameValue(type, metaTypes, attributes)) {
                    Object value = attributes.get("value");
                    if (value instanceof String) {
                        String strVal = (String) value;
                        if (StringUtils.hasLength(strVal)) {
                            if (beanName != null && !strVal.equals(beanName)) {
                                throw new IllegalStateException("Stereotype annotations suggest inconsistent component names: '" + beanName + "' versus '" + strVal + "'");
                            }

                            beanName = strVal;
                        }
                    }
                }
            }
        }

        return beanName;
    }

    protected boolean isStereotypeWithNameValue(String annotationType, Set<String> metaAnnotationTypes, @Nullable Map<String, Object> attributes) {
        boolean isStereotype = annotationType.equals("org.springframework.stereotype.Component") || metaAnnotationTypes.contains("org.springframework.stereotype.Component") || annotationType.equals("javax.annotation.ManagedBean") || annotationType.equals("javax.inject.Named");
        return isStereotype && attributes != null && attributes.containsKey("value");
    }

    protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return this.buildDefaultBeanName(definition);
    }

    protected String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();

        Assert.state(beanClassName != null, "No bean class name set");
        String shortClassName = ClassUtils.getShortName(beanClassName);
        String[] s = beanClassName.split("\\.");
        if (s.length > 4 && "service".equals(s[0]) && "services".equals(s[2]) && "impls".equals(s[3])) {
            log.debug("[DUBBO] {}{} 初始化名字", s[1],beanClassName);
            return Introspector.decapitalize(s[1] + "-" + shortClassName);
        }
        return Introspector.decapitalize(shortClassName);
    }
}
