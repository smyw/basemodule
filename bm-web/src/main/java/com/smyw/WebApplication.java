package com.smyw;

import com.smyw.core.repo.base.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

@EnableJpaRepositories(
		repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan
public class WebApplication {



	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize("128KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("256KB");
		//Sets the directory location wherefiles will be stored.
		//factory.setLocation("路径地址");
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
