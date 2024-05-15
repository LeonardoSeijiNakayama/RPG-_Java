package main;

import entity.Entity;

public class ChecadorColisao {

    PainelJogo pJ;

    public ChecadorColisao(PainelJogo pJ){
        this.pJ = pJ;
    }

    public void ChecaTile(Entity entity){
        int entidadeTopoMundoX = entity.worldY + entity.areaSolida.y;
        int entidadeBaixoMundoX = entity.worldY + entity.areaSolida.y + entity.areaSolida.height;
        int entidadeEsqMundoX = entity.worldX + entity.areaSolida.x;
        int entidadeDirMundoX = entity.worldX + entity.areaSolida.x + entity.areaSolida.width;

        int entidadeLinhaTopo = entidadeTopoMundoX/pJ.TamTile;
        int entidadeLinhaBaixo = entidadeBaixoMundoX/pJ.TamTile;
        int entidadeColEsq = entidadeEsqMundoX/pJ.TamTile;
        int entidadeColDir = entidadeDirMundoX/pJ.TamTile;

        int tileNum1, tileNum2;
        
        switch(entity.direcao){
            case "cima": 
            entidadeLinhaTopo = (entidadeTopoMundoX - entity.velocidade) / pJ.TamTile;
            tileNum1 = pJ.tileM.mapaTileNum[entidadeColEsq][entidadeLinhaTopo];
            tileNum2 = pJ.tileM.mapaTileNum[entidadeColDir][entidadeLinhaTopo];
            if(pJ.tileM.tile[tileNum1].colisao == true || pJ.tileM.tile[tileNum2].colisao == true){
                entity.colisaoOn = true;
            }
            break;

            case "baixo":
            entidadeLinhaBaixo = (entidadeBaixoMundoX + entity.velocidade) / pJ.TamTile;
            tileNum1 = pJ.tileM.mapaTileNum[entidadeColEsq][entidadeLinhaBaixo];
            tileNum2 = pJ.tileM.mapaTileNum[entidadeColDir][entidadeLinhaBaixo];
            if(pJ.tileM.tile[tileNum1].colisao == true || pJ.tileM.tile[tileNum2].colisao == true){
                entity.colisaoOn = true;
            }

            break;
            case "esq":
            entidadeColEsq = (entidadeEsqMundoX - entity.velocidade) / pJ.TamTile;
            tileNum1 = pJ.tileM.mapaTileNum[entidadeColEsq][entidadeLinhaTopo];
            tileNum2 = pJ.tileM.mapaTileNum[entidadeColEsq][entidadeLinhaBaixo];
            if(pJ.tileM.tile[tileNum1].colisao == true || pJ.tileM.tile[tileNum2].colisao == true){
                entity.colisaoOn = true;
            }

            break;
            case "dir":
            entidadeColDir = (entidadeDirMundoX + entity.velocidade) / pJ.TamTile;
            tileNum1 = pJ.tileM.mapaTileNum[entidadeColDir][entidadeLinhaTopo];
            tileNum2 = pJ.tileM.mapaTileNum[entidadeColDir][entidadeLinhaBaixo];
            if(pJ.tileM.tile[tileNum1].colisao == true || pJ.tileM.tile[tileNum2].colisao == true ){
                entity.colisaoOn = true;
            }

            break;
        }

    }

}
