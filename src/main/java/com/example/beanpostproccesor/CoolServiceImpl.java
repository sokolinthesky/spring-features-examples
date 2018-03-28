package com.example.beanpostproccesor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@CoolProfiling
@Service
public class CoolServiceImpl implements CoolService {
    private static final Logger log = LoggerFactory.getLogger(CoolServiceImpl.class);

    @Override
    public void doCoolStaff() {
        log.info("I am ding cool staff...");
    }
}
