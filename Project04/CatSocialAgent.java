/*
 * File: CatSocialAgent.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CatSocialAgent extends SocialAgent {

    // holds the category
    private int category;
    
    // calls the parent constructor and sets the category
    public CatSocialAgent(double x0, double y0, int radius, boolean isCriminal, int cat) {
        super(x0, y0, radius, isCriminal);
        this.category = cat;
    }

    // returns the category value
    public int getCategory() {
        return this.category;
    }

    // returns a single character string indicating the category
    public String toString() {
        String category = Integer.toString(this.category);
        return category;
    }

    // draws a circle of radius 5
    public void draw(Graphics2D g) {
        if (this.isCriminal == false) {
            if (this.category == 1) {
                if (this.moved == true) {
                    g.setColor(Color.GREEN);
                    g.fill(new Ellipse2D.Double(this.getX(), this.getY(), 5, 5));
                }
                else {
                    g.setColor(Color.GREEN.darker());
                    g.fill(new Ellipse2D.Double(this.getX(), this.getY(), 5, 5));
                }
            }
            else {
                if (this.moved == true) {
                    g.setColor(Color.CYAN);
                    g.fill(new Ellipse2D.Double(this.getX(), this.getY(), 5, 5));
                }
                else {
                    g.setColor(Color.BLUE);
                    g.fill(new Ellipse2D.Double(this.getX(), this.getY(), 5, 5));
                }
            }
        }
        else {
            if (this.category == 1) {
                if (this.moved == true) {
                    g.setColor(Color.ORANGE);
                    g.fill(new Rectangle2D.Double(this.getX(), this.getY(), 8, 8));
                }
                else {
                    g.setColor(Color.ORANGE.darker());
                    g.fill(new Rectangle2D.Double(this.getX(), this.getY(), 8, 8));
                }
            }
            else {
                if (this.moved == true) {
                    g.setColor(Color.MAGENTA);
                    g.fill(new Rectangle2D.Double(this.getX(), this.getY(), 8, 8));
                }
                else {
                    g.setColor(Color.MAGENTA.darker());
                    g.fill(new Rectangle2D.Double(this.getX(), this.getY(), 8, 8));
                }
            }
        }
    }

    // implements the rule
    public void updateState(Landscape scape) {
        Random ran = new Random();
        ArrayList<Agent> neighbors1 = scape.getNeighbors(this.getX(), this.getY(), this.getRadius());
        ArrayList<CatSocialAgent> neighbors = (ArrayList<CatSocialAgent>)(ArrayList<?>)(neighbors1);
        int countCat1 = 0;
        int countCat2 = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getCategory() == 1) {
                countCat1++;
            }
            else {
                countCat2++;
            }
        }
        if (this.isCriminal == false) {
            if (this.category == 1) {
                if (neighbors.size() >= 2 && countCat1 > countCat2) {
                    if (ran.nextDouble() <= 0.01) {
                        this.setX(this.getX() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                        this.setY(this.getY() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                        this.moved = true;
                    }
                    else {
                        this.moved = false;
                    }
                }
                else {
                    this.setX(this.getX() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                    this.setY(this.getY() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                    this.moved = true;
                }
            }
            else {
                if (neighbors.size() >= 2 && countCat2 > countCat1) {
                    if (ran.nextDouble() <= 0.01) {
                        this.setX(this.getX() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                        this.setY(this.getY() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                        this.moved = true;
                    }
                    else {
                        this.moved = false;
                    }
                }
                else {
                    this.setX(this.getX() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                    this.setY(this.getY() - (ran.nextDouble()*(20 + Double.MIN_VALUE) - 10));
                    this.moved = true;
                }
            }
        }
        else {
            if (this.category == 1) {
                if (neighbors.size() >= 5) {
                    for (int i = 0; i < neighbors.size(); i++) {
                        if (neighbors.get(i).getX() <= this.getX() && neighbors.get(i).getY() <= this.getY()) {
                            neighbors.get(i).setX(neighbors.get(i).getX() - ran.nextDouble()*10);
                            neighbors.get(i).setY(neighbors.get(i).getY() - ran.nextDouble()*10);
                        }
                        if (neighbors.get(i).getX() <= this.getX() && neighbors.get(i).getY() >= this.getY()) {
                            neighbors.get(i).setX(neighbors.get(i).getX() - ran.nextDouble()*10);
                            neighbors.get(i).setY(neighbors.get(i).getY() + ran.nextDouble()*10);
                        }
                        if (neighbors.get(i).getX() >= this.getX() && neighbors.get(i).getY() <= this.getY()) {
                            neighbors.get(i).setX(neighbors.get(i).getX() + ran.nextDouble()*10);
                            neighbors.get(i).setY(neighbors.get(i).getY() - ran.nextDouble()*10);
                        }
                        else {
                            neighbors.get(i).setX(neighbors.get(i).getX() + ran.nextDouble()*10);
                            neighbors.get(i).setY(neighbors.get(i).getY() + ran.nextDouble()*10);
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
            else {
                if (neighbors.size() >= 5) {
                    int countCriminal = 0;
                    for (int i = 0; i < neighbors.size(); i++) {
                        if (neighbors.get(i).getCriminal() == true) {
                            countCriminal++;
                        }
                    }
                    if (countCriminal >= 1) {
                        if (ran.nextDouble() <= 0.1*countCriminal) {
                            neighbors.get(ran.nextInt(neighbors.size())).turnCriminal();
                        }
                    }
                    else {
                        if (ran.nextDouble() <= 0.05) {
                            neighbors.get(ran.nextInt(neighbors.size())).turnCriminal();
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
    }

    // unit test
    public static void main(String[] args) {
        CatSocialAgent catSocialAgent = new CatSocialAgent(0, 0, 5, false, 1);
        System.out.println(catSocialAgent.getCategory());
        System.out.println(catSocialAgent.toString());
    }

}
