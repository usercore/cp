package com.bc.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:/conf/applicationProvider.xml"})
public class Config {

}
