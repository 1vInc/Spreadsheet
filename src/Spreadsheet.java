import java.util.Hashtable;

public class Spreadsheet {
	Hashtable<String, String> cells;
	
	private String[] allowedIntegerChars = {"1","2","3","4","5","6","7","8","9","-" };
	public final String ERROR_MESSAGE = "#Error";
	
	public Spreadsheet() {
		cells = new Hashtable<String,String>();
	}

	public String get(String cell) throws SpreadsheetException{
		if (null == cell)
			throw new SpreadsheetException("Non null string expected for cell identifier");
		
		return cells.get(cell);
	}
	
	public void set(String cell, String value) {
		// to be implemented
		cells.put(cell, value);
	}
	
	public String evaluate(String cell) throws SpreadsheetException {
		// to be implemented
		
		String rawVal = cells.get(cell);
		
		if (isString(rawVal))
			return evaluatedString(rawVal);
		
		if (!containsAllowedIntegers(rawVal))
			return ERROR_MESSAGE;
		
		return rawVal;
	}
	
	private boolean containsString(String value) {
		if (!(value.charAt(0).equals("'") && value.charAt(value.length()).equals("'")))
			return false;
		
		
	}
	
	private int characterCount(String str, String target) {
		int count = 0;
		
		if (target.length() != 1)
			throw new SpreadsheetException("Character count target length must be 1");
		
		for (int i = 0; i < str.length(); i++) {
			if (charAsStringFromPos(str, i).equals(target))
				count++;
		}
		
		return count;
	}
	
	private String evaluatedString(String unevaluatedString) {
		return null;
	}
	
	private String charAsStringFromPos(String str, int pos) throws SpreadsheetException {
		if (pos < 0 || pos > (str.length() - 1))
			throw new SpreadsheetException("Position out of bounds when getting string of 1 len from a string");
		
		String ret = str.substring(pos, pos + 1); 
		
		assert 1 == ret.length();
		
		return ret;
	}
	
	private boolean containsAllowedIntegers(String value) throws SpreadsheetException {
		for (int i = 0; i < value.length(); i++) {
			if (!isAllowedInteger(charAsStringFromPos(value, i)))
				return false;
		}
		
		return true;
	}
	
	private boolean isAllowedInteger(String character) throws SpreadsheetException {
		if (character.length() > 1)
			throw new SpreadsheetException("Checking if integer is allowed possible for only strings with len of 1");
		
		for (int i = 0; i < allowedIntegerChars.length; i++) {
			if (character.equals(allowedIntegerChars[i]))
				return true;
		}
		
		return false;
	}
	
}
