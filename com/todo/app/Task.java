package com.todo.app;
//Guide me without providing the code
//Below is the code for Task class of Task.java file
import java.util.Scanner ;
import java.time.LocalDate ;
import java.time.format.DateTimeFormatter ;
import java.time.format.DateTimeParseException ;
import java.util.InputMismatchException ;

public class Task implements Comparable<Task>{
    static private int taskID = 1 ;
    private int IDOfATask ;
    private String taskTitle ;
    private String taskDescription ;
    private LocalDate dueDate ;
    private boolean isCompleted ;   
    private boolean overDue ;

    Scanner sc = new Scanner(System.in) ;
    
    public Task(){
        this.IDOfATask = taskID++ ;
        this.isCompleted = false ;
        this.taskTitle = "NewTask" ;
        this.taskDescription = " " ;
        this.dueDate = null ;
        this.overDue = false ;
        this.levelOfCategory = null ;
    }

    public int getTaskID(){
        return IDOfATask ;
    }
 
    public void setCompleted(){//marksAsCompleted
        System.out.println("Do you want the Task to be marked as completed(Y/N)?") ; 
        String completedStatus = sc.nextLine() ;
        completedStatus = completedStatus.trim() ;

        if(completedStatus.equalsIgnoreCase("Y")){
            this.isCompleted = true ;
            System.out.println("Task has been completed");
        }
                
        else if (completedStatus.equalsIgnoreCase("N")){
            this.isCompleted = false ;
            System.out.println("Task is not Completed Yet");
        }

        else if((dueDate.isBefore(LocalDate.now()) && isCompleted == false))
            overDue() ;
    }
        
    public boolean getCompleted(){//isTaskCompleted
        return isCompleted ;
    }

    public void overDue(){
        if(dueDate == null)
            System.out.println("Due Date is not yet assigned") ;

        else if(dueDate.isBefore(LocalDate.now())){
            System.out.println("Task is Overdue But not marked complete") ;
            this.overDue = true ;
        }
        
        else if(dueDate.isEqual(LocalDate.now())){
            System.out.println("Deadline of the Task is Today");
            this.overDue = false ;
        }
            
        else if(dueDate.isAfter(LocalDate.now())){
            System.out.println("Task is not Overdue");
            this.overDue = false ;
        }
    }

    public void setTitle(){
        System.out.println("Enter the title for the Task :"); 
        taskTitle = sc.nextLine() ;
    }

    public String getTitle(){
        return taskTitle ;
    }

    public void setDescription(){
        System.out.println("Enter the description for the Task: ");
        taskDescription = sc.nextLine() ;
    }

    public String getDescription(){
        return taskDescription ;
    }

    public void setDueDate(){
        while(dueDate == null){
            System.out.println("Enter the Deadline for the Task :") ;
            String userDeadline = sc.nextLine() ;
            DateTimeFormatter deadline = DateTimeFormatter.ofPattern("dd/MM/yyyy") ; 
            
            try{
                LocalDate userDeadlineUpdated = LocalDate.parse(userDeadline , deadline) ;
                this.dueDate = userDeadlineUpdated ;
            }
            
            catch(DateTimeParseException e){
                System.out.println("You have entered an incorrect Date Format. Kindly enter the Date in dd/MM/yyyy format.") ;
            }
        }
    }
    
    public LocalDate getDueDate(){
            return this.dueDate ;
        }

    public String toString(){
        // if(isCompleted == true)
        //     return "Completed" ;
        // else if(isCompleted == false)
        //     return "Not Completed" ;
        return "Title: " + taskTitle + "\n" +
        "Description: " + taskDescription + "\n" +
        "Deadline: " + (dueDate == null ? "Not Assigned" : dueDate.format(DateTimeFormatter.ofPattern("dd MM yyyy"))) + "\n" +
        "Status: " + (isCompleted ? "Completed" : "Not Completed") + "\n" +
        "OverDue: " + (dueDate == null ? "Not Applicable" : ((!isCompleted && dueDate.isBefore(LocalDate.now())) ? "No": "Yes")) + "\n" +
        "Category: " + (levelOfCategory == null ? "Not Assigned" : levelOfCategory) ;
    }

    enum CompletionStatus{
            COMPLETED,
            NOT_COMPLETED;
    }
    
    CompletionStatus CStatus ;

    enum OverdueStatus{
        OVERDUE ,
        NOT_OVERDUE ;
    }

    OverdueStatus OStatus ;

    enum Priority{
        HIGHEST ,
        MODERATE ,
        LOWEST ; 
    }

    Priority level ;//Priority.HIGHEST ;

    public void setPriority(){
        System.out.println("Select the Priority for the Task");
        System.out.println("1 - Highest Priority") ;
        System.out.println("2 - Moderate Priority") ;
        System.out.println("3 - Lowest Priority") ;
        
        boolean isSet = false ;
        while(!isSet){
            try{
                int option = sc.nextInt() ;
                switch(option){
                case 1 :
                    level = Priority.HIGHEST ;
                    System.out.println("The Task has been assigned Highest Priority");
                    isSet = true ;
                    break ;
                
                case 2 :
                    level = Priority.MODERATE ;
                    System.out.println("The Task has been assigned Moderate Priority") ;
                    isSet = true ;
                    break ;
                
                case 3 :
                    level = Priority.LOWEST ;
                    System.out.println("The Task has been asssigned the Lowest Priority") ;
                    isSet = true ;
                    break ;
                
                default:
                    System.out.println("You have entered an invalid option. Enter a valid option.") ;            
                }
            }
            catch(InputMismatchException e){
                sc.nextLine() ;
                System.out.println("Please enter a valid number(1-3)");
            }
        }     
    }

    public String getPriority(){
        String priorityOfTask = level.name() ; 
        return priorityOfTask;
    }

    enum Category{
        NOT_ASSIGNED,
        PAST,
        PRESENT,
        UPCOMING; 
    }

    Category levelOfCategory ;

    public void setCategory(){ 
        if(dueDate == null)
            levelOfCategory = Category.NOT_ASSIGNED ;
        
        else if(dueDate.isBefore(LocalDate.now()))
            levelOfCategory = Category.PAST ;
        
        else if(dueDate.isAfter(LocalDate.now()))
            levelOfCategory = Category.UPCOMING ;
    
        else if(dueDate.isEqual(LocalDate.now()))
            levelOfCategory = Category.PRESENT ;
    } 

    public Category getCategory(){
        return levelOfCategory ;
    }

    public void editTask(){
        System.out.println("Choose what would you want to edit from the following :") ;
        System.out.println("1 - Title of the Task") ;
        System.out.println("2 - Description of the Task") ;
        System.out.println("3 - Deadline of the Task") ;
        System.out.println("4 - Priority of Tasks") ;
        System.out.println("5 - Category") ;
        System.out.println("6 - Exit") ;

        int editChoice = sc.nextInt() ;
        System.out.println() ;
        System.out.println();
        switch(editChoice){
            case 1 :
                System.out.println();
                System.out.println("Enter a new Title for the Task : ") ;
                this.taskTitle = sc.nextLine() ;
                System.out.println("Title Changed  Successfully to " + taskTitle) ;
                System.out.println();
                break ;
            
            case 2 :
                System.out.println("Enter a new Description for the Task : ");
                this.taskDescription = sc.nextLine() ;
                System.out.println("Description Changed Successfully to " + taskDescription) ;
                break ;
            
            case 3 :
                System.out.println("Enter the Updated Deadline : ") ;
                String updatedDueDate = sc.nextLine() ;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
                try{
                    LocalDate updatedDeadlineWithFormat = LocalDate.parse(updatedDueDate , formatter) ;
                    this.dueDate = updatedDeadlineWithFormat ;
                    System.out.println("Deadline has been Changed Successfully to " + updatedDeadlineWithFormat) ;
                }
                catch(DateTimeParseException e){
                    System.out.println("Incorrect Date Format has been entered. Kindly enter the correct format.");
                }
                setCategory();
                overDue() ;
                break ;
            
            case 4 :
                System.out.println("Priority Changed  Successfully") ;
                System.out.println("Enter the new priority for the Task :") ;
                sc.nextInt() ;
                setPriority();
                break ;
            
            case 5 :
                System.out.println("Category Changed  Successfully") ;
                break ;

            case 6 :
                System.out.println("Are you sure you want to Exit?") ;
                break ;
            
            default : 
                System.out.println("You have entered a wrong choice.Enter the Correct Choice.");
        }
    }

    public int compareTo(Task other){
        if(this.dueDate == null && other.dueDate == null)
            return 0 ;

        else if(other.dueDate == null)
            return -1 ;
        
        else if(this.dueDate == null)
            return 1 ;

        else 
            return this.dueDate.compareTo(other.dueDate) ;
              
    }
}