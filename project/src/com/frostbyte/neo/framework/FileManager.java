package com.frostbyte.neo.framework;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * 
 * This isn't documented, and is used solely for other items to work.<br />
 * It isn't suggested you use this.
 * 
 * @author Jedi Burrell
 *
 */
public class FileManager {

	public static void createFolder(String path){
		new File(path).mkdirs();
	}
	
	public static void createFile(String path){
		try {
			new File(path).createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(String path){
		new File(path).delete();
	}
	
	public static File[] fileList(String path){
		return new File(path).listFiles();
	}
	
	public static String[] folderList(String path){
		return new File(path).list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
			
		});
	}
	
	public static void createTextFile(String path, String... args){
		BufferedWriter writer = null;
        try {
            File file = new File(path);

            writer = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < args.length; i++){
            	writer.write(args[i]);
            	writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
	}
	
	public static void copyOverImage(BufferedImage bi, String fileName, String path){
		try {
		    File outputfile = new File(path+fileName);
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		}
	}
	
	public static void listFilesFromFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesFromFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}
	
	public static LinkedList<String> getFilesFromFolder(final File folder){
		LinkedList<String> files = new LinkedList<String>();
		for (final File fileEntry : folder.listFiles()) {
	        files.add(fileEntry.getName());
	    }
		
		return files;
	}
	
	public static boolean fileExists(String path){
		return new File(path).exists();
	}
	
	public static BufferedImage getImage(String path){
		// This time, you can use an InputStream to load
		try {                
			return ImageIO.read(new File(path));
		} catch (IOException e) {
		    System.out.println("The image '" + path + "' was not loaded.");
		    //System.exit(1);
		}
		return null;
	}
	
	public static String getAppdata(){
		return System.getenv("APPDATA");
	}

	public static String getText(String path) {
		String text = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line = br.readLine();
		    
		    text = line;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}
	
}
