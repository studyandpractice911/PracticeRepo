package com.practice.repo.utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AutomationCapabilities extends DesiredCapabilities {

    @Override
    public void setCapability(String capabilityName, String value) {
        super.setCapability(capabilityName, value);
    }

    @Override
    public Object getCapability(String capabilityName) {
        return super.getCapability(capabilityName);
    }

    @Override
    public Set<String> getCapabilityNames() {
        return super.getCapabilityNames();
    }

    @Override
    public boolean is(String capabilityName) {
        return super.is(capabilityName);
    }
}
