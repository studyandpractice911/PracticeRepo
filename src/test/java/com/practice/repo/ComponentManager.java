package com.practice.repo;

import com.practice.repo.utils.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ComponentManager extends SpringTestConfiguration {

    @Autowired
    BaseComponent baseComponent;

    public <T> T getComponent(Class<T> component) {
        if (!component.getPackageName().contains("com.practice.repo.components")) {
            throw new RuntimeException("getComponent() can only be used to access beans from package : com.practice.repo.components");
        }
        assert applicationContext != null;
        T bean = applicationContext.getBean(component);
        String packageName;
        String resourcePath;
        boolean shouldLaunchWebDriver = false;
        boolean shouldLaunchAndroidDriver = false;
        boolean shouldConfigureRestAssuredRequest = false;
        try {
            Resource resource = bean.getClass().getAnnotation(Resource.class);
            resourcePath = resource.path();
            switch (resource.type()) {
                case WEB -> shouldLaunchWebDriver = true;
                case ANDROID -> shouldLaunchAndroidDriver = true;
                case API -> shouldConfigureRestAssuredRequest = true;
            }
            packageName = Arrays.stream(bean.getClass().getPackageName().split("\\.")).toList().getLast();
        } catch (NullPointerException e) {
            throw new NullPointerException("Please define @Resource annotation in class : " + bean.getClass().getCanonicalName());
        }
        if (shouldLaunchWebDriver) baseComponent.launchWebDriver(packageName, resourcePath);
        if (shouldLaunchAndroidDriver) baseComponent.launchAndroidDriver(packageName, resourcePath);
        if (shouldConfigureRestAssuredRequest) baseComponent.requestSpecification(packageName, resourcePath);
        return bean;
    }

}
