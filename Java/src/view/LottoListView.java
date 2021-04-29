package view;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class LottoListView {
	
  JTable table;
  
   public LottoListView(){
	   
   }
  
  
  class tableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();

		String[] title = {};

		@Override
		public int getColumnCount() {

			return title.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}

}}
