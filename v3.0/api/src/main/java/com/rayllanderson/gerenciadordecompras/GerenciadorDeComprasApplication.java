package com.rayllanderson.gerenciadordecompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class GerenciadorDeComprasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorDeComprasApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

}
