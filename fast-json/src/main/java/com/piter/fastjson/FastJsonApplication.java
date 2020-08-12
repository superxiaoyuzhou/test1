package com.piter.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class FastJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastJsonApplication.class, args);
    }

//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters(){
//        //1. 需要定义一个converter转换消息的对象
//        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();
//
//        //2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//
//        //3. 在converter中添加配置信息
//        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        HttpMessageConverter<?> converter = fasHttpMessageConverter;
//        return new HttpMessageConverters(converter);
//    }

    /**
     * spring mvc/RestTemplate/fegin Converters
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(HttpMessageConverter.class)
    public HttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setCharset(Charset.forName("utf-8"));

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        return fastConverter;
    }

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate okHttp3RestTemplate(){
        OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        // 连接超时
        okHttp3ClientHttpRequestFactory.setConnectTimeout(10*1000);
        // 读取超时
        okHttp3ClientHttpRequestFactory.setReadTimeout(60*1000);
        // 写入超时
        okHttp3ClientHttpRequestFactory.setWriteTimeout(60*1000);
        RestTemplate restTemplate = new RestTemplate(okHttp3ClientHttpRequestFactory);

        // 添加fastjson
        List<HttpMessageConverter<?>> httpMessageConverterList= restTemplate.getMessageConverters();
        httpMessageConverterList.add(fastJsonHttpMessageConverter());
        //删除jackson,避免使用jackson
        for(Iterator<HttpMessageConverter<?>> iterator = httpMessageConverterList.iterator(); iterator.hasNext();){
            if(iterator.next() instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }
        // String换为utf-8
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverterList) {
            if(httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
                break;
            }
        }
        return restTemplate;
    }
}
