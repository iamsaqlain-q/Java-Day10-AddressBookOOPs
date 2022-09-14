package com.addressbook.controller;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
	
	public static void main(String[] args) {
		
		ArrayList<Contacts> contactList = new ArrayList<>();
		System.out.println("*****Welcome to My Address Book*****");
		Character ch = 'a';
		Scanner sc = new Scanner(System.in);
		AddressBookServices con = new AddressBookServImpl();
		
		while(!ch.equals('q')) {
			
			 System.out.print("\n a) Add \n d) Delete \n e) Edit \n s) Show \n q) Quit \nEnter a character: ");
		      ch = sc.next().charAt(0);
		      
		      switch (ch) {
		        case 'a':
		            Contacts c = con.createContact();
		        	contactList.add(c);
		        	//con.displayContact(c);
		            break;
		          
		        case 'e' :
		        	 con.editContact(contactList);
		        	 System.out.println("Contact has been edited");
		        	 break;
		        	 
		        case 'd' :
		        	 con.deleteContact(contactList);
		        	 break;
		        	 
		        case 's' : 
		        	System.out.println("Added person's details : ");
		        	con.displayContact();
		        	
		        	break;

		        case 'q':
		        	 break;

		        default:
		          System.out.println("Please enter a valid character");
		          break;

		      }
		}
		
		sc.close();
	}

}

interface AddressBookServices {
	
	public Contacts createContact();
	public void displayContact();
	public void editContact(ArrayList<Contacts> contactList);
	public void deleteContact(ArrayList<Contacts> contactList);
	
}

class AddressBookServImpl implements AddressBookServices {
	
	Scanner sc = new Scanner(System.in);
	
	@Override
	public Contacts createContact() {
		
		Contacts con = new Contacts();
	
		System.out.println("Enter First Name : ");
		con.setFirstN(sc.nextLine());
		
		System.out.println("Enter Last Name : ");
		con.setLastN(sc.nextLine());
		
		System.out.println("Address : ");
		con.setAddress(sc.nextLine());
		
		System.out.println("City : ");
		con.setCity(sc.nextLine());
		
		System.out.println("State : ");
		con.setState(sc.nextLine());
		
		System.out.println("E-mail : ");
		con.setEmail(sc.nextLine());
		
		return con;
	}
	
	@Override
	public void displayContact() {
		
		Contacts con = new Contacts();
		
		System.out.println("First Name : " + con.getFirstN());
		System.out.println("Last Name : " + con.getLastN());
		System.out.println("Address : " + con.getAddress());
		System.out.println("City : " + con.getCity());
		System.out.println("State : " + con.getState());
		System.out.println("E-mail : " + con.getEmail());
	}

	@Override
	public void editContact(ArrayList<Contacts> contactList) {
		if(contactList.size() == 0)
		{
			System.out.println("First add a name");
			return;
		}
		System.out.print("Your existing contacts are : " );
		for(Contacts c: contactList) {
			System.out.print(c.getFirstN() + " ");
		}
		
		System.out.print("Enter name of contact you wanna edit : ");
		String firstN = sc.nextLine(); 
		
		int index = checkContact(contactList, firstN);
		if(index == -1)
		{
			System.out.println("name not found");
		
		}else {
			
			contactList.set(index, createContact());
		}
	      }

	private int checkContact(ArrayList<Contacts> contactList, String name) {
		for(int i = 0; i < contactList.size(); i++) {
			if(contactList.get(i).firstN.equals(name))
			return i;
		}
		
		return -1;
	}
	
	@Override
	public void deleteContact(ArrayList<Contacts> contactList) {
		if(contactList.size() == 0)
		{
			System.out.println("First add a contact");
			return;
		}
		System.out.print("Your existing contacts are : " );
		for(Contacts c: contactList) {
			System.out.print(c.getFirstN() + " ");
		}
		
		System.out.print("Enter name of contact you wanna delete : ");
		String firstN = sc.nextLine(); 
		
		int index = checkContact(contactList, firstN);
		if(index == -1)
		{
			System.out.println("name not found");
		
		}else {
			
			contactList.remove(index);
			System.out.println("Contact " + firstN + " has been deleted");
		}
	      }
		
}
	