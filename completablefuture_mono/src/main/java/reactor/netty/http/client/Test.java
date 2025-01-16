package reactor.netty.http.client;

public class Test {

  public static void main(String[] args) {
    
    HttpConnectionProvider p = new HttpConnectionProvider() ;
    System.out.println(p.maxConnections()) ;
  }
  
}
