import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public final static int port = 2048;  
  public final static String dosya = "C:\\Users\\1905d\\Desktop\\Gönder\\abcd.png";  

  public static void main (String [] args ) throws IOException {
      
    FileInputStream fi = null;
    BufferedInputStream bi = null;
    OutputStream os = null;
    ServerSocket ssock = null;
    Socket sock = null;
    try {
      ssock = new ServerSocket(port);
      while (true) {
        System.out.println("Client Bekleniyor");
        try {
          sock = ssock.accept();
          System.out.println("Bağlantı Tamam ");
          File File = new File (dosya);
          byte [] b  = new byte [(int)File.length()];
          fi = new FileInputStream(File);
          bi = new BufferedInputStream(fi);
          bi.read(b,0,b.length);
          os = sock.getOutputStream();
          System.out.println("Gönderiliyor " + dosya);
          os.write(b,0,b.length);
          os.flush();
          System.out.println("Bitti.");
        }
        finally 
        {
          if (bi != null) bi.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally 
    {
      if (ssock != null) ssock.close();
    }
  }
}