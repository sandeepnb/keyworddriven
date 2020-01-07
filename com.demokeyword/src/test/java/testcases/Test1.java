package testcases;
import org.testng.annotations.Test;

import com.demokeyword.engine.KeywordEngine;

public class Test1 {
	
	
	
	public KeywordEngine keywordEngine;
	
	
	@Test
	public void loginTest() 
	{
		keywordEngine=new KeywordEngine();
		keywordEngine.startExecution("login");
		
		
	}
	
	
	

}
