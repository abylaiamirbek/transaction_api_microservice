package kz.abylai.solva.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "kz.abylai.solva.feigns")
public class FeignClientConfiguration {
}
