package scripting;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


public class TestingScript {
	
	RequirementReader rr= new RequirementReader();
	String cName = null;
	
	public String ContractName() throws IOException
	{
		rr.read();
		String path= rr.path;
		
		File folder = new File(path);
		
		File[] listOfFiles = folder.listFiles();
		String  contract;
		
		for (int i = 0; i < listOfFiles.length; i++) 
		{
		  if (listOfFiles[i].isFile()) 
		  {
			  contract = listOfFiles[i].getName();
			  if(contract.equalsIgnoreCase("ConvertLib.sol") || contract.equalsIgnoreCase("Migrations.sol") || contract.equalsIgnoreCase(".placeholder"))
			  {
			  }	 
			  else if(contract.equalsIgnoreCase(rr.smartContract))
			  {
				 // System.out.println("Contract : " + listOfFiles[i].getName());
				  cName=listOfFiles[i].getName();
			  }
		  } 
		  else
		  {
			  System.out.println("Wrong filepath given. Kindly check filepath.");
			  System.exit(0);
		  }
		}
		//System.out.println(cName);
		return cName;
	}
	
	public void ScriptWriter() throws IOException
	{
		try 
		{			
			String output= cName.replace("sol", "js").toLowerCase();
			System.out.println("Output Script: "+output);
			
			String finalPath= rr.path.replace("contracts", "test").concat("\\").concat(output);
			System.out.println("Script Location: "+finalPath);
			
			FileWriter fw = new FileWriter(finalPath);
			
			// create var for given smart contract
			String var=cName.substring(0, cName.indexOf("."));
			
			// artifacts for testing
			fw.write("const "+var+" = artifacts.require(\"./"+var+"\");\n");
			
			// script entry point
			fw.write("contract('"+var+"', (accounts) => {\n");
			
			Iterator<String> itr= rr.testcases.iterator();
			while(itr.hasNext())
			{
				fw.write("it('"+itr.next()+"', async () => {\n");
				fw.write("//write your code here!!!!\n");
				fw.write("assert.equal('enter your logic');\n});\n");
				fw.write("\n");
			}
			
			fw.write("});");
			
			fw.close();
			
			System.out.println("-----------------------------------------------------------------------------");
			
			// for printing the script file uncomment following snippet
			
			/*BufferedReader br= new BufferedReader(new FileReader(finalPath));
			String st;
			while((st= br.readLine()) != null)
			{
				System.out.println(st);
			}
			br.close();*/
			

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}