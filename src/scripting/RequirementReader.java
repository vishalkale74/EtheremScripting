package scripting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

class RequirementReader {
	
	String path;
	String path1;
	LinkedHashSet<String> testcases = new LinkedHashSet<>();
	String smartContract;
	
	public void read() throws IOException 
	{
		try {
			File fr = new File("Requirements.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(fr));
			
			String st;
			
			while((st= br.readLine()) != null)
			{
				if(st.contains("FilePath"))
				{
					path1= st.substring(st.indexOf(":")+1).trim();
					File file = new File(path1);
					if(file.isDirectory())
					{
						path=path1;
					}
					else
					{
						System.out.println("Exception occured: InvalidDirectoryException");
						System.out.println("No \""+path1+"\" Directory available.");
						System.exit(0);
					}
				}
				else if(st.contains("Smart Contract to be Tested"))
				{
					smartContract=st.substring(st.indexOf(":")+1).trim();
				}
				else if(st.contains(")"))
				{
					testcases.add(st.substring(st.indexOf(")")+1).trim());
				}
			}	
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}