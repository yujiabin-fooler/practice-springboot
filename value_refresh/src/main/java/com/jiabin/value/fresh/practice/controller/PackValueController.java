package com.jiabin.value.fresh.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.value.fresh.practice.propertysource.UpdatePropertySource;
import com.jiabin.value.fresh.practice.test.PackComponentInteger;
import com.jiabin.value.fresh.practice.test.PackComponentString;

@RestController
@RequestMapping("/packvalue")
public class PackValueController {

  private final UpdatePropertySource updatePropertySource ;
  private final PackComponentInteger pci ;
  private final PackComponentString pcs ;
  public PackValueController(UpdatePropertySource updatePropertySource,
      PackComponentInteger pci, PackComponentString pcs) {
    this.updatePropertySource = updatePropertySource ;
    this.pci = pci ;
    this.pcs = pcs ;
  }
  
  @GetMapping("/{key}/{value}")
  public Object change(@PathVariable("key") String key, @PathVariable("value") String value) {
    this.updatePropertySource.updateProperty(key, value) ;
    return "success" ;
  }
  
  @GetMapping("/get")
  public String get() {
    System.out.println(pci.hashCode()) ;
    System.out.println(pcs.hashCode()) ;
    return String.format("PackComponentInteger[sno = %s] / PackComponentString[sno = %s]", 
        pci.getSno(), pcs.getSno()) ;
  }
}
