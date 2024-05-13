package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.ConfTeclado;
import main.PainelJogo;

public class Player extends Entity{
    PainelJogo pJ;
    ConfTeclado cF;

    public final int telaX;
    public final int telaY;
    
    public Player(PainelJogo pJ, ConfTeclado cF){
        this.pJ = pJ;
        this.cF= cF;
        defineValoresPadroes();
        getPlayerImage();
        telaX = pJ.LarguraJanela/2 - (pJ.TamTile/2);
        telaY = pJ.AlturaJanela/2 - (pJ.TamTile/2);
    }

    public void defineValoresPadroes(){
        worldX = pJ.TamTile * 25;
        worldY = pJ.TamTile * 25;
        velocidade = 4;
        direcao = "baixo";
    }

    public void getPlayerImage(){
        try {

            cima1 = ImageIO.read(new FileInputStream("res/player/paradoCostas.png"));
            cima2 = ImageIO.read(new FileInputStream("res/player/andandoCostas.png"));

            baixo1 = ImageIO.read(new FileInputStream("res/player/paradoFrente.png"));
            baixo2 = ImageIO.read(new FileInputStream("res/player/andandoFrente.png"));

            esq1 = ImageIO.read(new FileInputStream("res/player/paradoEsquerda.png"));
            esq2 = ImageIO.read(new FileInputStream("res/player/andandoEsquerda.png"));

            dir1 = ImageIO.read(new FileInputStream("res/player/paradoDireita.png"));
            dir2 = ImageIO.read(new FileInputStream("res/player/andandoDireita.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if(cF.pressBaixo == true || cF.pressCima == true || cF.pressDireita == true || cF.pressEsquerda == true){
            
            if(cF.pressCima == true){
                direcao = "cima";
                worldY -= velocidade;
            }else if(cF.pressBaixo == true){
                direcao = "baixo";
                worldY += velocidade;
            }else if(cF.pressEsquerda == true){
                direcao = "esq";
                worldX -= velocidade;
            }else if(cF.pressDireita == true){
                direcao = "dir";
                worldX += velocidade;
            }
    
            contaSprite++;
            if(contaSprite > 10){
                if(numSprite == 1){
                    numSprite = 2;
                }
                else if(numSprite == 2){
                    numSprite = 1;
                }
                contaSprite = 0;
            }

        }

      
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;


        switch(direcao){
            case "cima": 
            if(numSprite == 1){
                image = cima1;
            }
            if(numSprite == 2){
                image = cima2;
            }
                break;

            case "baixo":
            if(numSprite == 1){
                image = baixo1;
                }
            if(numSprite == 2){
                image = baixo2;
            }
                break;

            case "esq":
            if(numSprite == 1){
                image = esq1;
            }
            if(numSprite == 2){
                image = esq2;
            }
                break;

            case "dir":
            if(numSprite == 1){
                image = dir1;
            }
            if(numSprite == 2){
                image = dir2;
            }
                break;
        }

        g2.drawImage(image, telaX, telaY, pJ.TamTile, pJ.TamTile, null);
        //g2.setColor(Color.white);
       // g2.fillRect(x, y, pJ.TamTile, pJ.TamTile);
    }
}
