/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

/**
 *
 * @author glaykiere
 */
public class ViewBox {

    private Geometry g1;
    private Geometry g2;

    public ViewBox(Geometry g1, Geometry g2) {
        this.g1 = g1;
        this.g2 = g2;
    }
    
    public String getViewBox() {
        Geometry uniao = g1.union(g2);
        Geometry envelope = uniao.getEnvelope();

        StringBuilder builder = new StringBuilder();

        builder.append(getXMin(envelope));
        builder.append(" ");
        builder.append(getYMin(envelope));
        builder.append(" ");
        builder.append(getXMax(envelope) - getXMin(envelope));
        builder.append(" ");
        builder.append(getYMax(envelope) - getYMin(envelope));
        builder.append(" ");

        return builder.toString();
    }


    private double getXMin(Geometry envelope) {
        Coordinate coordenadas[] = envelope.getCoordinates();        
        double xMin = coordenadas[0].x;        
        for(Coordinate c: coordenadas)
            if (c.x < xMin)
                xMin = c.x;        
        return xMin;
    }
    
    private double getYMin(Geometry envelope) {
        Coordinate coordenadas[] = envelope.getCoordinates();
        double yMin = coordenadas[0].y;     
        for(Coordinate c: coordenadas)
            if (c.y < yMin)
                yMin = c.y;
        
        return yMin;
    }
    
    private double getXMax(Geometry envelope) {
        Coordinate coordenadas[] = envelope.getCoordinates();     
        double xMax = coordenadas[0].x;        
        for(Coordinate c: coordenadas)
            if (c.x > xMax)
                xMax = c.x;      
        return xMax;
    }
    
    private double getYMax(Geometry envelope) {
        Coordinate coordenadas[] = envelope.getCoordinates();
        double yMax = coordenadas[0].y;  
        for(Coordinate c: coordenadas)
            if (c.y > yMax)
                yMax = c.y;
        
        return yMax;
    }
}