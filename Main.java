import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {

	 public static void main(String[] args) throws IOException {
	 	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
	 }	
}

class Result {
	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
	    List<Integer> result = new ArrayList<Integer>();
	    TreeSet<Integer> LeaderBoard = new TreeSet<Integer>();
	    HashMap<Integer,Integer> LeaderBoardwithorder = new HashMap<Integer,Integer>();
	    int index = 1;
	    for (Integer rnkd : ranked) {
	    	if(LeaderBoard.add(rnkd)) {
	    		LeaderBoardwithorder.put(rnkd, index);	
	    		index++;
	    	}
	    }
	    
	    for (Integer plyr : player) {
	    	if(LeaderBoard.add(plyr)) {
	    		try {
	    			int higherItem = LeaderBoard.higher(plyr);	
	    			index = LeaderBoardwithorder.get(higherItem)+1;
	    			result.add(index);
		 	        LeaderBoard.remove(plyr);
	    		}
	    		catch (Exception e) {
	    			result.add(1);
	    		}    		
	    	}
	    	else {
	    		try {
		    		int higherItem = LeaderBoard.higher(plyr);
		    		index = LeaderBoardwithorder.get(higherItem)+1;
					result.add(index);
	    		}
	    		catch (Exception e) {
	    			result.add(1);
	    		}  
	    	}
	    }	    
	    return result;
	}
}
