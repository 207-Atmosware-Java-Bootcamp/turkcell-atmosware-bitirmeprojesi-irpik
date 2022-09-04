package com.kadirirpik.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;

@Controller
@EnableJpaAuditing
public class AuditorAwareBean {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
