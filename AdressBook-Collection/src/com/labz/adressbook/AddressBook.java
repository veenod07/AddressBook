package com.labz.adressbook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {
	private static Scanner scan = new Scanner(System.in);
    static ArrayList<ContactPerson> personList = new ArrayList<>();
    Map<String,ArrayList<ContactPerson>> addressBook = new HashMap<String, ArrayList<ContactPerson>>();

    public void addDetails() {
        ContactPerson person = new ContactPerson();
        var wrapper = new Object() {boolean flag=true;};
        System.out.print("\nEnter Existing Book name or New Book Name to add contact : ");
        String bookName  = scan.next();

        if(addressBook.containsKey(bookName)) {
            System.out.println("Contact will be added to existing '"+bookName+"' Book");
        } else {
            System.out.println("New Address Book Created with the Name '"+bookName+"'");
        }
        System.out.println("Enter the number of contacts you want to enter");
		int number = scan.nextInt();

		for (int i = 0; i < number; i++) {
			System.out.println("Enter the contact details of person ");

			System.out.print("\nEnter First name : ");
			String firstName=scan.next();
			person.setFirstName(firstName);

			System.out.print("Enter Last name : ");
			String lastName=scan.next();
			person.setLastName(lastName);


        // Ensuring there is no Duplicate Entry of the same Person in a Address Book
			try {
				personList.stream().filter(contactList -> contactList.getFirstName().equals(firstName)&&contactList.getLastName().equals(lastName)).forEach(contactList -> {
					System.out.println("Can not allow Duplicate Contact");
					addDetails();
					wrapper.flag = false;
				});
			} catch(Exception e) {
				System.out.println();
			}

			while(wrapper.flag) {

				System.out.print("Enter Address : ");
				person.setAddress(scan.next());

				System.out.print("Enter City name : ");
				person.setCity(scan.next());

				System.out.print("Enter State name : ");
				person.setState(scan.next());

				System.out.print("Enter ZIP Code : ");
				person.setZip(scan.nextInt());;

				System.out.print("Enter Phone Number : ");
				person.setPhonenumber(scan.nextLong());

				System.out.print("Enter E-Mail ID : ");
				person.setEmail(scan.next());

            // Adding the details into list
				personList.add(person);
				addressBook.put(bookName,personList);
				System.out.println("\nGiven Details are added into the Book");
				wrapper.flag = false;
			}
		}		
    }

    
    private int searchName() {
        System.out.println("\nEnter the First name of person: ");
        String searchName=scan.next();
        for (int i=0; i<personList.size(); i++) {
            if (personList.get(i).getFirstName().equals(searchName)) {
                return i;
            } else if (i==personList.size()-1) {
                System.out.println("Given name is not found in the Book");
            }
        }
        return personList.size();
    }

   
   public void editDetails() {
        int index = searchName();
        if (index!=personList.size()) {
            ContactPerson details = personList.get(index);
            System.out.println(details.toString());
            System.out.println("\nEnter slot number that to be edit from above");
            int slotnumber = scan.nextInt();
            while(slotnumber<10) {
                switch (slotnumber) {
                    case 0: slotnumber=10;
                        break;
                    case 1: System.out.print("\nEnter First name : ");
                        details.setFirstName(scan.next());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 2: System.out.print("\nEnter Last name : ");
                        details.setLastName(scan.next());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 3: System.out.print("Enter Address : ");
                        details.setAddress(scan.next());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 4: System.out.print("Enter City name : ");
                        details.setCity(scan.next());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 5: System.out.println("Enter State name : ");
                        details.setState(scan.next());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 6: System.out.println("Enter ZIP Code : ");
                        details.setZip(scan.nextInt());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 7: System.out.print("Enter Phone Number : ");
                        details.setPhonenumber(scan.nextLong());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    case 8:	System.out.print("Enter E-Mail ID : ");
                        details.setEmail(scan.next());
                        System.out.println("\nEnter other slot number to Edit or Press'0' to Exit");
                        slotnumber=scan.nextInt();
                        break;
                    default: System.out.println("Enter valid input");
                }
            }
            System.out.println("After Updating:"+details.toString());
        }
    }
    
   public void deleteDetails() {
        int index = searchName();
        if (index!=personList.size()) {
            personList.remove(index);
            System.out.println("Given name details were deleted from the list");
        }
    }

   
    public void showDetails() {
        int index = searchName();
        if (index!=personList.size()) {
            ContactPerson person = personList.get(index);
            System.out.println(person.toString());
        }
    }

    
    public void contactBycity() {
        System.out.println("Enter Name of City or State to get Contact List : ");
        String nameCityState = scan.next();
        try {
            System.out.print("\nContact list of persons across '"+nameCityState+"' is");
            personList.stream().filter(contactList -> contactList.getCity().equals(nameCityState)||contactList.getState().equals(nameCityState)).forEach(contactList -> {
                System.out.println(contactList.getFirstName()+" "+contactList.getLastName());
            });
        }catch(Exception e) {
            System.out.println("Exception occured while getting Contact List");
        }
    }

    
    public void displayContacts() {
        try {
            System.out.println("\nContacts in AddessBook:");
            personList.stream().forEach(contactList -> {
                System.out.println(contactList.getFirstName()+" "+contactList.getLastName());
            });
        }catch(Exception e) {
            System.out.println("Exception occured while getting Contact List");
        }
    }

    
    public void contactsCount() {
        System.out.println("Enter Name of City or State to get count of Contacts across city or state");
        String nameCityState = scan.next();
        var wrapper = new Object() {int count=0;};
        personList.stream().filter(contactList -> contactList.getCity().equals(nameCityState)||contactList.getState().equals(nameCityState)).forEach(contactList -> {
            wrapper.count++;
        });
        System.out.println("Number of contact persons in "+nameCityState+" is : "+wrapper.count);
    }
    
    
    public void sorting() {
     Scanner sc=new Scanner(System.in);
        System.out.println("1. View By first name\n"+"2. View By city\n"+"3. View By state\n"+"4. View by Zip code"+"5. Back");
        System.out.print("Enter Your choice: ");
        int userInput= sc.nextInt();
        switch (userInput){
            case 1:sortByFirstName();
                   break;
            case 2:sortByCity();
                break;
            case 3:sortByState();
                break;
            case 4:sortByZip();
                break;
            case 5:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
    public void sortByFirstName() {
        addressBook.keySet().forEach((String name) -> {
            addressBook.get(name).stream().sorted(Comparator.comparing(ContactPerson::getFirstName))
                    .collect(Collectors.toList()).forEach(person -> System.out.println(person.toString()));
        });
    }
    public void sortByCity() {
        addressBook.keySet().forEach((String name) -> {
            addressBook.get(name).stream().sorted(Comparator.comparing(ContactPerson::getCity))
                    .collect(Collectors.toList()).forEach(person -> System.out.println(person.toString()));
        });
    }
    public void sortByState() {
        addressBook.keySet().forEach((String name) -> {
            addressBook.get(name).stream().sorted(Comparator.comparing(ContactPerson::getState))
                    .collect(Collectors.toList()).forEach(person -> System.out.println(person.toString()));
        });
    }
    public void sortByZip() {
        addressBook.keySet().forEach((String name) -> {
            addressBook.get(name).stream().sorted(Comparator.comparing(ContactPerson::getZip))
                    .collect(Collectors.toList()).forEach(person -> System.out.println(person.toString()));
        });
    }
    
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        boolean choice = true;
        while (choice) {
            System.out.println("\nChoose the option:\nPress '1' for Add Contact Details\nPress '2' for Edit Contact Deatils"
                    + "\nPress '3' for Delete Contact Details\nPress '4' for Show Contact Details\nPress '5' for Contact list by City State"
                    + "\nPress '6' for Show Contact list of Persons\nPress '7' for Count of contacts by City State\nPress '8' for Sort Contacts By Name"
                    + "\nPress '9' File Read and write operation"
                    + "\nPress '0' for Exit");
            int option = scan.nextInt();
            switch (option) {
                case 0:
                    choice = false;
                    System.out.println("Exited out of AddressBook");
                    break;
                case 1:
                    addressBook.addDetails();
                    break;
                case 2:
                    addressBook.editDetails();
                    break;
                case 3:
                    addressBook.deleteDetails();
                    break;
                case 4:
                    addressBook.showDetails();
                    break;
                case 5:
                    addressBook.contactBycity();
                    break;
                case 6:
                    addressBook.displayContacts();
                    break;
                case 7:
                    addressBook.contactsCount();
                    break;
                case 8:
                    addressBook.sorting();
                    break;
                case 9: 
                    File_read_and_write();
                break;   
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }
	
	private static void File_read_and_write() {
        AddressBookIO.createfile();
		String input = personList.toString();
        AddressBookIO.add_details_to_file(input);
        AddressBookIO addressBookIO = new AddressBookIO();
		addressBookIO.read_details_to_file();
    }
}