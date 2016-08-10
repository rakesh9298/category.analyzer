package category.analyzer.v1;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import category.analyzer.interfaces.ResponseHandler;
import category.analyzer.interfaces.ResponseWriter;
import category.analyzer.io.ConsoleWriter;
import category.analyzer.vo.CategoryVO;
import category.analyzer.vo.ResponseVO;

/**
 * 
 * @author rakesh
 * Response Handler that generates response in version 1 format.
 */
public class ResponseHandlerV1 implements ResponseHandler {

	private static final String CATEGORY = "CATEGORY";
	private static final String TAB = "\t";
	private static final String COUNT = "COUNT";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String INFO_MSG_NO_RESPONSE_FOUND = "No response found to display";
	
	public void handle(ResponseVO responseVO) throws IOException {
           if(responseVO==null){
        	   System.out.println(INFO_MSG_NO_RESPONSE_FOUND);
        	   return;
           }
           //Creating Count List in format PERSON	1
           ResponseWriter responseWriter = new ConsoleWriter();  //Configure writer based on where to write response, how efficient it should be etc.
                                                                 //In case writer needs to be changed, just change here.
           if(responseVO.getCategoryCountMap()!=null && responseVO.getCategoryCountMap().size()>0){
        	   StringBuilder countBuilder = new StringBuilder();
        	   countBuilder.append(CATEGORY).append(TAB).append(COUNT).append(NEW_LINE);
        	   responseWriter.write(countBuilder.toString());
        	   for(Entry<String, Integer> catCountEntry : responseVO.getCategoryCountMap().entrySet()){
        		   countBuilder = new StringBuilder();
        		   countBuilder.append(catCountEntry.getKey())
        		                  .append(TAB)
        		                  .append(catCountEntry.getValue())
        		                  .append(NEW_LINE);
        		   responseWriter.write(countBuilder.toString()); //Decouple response writing from business logic. 
        		                                                  //write each line to avoid memory issues in case of large input. 
        		                                                  //Configuration can be made to write set of lines at once.       		   
        	   }
           }
           
         //Creating Category details as in input. for e.g PERSON John
           if(responseVO.getCategoryVOs()!=null && responseVO.getCategoryVOs().size()>0){        	   
        	   StringBuilder categoryBuilder = null;
        	   Iterator<CategoryVO> categoryIter = responseVO.getCategoryVOs().iterator();
        	   while(categoryIter.hasNext()){
        		   CategoryVO categoryVO = categoryIter.next();        				   
        		   categoryBuilder = new StringBuilder();
        		   categoryBuilder.append(categoryVO.getCategory())
        		                  .append(SPACE)
        		                  .append(categoryVO.getSubCategory());
        		   if(categoryIter.hasNext()){
        			   categoryBuilder.append(NEW_LINE);
        		   }        		                  
        		   responseWriter.write(categoryBuilder.toString()); //Decouple response writing from business logic. 
                                                                     //write each line to avoid memory issues in case of large input. 
                                                                     //Configuration can be made to write set of lines at once.  
        	   }        	   
           }
	}  

}
