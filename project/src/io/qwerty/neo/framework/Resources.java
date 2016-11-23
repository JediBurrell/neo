package io.qwerty.neo.framework;

import io.qwerty.neo.exceptions.EncryptionFailureException;
import io.qwerty.neo.exceptions.FirstRunException;
import io.qwerty.neo.exceptions.ResourceNotFoundException;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * <strong><em>Resources</em></strong><br /><br />
 * 
 * Resources is responsible for saving and caching<br />
 * resources. It also encrypts important data, and<br />
 * helps make the game run faster.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class Resources {

	private static Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	private static Map<String, String> strings = new HashMap<String, String>();
	private static Map<String, Integer> integers = new HashMap<String, Integer>();
	private static Map<String, Float> floats = new HashMap<String, Float>();
	
	private static String decrypt;
	private static String path;
	
	private static DesEncrypter crypt;
	
	/**
	 * <strong><em>loadResources</em></strong><br /><br />
	 * 
	 * &emsp;Loads resources.<br />
	 * &emsp;This is quite important. This needs to be called before the game starts.<br />
	 * &emsp;other functions in this class depend on this function.<br />
	 * &emsp;This is basically the initialization.
	 * 
	 * @return BufferedImage
	 * @since NEO.1
	 */
	public static void loadResources(String patha, String decryptKey) throws FirstRunException, Exception{
		
		path = patha;
		
		decrypt = decryptKey;
		crypt = new DesEncrypter(decrypt);
		
		FileManager.createFolder(path);
		FileManager.createFolder(path+"/c/");
		FileManager.createFolder(path+"/c/i");
		FileManager.createFolder(path+"/c/s");
		FileManager.createFolder(path+"/c/n");
		FileManager.createFolder(path+"/c/f");
		
		int c = 0;
		
		//Load images first.
		for(String f : FileManager.getFilesFromFolder(new File(path+"/c/i/"))){
			images.put(crypt.decrypt((f+".").replaceAll(".gbiv", "")), FileManager.getImage(path+"/c/i/"+f));
			c++;
		}
		
		//Load strings.
		for(String f : FileManager.getFilesFromFolder(new File(path+"/c/s/"))){
			strings.put(crypt.decrypt((f+".").replaceAll(".gsv", "")), crypt.decrypt(FileManager.getText(path+"/c/s/"+f)));
			c++;
		}
		
		//Load integers.
		for(String f : FileManager.getFilesFromFolder(new File(path+"/c/n/"))){
			integers.put(crypt.decrypt((f+".").replaceAll(".gnv", "")), Integer.parseInt(crypt.decrypt(FileManager.getText(path+"/c/n/"+f))));
			c++;
		}
		
		//Load floats.
		for(String f : FileManager.getFilesFromFolder(new File(path+"/c/f/"))){
			floats.put(crypt.decrypt((f+".").replaceAll(".gfv", "")), Float.parseFloat(crypt.decrypt(FileManager.getText(path+"/c/f/"+f))));
			c++;
		}
		
		if(c==0)
			throw new FirstRunException();
		
	}
	
	/**
	 * <strong><em>getImage</em></strong><br /><br />
	 * 
	 * &emsp;Returns image resource.
	 * 
	 * @return BufferedImage
	 * @since NEO.1
	 */
	public static BufferedImage getImage(String name) throws ResourceNotFoundException{

		if(!images.containsKey(name))
			throw new ResourceNotFoundException();
		
		return images.get(name);
	}
	
	/**
	 * <strong><em>getString</em></strong><br /><br />
	 * 
	 * &emsp;Returns string resource.
	 * 
	 * @return BufferedImage
	 * @since NEO.1
	 */
	public static String getString(String name) throws ResourceNotFoundException{

		if(!strings.containsKey(name))
			throw new ResourceNotFoundException();
		
		return strings.get(name);
	}
	
	/**
	 * <strong><em>getInt</em></strong><br /><br />
	 * 
	 * &emsp;Returns int resource.
	 * 
	 * @return Integer
	 * @since NEO.1
	 */
	public static Integer getInt(String name) throws ResourceNotFoundException{

		if(!integers.containsKey(name))
			throw new ResourceNotFoundException();
		
		return integers.get(name);
	}
	
	/**
	 * <strong><em>getFloat</em></strong><br /><br />
	 * 
	 * &emsp;Returns float resource.
	 * 
	 * @return BufferedImage
	 * @since NEO.1
	 */
	public static Float getFloat(String name) throws ResourceNotFoundException{

		if(!floats.containsKey(name))
			throw new ResourceNotFoundException();
		
		return floats.get(name);
	}
	
	/**
	 * <strong><em>getImage</em></strong><br /><br />
	 * 
	 * &emsp;Returns image resource, if it doesn't exist, it creates it.
	 * 
	 * @return BufferedImage
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static BufferedImage getImage(String name, BufferedImage defaultValue) throws EncryptionFailureException{

		if(!images.containsKey(name))
			putImage(name, defaultValue);
		
		return images.get(name);
	}
	
	/**
	 * <strong><em>getString</em></strong><br /><br />
	 * 
	 * &emsp;Returns string resource, if it doesn't exist, it creates it.
	 * 
	 * @return BufferedImage
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static String getString(String name, String defaultValue) throws EncryptionFailureException{

		if(!strings.containsKey(name))
			putString(name, defaultValue);
		
		return strings.get(name);
	}
	
	/**
	 * <strong><em>getInt</em></strong><br /><br />
	 * 
	 * &emsp;Returns int resource, if it doesn't exist, it creates it.
	 * 
	 * @return Integer
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static Integer getInt(String name, int defaultValue) throws EncryptionFailureException{

		if(!integers.containsKey(name))
			putInteger(name, defaultValue);
		
		return integers.get(name);
	}
	
	/**
	 * <strong><em>getFloat</em></strong><br /><br />
	 * 
	 * &emsp;Returns float resource, if it doesn't exist, it creates it.
	 * 
	 * @return BufferedImage
	 * @throws EncryptionFailureException 
	 * @since NEO.1
	 */
	public static Float getFloat(String name, Float defaultValue) throws EncryptionFailureException{

		if(!floats.containsKey(name))
			putFloat(name, defaultValue);
		
		return floats.get(name);
	}
	
	/**
	 * <strong><em>putImage</em></strong><br /><br />
	 * 
	 * &emsp;Sets image resource.
	 * 
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static void putImage(String name, BufferedImage value) throws EncryptionFailureException{
		try {
			FileManager.copyOverImage(value, crypt.encrypt(name)+".gbiv", path+"/c/i/");
			images.put(name, value);
		} catch (Exception e) {
			throw new EncryptionFailureException();
		}
	}
	
	/**
	 * <strong><em>putString</em></strong><br /><br />
	 * 
	 * &emsp;Sets string resource.
	 * 
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static void putString(String name, String value) throws EncryptionFailureException{
		try {
			FileManager.createTextFile(path+"/c/s/"+crypt.encrypt(name)+".gsv", crypt.encrypt(value+"")+"");
			strings.put(name, value);
		} catch (Exception e) {
			throw new EncryptionFailureException();
		}
	}
	
	/**
	 * <strong><em>putInteger</em></strong><br /><br />
	 * 
	 * &emsp;Sets integer resource.
	 * 
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static void putInteger(String name, int value) throws EncryptionFailureException{
		try {
			FileManager.createTextFile(path+"/c/n/"+crypt.encrypt(name)+".gnv", crypt.encrypt(value+"")+"");
			integers.put(name, value);
		} catch (Exception e) {
			throw new EncryptionFailureException();
		}
	}
	
	/**
	 * <strong><em>putFloat</em></strong><br /><br />
	 * 
	 * &emsp;Sets float resource.
	 * 
	 * @throws EncryptionFailureException
	 * @since NEO.1
	 */
	public static void putFloat(String name, Float value) throws EncryptionFailureException{
		try {
			FileManager.createTextFile(path+"/c/f/"+crypt.encrypt(name)+".gfv", crypt.encrypt(value+"")+"");
			floats.put(name, value);
		} catch (Exception e) {
			throw new EncryptionFailureException();
		}
	}
    
    Cipher ecipher;

    Cipher dcipher;

    byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
        (byte) 0xE3, (byte) 0x03 };

    private static class DesEncrypter{
    
	    DesEncrypter(String passPhrase) {
	      
	    }
	
	    public String encrypt(String str) throws Exception {
	    	return new String(Base64.encodeBase64(str.getBytes()));
	    }
	
	    public String decrypt(String str) throws Exception {
	    	return new String(Base64.decodeBase64(str.getBytes()), "UTF-8");
	    }
    
    }
	
}
