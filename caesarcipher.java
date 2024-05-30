import java.util.Scanner;  
class caesarcipher
{
	public static StringBuffer encrypt(String text, int s)
	{
		StringBuffer result= new StringBuffer();
		for (int i=0; i<text.length(); i++)
		{
			if (Character.isUpperCase(text.charAt(i)))
			{
				char ch = (char)(((int)text.charAt(i) +s - 65) % 26 + 65);
				result.append(ch);
			}
			else
			{
				char ch = (char)(((int)text.charAt(i) +s - 97) % 26 + 97);
				result.append(ch);
			}
		}
		return result;
	}
	public static StringBuffer decrypt(String text, int s)
	{
		StringBuffer result= new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) - s - 65 + 26) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) - s - 97 + 26) % 26 + 97);
                result.append(ch);
            }
		}
		return result;
	}
	public static void main(String[] args)
	{
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
          System.out.println("Enter the text and key line by line: ");
          String text = myObj.nextLine();  // Read user input
          int s = myObj.nextInt();  // Read user input
          System.out.println("Text : " + text);
          System.out.println("Shift : " + s);

          StringBuffer encryptedResult = encrypt(text, s);
          System.out.println("Cipher (encryption): " + encryptedResult);

          StringBuffer decryptedResult = decrypt(encryptedResult.toString(), s);
          System.out.println("Plain text (decryption): " + decryptedResult);
	}
}
