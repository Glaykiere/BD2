/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.vividsolutions.jts.geom.Geometry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 *
 * @author glaykiere
 */
public class ImageConvert {
    
    
    public static void criarSVG(Geometry a, Geometry b, int cont) throws IOException, FileNotFoundException, TranscoderException{
        StringBuilder builder = new StringBuilder();
        ViewBox view = new ViewBox(a, b);
        
        builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        builder.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\" >\n");
        builder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"200\" height=\"200\" ");
        builder.append("viewBox=\"");
        builder.append(view.getViewBox());
        builder.append("\">\n");
        builder.append("<path d=\" ");
        builder.append(SvgFactory.svgConvert(a));
        builder.append("\" fill=\"blue\" />\n");
        builder.append("<path d=\" ");
        builder.append(SvgFactory.svgConvert(b));
        builder.append("\" fill=\"red\" />\n");
        builder.append("</svg>");
        
        File arquivoSvg = new File("src/main/java/imagem/imagem.svg");
        if(arquivoSvg.exists()){
            arquivoSvg.delete();
        }        
        arquivoSvg.createNewFile();
        BufferedWriter write = new BufferedWriter(new PrintWriter(new FileWriter(arquivoSvg, true), true));
        write.write(builder.toString());
        write.close();
        criaPng(cont);
        arquivoSvg.delete();
        
        
    }
    

    private static void criaPng(int cont) throws FileNotFoundException, TranscoderException, IOException {
        String path = Paths.get("src/main/java/imagem/imagem.svg").toString();
        TranscoderInput input = new TranscoderInput(path);
        FileOutputStream stream = new FileOutputStream("src/main/java/imagem/imagem"+cont+".png");
        TranscoderOutput output = new TranscoderOutput(stream);
        PNGTranscoder png = new PNGTranscoder();
        png.transcode(input, output);
        stream.close();
        
               
        
    }
    
}
