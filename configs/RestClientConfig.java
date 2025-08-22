package com.practices.demo.configs;

import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
//@Qualifier()
public class RestClientConfig {
//@Value("${employee.service.base.url}")
private String BASE_URL="http://localhost:8080";

@Bean
    RestClient getRestClient(){
    return RestClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
            .build();

}
}
