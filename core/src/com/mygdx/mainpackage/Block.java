package com.mygdx.mainpackage;

/**
 * Created by pppereira3 on 29-05-2018.
 */

public class Block implements Comparable<Block>{
    private Coords coords;
    private char colour;
    private char power;
    public boolean canDrop;

    public Block(){
        this.coords = new Coords(0 ,0);
        this.colour = ' ';
        this.power = ' ';
        canDrop = true;
    }

    public Block(Block b){
        this.coords = new Coords();
        this.coords.setCoords(b.getCoords());
        this.colour = b.colour;
        this.power = b.power;
        canDrop = b.canDrop;
    }

    public Block(Coords c, char colour, char pow) {
        this.coords = c;
        this.colour = colour;
        this.power = pow;
        canDrop = true;
    }

    public Coords getCoords(){
        return this.coords;
    }

    public void setCoords(Coords c){
        this.coords.setCoords(c);
    }

    public void setCoords(float x, float y){
        this.getCoords().setCoords(x, y);
    }

    public char getColour(){
        return  this.colour;
    }

    public char getPower(){
        return this.power;
    }

    public int compareTo(Block b){
        return this.coords.compareTo(b.getCoords());
    }

    public String toString(){
        String s = "";
        //s += this.colour + "\n";
        s += "(" + this.getCoords().X() + ", " + this.getCoords().Y() + ")\n";
        //s += this.power;
        return s;
    }

}
