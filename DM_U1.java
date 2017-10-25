package digitalmedia;

import gdmvalidation.Ueb1Validation;
import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)
// TODO refactor the class name
public class DM_U1 implements PlugIn {

	final static String[] choices = { "Schwarzes Bild", "Gelbes Bild",
			"Schwarz/Weiss Verlauf",
			"Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf",
			"Italienische Fahne", "Kegelschnitt", "Japanische Fahne",
			"Japanische Fahne mit weichen Kanten", "Streifenmuster" };

	private String choice;

	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
		ij.exitWhenQuitting(true);

		DM_U1 imageGeneration = new DM_U1();
		imageGeneration.run("");
	}

	public void run(String arg) {

		int width = 566; // Breite
		int height = 400; // Hoehe

		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("DM_U1", width, height,
				1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();

		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[]) ip.getPixels();

		dialog();

		// //////////////////////////////////////////////////////////////
		if (choice.equals("Schwarzes Bild")) {
			int r,g,b;
			r = g = b = 0;
			setImageToColor(pixels, width, height,r, g, b);
		} else if (choice.equals("Gelbes Bild")) {
			int r,g,b;
			
			
			
			r=255;
			g=255; 
			b=0; 
			
			
			//TODO set the color to yellow
			setImageToColor(pixels, width, height,r, g, b);
		} else if (choice.equals("Schwarz/Weiss Verlauf")) {
			blackToWhite(pixels, width, height);
			Ueb1Validation.validateBlackToWhite(pixels, width, height);
		} else if (choice.equals("Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf")) {
			black2RedAndBlack2Blue(pixels, width, height);
			Ueb1Validation.validateBlack2RedAndBlack2Blue(pixels, width, height);
		} else if (choice.equals("Italienische Fahne")) {
			flagItalian(pixels, width, height);
			Ueb1Validation.validateFlagItalian(pixels, width, height);
		} else if (choice.equals("Kegelschnitt")) {
			conicSection(pixels, width, height);
		} else if (choice.equals("Japanische Fahne")) {
			flagOfJapan(pixels, width, height);
		} else if (choice.equals("Japanische Fahne mit weichen Kanten")) {
			flagOfJapanSmooth(pixels, width, height);
		} else if (choice.equals("Streifenmuster")) {
			stripes(pixels, width, height);
		}

		// //////////////////////////////////////////////////////////////////

		// neues Bild anzeigen
		imagePlus.show();
		imagePlus.updateAndDraw();
	}
	
	private void blackToWhite(int[] pixels, int width, int height) {
		// TODO set some values here
		// Schleife ueber die y-Werte
		
	 
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				int r,g,b; 
				
					
				
				r=g=b= 255 * x / (width-1);
				
				// man teilt 255*x durch width-1, weil dadurch garantiert wird, dass ein recht größer Wert gebildet
				//wird und mit einem in der Regel kleineren Wert geteilt wird, sodass ein Integer entsteht
			
				
				
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void black2RedAndBlack2Blue(int[] pixels, int width, int height) {
		// TODO set some values here
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				//TODO code for the image.
				r = 255*x/ (width-1);
				g=0;
				b= 255*y/ (height-1);
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void flagItalian(int[] pixels, int width, int height) {
		// TODO set some values here
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				//TODO code for the flag.
				r = g = b = 0;

				int oneThird = (width-1)/3; 
				if(x<oneThird){
					
					r=b=0; 
					g=255; 
				}else if(x>=oneThird && x <oneThird*2){
					
					r=g=b=255; 
					
				}else{
					r=255; 
					g=b=0; 
				}
				
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void conicSection(int[] pixels, int width, int height) {
		// TODO set some values here
		// Schleife ueber die y-Werte
		
		int middleHorizon = height/2; 
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				//TODO code for the flag.
				r = g = b = 0;
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void flagOfJapan(int[] pixels, int width, int height) {
		int xMid = width/2; 
		int yMid = height/2;
		int radius = 70; 
		
		
		
	
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			
			
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				//TODO code for the flag.
				
				if((x-xMid)*(x-xMid) + (y-yMid)*(y-yMid) <= radius*radius){
					
					r=255;
					g=b=0;
					
					
				}else{
				
				r=g=b=255; 
				
				}
				
			
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	private void flagOfJapanSmooth(int[] pixels, int width, int height) {
		
		int xMid = width/2; 
		int yMid = height/2;
		
		int radius1 = 100; 
		int radius2 = 60; 
		int smoothFrame = radius1-radius2; 
		
		
		// rot soll 255 bleiben (100%), die anderen werte müssen ansteigen nach außen hin
		//hier müssen wir wieder geradengleichung benutzen. 
		// 2 Radien benutzen, einmal wo kreis absolut rot ist, dann radius wo die farbtransition passiert
		
		
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				
				
				int distanceFromCenterWithoutSqrt = (x-xMid)*(x-xMid) + (y-yMid)*(y-yMid);
				
				if( distanceFromCenterWithoutSqrt  <= radius2*radius2){
					
					r=255;
					g=b=0;
				}else if(distanceFromCenterWithoutSqrt <= radius1*radius1 && distanceFromCenterWithoutSqrt > radius2*radius2){
					
					int realDistanceFromC = (int) Math.sqrt((double)distanceFromCenterWithoutSqrt);
					
					int distanceFromInnerRadius = realDistanceFromC - radius2; 
					
					r=255;
					
					g=b= 255*distanceFromInnerRadius/(smoothFrame) ;
					
					
				}else{
					
					r=g=b=255; 
				}

			pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}			
			}
				
		
	}

	private void stripes(int[] pixels, int width, int height) {
		
		
		int Hstripe = width/12; 
		int Vstripe = height/12; 
		
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				int numberOfStripeV = (x/Hstripe);
				int numberOfStripeH = (y/Vstripe);
				
				
				r=g=b=0; 
				
				if(numberOfStripeV%2==0 &&numberOfStripeV!=numberOfStripeH){
				r=g=b=255;
				}else if(numberOfStripeV==numberOfStripeH){
				r=255; 
				g=b=0; 
				
				}
		
				
			
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}
	
	
	/*
	 * private void stripes(int[] pixels, int width, int height) {
		// TODO set some values here
		
		int stripe = width/12; 
	
		
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				int r,g,b;
				int numberOfStripe = (x/stripe); 

				
				if(numberOfStripe%2==0){
				r=g=b=255;
				}else{
					
				r=g=b=0; 
				}
			
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}
	 * *
	 */
	private void setImageToColor(int[] pixels, int width, int height,int r, int g, int b) {
	int color = 0xFF000000 | (r << 16) | (g << 8) | b;
	// Schleife ueber die y-Werte
	for (int y = 0; y < height; y++) {
		// Schleife ueber die x-Werte
		for (int x = 0; x < width; x++) {
			int pos = y * width + x; // Arrayposition bestimmen
			// Werte zurueckschreiben
			pixels[pos] = color;
		}
	}
	}
	private void dialog() {
		// Dialog fuer Auswahl der Bilderzeugung
		GenericDialog gd = new GenericDialog("Bildart");
		gd.addChoice("Bildtyp", choices, choices[0]);
		gd.showDialog(); // generiere Eingabefenster
		choice = gd.getNextChoice(); // Auswahl uebernehmen

		if (gd.wasCanceled())
			System.exit(0);
	}
}