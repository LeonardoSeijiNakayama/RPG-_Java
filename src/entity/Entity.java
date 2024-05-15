package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int velocidade;

    public BufferedImage cima1, cima2, baixo1, baixo2, esq1, esq2, dir1, dir2;
    public String direcao;

    public int contaSprite = 0;
    public int numSprite = 1;

    public Rectangle areaSolida;
    public boolean colisaoOn = false;
    
}
