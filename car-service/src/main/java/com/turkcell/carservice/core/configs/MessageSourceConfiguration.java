package com.turkcell.carservice.core.configs;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MessageSourceConfiguration {
  @Bean
  public ResourceBundleMessageSource bundleMessageSource() {

    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasenames("messages");
    return source;
  }

  @Bean
  public AcceptHeaderLocaleResolver localResolver() {
    AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
    acceptHeaderLocaleResolver.setDefaultLocale(new Locale("tr"));
    return acceptHeaderLocaleResolver;
  }
}
