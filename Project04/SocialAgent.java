/*
 * File: SocialAgent.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.ArrayList;

public class SocialAgent extends Agent {
    
    // holds a boolean indicating whether the agent moved
    protected boolean moved;

    // holds the radius R
    private int radius;

    // constructor - calls the super class constructor and sets the radius field
    public SocialAgent(double x0, double y0, int radius, boolean isCriminal) {
        super(x0, y0);
        this.radius = radius;
        this.isCriminal = isCriminal;
    }

    // sets the cell's radius of sensitivity to the value of radius
    public void setRadius(int radius) {
        this.radius = radius;
    }

    // returns the cell's radius of sensitivity
    public int getRadius() {
        return this.radius;
    }

    // updates the state of a social agent
    public void updateState(Landscape scape) {
        Random ran = new Random();
        ArrayList<Agent> arrNeighbors = scape.getNeighbors(this.getX(), this.getY(), this.getRadius());
        if (this.getCriminal() == false) {
            if (arrNeighbors.size() >= 3) {
                if (ran.nextDouble() <= 0.01) {
                    this.setX(this.getX() - (ran.nextDouble()*20 - 10));
                    this.setY(this.getY() - (ran.nextDouble()*20 - 10));
                    this.moved = true;
                }
                else {
                    this.moved = false;
                }
            }
            else {
                this.setX(this.getX() - (ran.nextDouble()*20 - 10));
                this.setY(this.getY() - (ran.nextDouble()*20 - 10));
                this.moved = true;
            }
        }
        else {
            if (arrNeighbors.size() >= 5) {
                int countCriminal = 0;
                for (int i = 0; i < arrNeighbors.size(); i++) {
                    if (arrNeighbors.get(i).getCriminal() == true) {
                        countCriminal++;
                    }
                }
                if (countCriminal >= 1) {
                    if (ran.nextDouble() <= 0.1*countCriminal) {
                        arrNeighbors.get(ran.nextInt(arrNeighbors.size())).turnCriminal();
                    }
                }
                else {
                    if (ran.nextDouble() <= 0.05) {
                        arrNeighbors.get(ran.nextInt(arrNeighbors.size())).turnCriminal();
                    }
                }
                for (int i = 0; i < arrNeighbors.size(); i++) {
                    if (arrNeighbors.get(i).getX() <= this.getX() && arrNeighbors.get(i).getY() <= this.getY()) {
                        arrNeighbors.get(i).setX(arrNeighbors.get(i).getX() - ran.nextDouble()*10);
                        arrNeighbors.get(i).setY(arrNeighbors.get(i).getY() - ran.nextDouble()*10);
                    }
                    if (arrNeighbors.get(i).getX() <= this.getX() && arrNeighbors.get(i).getY() >= this.getY()) {
                        arrNeighbors.get(i).setX(arrNeighbors.get(i).getX() - ran.nextDouble()*10);
                        arrNeighbors.get(i).setY(arrNeighbors.get(i).getY() + ran.nextDouble()*10);
                    }
                    if (arrNeighbors.get(i).getX() >= this.getX() && arrNeighbors.get(i).getY() <= this.getY()) {
                        arrNeighbors.get(i).setX(arrNeighbors.get(i).getX() + ran.nextDouble()*10);
                        arrNeighbors.get(i).setY(arrNeighbors.get(i).getY() - ran.nextDouble()*10);
                    }
                    else {
                        arrNeighbors.get(i).setX(arrNeighbors.get(i).getX() + ran.nextDouble()*10);
                        arrNeighbors.get(i).setY(arrNeighbors.get(i).getY() + ran.nextDouble()*10);
                    }
                }
                this.moved = false;
            }
            else {
                if (ran.nextDouble() <= 0.5) {
                    this.setX(this.getX() - (ran.nextDouble()*40 - 20));
                    this.setY(this.getY() - (ran.nextDouble()*40 - 20));
                    this.moved = true;
                }
                else {
                    this.moved = false;
                }
            }
        }
    }

    // draws a circle of radius 5 
    public void draw(Graphics2D g) {
        if (this.getCriminal() == false) {
            if (this.moved == true) {
                g.setColor(Color.PINK);
                g.fill(new Ellipse2D.Double(this.getX(), this.getY(), 5, 5));
            }
            else {
                g.setColor(Color.PINK.darker());
                g.fill(new Ellipse2D.Double(this.getX(), this.getY(), 5, 5));
            }
        }
        else {
            if (this.moved == true) {
                g.setColor(Color.RED);
                g.fill(new Rectangle2D.Double(this.getX(), this.getY(), 8, 8));
            }
            else {
                g.setColor(Color.RED.darker());
                g.fill(new Rectangle2D.Double(this.getX(), this.getY(), 8, 8));
            }
        }
    }

    // unit test
    public static void main(String[] args) {
        SocialAgent socialAgent = new SocialAgent(0, 0, 3, false);
        System.out.println(socialAgent.getRadius());
        System.out.println(socialAgent.getCriminal());
        socialAgent.setRadius(5);
        System.out.println(socialAgent.getRadius());
    }

}
