package com.mygdx.mainpackage;

public class TetroJ extends Tetromino {

    public TetroJ(char power){
        super('J');
        Block a = new Block(new Coords (0, 0), 'J', 'N');
        Block b = new Block(new Coords (0, 1), 'J', 'N');
        Block c = new Block(new Coords (1, 1), 'J', power);
        Block d = new Block(new Coords (2, 1), 'J', 'N');
        this.blocks.put("A", a);
        this.blocks.put("B", b);
        this.blocks.put("C", c);
        this.blocks.put("D", d);
    }

    public void rotate(){
        Coords ac = this.blocks.get("A").getCoords();
        Coords bc = this.blocks.get("B").getCoords();
        Coords dc = this.blocks.get("D").getCoords();

        switch (this.dir){
            case 0:
                this.blocks.get("A").getCoords().setCoords(ac.X() + 2, ac.Y() + 0);
                this.blocks.get("B").getCoords().setCoords(bc.X() + 1, bc.Y() - 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() - 1, dc.Y() + 1);
                this.dir = 1;
                break;

            case 1:
                this.blocks.get("A").getCoords().setCoords(ac.X() + 0, ac.Y() + 2);
                this.blocks.get("B").getCoords().setCoords(bc.X() + 1, bc.Y() + 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() - 1, dc.Y() - 1);
                this.dir = 2;
                break;

            case 2:
                this.blocks.get("A").getCoords().setCoords(ac.X() - 2, ac.Y() + 0);
                this.blocks.get("B").getCoords().setCoords(bc.X() - 1, bc.Y() + 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() + 1, dc.Y() - 1);
                this.dir = 3;
                break;

            case 3:
                this.blocks.get("A").getCoords().setCoords(ac.X() + 0, ac.Y() - 2);
                this.blocks.get("B").getCoords().setCoords(bc.X() - 1, bc.Y() - 1);
                this.blocks.get("D").getCoords().setCoords(dc.X() + 1, dc.Y() + 1);
                this.dir = 0;
                break;

            default:
                break;
        }

    }
}
