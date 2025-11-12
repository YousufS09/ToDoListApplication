package com.todo.app ;
import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList ;
import java.util.List ;
import java.util.Iterator ;
import java.util.Scanner ;
import java.time.LocalDate ;
import java.util.Collections ;
//Below is the code for ToDolist class of ToDoList.java file
//Guide me without providing the code
public class ToDoList{
    Scanner sc = new Scanner(System.in) ;
    List<Task> GroupOfTasks = new ArrayList<>() ;

    public ToDoList(Task t){
        GroupOfTasks.add(t) ;
    }

    public ToDoList(){
        
    }

    public void addingOfTask(Task t){ 
        GroupOfTasks.add(t) ;
    }

    public boolean removingOfTask(int IDReceived){ 
        boolean taskFound = false ;
        Iterator<Task> t = GroupOfTasks.iterator() ;

        while(t.hasNext()){
            Task GroupOfTask = t.next() ;
            System.out.println(GroupOfTask) ;
            if(IDReceived == GroupOfTask.getTaskID()){
                t.remove() ;
                taskFound = true ;
                break ;
            }      
        }
        if(taskFound == true){
             return taskFound ;
        }
        return taskFound;
        // for(Task t : GroupOfTasks){
        //     if(t.getTaskID() == IDReceived){
        //         taskFound = true ;
        //     }
        //     return taskFound ;
        // }
    }

    public void displayingOfTasks(){
        if(GroupOfTasks.isEmpty())
            System.out.println("No Tasks available");
        
        else{
            System.out.println("Task Details") ; 
            for(Task task : GroupOfTasks){
                System.out.println("Task ID: " + task.getTaskID() + "\n" + "Title of Task: " + task.getTitle() + "\n" + "Description of Task: " + task.getDescription() + "\n" + "Deadline:" + task.getDueDate() + "\n" + "Status of Task: " + "Completion: "+ task.getCompleted());
            }
        }
    }
    
    public void editingOfTasks(){
        boolean taskFound = false ;
        System.out.println("Task ID to edit :") ;
        int ID = sc.nextInt() ;

        for(Task task : GroupOfTasks) {
            if(task.getTaskID() == ID){
                taskFound = true ;
                task.editTask() ;
                break ; 
            } 
        }
    } 

    public boolean searchingOfTask(){
        System.out.println("Enter the Task ID to be searched: ");
        int sID = sc.nextInt() ;
        boolean sFound = false ;
        for (Task task : GroupOfTasks){
            if(task.getTaskID() == sID){
                System.out.println(task.getTitle()) ;
                System.out.println(task.getDescription()) ;
                System.out.println(task.getDueDate());
                sFound = true ;
                break ;
            }
        }
        return sFound ;
    }

    public void filteringOfTasks(){
        System.out.println("Choose the Factor to Filter among the Following: ") ;
        System.out.println("1 - Category of the Task") ;
        System.out.println("2 - Completion Status of the Task") ;
        System.out.println("3 - OverDue Status of the Task") ;
        int op = sc.nextInt() ;
        List<Task> filteredTasks = new ArrayList<>() ;
        
        switch(op){
            case 1 :    
                System.out.println("Enter the category by which you want to filter the Task: ") ;
                System.out.println("1 - PAST") ;
                System.out.println("2 - PRESENT") ;
                System.out.println("3 - UPCOMING");     
                System.out.println("4 - NOT_ASSIGNED");
                int ch = sc.nextInt() ;                                                                     
                
                Task.Category selectedCategory ;
                
                switch(ch){
                    case 1 :
                        selectedCategory = Task.Category.PAST ;
                        for(Task tFilteredTasks : GroupOfTasks){
                            if(tFilteredTasks.getCategory() == selectedCategory)
                                filteredTasks.add(tFilteredTasks) ;
                        }
                        break ;
                    case 2 :
                        selectedCategory = Task.Category.PRESENT ;
                        for(Task tFilteredTasks : GroupOfTasks){
                            if(tFilteredTasks.getCategory() == selectedCategory)
                                filteredTasks.add(tFilteredTasks) ;
                        }
                        break ;

                    case 3 :
                        selectedCategory = Task.Category.UPCOMING ;
                        for(Task tFilteredTasks : GroupOfTasks){
                            if(tFilteredTasks.getCategory() == selectedCategory)
                                filteredTasks.add(tFilteredTasks) ;
                        }
                        break ;

                    case 4 :
                        selectedCategory = Task.Category.NOT_ASSIGNED ;
                        for(Task tFilteredTasks : GroupOfTasks){
                            if(tFilteredTasks.getCategory() == selectedCategory)
                                filteredTasks.add(tFilteredTasks) ;
                        }
                        break ;

                    default :
                        System.out.println("Invalid Option Enter correct value");
                }
                break ;
           
                case 2 :
                    int cOp ;
                    do{
                    System.out.println("Enter the Completion Status to filter the Tasks: ") ;
                    System.out.println("1 - Completed") ;
                    System.out.println("2 - Not Completed") ;
                    System.out.println("0 - Exit") ;
                    cOp = sc.nextInt() ;
                    filteredTasks.clear() ;
                    switch(cOp){
                        case 1:
                            for(Task t : GroupOfTasks){
                                if(t.getCompleted())    
                                    filteredTasks.add(t) ;
                            }    
                            break ;
                        
                        case 2:
                            for(Task t : GroupOfTasks){
                                if(!(t.getCompleted())) 
                                filteredTasks.add(t) ; 
                            }
                            
                            break ;

                        case 0 :
                            break ;
                        
                        default:
                            System.out.println("Invalid choice, Please enter 1 , 2 OR 0 to exit") ;
                    }
                    }while(cOp != 0) ;
                    break ;
            
            
                case 3 :
                    int sOp ;
                    do{
                        filteredTasks.clear() ;
                        System.out.println("Enter the Overdue Status to Sort the Tasks: ") ;
                        System.out.println("1 - Overdue") ;
                        System.out.println("2 - Not Overdue") ;
                        System.out.println("0 - Exit") ;
                        sOp = sc.nextInt() ;

                        switch(sOp){
                            case 1 :
                                for(Task t : GroupOfTasks){
                                    if(t.getDueDate().isBefore(LocalDate.now()) && t.getCompleted() == false)
                                        filteredTasks.add(t) ;
                                }
                                    break ;
                            
                            case 2 :
                                for(Task t : GroupOfTasks){
                                    if(t.getDueDate() == null && t.getCompleted() == true && (t.getDueDate().isAfter(LocalDate.now()) || t.getDueDate().isEqual(LocalDate.now()))){
                                        filteredTasks.add(t) ;
                                    }    
                                }
                                break ;

                            default:
                                System.out.println("Invalid choice. Enter 1 OR 2") ;
                        }
                    }while(sOp != 0) ;
        }   
    }

    public void sortingOfTasks(){
        if(GroupOfTasks.isEmpty())
            System.out.println("No Tasks available") ;
        
        System.out.println("1 - Sort by Due Date") ;
        System.out.println("2 - Sort by Priority") ;
        System.out.println("3 - Sort by Title") ;

        int sOp = sc.nextInt() ;

        switch(sOp){
            case 1 :
                GroupOfTasks.sort(null) ;
                break ;

            case 2 :
                System.out.println("Enter the order of Priority of Tasks: ") ;
                System.out.println("1 - Highest-to-Lowest") ;
                System.out.println("2 - Lowest-to-Highest") ;
                int pChoice = sc.nextInt() ;
                if(pChoice == 1)
                    GroupOfTasks.sort((task1 , task2) ->  task1.level.compareTo(task2.level)) ;
                
                else if(pChoice == 2)
                    GroupOfTasks.sort((task1 , task2) -> task2.level.compareTo(task1.level)) ;
                
                break ;

            case 3 :
                GroupOfTasks.sort((task1, task2) -> task1.getTitle().compareToIgnoreCase(task2.getTitle()));
                break ;

        }
    }

    public void markingTaskAsCompleted(){
        boolean taskFound = false ;
        if(GroupOfTasks.isEmpty()){
            System.out.println("No Tasks are available to be marked as completedl ");
            return ;
        }
        
        System.out.println("Enter the Task ID to be marked as completed : ") ;
        int lTaskID = sc.nextInt() ;
        
        for(Task t : GroupOfTasks){
            if(t.getCompleted() == false){
                if(lTaskID == t.getTaskID()){
                    taskFound = true ;
                    t.setCompleted() ;
                    System.out.println("Task marked as completed successfully") ;
                }
            }
            else if(t.getCompleted() == true && lTaskID == t.getTaskID())
                System.out.println("This Task is already marked as completed") ;
        }
        if(taskFound == false)
            System.out.println("No Task found with the ID") ;
    }

    public boolean isEmpty() {
        return GroupOfTasks.isEmpty();
    }

}