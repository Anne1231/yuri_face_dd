package UI;

import Layers.FrontDotLayer;
import Layers.Layer;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

/**
 * Created by Akihiro on 2017/02/25.
 */
public class Dot extends Point2i {

    private Boolean selected;
    private ArrayList<Dot> connected_dots;

    public Dot(int x, int y){
        super(x, y);
        connected_dots = new ArrayList<>();
        selected = false;
    }

    public Dot(int x, int y, int interval){
        super(x, y);
        if (x % interval > interval / 2) {
            this.x += (interval - (x % interval));
        }else{
            this.x -= (x % interval);
        }

        if (y % interval > interval / 2) {
            this.y += (interval - (y % interval));
        }else{
            this.y -= (y % interval);
        }


        connected_dots = new ArrayList<>();
        selected = false;
    }

    public boolean isSelected(){
        return selected;
    }

    public void Select(){
        selected = true;
    }

    public void UnSelect(){
        selected = false;
    }

    public void Draw(Layer layer, Paint color){
        layer.getGraphicsContext().setFill(color);
        layer.getGraphicsContext().setStroke(color);
        layer.getGraphicsContext().setLineWidth(UIValues.DOT_CIRCLE_WIDTH);
        layer.getGraphicsContext().fillOval(x, y, 4, 4);
        layer.getGraphicsContext().strokeOval(x - 3, y - 3, 10, 10);
    }

    public void Erase(FrontDotLayer layer){
        layer.getGraphicsContext().clearRect(this.x-3, this.y-3, 11, 11);
    }

    public void Connect(Dot dot){
        connected_dots.add(dot);
    }

    public boolean equals(Dot dot){
        return (this.x == dot.getX() && this.y == dot.getY());
    }

    public ArrayList<Dot> getConnected_dots() {
        return connected_dots;
    }

    public void PrintInfo(){
        System.out.println("座標 : x = " + x + ", y = " + y);
        System.out.print("使用状態 : ");
        if(selected)
            System.out.println("選択状態");
        else
            System.out.println("未選択状態");
    }
}
