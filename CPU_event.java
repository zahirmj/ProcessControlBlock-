import java.util.Random ; 

public class CPU_event	
{
	private int	event ;		
	private int	rangeResult ;
	final static byte pct05 = 1 ;	final static byte pct10	= 2 ; 
	final static byte pct15	= 3 ;	final static byte pct20 = 4 ;
	final static byte pct50	= 5 ;	
	
	public void CPU_event()
	{
	
	}
	
	public int get_CPU_event()
	{
		Random random__X= new Random();	
		
		event = random__X.nextInt(100)+1 ;			
		if ((event % pct10) == 0)	
			rangeResult = 2 ; 			
		else
		if ((event % pct20) == 0)	
			rangeResult = 4 ;		
		else
		if ((event % pct15) == 0)	
			rangeResult = 3 ;	 
		else		
		if ((event % pct05)	== 0)	
			rangeResult = 1 ;		
		else		
			rangeResult = 5;	
			
		return rangeResult ;
	}
}