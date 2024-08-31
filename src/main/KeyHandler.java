package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, lShiftPressed, enterPressed;
    public boolean checkDrawTime = false;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Title State
        if (gp.gameState == gp.titleState) {
            // Up
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSFX(7);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }

            // Down
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSFX(7);
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

            // Enter
            if (code == KeyEvent.VK_ENTER) {
                gp.playSFX(8);
                switch (gp.ui.commandNum) {
                    case 0:
                        gp.gameState = gp.playState;
                        gp.stopMusic();
                        gp.playMusic(0);
                        break;
                    case 1:
                        gp.playSFX(1);
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            }
        }

        // Play State
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT) {
                lShiftPressed = true;
            }

            if (code == KeyEvent.VK_ESCAPE) {
                gp.playSFX(9);
                gp.gameState = gp.pauseState;
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }

        // Pause State
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.playSFX(10);
                gp.gameState = gp.playState;
            }
        }

        // Dialogue State
        else if (gp.gameState == gp.dialogue) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (!checkDrawTime) {
                checkDrawTime = true;
            } else if (checkDrawTime) {
                checkDrawTime = false;
            }
        }

        if (code == KeyEvent.VK_M) {
            if (gp.musicStopped) {
                gp.playMusic(0);
                gp.musicStopped = false;
            } else if (!gp.musicStopped) {
                gp.stopMusic();
                gp.musicStopped = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            lShiftPressed = false;
        }
    }
}
