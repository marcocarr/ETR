package Controll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import objects.Object;
import objects.interfaces.Drawable;
import objects.interfaces.Timer;

public class Level implements Drawable,Timer{
	private List<Object>  objects= new ArrayList<>();
	
	
	// TODO: ONLY FOR TEST
	public Level(int objectAmount){
		for(int i=0;i<objectAmount;i++){
			objects.add(new Object((i*70),((int)((i*70)/200))*70,50,50));
		}
	}
	
	@Override
	public void draw(GraphicsContext gc, double windowWidth, double windowHeight, double xOffsetWindow) {
		objects.stream()
			.sorted(Comparator.comparing(Object::getZ))
			.forEachOrdered(o->o.draw(gc,windowWidth,windowHeight,xOffsetWindow));
	}

	public void mouseClicked(double x, double y) {
		for(Object o:objects){
			o.ifHit(x,y);
		}
	}
	
	@Override
	public void update(double pastTime) {
		objects.stream()
			.forEach(o->o.call(pastTime));
	}
}
