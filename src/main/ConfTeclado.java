package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConfTeclado implements KeyListener{

    public boolean pressCima, pressBaixo, pressEsquerda, pressDireita;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            pressCima = true;
        }
        if(code == KeyEvent.VK_A){
            pressEsquerda = true;
        }
        if(code == KeyEvent.VK_S){
            pressBaixo = true;
        }
        if(code == KeyEvent.VK_D){
            pressDireita = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            pressCima = false;
        }
        if(code == KeyEvent.VK_A){
            pressEsquerda = false;
        }
        if(code == KeyEvent.VK_S){
            pressBaixo = false;
        }
        if(code == KeyEvent.VK_D){
            pressDireita = false;
        }
    }

}
