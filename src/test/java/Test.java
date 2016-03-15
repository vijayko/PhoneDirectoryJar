package test.java;

import main.java.PhoneDirectory;

public class Test{
	public static void main(String[] args){
		String fileLocation = "/Users/vijay/Code/PhoneDirectoryJar/src/main/java/resources/phone.txt";
		PhoneDirectory phoneBook = new PhoneDirectory(fileLocation, ",");
		
		phoneBook.addEntry("John", "2025678901");
		phoneBook.addEntry("Anu", "5718907890");
		phoneBook.changeEntry("Anu", "1245690909");
		phoneBook.changeEntry("John", "5712792121");
		phoneBook.addEntry("Biswa", "9091235678");
		phoneBook.addEntry("Mohit", "34520837468");
		phoneBook.addEntry("Shahid", "4238784609");
		System.out.println(phoneBook.getNumber("Biswa"));
		phoneBook.deleteEntry("John");
		phoneBook.deleteEntry("ashok");
		
		System.out.println();
		
		phoneBook.printState();
		phoneBook.write();
			
	}
}