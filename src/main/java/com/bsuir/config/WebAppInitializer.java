package com.bsuir.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Класс, который используется, чтобы инициализировать так называемый Front Controller
 * ({@link org.springframework.web.servlet.DispatcherServlet}) - класс, который перехватывает все запросы, поступающие
 * к приложению, являющийся стержнем этого приложения. Через него запросы делегируются контроллерам, которые мы
 * сами реализуем (пакет {@link com.bsuir.controller})
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Указываем класс конфиг-класс с бинами
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataConfig.class};
    }

    //Указываем класс-настройщик веб-приложения
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //Указываем, какие запросы будет обрабатывать Front Controller(В нашем случае все)
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
