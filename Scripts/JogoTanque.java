import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;
import java.util.*;

public class JogoTanque extends JFrame{
	static DataOutputStream dos = null;
	static DataInputStream dis = null;
    static String inputLine;

    JButton jogar = new JButton("JOGAR");
    JLabel label1 = new JLabel("Game War");
    JPanel p1 = new JPanel();
    Image[] vidas = new Image[4];
    Image[] img = new Image[30];
    Image[] obs = new Image[21];
    Image[] enemy = new Image[5];
    Image[] bomba = new Image[4];
    Rectangle[] p = new Rectangle[30];
    Rectangle p2 = new Rectangle(30,30);
    Rectangle p3 = new Rectangle(35,140);
    Rectangle[] p4 = new Rectangle[21];
    Rectangle p5 = new Rectangle(11,11);
    Rectangle p6 = new Rectangle(11,11);    
	Rectangle borda1 = new Rectangle(600,20);
	Rectangle inimigo = new Rectangle(30,30);
	Image fundo;
    private int i=0,acionado=0;
    int[] posX = new int[21];
    int[] posY = new int[21];
    static int x=272,y=480,posXb,posYb,posXbe,posYbe, xe=272, ye=30 	;
    final int b1 = 250,b2=20;
    Janela menu = new Janela();
    static int nump; //NUMERO DO CLIENTE (1 ou 2)
    int estado=1,estado2,conttiro=0;
    int vida1, vida2;
    int atirou =0;
    int salva;
    int cont=0;
    int achou=0;
    boolean cima,baixo,esquerda,direita,espaco,teclaapertada,gameover = false;
	Teclado teclado = new Teclado();

    class Janela extends JPanel {
		Janela(){
            try{
				vida1 = 3;
				vida2 = 3;
                
                enemy[1] = ImageIO.read(new File("anda5.png"));
                enemy[2] = ImageIO.read(new File("anda6.png"));
                enemy[3] = ImageIO.read(new File("anda7.png"));
                enemy[4] = ImageIO.read(new File("anda8.png"));
                img[0] = ImageIO.read(new File("Background2.png"));
				fundo  = ImageIO.read(new File("GameOver.png"));
         
				//OBSTACULOS
                obs[0] = ImageIO.read(new File("1.png"));
                posX[0] = 80;
                posY[0]=305;
                obs[1] = ImageIO.read(new File("2.png"));
                posX[1] = 300;
                posY[1] = 400;
                obs[2] = ImageIO.read(new File("5.png"));
                posX[2] = 80;
                posY[2] = 65;
                obs[3] = ImageIO.read(new File("3.png"));
                posX[3] = 320;
                posY[3] = 120;
                obs[4] = ImageIO.read(new File("8.png"));
                posX[4] = 255;
                posY[4] = 470;
                obs[5] = ImageIO.read(new File("13.png"));
                posX[5] = 455;
                posY[5] = 350;
                obs[6] = ImageIO.read(new File("27.png"));
                posX[6] = 400;
                posY[6] = 75;
                obs[7] = ImageIO.read(new File("12.png"));
                posX[7] = 40;
                posY[7] = 250;
                obs[8] = ImageIO.read(new File("7.png"));
                posX[8]= 155;
                posY[8] =400;
                obs[9] = ImageIO.read(new File("20.png"));
                posX[9] = 80;
                posY[9] = 140;
                obs[10] = ImageIO.read(new File("15.png"));
                posX[10] = 180;
                posY[10] = 120;
                obs[11] = ImageIO.read(new File("16.png"));
                posX[11] = 240;
                posY[11] = 143;
                obs[12] = ImageIO.read(new File("17.png"));
                posX[12]= 200;
                posY[12] = 320;
                obs[13] = ImageIO.read(new File("27.png"));
                posX[13] = 450;
                posY[13] = 210;
                obs[14] = ImageIO.read(new File("24.png"));
                posX[14] = 330;
                posY[14] = 235;
                obs[15] = ImageIO.read(new File("25.png"));
                posX[15] = 155;
                posY[15] = 245;
                obs[16] = ImageIO.read(new File("21.png"));
                posX[16] = 250;
                posY[16] = 25;
                obs[17] = ImageIO.read(new File("23.png"));
                posX[17]= 280;
                posY[17] = 320;
                obs[18] = ImageIO.read(new File("28.png"));
                posX[18] = 491;
                posY[18] = 150;
         
         			obs[19] = ImageIO.read(new File("p.png"));
					posX[19] = 520;
                    posY[19]= 485;
                    obs[20] = ImageIO.read(new File("p2.png"));
                    posX[20] = 55;
                    posY[20]= 30;
         
                    img[1] = ImageIO.read(new File("anda1.png"));
                    img[2] = ImageIO.read(new File("anda2.png"));
                    img[3] = ImageIO.read(new File("anda3.png"));
                    img[4] = ImageIO.read(new File("anda4.png"));
                    img[5] = ImageIO.read(new File("shell1.png"));
                    img[6] = ImageIO.read(new File("shell2.png"));
                    img[7] = ImageIO.read(new File("shell3.png"));
                    img[8] = ImageIO.read(new File("shell4.png"));
                    img[9] = ImageIO.read(new File("explosao.png"));
										
					vidas[3] = ImageIO.read(new File ("vida3.png"));
					vidas[2] = ImageIO.read(new File ("vida2.png"));
					vidas[1] = ImageIO.read(new File ("vida1.png"));
					vidas[0] = ImageIO.read(new File ("vida0.png"));

					if(nump==1){
						posXb = x;
						posYb = y;
					}else{
						posXbe = xe;
						posYbe = ye;
					}
			
			}catch(IOException e ){
                System.out.println(e);
            }
            for(int i=0;i<21;i++){
                try{
                    p4[i] = new Rectangle(obs[i].getWidth(this),obs[i].getHeight(this));
                    //  System.out.println("ok");
                    p4[i].setLocation(posX[i],posY[i]);
                    System.out.println(""+p4[i]);
                }catch(NullPointerException e){
                }catch(ArrayIndexOutOfBoundsException a){}
            }
            jogar.setFont((new Font("GODOFWAR",Font.PLAIN,25)));
            jogar.setContentAreaFilled(false);
            jogar.setFocusPainted(false);
            jogar.setOpaque(false);
            jogar.setBackground(Color.WHITE);
            jogar.setBorder(null);
            jogar.setForeground(new Color(250,250,250));
            setLayout(null);

			Socket socket = null;
			try {  //CONEXÃO COM O SERVIDOR
				socket = new Socket("127.0.0.1", 80);
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				nump = dis.readInt();      //RECEBE DO SERVIDOR O NÚMERO DO JOGADOR
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host.");
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to host");
			}
			new atualizaPos().start();
        }

	public void paintComponent(Graphics g){
		if(gameover == false){
			fundoobstaculo(g);
			player(g);
			playervida(g);
			if(atirou == 1){
				tiro(g);
				//playervida(g);
			}
			//	playervida(g);
		}
		if(gameover == true){
			fimdejogo(g);
		}
	}
		
	public void fundoobstaculo(Graphics g){
		g.drawImage(img[0],0,0,getSize().width, getSize().height, this);
        for(int i=0; i<21; i++)
			g.drawImage(obs[i], posX[i], posY[i],null);
	}
		
	public void player(Graphics g){
        g.drawImage(img[estado],x,y,null);      //MOVIMENTO PLAYER 1
        g.drawImage(enemy[estado],xe,ye,null);  //MOVIMENTO PLAYER 2
		p2.setLocation(x, y);
		p3.setLocation(posX[0],posY[0]);
		p5.setLocation(posXb,posYb);
		p6.setLocation(posXbe, posYbe);
		inimigo.setLocation(xe,ye);
	}
		
	 public void tiro(Graphics g){
		if(atirou == 1){
			if(nump==1) {
				g.drawImage(img[estado2],posXb,posYb,null);
				g.drawRect((int)p5.getX(),(int)p5.getY(),(int)p5.getWidth(),(int)p5.getHeight());
			}else if(nump==2){
				g.drawImage(img[estado2],posXbe,posYbe,null);	
				g.drawRect((int)p6.getX(),(int)p6.getY(),(int)p6.getWidth(),(int)p6.getHeight());
			}
			if(estado2 == 9){
					try{
						Thread.sleep(200);
					}catch(InterruptedException e){}
					teclado.bomba = null;
					atirou = 0;
					repaint();
				}
			}
	}

	public void playervida(Graphics g){
		g.drawImage(vidas[vida1], 115, 557, 11, 11, this);
		g.drawImage(vidas[vida2], 510, 557, 11, 11, this);
	}

	public void fimdejogo(Graphics g){
		g.drawImage(fundo,0,0,getSize().width, getSize().height, this);
	}

    public Dimension getPreferredSize() {
        return new Dimension(627, 607);
    }

    } //fecha Janela

	class atualizaPos extends Thread {
		public void run(){
			do{		  			  
				if(nump==1)
					try{
						dos.writeInt(x); 
						dos.writeInt(y);
						dos.writeInt(vida1); 
						xe = dis.readInt();
						ye = dis.readInt();
						vida2 = dis.readInt();
					} catch(IOException e){
						System.out.println(e);
					}
				if(nump==2)
					try{
						dos.writeInt(xe); 
						dos.writeInt(ye);
						dos.writeInt(vida2);
						x = dis.readInt();
						y = dis.readInt();
						vida1 = dis.readInt();
					} catch(IOException e){
						System.out.println(e);
					}
			System.out.println("x = "+x+"  y = "+y);
			System.out.println("xe = "+xe+"  ye = "+ye);
			repaint();
			} while(true);
		}
	};
    
    JogoTanque(){
        super("Jogo Tanque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		gameover = false;
        add(menu);
        pack();
        setVisible(true);
        addKeyListener(teclado);
	}

    class Botao implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == jogar){
				System.out.println("jogar");
				acionado = 1;
				try{
					img[0] = ImageIO.read(new File("Background2.png"));
				}catch (IOException a){}
				repaint();
            }
        }
    }

	class Teclado extends KeyAdapter {
		public Bomba bomba = null;
		public void keyPressed(KeyEvent e){
			teclaapertada = true;
			switch (e.getKeyCode()){
				case KeyEvent.VK_UP:
					cima = true;
					mudaimagem();
					repaint();
					break;
				case KeyEvent.VK_DOWN:
					baixo = true;
					mudaimagem();
					repaint();
					break;
				case KeyEvent.VK_LEFT:
					esquerda = true;
					mudaimagem();
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					direita = true;
					mudaimagem();
					repaint();
					break;
				case KeyEvent.VK_SPACE:
					espaco = true;
					if(bomba == null && conttiro < 4){
						mudaimagem();
						repaint();
						conttiro++;
//						System.out.println("Conttiro = "+conttiro);
					}
					else{
						tempo();
					}
			}
		}

		public void tempo(){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e ){}
			conttiro =0;
		}

		public void keyReleased(KeyEvent e) {
			teclaapertada = false;
			cima = false;
			direita = false;
			esquerda = false;
			baixo = false;
			espaco = false;
		}
    }

    public int interseccao(){
        int i=0;
        while(i<21){
            if(i != 4 && i!= 16){
                if(p2.intersects(inimigo) == true){
					return 1;
				}
                if(p2.intersects(p4[i]) || inimigo.intersects(p4[i]) == true){
                    return 1;
                }

            }
            i++;
        }
        return 0;
    }

    public int interseccaobala(){
		int i=0;
        while(i<21){
            if(i != 4 && i!= 16){
                if(p5.intersects(p4[i]) || p6.intersects(p4[i]) == true){
                    return 1;
                } 
				if(p5.intersects(inimigo) || p6.intersects(p2) == true){
					return -1;
				}
			}
            i++;
        }
        return 0;
    }

    public void mudaimagem(){
		Teclado b = new Teclado();
		if(teclaapertada == true){
			if(cima == true){
				if(nump==1)
					p2.setLocation(x,y-10);
				else
					inimigo.setLocation(xe, ye-10);
				if(interseccao() == 0){
					estado =1;
					if(y>30 && nump==1)
						y -= 10;
					else if(ye>30 && nump==2)
						ye -= 10;
				}
			}
			else if(baixo == true){
				if(nump==1)
					p2.setLocation(x,y+10);
				else
					inimigo.setLocation(xe, ye+10);
				if(interseccao() != 1 && interseccao() != 3){
					estado = 3;
					if(y<480 && nump==1)
						y += 10;
					else if(ye<480 && nump==2)
						ye += 10;
				}
			}
			else if(esquerda == true){
				if(nump==1)
					p2.setLocation(x,y);
				else
					inimigo.setLocation(xe, ye);
				if(interseccao() != 1 && interseccao() != 4){
					estado = 4;
					if(x > 47 && nump==1)
						x -= 10;
					else if(xe > 47  && nump==2)
						xe -= 10;
				}
			}
			else if(direita == true){
				if(nump==1)
					p2.setLocation(x+10,y);
				else
					inimigo.setLocation(xe+10, ye);
				if(interseccao() != 1){
					estado = 2;
					if(x < 550 && nump==1)
						x +=10;
					else if(xe < 550 && nump==2)
						xe +=10;
				}
			}
			else if(espaco == true){
				switch(estado){
					case 1 : 
						if(nump==1){
							posXb = posXb+16;
							posYb = posYb;
							p5.setLocation(posXb,posYb + 5);
						} else{
							posXbe = posXbe+16;
							posYbe = posYbe;
							p6.setLocation(posXbe,posYbe + 5);
						}	
						break;
					case 2 : 
						if(nump==1){
							posYb = posYb+4;
							posXb = posXb + 5 + img[2].getWidth(null);
							p5.setLocation(posXb+5,posYb);
						} else{
							posYbe = posYbe+4;
							posXbe = posXbe + 5 + img[2].getWidth(null);
							p6.setLocation(posXbe+5,posYbe);							
						}
						break;
					case 3 : 
						if(nump==1){
							posXb = posXb +11;
							posYb = posYb + img[3].getHeight(null);
							p5.setLocation(posXb,posYb +5);
						} else{
							posXbe = posXbe +11;
							posYbe = posYbe + img[3].getHeight(null);
							p6.setLocation(posXbe,posYbe +5);
						}							
						break;
					case 4 : 
						if(nump==1){
							posYb = posYb+8;
							posXb = posXb - img[4].getWidth(null);
							p5.setLocation(posXb-5,posYb);
						} else {
							posYbe = posYbe+8;
							posXbe = posXbe - img[4].getWidth(null);
							p6.setLocation(posXbe-5,posYbe);							
						}
						break;
			}
			salva = estado;
			atirou = 1;
			posXb = x;
			posYb = y;
			posXbe = xe;
			posYbe = ye;
			System.out.println("Ok - bomba");
			b.bomba = new Bomba();
			b.bomba.start();
			System.out.println("OK");
		}
			else{
				estado = estado;
			}
		}
	}
	private void Pintar(){
		do{
			repaint();
		} while(true);
	}
	
    class Bomba extends Thread{
		int a=0;
        public void run(){
			a = interseccaobala();
			while(( a == 0)){
				a = interseccaobala();
				switch(salva){
                    case 1 :
                        estado2 = 6;
                        if(nump==1){
							posYb -=5;
							p5.setLocation(posXb,posYb -5);
						} else{
							posYbe -=5;
							p6.setLocation(posXbe, posYbe -5);
						}
                        break;
                    case 2:
                        estado2 = 5;
                        if(nump==1){
							posXb +=5;
							p5.setLocation(posXb+5,posYb);
						} else{
							posXbe +=5;
							p6.setLocation(posXbe+5,posYbe);
						}
                        break;
                    case 3 :
                        estado2 = 8;
                        if(nump==1){
							posYb +=5;
							p5.setLocation(posXb,posYb +5);
						} else{
							posYbe +=5;
							p6.setLocation(posXbe, posYbe +5);
						}
                        break;
                    case 4 :
                        estado2 = 7;
                        if(nump==1){
							posXb -=5;
							p5.setLocation(posXb-5,posYb);
                        } else{
							posXbe -=5;
							p6.setLocation(posXbe-5, posYbe);
                        }
                        break;
                }
            repaint();
            try{
                sleep(10);
            }catch (InterruptedException e){
			}catch(NoSuchElementException e){}
            }
            estado2 = 9;
            repaint();
			if(a == -1){
				if(nump==1){
					vida2--;
					if(vida1 == 0){
						gameover = true;
						repaint();
					}
				}if(nump==2)
					vida1--;
					if(vida2 == 0){
						gameover = true;
						repaint();
					}
			}else{
				repaint();
			}
			}
		}
    


    static public void main(String[] args) {
		JogoTanque jogo = new JogoTanque();
	}
}

