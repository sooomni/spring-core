package hello.core.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void FilterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponenetFilterAppConfig.class);
        BeanA a = ac.getBean("beanA",BeanA.class);
        org.assertj.core.api.Assertions.assertThat(a).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB",BeanB.class) );

    }
    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponenetFilterAppConfig{

    }
}
