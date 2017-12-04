/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

/**
 *
 * @author glaykiere
 */
public class SvgFactory {
    
    public static String svgConvert(Geometry geometria) {

        Coordinate[] coordenadas = geometria.getCoordinates();
        StringBuilder svg = new StringBuilder();
        svg.append("M ");
        svg.append(coordenadas[0].x);
        svg.append(" ");
        svg.append(coordenadas[0].y);
        svg.append(" ");
        for (Coordinate c : coordenadas) {
            svg.append("L");
            svg.append(" ");
            svg.append((Double) c.x);
            svg.append(" ");
            svg.append((Double) c.y);
            svg.append(" ");
        }

        return svg.toString();
}
    
}
