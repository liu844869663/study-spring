package com.fullmoon.study.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class GenericUtil {
    public static void invoke() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-sample");
        application.setQosEnable(false);

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("192.250.107.148:2181");
        registry.setCheck(true);

        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setRegistry(registry);
        reference.setApplication(application);
        reference.setProtocol("dubbo");
        reference.setInterface("com.idwzx.info.service.IStockExchangeService");
        reference.setVersion("1.0.0");
        reference.setGeneric(true);

        // 方法配置
        List<MethodConfig> methods = new ArrayList<>();
        MethodConfig method = new MethodConfig();
        method.setName("getStockExchangeReport");
        method.setTimeout(10000);
        method.setRetries(1);
        methods.add(method);
        reference.setMethods(methods);

        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

        Map<String,Object> param = new HashMap<>();
        param.put("trdCode", "000159.SZ");

        Object result = genericService.$invoke("getStockExchangeReport", new String[]{"com.idwzx.info.domain.SalesDeParam"}, new Object[]{param});
        log.info("result::{}", JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss" , SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect));
    }

    public static void main(String[] args){
        GenericUtil.invoke();
    }
}
