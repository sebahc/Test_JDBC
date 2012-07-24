import java.io.*;


public class TestRuntime {
public static void main (String[] args) throws IOException, InterruptedException{
	try{
	String line;
	Runtime rt = Runtime.getRuntime();
	Process proc = rt.exec("cmd /c dir ");
	BufferedReader bri = new BufferedReader (new InputStreamReader(proc.getInputStream()));
	BufferedReader bre = new BufferedReader (new InputStreamReader(proc.getErrorStream()));
	
	while ((line=bri.readLine())!=null){
		System.out.println(line);
	}
	bri.close();
	
	while ((line=bre.readLine())!=null){
		System.out.println(line);
	}
	bre.close();
	proc.waitFor();
	System.out.println("Done");
			
	
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
