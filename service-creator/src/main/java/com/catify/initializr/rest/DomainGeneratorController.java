package com.catify.initializr.rest;

import com.catify.initializr.domain.Domain;
import com.catify.initializr.domain.MicroService;
import com.catify.initializr.services.ZipService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claus
 */
@RestController
public class DomainGeneratorController {
    
    private static final Logger LOG= Logger.getLogger( DomainGeneratorController.class.getName() );
    
    private final ZipService zip;

    @Autowired
    public DomainGeneratorController(ZipService zip) {
        this.zip = zip;
    }
    
    @RequestMapping(value = "/zip", method = RequestMethod.POST, produces = "application/zip")
    public byte[] createDomain(@RequestBody Domain domain) {
        LOG.log(Level.INFO, "request to create domain");
        return zip.zipFileSystem(domain);
    }
    
    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public Domain createSample() {
        LOG.log(Level.INFO, "request to create sample");
        Domain domain = new Domain("My-Domain", "com.my.domain");
        domain.addService(new MicroService("service-a", "0.0.1-SNAPSHOT"));
        domain.addService(new MicroService("service-b", "0.0.1-SNAPSHOT"));
        return domain;
    }
    
}