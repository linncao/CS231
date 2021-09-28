/*
 * File: HuntTheWumpus.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: May 12th, 2021
 * Project 9: Game: Hunt the Wumpus
 * CS231
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

public class HuntTheWumpus {
    
    // holds the Landscape object
    private Landscape scape;

    // holds the LandscapeDisplay object
    private LandscapeDisplay display;

    // holds the Graph object
    private Graph graph;

    // holds the Hunter object
    private Hunter hunter;

    // holds the Wumpus object
    private Wumpus wumpus;

    // holds the label to describe the events of the game
    private JLabel message;

    // holds the states of the game
    private enum PlayState {
        PLAY,
        WIN,
        LOST,
        RESET;
    }

    // holds the game's current state
    private PlayState state;

    // constructor method - builds the graph, 
    // inserts the vertices, Hunter, and Wumpus into the Landscape
    // adds any other user interface elements
    public HuntTheWumpus() {

        // Create the elements of the Landscape and the game.
        this.graph = new Graph();
        this.scape = new Landscape(10, 7);
        Random ran = new Random();

        // adds two rooms to the cave to start
        Vertex v1 = new Vertex(0, 0);
        Vertex v2 = new Vertex(1, 0);
        this.graph.addEdge(v1, v2, Vertex.Direction.EAST);
        // adds 25 more rooms to the cave
        while (this.graph.vertexCount() < 25) {
            Vertex v0 = this.graph.getVertices().get(ran.nextInt(this.graph.vertexCount()));
            int x0 = v0.getX();
            int y0 = v0.getY();
            // randomly add connected vertices with possibility of 25%
            if (ran.nextDouble() < 0.25 && (y0 - 1) >= 0) {
                Vertex v = new Vertex(x0, y0 - 1);
                for (Vertex ver: this.graph.getVertices()) {
                    if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                        v = ver;
                    }
                }
                this.graph.addEdge(v0, v1, Vertex.Direction.NORTH);
            }
            if (ran.nextDouble() < 0.25 && (y0 + 1) < this.scape.getHeight()) {
                Vertex v = new Vertex(x0, y0 + 1);
                for (Vertex ver: this.graph.getVertices()) {
                    if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                        v = ver;
                    }
                }
                this.graph.addEdge(v0, v1, Vertex.Direction.SOUTH);
            }
            if (ran.nextDouble() < 0.25 && (x0 - 1) >= 0) {
                Vertex v = new Vertex(x0 - 1, y0);
                for (Vertex ver: this.graph.getVertices()) {
                    if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                        v = ver;
                    }
                }
                this.graph.addEdge(v0, v1, Vertex.Direction.WEST);
            }
            if (ran.nextDouble() < 0.25 && (x0 + 1) >= this.scape.getWidth()) {
                Vertex v = new Vertex(x0 + 1, y0);
                for (Vertex ver: this.graph.getVertices()) {
                    if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                        v = ver;
                    }
                }
                this.graph.addEdge(v0, v1, Vertex.Direction.EAST);
            }
        }
        for (Vertex v0: this.graph.getVertices()) {
            this.scape.addBackgroundAgent(v1);
        }

        // adds the hunter, wumpus to the landscape
        this.hunter = new Hunter(this.graph.getVertices().get(0));
        this.scape.addBackgroundAgent(this.hunter);

        int wumpus = ran.nextInt(this.graph.getVertices().size());
        this.wumpus = new Wumpus(this.graph.getVertices().get(wumpus));
        this.graph.shortestPath(this.hunter.getLocation());
        while (this.wumpus.getHome().getCost() <= 2) {
            wumpus = ran.nextInt(this.graph.getVertices().size());
            this.wumpus = new Wumpus(this.graph.getVertices().get(wumpus));
        }
        this.scape.addBackgroundAgent(this.wumpus);

        this.display = new LandscapeDisplay(scape, 100);
        
        this.state = PlayState.PLAY;

        // sets up the UI for the game
        this.message = new JLabel("Hunt The Wumpus.");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.message);
        this.display.add(panel, BorderLayout.SOUTH);
        this.display.pack();
        Control control = new Control();
        this.display.addKeyListener(control);
        this.display.setFocusable(true);
        this.display.requestFocus();
    }

    // runs one iteration of the game
    public void iterate() throws InterruptedException {
        if (this.state == PlayState.PLAY) {
            // updates the landscape and landscape display
            this.scape.advance();
            this.scape.setDirection(null);
            this.graph.shortestPath(this.wumpus.getHome());
            this.display.update();

            // deals with the wumpus
            if (this.hunter.getLocation() == this.wumpus.getHome()) {
                this.message.setText("You have been eaten by the Wumpus.");
                this.wumpus.setVisible(true);
                this.display.update();
                this.state = PlayState.LOST;
            }
            else if (this.hunter.getLocation().getCost() <= 2) {
                this.message.setText("The wumpus is nearby.");
            }
            else {
                this.message.setText("Hunt the Wumpus.");
            }
        }
        Thread.sleep(1000);
    }

    // gives option of replaying the game
    public boolean replay() throws InterruptedException {
        Thread.sleep(1000);

        // adds new frame asking user whether they want to replay
		Control control = new Control();
		JFrame frame = new JFrame("Game over");
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Replay?");
		JButton yes = new JButton("Yes");
		yes.addActionListener(control);
		JButton no = new JButton("No");
		no.addActionListener(control);
		panel.add(label);
		panel.add(yes);
		panel.add(no);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
		while (this.state == PlayState.LOST || this.state == PlayState.WIN) {
			Thread.sleep(1000);
		}
		
		// resets and restarts 
		if (this.state == PlayState.RESET) {
			LandscapeDisplay oldDisplay = this.display;
			// Create the elements of the Landscape and the game.
            this.graph = new Graph();
            this.scape = new Landscape(10, 7);
            Random ran = new Random();
    
            // adds two rooms to the cave to start
            Vertex v1 = new Vertex(0, 0);
            Vertex v2 = new Vertex(1, 0);
            this.graph.addEdge(v1, v2, Vertex.Direction.EAST);
            // adds 25 more rooms to the cave
            while (this.graph.vertexCount() < 25) {
                Vertex v0 = this.graph.getVertices().get(ran.nextInt(this.graph.vertexCount()));
                int x0 = v0.getX();
                int y0 = v0.getY();
                // randomly add connected vertices with possibility of 25%
                if (ran.nextDouble() < 0.25 && (y0 - 1) >= 0) {
                    Vertex v = new Vertex(x0, y0 - 1);
                    for (Vertex ver: this.graph.getVertices()) {
                        if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                            v = ver;
                        }
                    }
                    this.graph.addEdge(v0, v1, Vertex.Direction.NORTH);
                }
                if (ran.nextDouble() < 0.25 && (y0 + 1) < this.scape.getHeight()) {
                    Vertex v = new Vertex(x0, y0 + 1);
                    for (Vertex ver: this.graph.getVertices()) {
                        if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                            v = ver;
                        }
                    }
                    this.graph.addEdge(v0, v1, Vertex.Direction.SOUTH);
                }
                if (ran.nextDouble() < 0.25 && (x0 - 1) >= 0) {
                    Vertex v = new Vertex(x0 - 1, y0);
                    for (Vertex ver: this.graph.getVertices()) {
                        if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                            v = ver;
                        }
                    }
                    this.graph.addEdge(v0, v1, Vertex.Direction.WEST);
                }
                if (ran.nextDouble() < 0.25 && (x0 + 1) >= this.scape.getWidth()) {
                    Vertex v = new Vertex(x0 + 1, y0);
                    for (Vertex ver: this.graph.getVertices()) {
                        if (v.getX() == ver.getX() && v.getY() == ver.getY()) {
                            v = ver;
                        }
                    }
                    this.graph.addEdge(v0, v1, Vertex.Direction.EAST);
                }
            }
            for (Vertex v0: this.graph.getVertices()) {
                this.scape.addBackgroundAgent(v1);
            }
    
            // adds the hunter, wumpus to the landscape
            this.hunter = new Hunter(this.graph.getVertices().get(0));
            this.scape.addBackgroundAgent(this.hunter);
    
            int wumpus = ran.nextInt(this.graph.getVertices().size());
            this.wumpus = new Wumpus(this.graph.getVertices().get(wumpus));
            this.graph.shortestPath(this.hunter.getLocation());
            while (this.wumpus.getHome().getCost() <= 2) {
                wumpus = ran.nextInt(this.graph.getVertices().size());
                this.wumpus = new Wumpus(this.graph.getVertices().get(wumpus));
            }
            this.scape.addBackgroundAgent(this.wumpus);
    
            this.display = new LandscapeDisplay(scape, 100);
            
			this.state = PlayState.PLAY;
    
            // sets up the UI for the game
            this.message = new JLabel("Hunt The Wumpus.");
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pane.add(this.message);
            this.display.add(pane, BorderLayout.SOUTH);
            this.display.pack();
            this.display.addKeyListener(control);
            this.display.setFocusable(true);
            this.display.requestFocus();
			oldDisplay.dispose();
			frame.dispose();
			return true;
		}
		else { 
			frame.dispose();
			return false;
		}	
    }

    // keyboard control
    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
            // moves the hunter
			if (("" + e.getKeyChar()).equalsIgnoreCase("w"))
			{
				scape.setDirection(Vertex.Direction.NORTH);
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("a"))
			{
				scape.setDirection(Vertex.Direction.WEST);
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("s"))
			{
				scape.setDirection(Vertex.Direction.SOUTH);
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("d"))
			{
				scape.setDirection(Vertex.Direction.EAST);
			}
			else if (Character.isSpaceChar(e.getKeyChar()))
			{
				hunter.setReady(! hunter.isReady());
            }
			
			// the hunter hunts
			if (hunter.isReady()) {
				if (hunter.getLocation().getNeighbor(scape.getDirection()) == wumpus.getHome()) {
					message.setText("You have skilled the wumpus.");
					wumpus.getHome().setVisible(true);
					wumpus.setAlive(false);
					wumpus.setVisible(true);
					scape.setDirection(null);
					display.update();
					state = PlayState.WIN;
				}
				else if (scape.getDirection() != null) {
					message.setText("You have missed the wumpus.");
					scape.setDirection(null);
					wumpus.setHome(hunter.getLocation());
					wumpus.setVisible(true);
					display.update();
					state = PlayState.LOST;
				}
			}
        }

        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equalsIgnoreCase("Yes")) {
				state = PlayState.RESET;
			}
			else if (event.getActionCommand().equalsIgnoreCase("No")) {
				state = PlayState.PLAY;
			}
        }
    } // end class Control

    // runs the game
    public static void main(String[] args) throws InterruptedException {
        HuntTheWumpus game = new HuntTheWumpus();
        int hunterWin = 0;
        int wumpusWin = 0;

        do {
            while (game.state == PlayState.PLAY) {
                game.iterate();
            }
            if (game.state == PlayState.WIN) {
                hunterWin++;
            }
            else {
                wumpusWin++;
            }
        }
        while (game.replay());

        System.out.println("Hunter wins: " + hunterWin);
        System.out.println("Wumpus wins: " + wumpusWin);

        game.display.dispose();
    }
}
