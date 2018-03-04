import java.util.LinkedList ;
import java.util.Random ; 

public class ProcessMgmt 
{
	public static void main(String args[]) 	
	{
		int QREADY__T	= 25 ;		
		final int BLOCKIO	= 3 ;		final int BLOCKPAGE	= 4 ;		
		final int INTERRUPT	= 2 ;		final int COMPLETED	= 1 ; 
				
		Random random__X	= new Random();
		CPU_event event		= new CPU_event();
		
		int CPU_runtime ;		int event__X = 0;
 		LinkedList<PCB> QReady       = new LinkedList<PCB>();   //#005 Create the List for the Priority QReady
      
       		PCB PCB_Running ;        
      		PCB PCB_Ready;		
		
		for (int ii = 0; ii < QREADY__T; ii++)
		{
			QReady.add(new PCB()) ;       
 
		}
		
		System.out.println("\n*****\t\t\tReady Queue\t\t\t*****");  
		 for(PCB pcbLoop : QReady)		
			System.out.println(pcbLoop.showPCB());
  
		
		while ( QReady.size()>0)	
		{
			 PCB_Running = QReady.removeFirst();           
           		 PCB_Running.set_state("Running") ;           
			
			CPU_runtime	= random__X.nextInt(20) + 1 ;	
			PCB_Running.set_CPU_used(CPU_runtime) ;



			System.out.println("\n*****\t\t\tRunning Queue\t\t\t*****");   	  
			System.out.println(PCB_Running.showPCB());
	
			  for (PCB pcbLoop : QReady)
          		 {
               		   pcbLoop.set_timeWaiting(CPU_runtime);
           }				
					
			  if (PCB_Running.get_CPU_used() > PCB_Running.get_CPU_max())
           			{
               			PCB_Running.set_state("Completed");
              
               			System.out.println("\n>>>>>\tProcess\t"
                      				 + PCB_Running.showPCB()
                      				 + "\t<<<<<" );
              
               			continue;   
           }
			
			event__X	= event.get_CPU_event() ;
			
			if (event__X == COMPLETED)
			{
				System.out.println("\n*****\t\t\tProcess Completed\t\t\t*****CPU event*****");   	  
				System.out.println(PCB_Running.showPCB());		
			}
			else
			{
				PCB_Running.set_state("Ready");
			
				switch (event__X)
				{
					case INTERRUPT :
					{
					  QReady.addFirst(PCB_Running) ;   
                       			  System.out.println("*****\t\t\tContext Switch\tINTERRUPT event\t\t\t*****");
                       			  Random ran = new Random();
                       			  int random = ran.nextInt(500)+1;

                       			  int[] z =  ran.ints(1,500).limit(10).toArray();                       			  DiskScheduler y = new DiskScheduler();
                       			  DiskScheduler sstf = new DiskScheduler();
                       			  int[]s = sstf.getSftf(z,random);
                       			  System.out.println("The first request is: " + random);
                       			  for(int i = 0; i<s.length;i++)
                       			  {
                       				  System.out.print(s[i]);
                       				  System.out.println("");
                       			  }
                       			  
                       			  
                       				break; 
					}				
					case BLOCKPAGE :
					{	
					  int top3rd   = QReady.size() / 3 ;
                       			  QReady.add(top3rd, PCB_Running) ;  
                       			  System.out.println("*****\t\t\tContext Switch\tPAGE event\t\t\t*****");
                     			        break;
					}
					case BLOCKIO :
					{
					  int topHalf = QReady.size() / 2 ;
                      			  QReady.add(topHalf, PCB_Running) ;   
                       			  System.out.println("*****\t\t\tContext Switch\tI/O event\t\t\t*****");
                       				break;
					}
					default :
					{
					   QReady.addLast(PCB_Running);
                      			   System.out.println("*****\t\t\tContext Switch\tTIME event\t\t\t*****");
                       				break;
					}
				}
			}
			
			System.out.println("\n*****\t\t\tContext Switch\tReady Queue\t\t\t*****");   
			 for (PCB pcbLoop : QReady)
               		 	System.out.println(pcbLoop.showPCB());  
		}	
	}
}