import java.net.*;
import java.io.*;
import java.util.*;

class Servidor {
  public static void main(String[] args) {
    ServerSocket serverSocket=null;

    try {
      serverSocket = new ServerSocket(80);
    } catch (IOException e) {
      System.out.println("Could not listen on port: " + 80 + ", " + e);
      System.exit(1);
    }
	
	Socket p1 = null;
	Socket p2 = null;
	
      Socket clientSocket = null;
      try {
        System.out.println("Aguardando o primeiro jogador!");
        p1 = serverSocket.accept();
        System.out.println("Aguardando o segundo jogador!");
        p2 = serverSocket.accept();
      } catch (IOException e) {
        System.out.println("Accept failed: " + 80 + ", " + e);
        System.exit(1);
      }

      System.out.println("Accept Funcionou!");

      new Servindo(p1, 1).start(); //serve para o player 1
      new Servindo(p2, 2).start(); //serve para o player 2


    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


class Servindo extends Thread {
  Socket clientSocket;
  static int np=0;
  private int playn=0;
  private int num;
  static int x, xe, y, ye, v1, v2;
  static PrintStream os[] = new PrintStream[2];
  static int cont=0;
  static DataOutputStream dos[] = new DataOutputStream[2];
  static DataInputStream dis[] = new DataInputStream[2];
  
  Servindo(Socket clientSocket, int n) {
    this.clientSocket = clientSocket;
    playn = n;
    np++;
    num=n-1;
  }

  public void run() {
    try {
	  dis[num] = new DataInputStream(clientSocket.getInputStream());   //Socket de input de dados 
	  dos[num] = new DataOutputStream(clientSocket.getOutputStream()); //Socket de output de dados -- num=1 para player 2
	
	  new MandarPos().start(); //Começa a thread de troca de dados

    } catch (IOException e) {
      e.printStackTrace();
    } catch (NoSuchElementException e) {
      System.out.println("Conexacao terminada pelo cliente");
    } 
  }

class MandarPos extends Thread {
	
	public void run() {
		try{
			(dos[num]).writeInt(playn);  //ENVIA AO CLIENTE O NÚMERO DE JOGADOR (1 ou 2) 
		} catch(IOException e){
			System.out.println(e);
		}
		do{		
			if(num==0)
				try{
					x = (dis[num]).readInt();
					y = (dis[num]).readInt();
					v1 = (dis[num]).readInt();
					dos[num].writeInt(xe);
					dos[num].writeInt(ye);	
					dos[num].writeInt(v2);				
				} catch(IOException e){};
			if(num==1)
				try{
					xe = (dis[num]).readInt();
					ye = (dis[num]).readInt();
					v2 = (dis[num]).readInt();
					dos[num].writeInt(x);
					dos[num].writeInt(y);
					dos[num].writeInt(v1);
				}catch(IOException e){};
			try{
				sleep(100);
			}catch(InterruptedException e){};

			} while(true);
		}
	}
};

/*				switch(num){
//					case 0:
				

						posx = (dis[0]).readInt(); //RECEBE A POSICAO X OU XE (depende do cliente que enviou)
						posx2 = (dis[0]).readInt(); //RECEBE A POSICAO X OU XE (depende do cliente que enviou)
						System.out.println("Posicao do player 1: " + posx);
						System.out.println("Posicao do player 2: " +posx2);
						(dos[0]).writeInt(posx); //retorna o valor para o cliente 2
						(dos[0]).writeInt(posx2); //retorna o valor para o cliente 1

//						break;
//					case 1:
			//			System.out.println("Posicao do player 2: " + posx);
				//		(dos[0]).writeInt(posx); //retorna o valor para o cliente 1
			//			break;
				//	default:
				//		System.out.println("erro");
				} */	

