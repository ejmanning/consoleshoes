

import java.util.List;
import java.util.Scanner;

import controller.ShoeItemHelper;
import model.ShoeItem;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ShoeItemHelper lih = new ShoeItemHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a brand: ");
			String brand = in.nextLine();
			System.out.print("Enter a shoe type: ");
			String type = in.nextLine();
			System.out.print("Enter a color: ");
			String color = in.nextLine();
			ShoeItem toAdd = new ShoeItem(brand, color, type);
			lih.insertItem(toAdd);

		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the brand to delete: ");
			String brand = in.nextLine();
			System.out.print("Enter the shoe type to delete: ");
			String type = in.nextLine();
			System.out.print("Enter the color to delete: ");
			String color = in.nextLine();
			ShoeItem toDelete = new ShoeItem(brand, color, type);
			lih.deleteItem(toDelete);

		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Brand");
			System.out.println("2 : Search by Shoe Type");
			System.out.println("3 : Search by Color");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ShoeItem> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the brand name: ");
				String brandName = in.nextLine();
				foundItems = lih.searchForItemByBrand(brandName);
				
			} else if (searchBy == 2 ) {
				System.out.print("Enter the shoe type: ");
				String typeName = in.nextLine();
				foundItems = lih.searchForItemByType(typeName);
			} else {
				System.out.print("Enter the color: ");
				String colorName = in.nextLine();
				foundItems = lih.searchForItemByColor(colorName);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ShoeItem l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ShoeItem toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getColor() + ", " + toEdit.getType() + " from " + toEdit.getBrand());
				System.out.println("1 : Update Brand");
				System.out.println("2 : Update Type");
				System.out.println("3 : Update Color");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Brand: ");
					String newBrand = in.nextLine();
					toEdit.setBrand(newBrand);
				} else if (update == 2) {
					System.out.print("New Type: ");
					String newType = in.nextLine();
					toEdit.setShoeType(newType);
				} else if (update == 3) {
					System.out.print("New Color: ");
					String newColor = in.nextLine();
					toEdit.setColor(newColor);
				}

				lih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ShoeItem> allItems = lih.showAllItems();
			for(ShoeItem singleItem : allItems) {
				System.out.println(singleItem.returnShoeDetails());
			}
			
		}

	}
