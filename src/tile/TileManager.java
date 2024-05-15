package tile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class TileManager {
    PainelJogo pJ;
    public Tile[] tile;
    public int mapaTileNum[][];

    public TileManager(PainelJogo pJ){

        this.pJ = pJ;

        tile = new Tile[10];
        mapaTileNum = new int[pJ.mundoMaxColuna][pJ.mundoMaxLinha];

        getImagemTile();
        carregaMapa("res/mapas/mapaMundo.txt");
    }

    public void getImagemTile(){
        
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("res/terreno/gramaTile.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("res/terreno/aguaTile.png"));
            tile[1].colisao = true;
           
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("res/terreno/tijoloTile.png"));
            tile[2].colisao = true;
        
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream("res/terreno/areiaTile.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream("res/terreno/terraTile.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new FileInputStream("res/coisas/arvoreTile.png"));
            tile[5].colisao = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new FileInputStream("res/coisas/coqueiroTile.png"));
            tile[6].colisao = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void carregaMapa(String caminhoMapa){

        try {
            
            //InputStream is = getClass().getResourceAsStream(caminhoMapa);
           // BufferedReader br = new BufferedReader(new InputStreamReader(is));

            File arquivoMapa = new File(caminhoMapa);
            FileReader fileReader = new FileReader(arquivoMapa);
            BufferedReader br = new BufferedReader(fileReader);

            int col = 0;
            int linha = 0;

            while(col < pJ.mundoMaxColuna && linha < pJ.mundoMaxLinha){

                String linhaArq = br.readLine();

                while(col < pJ.mundoMaxColuna){
                    String nums[] = linhaArq.split(" ");

                    int numero = Integer.parseInt(nums[col]);

                    mapaTileNum[col][linha] = numero;
                    col++;
                }
                if(col == pJ.mundoMaxColuna){
                    col = 0;
                    linha++;
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("aaaaa");
            e.printStackTrace();
            
        }
    }

    public void draw(Graphics2D g2){

        int mundoCol = 0;
        int mundoLinha = 0;
        

        while(mundoCol < pJ.mundoMaxColuna && mundoLinha < pJ.mundoMaxLinha){

            int numTile = mapaTileNum[mundoCol][mundoLinha];

            int mundoX = mundoCol * pJ.TamTile;
            int mundoY = mundoLinha * pJ.TamTile;
            int telaX = mundoX - pJ.player.worldX + pJ.player.telaX;
            int telaY = mundoY - pJ.player.worldY + pJ.player.telaY;

            if(mundoX + pJ.TamTile > pJ.player.worldX - pJ.player.telaX && 
               mundoX - pJ.TamTile < pJ.player.worldX + pJ.player.telaX &&
               mundoY + pJ.TamTile > pJ.player.worldY - pJ.player.telaY && 
               mundoY - pJ.TamTile< pJ.player.worldY + pJ.player.telaY ){

                 g2.drawImage(tile[numTile].image, telaX, telaY, pJ.TamTile, pJ.TamTile, null);

                }
            mundoCol++;

            if(mundoCol == pJ.mundoMaxColuna){
                mundoCol = 0;
                mundoLinha++;

            }

        }

    }
}
