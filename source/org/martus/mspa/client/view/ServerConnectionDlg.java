/*

The Martus(tm) free, social justice documentation and
monitoring software. Copyright (C) 2001-2004, Beneficent
Technology, Inc. (Benetech).

Martus is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later
version with the additions and exceptions described in the
accompanying Martus license file entitled "license.txt".

It is distributed WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, including warranties of fitness of purpose or
merchantability.  See the accompanying Martus License and
GPL license for more details on the required license terms
for this software.

You should have received a copy of the GNU General Public
License along with this program; if not, write to the Free
Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA 02111-1307, USA.

*/
package org.martus.mspa.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.martus.mspa.main.UiMainWindow;
import org.martus.swing.Utilities;


public class ServerConnectionDlg extends JDialog
{
	public ServerConnectionDlg(UiMainWindow owner, Vector availableServers) 
	{				
		super(owner, "Connect to Server: ", true);
		parent = owner;		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());		
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.add(new JLabel("Select a server: "), BorderLayout.NORTH);	
		mainPanel.add(createDisplayServerListPane(availableServers), BorderLayout.CENTER);
		mainPanel.add(buildButtonsPanel(), BorderLayout.SOUTH);						
		mainPanel.setPreferredSize(new Dimension(300,250));		
		
		getContentPane().add(mainPanel);
		Utilities.centerDlg(this);
		setResizable(true);
	}	
	
	private JScrollPane createDisplayServerListPane(Vector availableServers)
	{
		availabelServerListModel = loadElementsToList(availableServers);
		availabelServerList = createServerList(availabelServerListModel);
		availabelServerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			

		return new JScrollPane(availabelServerList);
	}
	
	private JList createServerList(DefaultListModel dataModel)
	{
		JList list = new JList(dataModel);
		list.setFixedCellWidth(220);
		configureTabList(list);

		return list;
	}
	
	private DefaultListModel loadElementsToList(Vector items)
	{
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i=0; i<items.size();i++)
			listModel.add(i, items.get(i));
			
		return listModel;
	}

	private void configureTabList(JList list)
	{
		TabListCellRenderer renderer = new TabListCellRenderer();
		renderer.setTabs(new int[] {130, 200, 300});
		list.setCellRenderer(renderer);
		
		TabListCellRenderer renderer2 = new TabListCellRenderer();
		renderer2.setTabs(new int[] {130, 200, 300});
		list.setCellRenderer(renderer);
	}		
	
	private JPanel buildButtonsPanel()
	{
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
							
		connect = new JButton("Connect");
		connect.addActionListener(new CommitButtonHandler());	
		panel.add(connect);					

		return panel;
	}	
	
	public String getServerIPToUse()
	{
		return serverIpToUse;
	}
	
	public String getServerPublicCodeToUse()
	{
		return serverPublicCodeToUse;
	}		
	
	class CommitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			if (ae.getSource().equals(connect))				
				handleServerToCall();						
				
		}
		
		private void handleServerToCall()
		{

			if (!availabelServerList.isSelectionEmpty())
			{
				String item = (String) availabelServerList.getSelectedValue();
				serverIpToUse = item.substring(0, item.indexOf("\t"));
				serverPublicCodeToUse= item.substring(item.indexOf("\t")+1);
				parent.getMSPAApp().setCurrentServerIp(serverIpToUse.trim());
				parent.getMSPAApp().setCurrentServerPublicCode(serverPublicCodeToUse.trim());
				dispose();
			}
			else
				JOptionPane.showMessageDialog(parent, "No server has been selected.", "MSPA message", JOptionPane.WARNING_MESSAGE);						
		}

	}

	
	UiMainWindow parent;
	JList availabelServerList;	
	DefaultListModel availabelServerListModel;
	
	JButton connect;	
	
	String serverIpToUse="";
	String serverPublicCodeToUse=""; 
}
