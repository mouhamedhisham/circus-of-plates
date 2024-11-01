package View;

import Controller.Strategy;
import View.Circus;
import circus.Main;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elsam
 */
public class StartGame {

    private static Strategy strategy;

    public StartGame(Strategy strategy) {
        this.strategy = strategy;

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        final GameController gameController = GameEngine.start("Project made by clowns with love <3", new Circus(800, 450, StartGame.strategy), menuBar);

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Main main = null;
                try {
                    main = new Main();
                } catch (IOException ex) {
                    Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                main.setVisible(true);
               
            }
        });
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.pause();
            }
        });
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.resume();
            }
        });
    }
}
