package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.map.Coordonnee;
import engine.map.Grille;
import engine.map.generation.GrilleBuilder;
import engine.personnage.Gardien;
import engine.personnage.Intrus;
import engine.personnage.PersonnageManager;

/**
 * MainGUI est la classe principale de l'interface graphique du jeu.
 * Elle crée et gère les principaux composants de l'interface utilisateur,
 * y compris la grille de jeu, le gardien et les contrôles de jeu.
 */
public class MainGUI extends JFrame {
    private static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

    private Grille grille;
    private Gardien gardien;
    private List<Intrus> intrusList;
    private GameDisplay dashboard;
    private PersonnageManager manager;

    private JButton startButton;
    private JButton stopButton;
    private JLabel timeLabel;
    private boolean isRunning = false; // Pour contrôler l'état du jeu
    private int elapsedTime = 0; // Temps écoulé en secondes
    private Timer gameTimer;
    private Timer intrusTimer;

    public MainGUI(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Créer la grille et les personnages
        GrilleBuilder mapBuilder = new GrilleBuilder();
        this.grille = mapBuilder.getGrille();

        Coordonnee gardienPosition = grille.getCase(0, 0).getPosition();
        this.gardien = new Gardien(gardienPosition);

        this.intrusList = new ArrayList<>();
        for (int i = 0; i < GameConfiguration.NB_VOLEURS; i++) {
            Coordonnee intrusPosition = new Coordonnee((int) (Math.random() * GameConfiguration.NB_LIGNE), (int) (Math.random() * GameConfiguration.NB_COLONNE));
            Intrus intrus = new Intrus(intrusPosition);
            this.intrusList.add(intrus);
        }

        this.manager = new PersonnageManager(grille, this.gardien, this.intrusList);

        // Créer la stratégie de dessin et le tableau de bord
        PaintStrategy paintStrategy = new PaintStrategy();
        dashboard = new GameDisplay(this.grille, gardien, intrusList, paintStrategy);
        dashboard.setPreferredSize(preferredSize);
        contentPane.add(dashboard, BorderLayout.CENTER);

        // Créer un panel pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajouter des marges
        startButton = new JButton("Commencer");
        stopButton = new JButton("Arrêter");

        // Ajouter des écouteurs aux boutons
        startButton.addActionListener(e -> startGame());
        stopButton.addActionListener(e -> stopGame());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Créer un panneau latéral pour afficher les informations du jeu
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajouter des marges
        sidePanel.setBackground(Color.LIGHT_GRAY);

        JLabel scoreLabel = new JLabel("Score: 0");
        timeLabel = new JLabel("Temps: 0s");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        sidePanel.add(scoreLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace vertical
        sidePanel.add(timeLabel);

        contentPane.add(sidePanel, BorderLayout.EAST);

        // Rendre le panel focusable et ajouter l'écouteur clavier
        dashboard.setFocusable(true);
        dashboard.addKeyListener(new KeyControls());

        // Configurer la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setPreferredSize(preferredSize);
        setResizable(false);
    }

    private void startGame() {
        if (!isRunning) {
            isRunning = true;
            elapsedTime = 0;
            dashboard.requestFocusInWindow(); // Assurer que le dashboard a le focus
            startTimers();
            System.out.println("Jeu démarré.");
        }
    }

    private void stopGame() {
        if (isRunning) {
            isRunning = false;
            if (gameTimer != null) {
                gameTimer.stop();
            }
            if (intrusTimer != null) {
                intrusTimer.stop();
            }
            System.out.println("Jeu arrêté.");
        }
    }

    private void startTimers() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    elapsedTime++;
                    timeLabel.setText("Temps: " + elapsedTime + "s");
                }
            }
        });
        gameTimer.start();

        intrusTimer = new Timer(GameConfiguration.VITESSE_INTRUS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    for (Intrus intrus : intrusList) {
                        intrus.deplacerAleatoirement(grille);
                    }
                    dashboard.repaint();
                }
            }
        });
        intrusTimer.start();
    }

    public class KeyControls implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_Q:
                case KeyEvent.VK_A:
                    manager.moveLeftGardien();
                    break;

                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    manager.moveRightGardien();
                    break;

                case KeyEvent.VK_UP:
                case KeyEvent.VK_Z:
                case KeyEvent.VK_W:
                    manager.moveUpGardien();
                    break;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    manager.moveDownGardien();
                    break;
            }
            System.out.println("Déplacement du gardien. Rafraîchissement de l'affichage...");
            dashboard.repaint(); // Rafraîchir l'affichage après un déplacement
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Ne rien faire
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Ne rien faire
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI("Jeu de Gardien");
            mainGUI.setVisible(true);
        });
    }
}