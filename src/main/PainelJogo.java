package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Player;

import javax.swing.JPanel;

import tile.TileManager;

public class PainelJogo extends JPanel implements Runnable{
    final int TamTileOriginal = 16;
    final int Escala = 3;

    public final int TamTile = TamTileOriginal * Escala; // Tamanho 48x48px

    public int  JanelaMaxColuna = 16;
    public int  JanelaMaxLinha = 12;
    public int  LarguraJanela = JanelaMaxColuna * TamTile; // 768px
    public int  AlturaJanela = JanelaMaxLinha * TamTile; // 576px

    //Configuracoes do mundo
    public final int mundoMaxColuna = 50;
    public final int mundoMaxLinha = 50;
    public final int mundoAltura = TamTile * mundoMaxColuna;
    public final int mundoLargura = TamTile * mundoMaxLinha;

    //FPS
    int fps = 60;

    ConfTeclado  confTec = new ConfTeclado();
    Thread gameThread;
    public ChecadorColisao cColisao = new ChecadorColisao(this);
    public Player player = new Player(this, confTec);
    TileManager tileM = new TileManager(this);

    public PainelJogo(){
        this.setPreferredSize(new Dimension(LarguraJanela, AlturaJanela));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        this.addKeyListener(confTec);
        this.setFocusable(true);


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run(){

        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta > 1){
                update();
                repaint();
                delta--;
                drawCounter++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
        }
    }

    public void update(){
       player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        

        g2.dispose();

    }
}
