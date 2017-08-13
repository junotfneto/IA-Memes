/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencvexample;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import doc.*;
import java.util.ArrayList;

public class GetPixelColor
{
    
    public static void addInstance(String pathImg, String pack, String name) throws IOException{
        System.out.println(name);
        File file= new File(pathImg);
        BufferedImage image = ImageIO.read(file);
        int scale = 10;

        //BufferedImage off_Image = new BufferedImage(image.getWidth()/scale+1, image.getHeight()/scale+1, BufferedImage.TYPE_INT_RGB);
        int x = 0;
        int y = 0;

        int clr = 0;

        Arquivos arq;
        arq = new Arquivos("base-"+scale+".csv");
        arq.iniciaEscrita();
        arq.escreve(name+",");
        arq.escreve(pack+",");
        for(x = 0; x < image.getWidth(); x++){
            for(y = 0; y < image.getHeight(); y++){

                if(x % scale > 0)
                    continue;
                if(y % scale > 0)
                    continue;
                  clr=  image.getRGB(x,y);
                  //off_Image.setRGB(x/scale, y/scale, clr);
                  int  red   = (clr & 0x00ff0000) >> 16;
                  int  green = (clr & 0x0000ff00) >> 8;
                  int  blue  =  clr & 0x000000ff;
                  arq.escreve(((red+green+blue)/3)+",");
                  //System.out.println("Red Color value = "+ red);
                  //System.out.println("Green Color value = "+ green);
                  //System.out.println("Blue Color value = "+ blue);

            }
        }
        arq.novaLinha();
        arq.acabaEscrita();


        scale = 6;
        arq = new Arquivos("base-"+scale+".csv");
        arq.iniciaEscrita();
        arq.escreve(name+",");
        arq.escreve(pack+",");
        for(x = 0; x < image.getWidth(); x++){
            for(y = 0; y < image.getHeight(); y++){

                if(x % scale > 0)
                    continue;
                if(y % scale > 0)
                    continue;
                  clr=  image.getRGB(x,y);
                  //off_Image.setRGB(x/scale, y/scale, clr);
                  int  red   = (clr & 0x00ff0000) >> 16;
                  int  green = (clr & 0x0000ff00) >> 8;
                  int  blue  =  clr & 0x000000ff;
                  arq.escreve(((red+green+blue)/3)+",");
                  //System.out.println("Red Color value = "+ red);
                  //System.out.println("Green Color value = "+ green);
                  //System.out.println("Blue Color value = "+ blue);

            }
        }
        arq.novaLinha();
        arq.acabaEscrita();
        
        
    }
    
    public static void addInstance2(String pathImg, String pack, String name) throws IOException{
        System.out.println(name);
        File file= new File(pathImg);
        BufferedImage image = ImageIO.read(file);

        //BufferedImage off_Image = new BufferedImage(image.getWidth()/scale+1, image.getHeight()/scale+1, BufferedImage.TYPE_INT_RGB);
        int x = 0;
        int y = 0;

        int clr = 0;

        Arquivos arq;
        arq = new Arquivos("base-cores.csv");
        arq.iniciaEscrita();
        arq.escreve(name+",");
        arq.escreve(pack+",");
        double rgbwp[] = new double[5];
        for(x = 0; x < image.getWidth(); x++){
            for(y = 0; y < image.getHeight(); y++){
                
                  clr=  image.getRGB(x,y);
                  //off_Image.setRGB(x/scale, y/scale, clr);
                  int  red   = (clr & 0x00ff0000) >> 16;
                  int  green = (clr & 0x0000ff00) >> 8;
                  int  blue  =  clr & 0x000000ff;
                  rgbwp[0] += red/255.0;
                  rgbwp[1] += green/255.0;
                  rgbwp[2] += blue/255.0;
                  if((red+green+blue)/(3.0*255.0) < 0.1)
                      rgbwp[3]++;
                  if((red+green+blue)/(3.0*255.0) > 0.9)
                      rgbwp[4] ++;
                  
                  //System.out.println("Red Color value = "+ red);
                  //System.out.println("Green Color value = "+ green);
                  //System.out.println("Blue Color value = "+ blue);

            }
        }
        for(x = 0; x < rgbwp.length; x++){
            rgbwp[x] = rgbwp[x]/(image.getWidth()*image.getHeight());
            if(x < rgbwp.length-1)
                arq.escreve(rgbwp[x]+",");
            else
                arq.escreve(rgbwp[x]+"");
        }
        arq.novaLinha();
        arq.acabaEscrita();  
    }
    
    private static double getMaior(double a, double b, double c){
        if((a > b)&&(a > c))
            return a;
        if((b > a)&&(b > c))
            return b;
        return c;
    }
    
    public static void addInstance3(String pathImg, String pack, String name) throws IOException{
        System.out.println(name);
        File file= new File(pathImg);
        BufferedImage image = ImageIO.read(file);

        //BufferedImage off_Image = new BufferedImage(image.getWidth()/scale+1, image.getHeight()/scale+1, BufferedImage.TYPE_INT_RGB);
        int x = 0;
        int y = 0;

        int clr = 0;

        Arquivos arq;
        arq = new Arquivos("base-cores3.csv");
        arq.iniciaEscrita();
        arq.escreve(name+",");
        arq.escreve(pack+",");
        double rgbwp[] = new double[5];
        double maiorcor = 0;
        double maiorcanal = 0;
        double rgbwp2[][] = new double[5][5];
        
        for(x = 0; x < image.getWidth(); x++){
            for(y = 0; y < image.getHeight(); y++){
                
                  clr=  image.getRGB(x,y);
                  //off_Image.setRGB(x/scale, y/scale, clr);
                  double  red   = (clr & 0x00ff0000) >> 16;
                  double  green = (clr & 0x0000ff00) >> 8;
                  double  blue  =  clr & 0x000000ff;
                  
                  double media = (red+green+blue)/3.0;
                  
                  if(getMaior(red/255.0, green/255.0, blue/255.0) > 0.7){
                      maiorcanal += getMaior(red/255.0, green/255.0, blue/255.0);
                  }
                  if(media > 0.7){
                      maiorcor += media;
                  }
                  
                  int i = 4;
                  
                  if((x < image.getWidth()/2) && (y < image.getHeight()/2))
                      i = 0;
                  if((x >= image.getWidth()/2) && (y < image.getHeight()/2))
                      i = 1;
                  if((x < image.getWidth()/2) && (y >= image.getHeight()/2))
                      i = 2;
                  if((x >= image.getWidth()/2) && (y >= image.getHeight()/2))
                      i = 3;
                  
                  if(red >= media){
                    rgbwp[0] += red/255.0;
                    rgbwp2[i][0] += red/255.0;
                  }
                  if(green >= media){
                    rgbwp[1] += green/255.0;
                    rgbwp2[i][1] += green/255.0;
                  }
                  if(blue >= media){
                    rgbwp[2] += blue/255.0;
                    rgbwp2[i][2] += blue/255.0;
                  }
                  if((red+green+blue)/(3.0*255.0) < 0.1){
                      rgbwp[3]++;
                      rgbwp2[i][3]++;
                  }
                  if((red+green+blue)/(3.0*255.0) > 0.9){
                      rgbwp[4]++;
                      rgbwp2[i][4]++;
                  }
                  
                  if((x >= image.getWidth()/4)&&(x <= 3*image.getWidth()/4)&&(y >= image.getHeight()/4)&&(y <= 3*image.getHeight()/4)){
                      i = 4;
                      if(red >= media){
                        rgbwp2[i][0] += red/255.0;
                      }
                      if(green >= media){
                        rgbwp2[i][1] += green/255.0;
                      }
                      if(blue >= media){
                        rgbwp2[i][2] += blue/255.0;
                      }
                      if((red+green+blue)/(3.0*255.0) < 0.1){
                          rgbwp2[i][3]++;
                      }
                      if((red+green+blue)/(3.0*255.0) > 0.9){
                          rgbwp2[i][4]++;
                      }
                  }
                  

            }
        }
        for(x = 0; x < rgbwp.length; x++){
            rgbwp[x] = rgbwp[x]/(image.getWidth()*image.getHeight());
                arq.escreve(rgbwp[x]+",");
        }
        for(x = 0; x < rgbwp2.length; x++){
            for(y = 0; y < rgbwp2[x].length; y++){
                if(x < 4)
                    continue;
                rgbwp2[x][y] = rgbwp2[x][y]/(image.getWidth()*image.getHeight());
                arq.escreve(rgbwp2[x][y]+",");
            }
        }
        
        maiorcor = maiorcor / (image.getWidth()*image.getHeight());
        maiorcanal = maiorcanal / (image.getWidth()*image.getHeight());
        arq.escreve(maiorcor+","+maiorcanal);
        
        arq.novaLinha();
        arq.acabaEscrita();  
    }
    
  public static void main(String args[]) throws IOException{
    
    Diretorios d = new Diretorios("Memes");
    ArrayList<Diretorios> ar = d.listDiretorios();
    int i, j;
    for(i = 0; i < ar.size(); i++){
        ArrayList<Arquivos> aq = ar.get(i).listArquivos();
        for(j = 0; j < aq.size(); j++){
            if(aq.get(j).getName().endsWith("g"))
            addInstance3(aq.get(j).caminhoCompleto(), ar.get(i).getFile().getName(),aq.get(j).getName());
        }
    }
  }
}