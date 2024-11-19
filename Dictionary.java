import java.util.*;
import java.io.*;

class Dictionary{
    HashMap<String, String> hm = new HashMap<String, String>(); 
    Scanner s;
    String word,meaning; 

    Dictionary(){
        s= new Scanner(System.in);
    }

    void readData(){
        try  
        {  
            File file=new File("dictionary.txt"); 
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr); 
            String line; 
 
            while((line=br.readLine())!=null)  
            {  
                hm.put(line.substring(0,line.indexOf(":")),line.substring(line.indexOf(":"))); 
            }  
            fr.close(); 
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
    }

    void insertWord(){
        word= s.nextLine();
        meaning=s.nextLine();
        meaning=": "+meaning;

        if(hm.containsKey(word))
            System.out.println("Word already exists in the dictionary");

        else
        {
            hm.put(word,meaning);
            String sentence= word + meaning+"\n";
            try { 
                BufferedWriter out = new BufferedWriter(new FileWriter("dictionary.txt", true)); 
                out.write(sentence);
                out.close(); 
                System.out.println(word+" successfully added to the dictionary"); 
            } 
            catch (IOException e) { 
                System.out.println("exception occoured" + e); 
            } 
        }
    }

    void searchWord(){
        word=s.nextLine();
        if(hm.containsKey(word.trim()))
            System.out.println("The meaning of the word "+ word+" is"+hm.get(word.trim()));
        else    
            System.out.println("The word is not present in the dictionary");
    }

    String fileWriteContent(){
        String sentence="";
        Iterator hmIterator= hm.entrySet().iterator();
        while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            String meaning = (String.valueOf(mapElement.getValue()));
            sentence+= mapElement.getKey() + meaning + "\n";
        }
        return sentence;
    }

    void writeChanges(){
        try { 
                BufferedWriter out = new BufferedWriter(new FileWriter("dictionary.txt"));
                String sentence =  fileWriteContent();
                out.write(sentence); 
                out.close(); 
            } 
            catch (IOException e) { 
                System.out.println("exception occoured" + e); 
            }
    }

    void updateWord(){
        word= s.nextLine();
        meaning=s.nextLine();
        meaning=": "+meaning;
        if(hm.containsKey(word)){
            hm.replace(word.trim(),meaning);
            writeChanges();
            System.out.println("The meaning of the word "+word+" is"+meaning);
        }
        else    
            System.out.println("The word "+word+" is not present in the dictionary hence can't be updated");
    }

    void deleteWord(){
        word=s.nextLine();
        String removed;
        if(hm.containsKey(word)){
            removed=(String)hm.remove(word.trim());
            writeChanges();
            System.out.println("The word "+word+" has been deleted from the dictionary");
        }
        else{
            System.out.println("There is so such word as "+word+" in the dictionary");
        }
    }


    void displayDictionary(){
   
        Iterator hmIterator= hm.entrySet().iterator();

        while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            String meaning = (String.valueOf(mapElement.getValue()));
            System.out.println(mapElement.getKey() + meaning);
        }
    }

    // displaying the menu
    void printMenu(){
        System.out.println("-------------------------------");
        System.out.println("\tMenu\t");
        System.out.println("1. Insert a word into the dictionary");
        System.out.println("2. Search a word from the dictionary");
        System.out.println("3. Update the meaning of a word");        
        System.out.println("4. Delete a word and its meaning from the dictionary");
        System.out.println("5. Display the Dictionary");
        System.out.println("6. Exit");
        System.out.println("-------------------------------");
    }




    public static void main(String args[]){
        Dictionary dict = new Dictionary();
        dict.readData();

        Scanner sc = new Scanner(System.in);
        
        int choice;

        while(true){
            dict.printMenu();
            System.out.println("Enter your choice:");
            

            while(!sc.hasNextInt()){
                System.out.println("Error! Please enter a valid integer");
                sc.next();
            }
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter the word and its meaning:");
                    dict.insertWord();
                    break;
                case 2:
                    System.out.println("Enter the word for which its meaning is to be searched:");
                    dict.searchWord();
                    break;
                case 3:
                    System.out.println("Enter the word and its meaning:");
                    dict.updateWord();
                    break;
                case 4:
                    System.out.println("Enter the word to be deleted:");
                    dict.deleteWord();
                    break;
                case 5:
                    dict.displayDictionary();
                    break;
                case 6:
                    System.out.println("-----Exiting-----");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice! Please choose a valid option");
            }
        }
    }
}
