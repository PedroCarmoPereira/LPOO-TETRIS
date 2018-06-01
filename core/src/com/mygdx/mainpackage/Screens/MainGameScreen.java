package com.mygdx.mainpackage.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.mainpackage.*;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.event.TextEvent;
import java.util.HashMap;
import java.util.LinkedList;


public class MainGameScreen implements Screen{

    protected Stage stage;
    protected Tetris t;
    protected Texture background= new Texture("background2.png");
    protected Texture holdNextBox = new Texture("HoldNextBox.png");
    protected Texture holdText = new Texture("hold.png");
    protected Texture nextText = new Texture("next.png");
    protected Texture scoreText = new Texture("score.png");
    protected Sprite muteSprite = new Sprite (new Texture("sound.png"));
    protected HashMap<String, Sprite> spriteMap = new HashMap<String, Sprite>();

    protected static final float blockSize = (float) (Gdx.graphics.getHeight() * (0.056));
    protected static final float displacementX = 10*blockSize;
    protected static final float displacementY = (float) (Gdx.graphics.getHeight() - 3*blockSize);

    protected float time;

    protected Button muteButton = new Button(new SpriteDrawable(muteSprite));

    public MainGameScreen(final Tetris t){

        this.t = t;
        Sprite lb = new Sprite(new Texture("lightBlueBlock.png")); //Light Blue (I)
        Sprite p = new Sprite(new Texture("purpleBlock.png")); //Purple (T)
        Sprite r = new Sprite(new Texture("redBlock.png"));//Red (Z)
        Sprite g = new Sprite(new Texture("greenBlock.png")); //Green (S)
        Sprite y = new Sprite(new Texture("yellowBlock.png")); //Yellow (O)
        Sprite db = new Sprite(new Texture("darkBlueBlock.png")); //Dark Blue (J)
        Sprite o = new Sprite(new Texture("orangeBlock.png")); //Orange (L)
        Sprite k = new Sprite(new Texture("krayZBlox.png"));
        Sprite w = new Sprite(new Texture("wallBlock.png"));
        time = 0;

        spriteMap.put("I", lb);
        spriteMap.put("T", p);
        spriteMap.put("Z", r);
        spriteMap.put("S", g);
        spriteMap.put("O", y);
        spriteMap.put("J", db);
        spriteMap.put("L", o);
        spriteMap.put("KRAYZ", k);
        spriteMap.put("W", w);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), t.batch);

        muteButton.setX(Gdx.graphics.getWidth() - muteSprite.getWidth());
        muteButton.setY(Gdx.graphics.getHeight() - muteSprite.getRegionHeight());
        muteButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SwapImageClick.mute(t, muteSprite);
            }
        });

        stage.addActor(muteButton);
        Gdx.input.setInputProcessor(stage);


    }

    public Coords genScreenCoords(Block b){
        Coords c = new Coords(displacementX + b.getCoords().X() * blockSize,
                displacementY - b.getCoords().Y()*blockSize + blockSize);
        return c;
    }

    public boolean validScreenCoordsBlock(Block b){

        Coords c = genScreenCoords(b);

        /*
        if (c.X() >= displacementX && c.X() <= Gdx.graphics.getWidth() - displacementX){
            if (c.Y() <= displacementY + blockSize && c.Y() >= (Gdx.graphics.getHeight() - displacementY) - 2*blockSize) {
                return true;
            }
        }*/

        if(c.X() >= displacementX && c.X() <= displacementX + 10 * blockSize){
            if (c.Y() <= displacementY + blockSize && c.Y() >=  displacementY - 14 * blockSize);{
                return true;
            }
        }

        return false;
    }

    public void drawPlaced(){
        t.batch.begin();
        for(Block b: t.g.getPlaced()){
            Coords c = genScreenCoords(b);
            if (b.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), c.X(), c.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(b.getColour())), c.X(), c.Y(), blockSize, blockSize);
            }

        }

        t.batch.end();
    }

    public void drawTetromino(Tetromino tetrom){

        Block a = tetrom.getBlocks().get("A");
        Block b = tetrom.getBlocks().get("B");
        Block c = tetrom.getBlocks().get("C");
        Block d = tetrom.getBlocks().get("D");

        Coords ac = genScreenCoords(a);
        Coords bc = genScreenCoords(b);
        Coords cc = genScreenCoords(c);
        Coords dc = genScreenCoords(d);

        t.batch.begin();

        if (validScreenCoordsBlock(a)) {
            if (a.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), ac.X(), ac.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), ac.X(), ac.Y(), blockSize, blockSize);
            }
        }

        if (validScreenCoordsBlock(b)) {
            if (b.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), bc.X(), bc.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), bc.X(), bc.Y(), blockSize, blockSize);
            }
        }

        if (validScreenCoordsBlock(c)) {
            if (c.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), cc.X(), cc.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), cc.X(), cc.Y(), blockSize, blockSize);
            }
        }

        if (validScreenCoordsBlock(d)) {
            if (d.getPower() != 'N') {
                t.batch.draw(spriteMap.get("KRAYZ"), dc.X(), dc.Y(), blockSize, blockSize);
            } else {
                t.batch.draw(spriteMap.get(String.valueOf(tetrom.getShape())), dc.X(), dc.Y(), blockSize, blockSize);
            }
        }
        t.batch.end();

    }

    public void drawPlayingField(){
        int i, j;
        t.batch.begin();

        for (i = -1; i < 11; i++){

            t.batch.draw(spriteMap.get("W"), displacementX + i * blockSize, displacementY - 14 * blockSize, blockSize, blockSize); //linha de baixo
            t.batch.draw(spriteMap.get("W"), displacementX + i * blockSize, displacementY + blockSize, blockSize, blockSize); //linha de cima

        }

        for (j = -1; j < 14; j++) {
            t.batch.draw(spriteMap.get("W"), displacementX - blockSize, displacementY - j * blockSize, blockSize, blockSize); // esquerda
            t.batch.draw(spriteMap.get("W"), displacementX + 10 * blockSize, displacementY  - j * blockSize ,blockSize, blockSize); // direita
        }

        t.batch.end();
    }


    public int findLine(){


        for(int i = 0; i < 15; i++){

            if(t.g.getLines()[i] == 10){
                return i;
            }
        }
        return -1;
    }

    public int deleteLine(){

        LinkedList <Block> newBlockList = new LinkedList<Block>();

        int line = findLine();

        if(line == -1){
            return -1;
        }

        for(Block b: t.g.getPlaced()){
            if(b.getCoords().Y() > line){
                newBlockList.add(b);
            }else if (b.getCoords().Y() < line){
                Block c = new Block (new Coords(b.getCoords().X(), b.getCoords().Y() + 1), b.getColour(), b.getPower());
                newBlockList.add(c);
            }
        }

        t.g.setPlaced(newBlockList);


        int[] newList = new int[15];


        for(int i = 0; i <= line; i++){
            if (i == 0){
                newList[0] = 0;
            } else {
                newList[i] = t.g.getLines()[i - 1];
            }
        }
        for (int j = line + 1; j < 15; j ++){
            newList[j] = t.g.getLines()[j];
        }

        t.g.setLines(newList);

        return 0;

    }

    public void clearLinesScore(){
        int noOfLines = 0;
        int pointsAdded = 0;
        while(deleteLine() != -1){
            noOfLines ++;
        }
        switch (noOfLines){
            case 1:
                pointsAdded = 100;
                break;
            case 2:
                pointsAdded = 250;
                break;
            case 3:
                pointsAdded = 500;
                break;
            case 4:
                pointsAdded = 1000;
                break;
            default:
                break;
        }

        t.g.incrementScore(pointsAdded);

    }

    public void drawHoldAndNext(){

        t.batch.begin();
        t.batch.draw(holdNextBox, 150, 430, blockSize * 4, blockSize * 4);
        t.batch.draw(holdNextBox, 150, 130, blockSize * 4, blockSize * 4);
        t.batch.draw(holdText, 150, 600, blockSize * 4, blockSize * 2);
        t.batch.draw(nextText, 150, 300, blockSize * 4, blockSize * 2);
        t.batch.end();

        drawNHTetrominos(t.g.getNextTetromino(), true);

        if(t.g.getHold() != null){
            drawNHTetrominos(t.g.getHold(), false);
        }
    }

    public void drawNHTetrominos(Tetromino tetro, boolean isNext){

        Coords holdCoords = new Coords(175, 480);
        Coords nextCoords = new Coords(175, 170);

        int blockSide = 40;

        Texture darkBlue = new Texture("darkBlueBlock.png");
        Texture lightBlue = new Texture("lightBlueBlock.png");
        Texture purple = new Texture("purpleBlock.png");
        Texture orange = new Texture("orangeBlock.png");
        Texture red = new Texture("redBlock.png");
        Texture green = new Texture("greenBlock.png");
        Texture yellow = new Texture("yellowBlock.png");

        t.batch.begin();


        switch(tetro.getShape().charAt(0)){

            case 'I':
                if(isNext){

                    for(int i = 0; i < 4; i++){
                        t.batch.draw(lightBlue, nextCoords.X() + i * 35 - 10, nextCoords.Y() + 20, 35, 35);
                    }
                }else {
                    for(int i = 0; i < 4; i++){
                        t.batch.draw(lightBlue, holdCoords.X() + i * 35 - 10, holdCoords.Y() + 20, 35, 35);
                    }
                }
                break;

            case 'T':
                if(isNext){
                    t.batch.draw(purple, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);

                } else {
                    t.batch.draw(purple, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(purple, holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                }
                break;

            case 'S':

                if(isNext){
                    t.batch.draw(green, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(green, nextCoords.X() + 2 * blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);

                } else {
                    t.batch.draw(green, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(green, holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(green, holdCoords.X() + 2 * blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);

                }


                break;
            case 'Z':

                if(isNext){
                    t.batch.draw(red, nextCoords.X(), nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(red, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);

                } else {
                    t.batch.draw(red, holdCoords.X(), holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(red, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(red, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                }
                break;

            case 'L':

                if(isNext){
                    t.batch.draw(orange, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, nextCoords.X() + 2 * blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                } else {
                    t.batch.draw(orange, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(orange, holdCoords.X() + 2 * blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);
                }
                break;

            case 'J':

                if(isNext){
                    t.batch.draw(darkBlue, nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, nextCoords.X(), nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(darkBlue, nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, nextCoords.X() + 2 * blockSide, nextCoords.Y(), blockSide, blockSide);
                } else {
                    t.batch.draw(darkBlue, holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, holdCoords.X(), holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(darkBlue, holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(darkBlue, holdCoords.X() + 2 * blockSide, holdCoords.Y(), blockSide, blockSide);
                }
                break;

            case 'O':

                if(isNext){
                    t.batch.draw(yellow, 20 + nextCoords.X(), nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  nextCoords.X() + blockSide, nextCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  nextCoords.X(), nextCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  nextCoords.X() + blockSide, nextCoords.Y() + blockSide, blockSide, blockSide);
                } else {
                    t.batch.draw(yellow, 20 + holdCoords.X(), holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  holdCoords.X() + blockSide, holdCoords.Y(), blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  holdCoords.X(), holdCoords.Y() + blockSide, blockSide, blockSide);
                    t.batch.draw(yellow, 20 +  holdCoords.X() + blockSide, holdCoords.Y() + blockSide, blockSide, blockSide);

                }



                break;
            default:
                break;

        }

        t.batch.end();
    }


    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        if (time >= 1){
            t.g.drop();
            time = 0;
        }
        t.batch.begin();
        t.batch.draw(background, 0, 0);
        t.batch.end();
        stage.draw();
        //while(deleteLine() != -1);
        clearLinesScore();
        drawTetromino(t.g.getCurr());
        drawPlayingField();
        drawPlaced();
        drawHoldAndNext();
        if (t.g.defeat()){
            this.dispose();
            t.setScreen(new GameOverScreen(t));
        }
        //t.batch.end();
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

}
