import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.*;
public class Cisco {

	public static void main (String [] args)
	{		       
		
		try {
			
			Scanner scan=new Scanner(System.in);
			System.out.println("Plz enter name of your file [in this format *.txt]");
			String file=scan.next();  //Read file name
			
			Pattern numOfInterfaces = Pattern.compile("FastEthernet\\S*");//To find number of interfaces
			Matcher  numInterfaces = numOfInterfaces.matcher(fromFile(file));

			int count = 0;
			while (numInterfaces.find())
				count++; // Count number of Interfaces
			//^\\S*
			System.out.println("Number of interfaces:"+count);
			System.out.println("****************************************");
			// Create matcher on file
			Pattern IntefaceName = Pattern.compile(".*?Fast.*\\d"); // Interface name
			Matcher InterfaceName_matcher = IntefaceName.matcher(fromFile(file));
			Pattern adminStatus = Pattern.compile("((?<=is\\s)[a-zA-z]+)(?=,)"); // admin staus
			Matcher adminStatus_matcher = adminStatus.matcher(fromFile(file));
			Pattern operationStatus = Pattern.compile("(?<=line\\sprotocol\\sis\\s)[a-zA-z]+");
			Matcher operationStatus_matcher = operationStatus.matcher(fromFile(file));
			Pattern MACaddress = Pattern.compile("((?<=address\\sis\\s)[\\w]{4}\\.[\\w]{4}\\.[\\w]{4})(?=\\s\\()");
			Matcher MACaddress_matcher = MACaddress.matcher(fromFile(file));
			Pattern ipAddress = Pattern.compile("((?<=Internet\\saddress\\sis\\s)[\\d]+\\.[\\d]+\\.[\\d]+.[\\d]+)(?=\\/)");
			Matcher ipAddress_matcher = ipAddress.matcher(fromFile(file));
			Pattern describtion = Pattern.compile("(?<=Description:\\s).+");
			Matcher describtion_matcher = describtion.matcher(fromFile(file));
			Pattern mtu = Pattern.compile("((?<=MTU\\s)\\d+)");
			Matcher mtu_matcher = mtu.matcher(fromFile(file));
			Pattern dublex = Pattern.compile("[a-zA-z]+(?=-duplex,)");
			Matcher dublex_matcher = dublex.matcher(fromFile(file));
			Pattern ifSpeed = Pattern.compile("((?<=-duplex,\\s).+)(?=,)");
			Matcher ifSpeed_matcher = ifSpeed.matcher(fromFile(file));

			// Find all matches
			int j;
			for(int i=0;i<count;i++)
			{ System.out.println("Interface No."+ (i+1));

			while (InterfaceName_matcher.find()) {
								// Get the matching string
				String match = InterfaceName_matcher.group();
			
				System.out.println("Interface Name: "+ match);
				break;
			}
			
			while (adminStatus_matcher.find()) {
				// Get the matching string
				String match = adminStatus_matcher.group();
				
				
					System.out.println("admin status: "+ match);
					break;
					
			}
			
			while (operationStatus_matcher.find()) {
				// Get the matching string
				String match = operationStatus_matcher.group();
			
					System.out.println("operation status: "+ match);
					break;
					
			}
			
			while (MACaddress_matcher.find()) {
				// Get the matching string
				String match = MACaddress_matcher.group();
				
				System.out.println("MAC address: "+ match);
					break;
					
			}
			
			while (ipAddress_matcher.find()) {
				// Get the matching string
				String match = ipAddress_matcher.group();
				
				System.out.println("ip address: "+ match);
					break;
				
			}
			
			while (describtion_matcher.find()) {
				// Get the matching string
				String match = describtion_matcher.group();
				
				
					System.out.println("describtion: "+ match);
					break;
				
			} 
			
			while (mtu_matcher.find()) {
				// Get the matching string
				String match = mtu_matcher.group();
				
				System.out.println("mtu: "+ match);
					break;
					
			}
			
			while (dublex_matcher.find()) {
				// Get the matching string
				String match = dublex_matcher.group();
				
				System.out.println("dublex mode: "+ match+"-dublex");
					break;
					
			}
			
			while (ifSpeed_matcher.find()) {
				// Get the matching string
				String match = ifSpeed_matcher.group();
				
				System.out.println("ifSpeede: "+ match);
					break;
					
			}
			System.out.println("---------------------------------------");
			}
		} catch (IOException e) {
			System.out.println("exp");

		}
	}


	public static CharSequence fromFile(String filename) throws IOException {
		FileInputStream input = new FileInputStream(filename);
		FileChannel channel = input.getChannel();

		// Create a read-only CharBuffer on the file
		ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
		CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
		return cbuf;
	}


}