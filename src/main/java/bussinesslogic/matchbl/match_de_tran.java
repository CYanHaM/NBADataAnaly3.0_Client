package bussinesslogic.matchbl;

public class match_de_tran {
	public String tran(String con){
		switch (con){
		case"score":return"PTS";
		case"blockshot":return "BLK";
		case"secondaryattack":return"AST";
		case"rebound":return"REB";
		case"steal":return"STL";
		default:System.out.println("errrrrrrrrrrr");return null;
		}
	}
}
