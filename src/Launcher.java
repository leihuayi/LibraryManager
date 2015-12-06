
import java.text.ParseException;
import java.util.Date;

public class Launcher {
	
	
	
	public static void main(String[] args) throws ParseException{
		// TODO Auto-generated method stub	
		Library library = new Library("Hey",0,0,0);
		Member member= new Member("Cocher","Thomas",new Date(),123,library);
		library.getListMembers().add(member);
		member.higherSuspensionTime();
		System.out.println(member.getAmount());
		
	}

}
