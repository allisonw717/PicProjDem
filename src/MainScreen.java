import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.text.*;


public class MainScreen extends FlexiblePictureExplorer implements ImageObserver {
	private String path = "slideshow\\";
	private final String[][][] images = new String[][][] {{{path+"childhood.jpg",path+"FINDTHIS.jpg",path+"Nassau Hall.jpg",path+"Amtrak.jpg",path+"Plane.jpg",path+"Jane.jpg",path+"TravelCollage.jpg",path+"Cat.jpg",path+"watermelon-tourmaline.jpg",path+"Littlebrook.PNG",path+"Princeton-Day-School.PNG",path+"Bucks-Rock.jpg",path+"graduation.jpg"},
															{path+"school.jpg",path+"Brown.jpg",path+"formula.jpg",path+"IBM.jpg",path+"StonyBrook.jpg",path+"Generic-Diploma.jpg",path+"Temp-ui.jpg"},
															{path+"HubbleClipArt.jpg",path+"maryland-road-map.gif",path+"STSI.jpg",path+"hubble.jpg",path+"JHU.jpg",path+"Schedule.jpg",path+"FINDTHIS.jpg",path+"auditorium.jpg"}},
															{{path+"family.jpg",path+"FINDTHIS.jpg",path+"timonium.png",path+"Matt.jpg",path+"Rachel.jpg",path+"Ellicott.PNG",path+"Guinea Pigs.jpg",path+"centennial.png",path+"Columbia.PNG"},
															{path+"teacher.jpg"},
															{path+"Gender-Symbol.jpg",path+"Trans-Pride.jpg",path+"boy-and-girl-playing.jpg",path+"Gender-Dysphoria.jpg",path+"brain.jpg",path+"Transition.jpg",path+"FINDTHIS.jpg",path+"Discrimination.png",path+"FINDTHIS.jpg"}},
															{{path+"clipart-earth.png",path+"earth.jpg",path+"GSA.jpg",path+"OWL.jpg",path+"Trans-Lifeline.png",path+"FINDTHIS.jpg",path+"PFLAG-Picture.PNG",path+"pastoral-care.jpg"},
															{path+"Chalice.png",path+"Menorah.png",path+"UUA-Logo.png",path+"Sunburst.png",path+"Seven-UU-Principles.png",path+"Religions-Collage.png",path+"UUCC.png"},
															{path+"Boardgame.png",path+"Power-Grid.png",path+"Library.png",path+"Writing.png",path+"Sports.png",path+"Aerial-Silks.png",path+"Trail.png"}}};

	private int row;
	private int col;
	private int depth;
	private boolean main;
	public MainScreen(){
		super(new Picture(700,600));
		displayMain();
		row = 0; 
		col = 0;
		depth = 0;
	}
	private void displayMain() {
		main = true;
		Picture disp = new Picture(600, 600);
		Graphics2D graphics = disp.createGraphics();
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				Picture pict;
				pict = new Picture(images[x][y][0]);
				graphics.drawImage(pict.getBufferedImage(), 200*y, 200*x, this);
			}
		}

		setImage(disp);
		// setImage() changes the title each time it's called
		setTitle("All About Me");
	}
	private void displayPhoto(int row, int col, int depth){
		main = false;
		Picture disp = new Picture(700,600);
		disp.setAllPixelsToAColor(Color.black);
		Graphics2D graphics = disp.createGraphics();
		//System.out.println("row:" + row + " col:" + col + " depth:" + depth);
		Picture photo = new Picture(images[row][col][depth]);
		int x = (600 - photo.getWidth())/2;
		int y = (600 - photo.getHeight())/2;
		graphics.drawImage(photo.getBufferedImage(), x, y, this);
		Picture arrows = new Picture("src\\arrows.png");
		graphics.drawImage(arrows.getBufferedImage(),0,600,this);
		setImage(disp);
		setTitle("All About Me");
	}

	public void mouseClickedAction(DigitalPicture pict, Pixel pix) {
		if(main){
			row = (int) pix.getY()/200;
			col = (int) pix.getX()/200;
			depth = 1;
			displayPhoto(row,col,depth);
		}
		else if(pix.getY()>600){
			if(pix.getX()/200 == 0 && depth>1){
				depth--;
				displayPhoto(row,col,depth);
			}
			else if(pix.getX()/200 == 1)
				displayMain();
			else if(pix.getX()/200 == 2 && depth < images[row][col].length-1){
				depth++;
				displayPhoto(row,col,depth);
			}
		}
	}
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		return false;
	}
	public static void main (String args[]){
		new MainScreen();
	}


}
