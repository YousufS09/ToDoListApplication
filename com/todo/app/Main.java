package com.todo.app;
//Below is the code for Main class of Main.java file
//Guide me without providing the code
import java.util.Scanner ;

// import java.time.LocalDate; 
// import java.time.format.DateTimeFormatter ;
// import java.time.format.DateTimeParseException ; 
public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in) ;

        ToDoList to1 = new ToDoList() ;
        boolean running = true ;
        while(running){
            System.out.println("1 - Add a new Task") ;
            System.out.println("2 - Edit a Task") ;
            System.out.println("3 - Sort Tasks") ;
            System.out.println("4 - View Tasks") ;
            System.out.println("5 - Exit") ; 
            int mOp = sc.nextInt() ;
            sc.nextLine() ;
        
            switch(mOp){
                case 1 : 
                    Task t1 ;
                    System.out.println("Do you want to add a New Task(Y OR N)?") ;
                    String c ;
                    
                    while(true){
                        c = sc.nextLine() ;
                        c = c.trim().toUpperCase() ;

                        if(c.equals("Y")){
                            t1 = new Task() ;
                            t1.setTitle() ;
                            t1.setDescription() ;
                            t1.setDueDate() ;
                            t1.setPriority() ;
                            t1.setCategory() ;
                            t1.setCompleted() ;
                            to1.addingOfTask(t1) ;

                            System.out.println("Do you want to add another Task(Y/N)?") ;
                        }

                        else if(c.equals("N")){
                            running = false ;
                            break ;
                            
                        }
                            

                        else
                            System.out.println("Invalid Input! Kindly enter Y OR N") ;
                    }
                    break ;

                case 2 :
                    to1.editingOfTasks() ;
                    break ;

                case 3 :
                    to1.sortingOfTasks() ;
                    break ;

                case 4 :
                    if(to1.isEmpty())
                        System.out.println("No Tasks  Available") ;
                    
                    else
                        to1.displayingOfTasks();
    
                    break;

                case 5 :
                    running = false ;
                    break;

                default : 
                    System.out.println("Invalid choice! Kindly enter 1 - 5") ;
            }
        }
        // System.out.println("Enter the title of the task : ") ;
        // String taskTitleInput = sc.nextLine() ;
        
        // System.out.println("Enter the description for this task : ") ;
        // String taskDescriptionInput = sc.nextLine() ; 
        
        // System.out.println("Enter the Due Date for this task(dd/MM/yyyy)") ;
        // String dt = sc.nextLine() ;
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
        // try{
        //     LocalDate date = LocalDate.parse(dt , formatter) ;
        //     System.out.println("You entered : " + date) ;
        // }
        // catch(DateTimeParseException e){
        //     System.out.println("Invalid Date Format. Kindly enter the date in dd/MM/yyyy format.") ;
        // }
        sc.close() ;
        
    }
}
