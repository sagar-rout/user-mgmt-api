package com.rout.usermgmt;

import com.rout.usermgmt.domain.AuditorAwareImpl;
import com.rout.usermgmt.domain.User;
import com.rout.usermgmt.repo.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author sagar@sagarrout.com
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan(basePackageClasses = {User.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringDataConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
