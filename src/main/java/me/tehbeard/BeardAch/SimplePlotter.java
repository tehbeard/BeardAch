package me.tehbeard.BeardAch;

public class SimplePlotter extends Metrics.Plotter{

    int value = 0;
    
    public SimplePlotter(final String name){
        super(name);
    }
    
    @Override
    public int getValue() {
        // TODO Auto-generated method stub
        return value;
    }
    
    public void increment(){
        value ++;
    }
    
    public void decrement(){
        value --;
    }
    
    public void set(int value){
        this.value = value;
    }

}
