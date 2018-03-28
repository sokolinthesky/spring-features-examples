package com.example.beanpostproccesor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CoolProfilingBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(CoolProfilingBeanPostProcessor.class);

    private Map<String, Class> beansNeedToCoolProfiling = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(CoolProfiling.class)) {
            beansNeedToCoolProfiling.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = beansNeedToCoolProfiling.get(beanName);
        if (!Objects.isNull(beanClass)) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (o, method, objects) -> {
                log.info("I am profiling...");
                long before = System.nanoTime();
                Object retVal = method.invoke(bean, objects);
                long after = System.nanoTime();
                log.info(String.valueOf(after - before));
                log.info("I have finished profiling!");
                return retVal;
            });
        }
        return bean;
    }
}
