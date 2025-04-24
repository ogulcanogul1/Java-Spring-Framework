package main;

import com.example.beans.Person;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example4 {
    public static void main(String[] args) {

       var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

       Person person = context.getBean(Person.class);

        System.out.println(person);

//        NoUniqueBeanDefinitionException 3 tane aynı tipte bean var hangisini getireceğine karar veremiyor
        // bunun için bu çakışan beanlerden birine @Primary eklenebilir.

        // diğeri de Person ekleyeceğimiz Vehicle nesnesinin adını @Qualifier ile belirtebiliriz.

    }
}



