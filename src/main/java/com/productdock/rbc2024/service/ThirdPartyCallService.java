package com.productdock.rbc2024.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThirdPartyCallService {

    // Simulate a call to a third-party service
    public void connectToThirdPartyService() {
        log.error("Connection to third-party service failed.");
    }

    // Simulate fetching a list of files from a third-party service
    public List<String> getListOfFiles() {
        return List.of();
    }
}
