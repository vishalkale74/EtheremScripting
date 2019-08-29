package scripting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatternFinder {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path;
		
		try {
			
			RequirementReader rr= new RequirementReader();
			rr.read();
			
			path=rr.path;
			System.out.println("Path : "+path);
			
			TestingScript tS= new TestingScript();
			System.out.println("Smart Contact to be tested : "+tS.ContractName());
			
			System.out.println("-----------------------------------------------------------------------------");
			
			System.out.println("Test Cases are: ");
			Iterator<String> itr = rr.testcases.iterator();
			
			int j=1;
			if(!itr.hasNext())
			{
				System.out.println("No Test cases are mentioned.");
				System.exit(0);
			}
			while(itr.hasNext())
			{
				System.out.println(j+") "+itr.next());
				j++;
			}

			
			BufferedReader br= new BufferedReader(new FileReader(""+path+"\\"+tS.ContractName()+""));
			
			String line;
			List<String> functions= new ArrayList<String>();
			
			while((line=br.readLine()) !=null)
			{
				if(line.contains("function "))
				{
					int startIndex=line.indexOf("function")+9;
					int endIndex=line.indexOf(") ")+1;
					
					line=line.substring(startIndex,endIndex);
					functions.add(line);
					//System.out.println(line);
				}
			}
			
			System.out.println("-----------------------------------------------------------------------------");
			
			System.out.println("Functions present in the Smart Contract are: ");
			
			Iterator<String> itr1= functions.iterator();
			
			int k=1;
			while(itr1.hasNext())
			{
				System.out.println(k+") "+itr1.next());
				k++;
			}	
			
			
			System.out.println("-----------------------------------------------------------------------------");
			
			tS.ScriptWriter();
			
			//System.out.println("-----------------------------------------------------------------------------");
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
