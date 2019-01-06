package com.ironshutter.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * Jpa Object의 json 변환중 lazy load 하는 문제를 해결하기 위한 설정
 * 이 설정은 JSON 변환 시 lazy load를 하지 않게 해준다.
 * */
@Configuration
public class JpaJacksonConfiguration extends WebMvcConfigurerAdapter{
	
    //More configuration....

    /* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
     * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
/*    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        
        Hibernate4Module hm = new Hibernate4Module();
        hm.disable(Feature.USE_TRANSIENT_ANNOTATION);//this option is that enable serializing and deserializing of object property which is annotated as jpa:@Transient !!
        mapper.registerModule(hm);

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }*/
}
