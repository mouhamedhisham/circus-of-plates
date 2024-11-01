/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Circus;


public abstract class GameState {
    private boolean state;
    private Circus circus;
    
    public GameState(Circus c){
        this.circus =c;
    }
    
    public void setState(boolean state){
        this.state = state;
    } 
    
    public Circus getCircus(){
        return this.circus;
    }

    public boolean getState(){
        return this.state;
    }
    
    public abstract void gameAction();
}
