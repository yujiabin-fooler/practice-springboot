package com.jiabin.dynamic.router.practice.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class PackHandlerClassLoader extends ClassLoader {

  private File path ;
  
  public PackHandlerClassLoader(File path) {
    this.path = path ;
  }
  
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    // 1.先父类加载器加载
    Class<?> clazz = super.loadClass(name) ;
    if (clazz == null) {
      clazz = findClass(name) ;
    }
    // 2.父加载器无法加载再自己加载
    return super.loadClass(name) ;
  }
  
  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] bytes = getClassBytes() ;
    if (bytes == null) {
      return null ;
    }
    return this.defineClass(name, bytes, 0, bytes.length) ;
  }
  
  private byte[] getClassBytes() {
    try (FileInputStream fis = new FileInputStream(this.path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      byte [] buf = new byte[1024 * 10] ;
      int leng = -1 ;
      while((leng = fis.read(buf)) > -1) {
        baos.write(buf, 0, leng) ;
      }
      return baos.toByteArray() ;
    } catch (Exception e) {
      System.err.println(e.getMessage()) ;
      return null ;
    }
  }
}