package main.java;

import java.io.*;
import java.util.*;

/**
 * 
 * @author vijay
 *
 * PhoneDirectory is a class for a simple booking keeping process. It contains location of text file, name and number as its field
 * while it has few methods to add, delete, and update the entries. User can take a text file, and make some changes to it. 
 * The constructor requires a location of a file and later we have a "write" method which writes the all the changes to the text file
 */
public class PhoneDirectory{
	/**
	 * 'location' is a string that stores the location of the text file
	 * 'name' is the variable to store the name of a person while 'number' is the phone number of the person
	 * I am using a table of type Hashtable which acts as a dictionary for this class. I have used the table to store all the names and phone number of 
	 * corresponding persons. So, basically, I am loading everything from a text file and storing the content to the table. A user will make all the changes to
	 * the table and later I am "overwriting" all the content of the table to the same text file in order to update it. 
	 */
	String location, name, number;
	
	/**
	 * I am using "separator" and "delimiters" as two fields of this class. Turned out, I need these two variables to overload the constructor that takes two
	 * parameters, fileLocation and newSeparator. "delimiters" is useful for ".split(regex)" method I am using to split the name and the phone number. 
	 * Currently, I am just using three different separators, "-", "|" and ",". 
	 */
	String separator = ",";
	String delimiters = ",|\\|";
	Hashtable table = new Hashtable();
	
	/**
	 * This is the constructor of the class PhoneDirectory. It takes one parameter called "newLocation". "newLocation" is the location to a text file that 
	 * contains the name and the phone numbers of the people.
	 * Here, the constructor is loading all the content of the text file to the table (Hashtable). 
	 * @param newLocation
	 */
	public PhoneDirectory(String newLocation){
		location = newLocation;
		
		String line = null;
		
		try{
			FileReader fileReader = new FileReader(location);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null){
				String[] temp = line.split(delimiters);
				name = temp[0];
				number = temp[1];
				table.put(name, number);
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Didn't find the file");
		}
		catch(IOException ex){
			System.out.println("Error reading the file");
		}
	}
	
	public PhoneDirectory(String newLocation, String newSeparator){
		location = newLocation;
		
		String line = null;
		
		try{
			FileReader fileReader = new FileReader(location);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null){
				String[] temp = line.split(delimiters);
				name = temp[0];
				number = temp[1];
				table.put(name, number);
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Didn't find the file");
		}
		catch(IOException ex){
			System.out.println("Error reading the file");
		}
		separator = newSeparator;
		
	}
	
	/**
	 * This method takes two parameters, name and number, both of String type. It simply add new name and corresponding phone number to the table.
	 * The method is adding values to the table variable/field only. The text added entry has not been added to the text file yet.
	 * @param name
	 * @param number
	 */
	public void addEntry(String name, String number){
		if (table.get(name) == null){
			table.put(name, number);
		}
		else {
			System.out.println("Already in the directory!");
		}
	}
	
	/**
	 * This method simply delete an entry from the table. Again, the method is only acting to the table variable only. If the intended entry is not in the table 
	 * then the method throws a message saying "Not in the directory".
	 * @param name
	 */
	public void deleteEntry(String name){
		if (table.get(name) != null){
			table.remove(name);
		}
		else{
			System.out.println("Not in the directory");
		}
	}
	
	/**
	 * This a return-type method that returns the phone number of a person. It takes a parameter called name of String type. If the name is not in the table,
	 * the method will throws a message saying "Not in the directory". Again, the method is acting on the table variable/field only.
	 * @param name
	 * @return
	 */
	public String getNumber(String name){
		String temp = (String) table.get(name);
		if (temp != null){
			return temp;
		}
		else{
			return "Not in the directory!";
		}
	}
	
	/**
	 * This method simply updates the directory. It takes two parameters, name and number. Again, it only updates the table field only, meaning it's not 
	 * updating the text file yet.
	 * @param name
	 * @param number
	 */
	public void changeEntry(String name, String number){
		table.put(name, number);
	}
	
	/**
	 * This is an important method for this class. As you can see, all the previous methods, changeEntry, addEntry, deleteEntry are acting on the table only 
	 * while this one acts on the text file from where I read and populate the table. The constructor is taking a parameter called location. I stored that 
	 * location in a field called "location". The table read the content of the text file, and a user might have made some changes to the table, even added or deleted 
	 * some entries. I am writing all the content of the table to the text file via this method. I have to overwrite the file to update the content of the file.
	 * This method, in a sense, "dumps" all the changes a user might have intended to make to the text file. Hence, "overwriting" was crucial rather than 
	 * appending changes to the file. 
	 */
	public void write(){
		Enumeration name_keys;
		name_keys = table.keys();
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		String fileName = location;
		try{
			File file = new File(fileName);
			
			if (!file.exists()){
				file.createNewFile();
			}
			
			fWriter = new FileWriter(file, false);
			writer = new BufferedWriter(fWriter);
			
			while(name_keys.hasMoreElements()){
				String temp_name = (String) name_keys.nextElement();
				writer.write(temp_name + separator);
				writer.write(table.get(temp_name) + "\n");
			}
			writer.close();
			
		}
		catch(IOException ex){
			System.out.println("Error");
		}
		
	}
	
	/**
	 * This method simply prints all the content of the table. I am using it for debugging purpose. 
	 */
	public void printState(){
		String str;
		Enumeration temps;
		temps = table.keys();
		while(temps.hasMoreElements()){
			str = (String) temps.nextElement();
			System.out.println("Name: " + str + " Phone Number: " + table.get(str));
		}
		System.out.println();
	}
	
}