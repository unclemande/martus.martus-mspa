
package org.martus.mspa.network;


public interface NetworkInterfaceXmlRpcConstants
{
	public static final String serverObjectName = "MSPAServer";
	
	public final static String cmdPing = "ping";
	public static final String cmdGetAccountIds = "getAccountIds";
	public static final String cmdGetListPackets = "getListPackets";
	public static final String cmdGetContactInfo = "getContactInfo";
	
	public static final String cmdGetAllMagicWords = "getAllMagicWords";
	public static final String cmdGetActiveMagicWords = "getActiveMagicWords";
	public static final String cmdGetInActiveMagicWords = "getInactiveMagicWords";
	public static final String cmdGetAccountManageInfo = "getAccountManageInfo";
	public static final String cmdGetNumOfHiddenBulletins = "getNumOfHiddenBulletins";
	public static final String cmdUpdateAccountManageInfo = "updateAccountManageInfo";
	public static final String cmdGetPacketDirNames = "getPacketDirNames";	
	public static final String cmdUpdateMagicWords = "updateMagicWords";
			
	public static final String cmdGetListOfAvailableServers = "getListOfAvailableServers";		
	public static final String cmdGetListOfAssignedServers = "getListOfAssignedServers";
	public static final String cmdAddAvailableMirrorServer = "addAvailableMirrorServer";
	public static final String cmdUpdateManagingMirrorServers = "updateManagingMirrorServers";
		
}
